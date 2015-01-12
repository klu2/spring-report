package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportElementStatic;
import cc.catalysts.springreport.pdf.elements.ReportImage;
import cc.catalysts.springreport.pdf.elements.ReportTable;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * @author Paul Klingelhuber
 */
public class PdfReportPrinter {

    private final PdfConfig configuration;
    private Map<ReportFontType, PDFont> fontLibrary = new HashMap<>();

    public PdfReportPrinter(PdfConfig configuration) {
        this.configuration = configuration;
        initDefaultFontTypes();
    }

    private void initDefaultFontTypes() {
        fontLibrary.put(ReportFontType.NORMAL, PDType1Font.HELVETICA);
        fontLibrary.put(ReportFontType.ITALIC, PDType1Font.HELVETICA_OBLIQUE);
        fontLibrary.put(ReportFontType.BOLD, PDType1Font.HELVETICA_BOLD);
    }

    public void printToStream(PdfPageConfig pageConfig, Resource templateResource, PdfReport report, OutputStream stream) throws IOException, COSVisitorException {
        PDDocument page = print(pageConfig, templateResource, report);
        page.save(stream);
        page.close();
    }

    /**
     * @param pageConfig page config
     * @param report     the report to print
     * @return the printed PdfBox document
     * @throws java.io.IOException
     */
    public PDDocument print(PdfPageConfig pageConfig, Resource templateResource, PdfReport report) throws IOException {
        pageConfig.setFooter(configuration.getFooterText().getFontSize() + pageConfig.getLineDistance());

        PrintData printData = new PrintData(templateResource, pageConfig);
        PrintCursor cursor = new PrintCursor();
        PDDocument document = new PDDocument();

        breakPage(document, cursor, printData);
        float maxWidth = pageConfig.getUsableWidth();

        int reportElementIndex = 0;
        ReportElement currentReportElement = report.getElements().isEmpty() ? null : report.getElements().get(reportElementIndex);
        ReportElement nextReportElement = null;

        while (currentReportElement != null) {
            boolean forceBreak = false;
            //currentReportElement.setFontLib(fontLibrary);
            float height = currentReportElement.getHeight(maxWidth);
            if (cursor.yPos - height < pageConfig.getLastY()) {
                //out of bounds
                if (currentReportElement.isSplitable() && currentReportElement instanceof ReportTable && (cursor.yPos - currentReportElement.getFirstSegmentHeight(maxWidth)) >= pageConfig.getLastY()) {
                    //it's a Table out of bounds, so we also do a height split
                    ReportElement[] twoElements = currentReportElement.split(maxWidth, cursor.yPos - pageConfig.getLastY());
                    if (twoElements.length != 2) {
                        throw new IllegalStateException("The split method should always two parts.");
                    }
                    currentReportElement = twoElements[0];
                    nextReportElement = twoElements[1];
                    if (((ReportTable) currentReportElement).getExtraSplitting()) {
                        forceBreak = true;
                    }
                } else if (currentReportElement.isSplitable() && (cursor.yPos - currentReportElement.getFirstSegmentHeight(maxWidth) >= pageConfig.getLastY())) {
                    ReportElement[] twoElements = currentReportElement.split(maxWidth);
                    if (twoElements.length != 2) {
                        throw new IllegalStateException("The split method should always two parts.");
                    }
                    currentReportElement = twoElements[0];
                    nextReportElement = twoElements[1];
                } else {
                    breakPage(document, cursor, printData);
                    continue;
                }
            }
            float nextY = currentReportElement.print(document, cursor.currentStream, cursor.currentPageNumber, cursor.xPos, cursor.yPos, maxWidth, fontLibrary);
            nextY -= pageConfig.getLineDistance();
            cursor.imageList.addAll(currentReportElement.getImageIntents());

            currentReportElement = nextReportElement;
            nextReportElement = null;
            if (currentReportElement == null && reportElementIndex + 1 < report.getElements().size()) {
                currentReportElement = report.getElements().get(++reportElementIndex);
            }
            cursor.yPos = nextY;
            if (forceBreak) {
                breakPage(document, cursor, printData);
            }
        }
        cursor.currentStream.close();

        report.expandPagesStaticElements(cursor.currentPageNumber + 1);

        for (ReportElementStatic staticElem : report.getStaticElements()) {
            staticElem.print(document, null, 0, 0, 0, 0, fontLibrary);
        }

        printImages(document, cursor);

        return document;
    }

    ReportElement[] specialSplitTable(ReportTable reportTable, float allowedHeight, float allowedWidth) {
        return reportTable.splitFirstCell(allowedHeight, allowedWidth);
    }

    private void breakPage(PDDocument document, PrintCursor cursor, PrintData printData) throws IOException {
        if (cursor.currentStream != null) {
            cursor.currentStream.close();
        }

        if (printData.templateResource == null) {
            document.addPage(new PDPage(printData.pageConfig.getPageSize()));
        } else {
            PDDocument templateDoc = PDDocument.load(printData.templateResource.getInputStream());
            cursor.cacheTempalte(templateDoc);
            PDPage templatePage = (PDPage) templateDoc.getDocumentCatalog().getAllPages().get(0);
            document.importPage(templatePage);
        }
        PDPage currPage = (PDPage) document.getDocumentCatalog().getAllPages().get(++cursor.currentPageNumber);
        cursor.currentStream = new PDPageContentStream(document, currPage, true, false);
        cursor.yPos = printData.pageConfig.getStartY();
        cursor.xPos = printData.pageConfig.getStartX();
    }

    private void printImages(PDDocument document, PrintCursor cursor) throws IOException {
        for (ReportImage.ImagePrintIntent ipi : cursor.imageList) {
            ipi.getImg().printImage(document, ipi.getPage(), ipi.getX(), ipi.getY());
        }
    }

    private static class PrintData {
        private Resource templateResource;
        private PdfPageConfig pageConfig;

        public PrintData(Resource templateResource, PdfPageConfig pageConfig) {
            this.templateResource = templateResource;
            this.pageConfig = pageConfig;
        }
    }

    private static class PrintCursor {
        private int currentPageNumber = -1;
        private PDPageContentStream currentStream;
        private float yPos;
        private float xPos;
        private List<ReportImage.ImagePrintIntent> imageList = new ArrayList<>();
        private Queue<Object> templateCache = new LinkedList<>();

        /**
         * used to prevent GC from pdfBox templates
         */
        public void cacheTempalte(Object template) {
            templateCache.add(template);
        }
    }

}

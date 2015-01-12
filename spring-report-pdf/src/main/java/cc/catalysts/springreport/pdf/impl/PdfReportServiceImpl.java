package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.PdfReportPrinter;
import cc.catalysts.springreport.pdf.PdfReportService;
import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
class PdfReportServiceImpl implements PdfReportService {


    private final PdfConfiguration defaultConfiguration;

    public PdfReportServiceImpl(PdfConfiguration defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public PdfReportBuilder createBuilder() {
        return new PdfReportBuilderImpl(defaultConfiguration);
    }

    @Override
    public PdfReportBuilder createBuilder(PdfConfiguration config) {
        return new PdfReportBuilderImpl(config);
    }

    @Override
    public void printToFile(PdfReport report, File outputFile, PdfReportPageConfig pageConfig, Resource templateResource) throws IOException {
        try {
            PDDocument document = new PdfReportPrinter().print(pageConfig, templateResource, report);
            document.save(outputFile);
            document.close();
        } catch (COSVisitorException e) {
            throw new IOException("Error on generating PDF", e);
        }
    }
}

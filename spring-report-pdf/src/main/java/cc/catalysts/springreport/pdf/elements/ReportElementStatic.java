package cc.catalysts.springreport.pdf.elements;

import cc.catalysts.pdfreport.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class ReportElementStatic implements ReportElement {

    private ReportElement base;
    private float x;
    private float y;
    private float width;
    private int pageNo;


    /**
     * @param base   ReportElement to be printed
     * @param pageNo 0-based number of page, where to print element
     * @param x      starting X coordinate of print location
     * @param y      starting Y coordinate of print location
     */
    public ReportElementStatic(ReportElement base, int pageNo, float x, float y, float width) {
        this.base = base;
        this.x = x;
        this.y = y;
        this.pageNo = pageNo;
        this.width = width;
    }

    public ReportElement getBase() {
        return base;
    }

    /**
     * @param document     PdfBox document
     * @param stream       IGNORED
     * @param pageNumber   IGNORED (taken from constructor)
     * @param startX       IGNORED (taken from constructor)
     * @param startY       IGNORED (taken from constructor)
     * @param allowedWidth IGNORED (taken from constructor)
     * @return 0
     * @throws java.io.IOException
     */
    @Override
    public float print(PDDocument document, PDPageContentStream stream, int pageNumber, float startX, float startY, float allowedWidth, Map<ReportFontType, PDFont> fontLibrary) throws IOException {
        PDPage currPage = (PDPage) document.getDocumentCatalog().getAllPages().get(pageNo);
        PDPageContentStream pageStream = new PDPageContentStream(document, currPage, true, false);
        base.print(document, pageStream, pageNo, x, y, width, fontLibrary);
        pageStream.close();
        return 0F;
    }

    @Override
    public float getHeight(float allowedWidth) {
        throw new IllegalStateException("Height of static elements is irrelevant");
    }

    @Override
    public boolean isSplitable() {
        return false;
    }

    @Override
    public float getFirstSegmentHeight(float allowedWidth) {
        throw new IllegalStateException("static elements are not splittable");
    }

    @Override
    public ReportElement[] split(float allowedWidth) {
        throw new IllegalStateException("static elements are not splittable");
    }

    @Override
    public ReportElement[] split(float allowedWidth, float allowedHeight) {
        throw new IllegalStateException("static elements are not splittable");
    }

    @Override
    public Collection<ReportImage.ImagePrintIntent> getImageIntents() {
        return base.getImageIntents();
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public int getPageNo() {
        return pageNo;
    }

    @Override
    public void setFontLib(Map<ReportFontType, PDFont> fontLib) {
        getBase().setFontLib(fontLib);
    }
}

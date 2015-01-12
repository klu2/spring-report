package cc.catalysts.springreport.pdf.config;

import cc.catalysts.springreport.pdf.utils.PdfReportTextConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

/**
 * @author Klaus Lehner
 */
public class PdfConfiguration {

    private int sectionPadding = 10;
    private int headingPaddingAfter = 4;
    private PdfReportTextConfig headingConfig = new PdfReportTextConfig(20, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfReportTextConfig textBodyConfig = new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    public int getSectionPadding() {
        return sectionPadding;
    }

    public void setSectionPadding(int sectionPadding) {
        this.sectionPadding = sectionPadding;
    }

    public int getHeadingPaddingAfter() {
        return headingPaddingAfter;
    }

    public void setHeadingPaddingAfter(int headingPaddingAfter) {
        this.headingPaddingAfter = headingPaddingAfter;
    }

    public PdfReportTextConfig getHeadingConfig() {
        return headingConfig;
    }

    public void setHeadingConfig(PdfReportTextConfig headingConfig) {
        this.headingConfig = headingConfig;
    }

    public PdfReportTextConfig getTextBodyConfig() {
        return textBodyConfig;
    }

    public void setTextBodyConfig(PdfReportTextConfig textBodyConfig) {
        this.textBodyConfig = textBodyConfig;
    }
}

package cc.catalysts.springreport.pdf.config;

import cc.catalysts.springreport.pdf.utils.PdfReportTextConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

/**
 * @author Klaus Lehner
 */
public class PdfConfiguration {

    private float lineDistance = 1;

    private int sectionPadding = 10;
    private int headingPaddingAfter = 4;
    private PdfReportTextConfig headingConfig = new PdfReportTextConfig(20, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfReportTextConfig textBodyConfig = new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    private PdfReportTextConfig tableTitleConfig = new PdfReportTextConfig(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfReportTextConfig tableBodyConfig = new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    private PdfReportTextConfig footerText = new PdfReportTextConfig(7, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

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

    public PdfReportTextConfig getTableTitleConfig() {
        return tableTitleConfig;
    }

    public void setTableTitleConfig(PdfReportTextConfig tableTitleConfig) {
        this.tableTitleConfig = tableTitleConfig;
    }

    public PdfReportTextConfig getTableBodyConfig() {
        return tableBodyConfig;
    }

    public void setTableBodyConfig(PdfReportTextConfig tableBodyConfig) {
        this.tableBodyConfig = tableBodyConfig;
    }

    public PdfReportTextConfig getFooterText() {
        return footerText;
    }

    public void setFooterText(PdfReportTextConfig footerText) {
        this.footerText = footerText;
    }

    public float getLineDistance() {
        return lineDistance;
    }

    public void setLineDistance(float lineDistance) {
        this.lineDistance = lineDistance;
    }
}

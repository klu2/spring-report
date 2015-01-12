package cc.catalysts.springreport.pdf.config;

import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

/**
 * @author Klaus Lehner
 */
public class PdfConfig {

    private float lineDistance = 1;

    private int sectionPadding = 10;
    private int headingPaddingAfter = 4;
    private PdfTextConfig headingConfig = new PdfTextConfig(20, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfTextConfig textBodyConfig = new PdfTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    private PdfTextConfig tableTitleConfig = new PdfTextConfig(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfTextConfig tableBodyConfig = new PdfTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    private PdfTextConfig footerText = new PdfTextConfig(7, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

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

    public PdfTextConfig getHeadingConfig() {
        return headingConfig;
    }

    public void setHeadingConfig(PdfTextConfig headingConfig) {
        this.headingConfig = headingConfig;
    }

    public PdfTextConfig getTextBodyConfig() {
        return textBodyConfig;
    }

    public void setTextBodyConfig(PdfTextConfig textBodyConfig) {
        this.textBodyConfig = textBodyConfig;
    }

    public PdfTextConfig getTableTitleConfig() {
        return tableTitleConfig;
    }

    public void setTableTitleConfig(PdfTextConfig tableTitleConfig) {
        this.tableTitleConfig = tableTitleConfig;
    }

    public PdfTextConfig getTableBodyConfig() {
        return tableBodyConfig;
    }

    public void setTableBodyConfig(PdfTextConfig tableBodyConfig) {
        this.tableBodyConfig = tableBodyConfig;
    }

    public PdfTextConfig getFooterText() {
        return footerText;
    }

    public void setFooterText(PdfTextConfig footerText) {
        this.footerText = footerText;
    }

    public float getLineDistance() {
        return lineDistance;
    }

    public void setLineDistance(float lineDistance) {
        this.lineDistance = lineDistance;
    }
}

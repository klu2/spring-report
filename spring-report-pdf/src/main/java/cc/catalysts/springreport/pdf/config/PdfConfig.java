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

    private PdfTextConfig heading1Text = new PdfTextConfig(20, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfTextConfig bodyText = new PdfTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

    private PdfTextConfig tableTitleText = new PdfTextConfig(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    private PdfTextConfig tableBodyText = new PdfTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);

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

    public PdfTextConfig getHeading1Text() {
        return heading1Text;
    }

    public void setHeading1Text(PdfTextConfig heading1Text) {
        this.heading1Text = heading1Text;
    }

    public PdfTextConfig getBodyText() {
        return bodyText;
    }

    public void setBodyText(PdfTextConfig bodyText) {
        this.bodyText = bodyText;
    }

    public PdfTextConfig getTableTitleText() {
        return tableTitleText;
    }

    public void setTableTitleText(PdfTextConfig tableTitleText) {
        this.tableTitleText = tableTitleText;
    }

    public PdfTextConfig getTableBodyText() {
        return tableBodyText;
    }

    public void setTableBodyText(PdfTextConfig tableBodyText) {
        this.tableBodyText = tableBodyText;
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

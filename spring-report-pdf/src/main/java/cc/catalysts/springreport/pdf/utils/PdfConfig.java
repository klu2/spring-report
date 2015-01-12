package cc.catalysts.springreport.pdf.utils;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

public final class PdfConfig {

    public static final float DEFAULT_LINE_DISTANCE = 1;

    private PdfConfig() {
    }

    public static PdfReportTextConfig getTextConfig() {
        return new PdfReportTextConfig(9, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getTextFooter() {
        return new PdfReportTextConfig(7, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getTextBoldConfig() {
        return new PdfReportTextConfig(9, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    }

    public static PdfReportTextConfig getTextItalicConfig() {
        return new PdfReportTextConfig(9, PDType1Font.HELVETICA_OBLIQUE, ReportFontType.ITALIC, Color.BLACK);
    }

    public static PdfReportTextConfig getTextBoldConfigExtraSize() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    }

    public static PdfReportTextConfig getTextItalicConfigExtraSize() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA_OBLIQUE, ReportFontType.ITALIC, Color.BLACK);
    }
}

package cc.catalysts.springreport.pdf.utils;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;

public final class PdfConfig {

    public static final float DEFAULT_LINE_DISTANCE = 1;
    private static final float DEFAULT_INTER_PARAGRAPH_PADDING = 10;

    private PdfConfig() {
    }

    public static float getDefaultInterParagraphPadding() {
        return DEFAULT_INTER_PARAGRAPH_PADDING;
    }

    public static float getDoubleInterParagraphPadding() {
        return 2 * DEFAULT_INTER_PARAGRAPH_PADDING;
    }

    public static float getTripleInterParagraphPadding() {
        return 3 * DEFAULT_INTER_PARAGRAPH_PADDING;
    }

    public static PdfReportTextConfig getTitleConfig() {
        return new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getTitleXXLConfig() {
        return new PdfReportTextConfig(15, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getH1Config() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);
    }

    public static PdfReportTextConfig getHeaderConfig() {
        return new PdfReportTextConfig(13, PDType1Font.HELVETICA, ReportFontType.BOLD, Color.BLACK);
    }

    public static PdfReportTextConfig getTextConfig() {
        return new PdfReportTextConfig(9, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getTextConfigExtraSize() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK);
    }

    public static PdfReportTextConfig getLightTextConfig() {
        return new PdfReportTextConfig(9, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.GRAY);
    }

    public static PdfReportTextConfig getLightTextConfigExtraSize() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.GRAY);
    }

    public static PdfReportTextConfig getLightBoldTextConfigExtraSize() {
        return new PdfReportTextConfig(10, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.GRAY);
    }

    public static PdfReportPageConfig getPortraitA4Page() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 28.346457F, 10, 100, 20, 1);
    }

    public static PdfReportPageConfig getPortraitA4PageWithSmallTopMargin() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 28.346457F, 10, 20, 20, 1);
    }

    public static PdfReportPageConfig getPortraitA4PageWithDoubleMargins() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 56.6929F, 56.6929F, 100, 20, 1);
    }

    public static PdfReportPageConfig getLandscapeA4Page() {
        return new PdfReportPageConfig(841.8898F, 595.27563F, 28.346457F, 10, 100, 20, 1);
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

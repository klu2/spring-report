package cc.catalysts.springreport.pdf.config;

import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.util.Map;

public class PdfTextConfig {

    private int fontSize;
    private PDFont font;
    private ReportFontType fontType;
    private Color color;

    public PdfTextConfig(int fontSize, PDType1Font defaultFont, ReportFontType fontType, Color color) {
        this.fontSize = fontSize;
        this.font = defaultFont;
        this.fontType = fontType;
        this.color = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public PDFont getFont() {
        return font;
    }

    public Color getColor() {
        return color;
    }

    public void useFontLib(Map<ReportFontType, PDFont> fontLibrary) {
        PDFont pdFont = fontLibrary.get(fontType);
        if (pdFont != null) {
            font = pdFont;
        }
    }
}

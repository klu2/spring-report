package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import cc.catalysts.springreport.pdf.config.PdfPageLayout;

/**
 * @author Paul Klingelhuber
 */
public class PdfFooterGenerator extends AbstractFixedLineGenerator {

    public PdfFooterGenerator(PdfStyleSheet pdfStyleSheet, String leftText, String centerText, String rightText) {
        super(pdfStyleSheet, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfPageLayout pageConfig) {
        return pageConfig.getLastY();
    }

}

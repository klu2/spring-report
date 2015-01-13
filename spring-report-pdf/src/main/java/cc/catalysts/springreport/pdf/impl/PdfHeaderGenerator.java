package cc.catalysts.springreport.pdf.impl;


import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import cc.catalysts.springreport.pdf.config.PdfPageLayout;

/**
 * @author Paul Klingelhuber
 */
public class PdfHeaderGenerator extends AbstractFixedLineGenerator {


    public PdfHeaderGenerator(PdfStyleSheet configuration, String leftText, String centerText, String rightText) {
        super(configuration, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfPageLayout pageConfig) {
        return pageConfig.getHeight();
    }

}

package cc.catalysts.springreport.pdf;


import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;

/**
 * @author Paul Klingelhuber
 */
public class PdfHeaderGenerator extends AbstractFixedLineGenerator {


    public PdfHeaderGenerator(String leftText, String centerText, String rightText) {
        super(leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfReportPageConfig pageConfig) {
        return pageConfig.getHeight();
    }

}

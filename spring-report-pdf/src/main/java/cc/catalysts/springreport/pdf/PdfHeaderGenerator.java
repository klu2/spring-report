package cc.catalysts.springreport.pdf;


import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;

/**
 * @author Paul Klingelhuber
 */
public class PdfHeaderGenerator extends AbstractFixedLineGenerator {


    public PdfHeaderGenerator(PdfConfig configuration, String leftText, String centerText, String rightText) {
        super(configuration, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfPageConfig pageConfig) {
        return pageConfig.getHeight();
    }

}

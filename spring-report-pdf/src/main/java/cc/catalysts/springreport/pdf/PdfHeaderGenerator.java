package cc.catalysts.springreport.pdf;


import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;

/**
 * @author Paul Klingelhuber
 */
public class PdfHeaderGenerator extends AbstractFixedLineGenerator {


    public PdfHeaderGenerator(PdfConfiguration configuration, String leftText, String centerText, String rightText) {
        super(configuration, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfReportPageConfig pageConfig) {
        return pageConfig.getHeight();
    }

}

package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;

/**
 * @author Paul Klingelhuber
 */
public class PdfFooterGenerator extends AbstractFixedLineGenerator {

    public PdfFooterGenerator(PdfConfiguration pdfConfiguration, String leftText, String centerText, String rightText) {
        super(pdfConfiguration, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfReportPageConfig pageConfig) {
        return pageConfig.getLastY();
    }

}

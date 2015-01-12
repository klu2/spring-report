package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;

/**
 * @author Paul Klingelhuber
 */
public class PdfFooterGenerator extends AbstractFixedLineGenerator {

    public PdfFooterGenerator(PdfConfig pdfConfig, String leftText, String centerText, String rightText) {
        super(pdfConfig, leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfPageConfig pageConfig) {
        return pageConfig.getLastY();
    }

}

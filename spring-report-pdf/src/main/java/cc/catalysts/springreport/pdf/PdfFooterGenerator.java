package cc.catalysts.springreport.pdf;

import cc.catalysts.pdfreport.utils.PdfReportPageConfig;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Paul Klingelhuber
 */
public class PdfFooterGenerator extends AbstractFixedLineGenerator {

    public PdfFooterGenerator() {
        this(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDate.now()), "", DEFAULT_PAGE_NUMBER_TEMPLATE);
    }

    public PdfFooterGenerator(String leftText, String centerText, String rightText) {
        super(leftText, centerText, rightText);
    }

    @Override
    float getVerticalPosition(PdfReportPageConfig pageConfig) {
        return pageConfig.getLastY();
    }

}

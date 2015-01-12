package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.elements.ReportElementStatic;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;
import cc.catalysts.springreport.pdf.utils.PdfConfig;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;
import cc.catalysts.springreport.pdf.utils.ReportAlignType;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Base for static page decorators that add a single line (split in left, center, right parts) to a certain
 * y-position of the page (typically used for header and footer lines)
 *
 * @author Paul Klingelhuber
 */
public abstract class AbstractFixedLineGenerator {
    private String leftText;
    private String centerText;
    private String rightText;

    public static final String PAGE_TEMPLATE_CURR = "%PAGE_NUMBER%";
    public static final String PAGE_TEMPLATE_TOTAL = "%TOTAL_PAGES%";
    public static final String DEFAULT_PAGE_NUMBER_TEMPLATE = "Page " + PAGE_TEMPLATE_CURR + " of " + PAGE_TEMPLATE_TOTAL;

    public AbstractFixedLineGenerator(String leftText, String centerText, String rightText) {
        this.leftText = leftText;
        this.centerText = centerText;
        this.rightText = rightText;
    }

    /**
     * adds a footer to all pages
     */
    public void addFooterToAllPages(PdfReport report, PdfReportPageConfig pageConfig) {
        float x = pageConfig.getStartX();
        float y = getVerticalPosition(pageConfig);
        float w = pageConfig.getUsableWidth();
        List<ReportElementStatic> staticElements = new ArrayList<ReportElementStatic>();
        if (StringUtils.isNotBlank(leftText)) {
            ReportTextBox footerElem = new ReportTextBox(PdfConfig.getTextFooter(), leftText);
            footerElem.setAlign(ReportAlignType.LEFT);
            staticElements.add(new ReportElementStatic(footerElem, 0, x, y, w));
        }
        if (StringUtils.isNotBlank(centerText)) {
            ReportTextBox footerElem = new ReportTextBox(PdfConfig.getTextFooter(), centerText);
            footerElem.setAlign(ReportAlignType.CENTER);
            staticElements.add(new ReportElementStatic(footerElem, 0, x, y, w));
        }
        if (StringUtils.isNotBlank(rightText)) {
            ReportTextBox footerElem = new ReportTextBox(PdfConfig.getTextFooter(), rightText);
            footerElem.setAlign(ReportAlignType.RIGHT);
            staticElements.add(new ReportElementStatic(footerElem, 0, x, y, w));
        }

        report.addStaticElementsForEachPage(staticElements.toArray(new ReportElementStatic[staticElements.size()]));
    }

    abstract float getVerticalPosition(PdfReportPageConfig pageConfig);

}

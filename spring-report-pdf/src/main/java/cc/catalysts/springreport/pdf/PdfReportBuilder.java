package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.elements.ReportElement;

/**
 * @author Klaus Lehner
 */
public interface PdfReportBuilder {

    PdfReportBuilder beginNewSection(String title, boolean startNewPage);

    PdfReportBuilder addHeading(String heading);

    PdfReportBuilder addText(String text);

    PdfReportBuilder addElement(ReportElement element);

    PdfReport buildReport();

}

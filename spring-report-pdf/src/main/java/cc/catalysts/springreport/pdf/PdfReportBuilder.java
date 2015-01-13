package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfPageConfig;
import cc.catalysts.springreport.pdf.config.PdfTextConfig;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
public interface PdfReportBuilder {

    PdfReportBuilder beginNewSection(String title, boolean startNewPage);

    PdfReportBuilder addHeading(String heading);

    PdfReportBuilder addText(String text);

    PdfReportBuilder addText(String text, PdfTextConfig textConfig);

    PdfReportBuilder addElement(ReportElement element);

    PdfReportBuilder withHeaderOnAllPages(String left, String middle, String right);

    PdfReportBuilder withFooterOnAllPages(String left, String middle, String right);

    ReportTableBuilder startTable();

    PdfReport buildReport(PdfPageConfig pageConfig);

    void printToFile(File outputFile, PdfPageConfig pageConfig, Resource templateResource) throws IOException;

}

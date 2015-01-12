package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportPadding;
import cc.catalysts.springreport.pdf.elements.ReportPageBreak;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Klaus Lehner
 */
class PdfReportBuilderImpl implements PdfReportBuilder {

    private final PdfConfiguration configuration;
    private List<ReportElement> elements = new ArrayList<>();

    public PdfReportBuilderImpl(PdfConfiguration configuration) {
        this.configuration = configuration;
    }

    public PdfReportBuilderImpl addElement(ReportElement element) {
        elements.add(element);
        return this;
    }

    public PdfReport buildReport() {
        PdfReport report = new PdfReport();
        for (ReportElement element : elements) {
            report.addElement(element);
        }
        return report;
    }

    public PdfReportBuilder beginNewSection(String title, boolean startNewPage) {
        if (startNewPage && !elements.isEmpty()) {
            elements.add(new ReportPageBreak());
        }
        addElement(new ReportPadding(configuration.getSectionPadding()));
        addHeading(title);
        return this;
    }

    public PdfReportBuilderImpl addHeading(String heading) {
        addElement(new ReportTextBox(configuration.getHeadingConfig(), heading));
        addElement(new ReportPadding(configuration.getHeadingPaddingAfter()));
        return this;
    }

    public PdfReportBuilderImpl addText(String text) {
        addElement(new ReportTextBox(configuration.getTextBodyConfig(), text));
        return this;
    }

    public List<ReportElement> getElements() {
        return elements;
    }

}

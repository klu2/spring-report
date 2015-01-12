package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportPadding;
import cc.catalysts.springreport.pdf.elements.ReportPageBreak;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;
import cc.catalysts.springreport.pdf.utils.PdfReportTextConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Paul Klingelhuber
 */
public class PdfReportBuilderImpl {

    private List<ReportElement> elements = new ArrayList<>();
    private PdfReportTextConfig headingConfig;
    private PdfReportTextConfig textBodyConfig;
    private static final int SECTION_PADDING = 10;
    private static final int HEADING_PADDING_AFTER = 4;

    public PdfReportBuilderImpl() {
        this(new PdfReportTextConfig(20, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK),
                new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK));
    }

    public PdfReportBuilderImpl(PdfReportTextConfig headingConfig, PdfReportTextConfig textBodyConfig) {
        this.headingConfig = headingConfig;
        this.textBodyConfig = textBodyConfig;
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

    public PdfReportBuilderImpl beginNewSection(String title, boolean startNewPage) {
        if (startNewPage && !elements.isEmpty()) {
            elements.add(new ReportPageBreak());
        }
        addElement(new ReportPadding(SECTION_PADDING));
        addHeading(title);
        return this;
    }

    public PdfReportBuilderImpl addHeading(String heading) {
        addElement(new ReportTextBox(headingConfig, heading));
        addElement(new ReportPadding(HEADING_PADDING_AFTER));
        return this;
    }

    public PdfReportBuilderImpl addText(String text) {
        addElement(new ReportTextBox(textBodyConfig, text));
        return this;
    }

    public List<ReportElement> getElements() {
        return elements;
    }

}

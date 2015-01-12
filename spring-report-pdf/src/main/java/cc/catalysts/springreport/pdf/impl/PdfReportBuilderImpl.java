package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.ReportTableBuilder;
import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;
import cc.catalysts.springreport.pdf.config.PdfTextConfig;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportPadding;
import cc.catalysts.springreport.pdf.elements.ReportPageBreak;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Klaus Lehner
 */
class PdfReportBuilderImpl implements PdfReportBuilder {

    private final PdfConfig configuration;
    private List<ReportElement> elements = new ArrayList<>();

    public PdfReportBuilderImpl(PdfConfig configuration) {
        this.configuration = configuration;
    }

    public PdfReportBuilderImpl addElement(ReportElement element) {
        elements.add(element);
        return this;
    }

    @Override
    public ReportTableBuilder startTable() {
        return new ReportTableBuilderImpl(configuration, this);
    }

    public PdfReport buildReport() {
        PdfReport report = new PdfReport(configuration);
        for (ReportElement element : elements) {
            report.addElement(element);
        }
        return report;
    }

    @Override
    public void printToFile(File outputFile, PdfPageConfig pageConfig, Resource templateResource) throws IOException {
        try {
            PDDocument document = new PdfReportPrinter(configuration).print(pageConfig, templateResource, this.buildReport());
            document.save(outputFile);
            document.close();
        } catch (COSVisitorException e) {
            throw new IOException("Error on generating PDF", e);
        }
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
        addElement(new ReportTextBox(configuration.getHeading1Text(), configuration.getLineDistance(), heading));
        addElement(new ReportPadding(configuration.getHeadingPaddingAfter()));
        return this;
    }

    public PdfReportBuilderImpl addText(String text, PdfTextConfig textConfig) {
        addElement(new ReportTextBox(textConfig, configuration.getLineDistance(), text));
        return this;
    }

    public PdfReportBuilderImpl addText(String text) {
        addElement(new ReportTextBox(configuration.getBodyText(), configuration.getLineDistance(), text));
        return this;
    }

    public List<ReportElement> getElements() {
        return elements;
    }

}

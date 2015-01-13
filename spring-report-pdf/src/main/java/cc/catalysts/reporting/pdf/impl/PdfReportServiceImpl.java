package cc.catalysts.reporting.pdf.impl;

import cc.catalysts.reporting.pdf.PdfReport;
import cc.catalysts.reporting.pdf.PdfReportBuilder;
import cc.catalysts.reporting.pdf.config.PdfPageLayout;
import cc.catalysts.reporting.pdf.PdfReportService;
import cc.catalysts.reporting.pdf.config.PdfStyleSheet;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
@Service
public class PdfReportServiceImpl implements PdfReportService {

    private final PdfStyleSheet defaultConfiguration;

    @Autowired
    public PdfReportServiceImpl(PdfStyleSheet defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public PdfReportBuilder createBuilder() {
        return new PdfReportBuilderImpl(defaultConfiguration);
    }

    @Override
    public PdfReportBuilder createBuilder(PdfStyleSheet config) {
        return new PdfReportBuilderImpl(config);
    }

    @Override
    public void printToFile(PdfReport report, File outputFile, PdfPageLayout pageConfig, Resource templateResource) throws IOException {
        try {
            PDDocument document = new PdfReportPrinter(report.getConfiguration()).print(pageConfig, templateResource, report);
            document.save(outputFile);
            document.close();
        } catch (COSVisitorException e) {
            throw new IOException("Error on generating PDF", e);
        }
    }
}

package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.PdfReportService;
import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import cc.catalysts.springreport.pdf.config.PdfPageLayout;
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
class PdfReportServiceImpl implements PdfReportService {

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

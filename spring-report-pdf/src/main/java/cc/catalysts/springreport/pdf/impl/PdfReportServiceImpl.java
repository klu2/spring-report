package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReport;
import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.PdfReportService;
import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;
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

    private final PdfConfig defaultConfiguration;

    @Autowired
    public PdfReportServiceImpl(PdfConfig defaultConfiguration) {
        this.defaultConfiguration = defaultConfiguration;
    }

    @Override
    public PdfReportBuilder createBuilder() {
        return new PdfReportBuilderImpl(defaultConfiguration);
    }

    @Override
    public PdfReportBuilder createBuilder(PdfConfig config) {
        return new PdfReportBuilderImpl(config);
    }

    @Override
    public void printToFile(PdfReport report, File outputFile, PdfPageConfig pageConfig, Resource templateResource) throws IOException {
        try {
            PDDocument document = new PdfReportPrinter(report.getConfiguration()).print(pageConfig, templateResource, report);
            document.save(outputFile);
            document.close();
        } catch (COSVisitorException e) {
            throw new IOException("Error on generating PDF", e);
        }
    }
}

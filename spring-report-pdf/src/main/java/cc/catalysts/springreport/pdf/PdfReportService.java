package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import cc.catalysts.springreport.pdf.config.PdfPageLayout;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
public interface PdfReportService {

    PdfReportBuilder createBuilder();

    PdfReportBuilder createBuilder(PdfStyleSheet config);

    void printToFile(PdfReport report, File outputFile, PdfPageLayout pageConfig, Resource templateResource) throws IOException;
}

package cc.catalysts.reporting.pdf;

import cc.catalysts.reporting.pdf.config.PdfPageLayout;
import cc.catalysts.reporting.pdf.config.PdfStyleSheet;
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

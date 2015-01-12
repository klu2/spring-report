package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.config.PdfPageConfig;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
public interface PdfReportService {

    PdfReportBuilder createBuilder();

    PdfReportBuilder createBuilder(PdfConfig config);

    void printToFile(PdfReport report, File outputFile, PdfPageConfig pageConfig, Resource templateResource) throws IOException;
}

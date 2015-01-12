package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
public interface PdfReportService {

    PdfReportBuilder createBuilder();

    PdfReportBuilder createBuilder(PdfConfiguration config);

    void printToFile(PdfReport report, File outputFile, PdfReportPageConfig pageConfig, Resource templateResource) throws IOException;
}

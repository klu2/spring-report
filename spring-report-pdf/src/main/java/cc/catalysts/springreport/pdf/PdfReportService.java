package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfiguration;

/**
 * @author Klaus Lehner
 */
public interface PdfReportService {

    PdfReportBuilder createBuilder();

    PdfReportBuilder createBuilder(PdfConfiguration config);
}

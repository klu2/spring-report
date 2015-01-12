package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfConfig;

/**
 * @author Klaus Lehner
 */
public interface PdfReportService {

    PdfReportBuilder createBuilder();

    PdfReportBuilder createBuilder(PdfConfig config);
}

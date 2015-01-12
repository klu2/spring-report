package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.PdfReportService;
import cc.catalysts.springreport.pdf.config.PdfConfig;

/**
 * @author Klaus Lehner
 */
public class PdfReportServiceImpl implements PdfReportService {

    public PdfReportServiceImpl() {
    }

    @Override
    public PdfReportBuilder createBuilder() {
        return null;
    }

    @Override
    public PdfReportBuilder createBuilder(PdfConfig config) {
        return null;
    }
}

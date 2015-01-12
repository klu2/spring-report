package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.PdfReportService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Klaus Lehner
 */
public class PdfReportServiceTest {

    private PdfReportService pdfReportService;

    @Before
    public void before() {
        pdfReportService = new PdfReportServiceImpl();
    }

    @Test
    public void buildReport() {
        PdfReportBuilder builder = pdfReportService.createBuilder();

    }
}

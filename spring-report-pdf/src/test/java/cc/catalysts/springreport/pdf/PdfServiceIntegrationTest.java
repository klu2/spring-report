package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Klaus Lehner
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PdfReportModule.class)
public class PdfServiceIntegrationTest {

    @Autowired
    PdfReportService pdfReportService;

    @Autowired
    PdfStyleSheet defaultConfig;

    @Test
    public void loadContext() {
        Assert.assertEquals(1, defaultConfig.getLineDistance(), 0.01);
    }
}

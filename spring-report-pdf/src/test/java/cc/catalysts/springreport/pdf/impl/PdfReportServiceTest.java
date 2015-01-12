package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.*;
import cc.catalysts.springreport.pdf.config.PdfConfiguration;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;
import cc.catalysts.springreport.pdf.utils.PdfReportPageConfig;
import cc.catalysts.springreport.pdf.utils.PdfReportTextConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Klaus Lehner
 */
public class PdfReportServiceTest {

    private PdfReportService pdfReportService;
    private static File outDirectory = new File("pdf-out");

    @BeforeClass
    public static void cleanupDirectory() throws IOException {
        if (outDirectory.exists()) {
            FileUtils.forceMkdir(outDirectory);
        }
        FileUtils.forceMkdir(outDirectory);
    }

    @Before
    public void before() {
        pdfReportService = new PdfReportServiceImpl(new PdfConfiguration());
    }

    @Test
    public void buildReport() throws IOException {
        PdfReportBuilder builder = pdfReportService.createBuilder();
        PdfReport report = builder.beginNewSection("test", true)
                .beginNewSection("foo", true)
                .beginNewSection("bar", true)
                .buildReport();
        File file = new File(outDirectory, "example.pdf");
        pdfReportService.printToFile(report, file, PdfReportPageConfig.getLandscapeA4Page(), null);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void generateAndSavePlainExample() throws Exception {
        PdfReport report = createTestReport();
        pdfReportService.printToFile(report, new File(outDirectory, "example-special.pdf"), PdfReportPageConfig.getPortraitA4Page(), null);
    }

    @Test
    public void specialCharactersExample() throws Exception {
        PdfReport report = pdfReportService.createBuilder()
                .addHeading("special chars test")
                .addText("start 1€ foo@bar.at öäü !\"§$%&%&//()=?`îôâ Ružomberok " + (char) 8220 + "123456789" + (char) 8222 + " - end").buildReport();
        pdfReportService.printToFile(report, new File(outDirectory, "example-special.pdf"), PdfReportPageConfig.getPortraitA4Page(), null);
    }

    @Test
    public void generateAndSaveTemplateExample() throws Exception {
        PdfReport report = createTestReport();
        Resource template = new ClassPathResource("template.pdf");
        pdfReportService.printToFile(report, new File(outDirectory, "example-template.pdf"), PdfReportPageConfig.getPortraitA4Page(), template);
    }

    @Test
    public void generateAndSaveHeaderFooterSmallMarginExample() throws Exception {
        PdfReport report = createTestReport();
        PdfReportPageConfig pageConfig = PdfReportPageConfig.getPortraitA4PageWithSmallTopMargin();

        new PdfHeaderGenerator(new PdfConfiguration(), "one", "two", "three").addFooterToAllPages(report, pageConfig);
        new PdfFooterGenerator(new PdfConfiguration(), "left", "center", "right: " + PdfFooterGenerator.PAGE_TEMPLATE_CURR + "/"
                + PdfFooterGenerator.PAGE_TEMPLATE_TOTAL).addFooterToAllPages(report, pageConfig);

        pdfReportService.printToFile(report, new File(outDirectory, "example-header-footer.pdf"), PdfReportPageConfig.getPortraitA4Page(), null);
    }

    PdfReport createTestReport() {

        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            longText.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        }

        PdfReportTextConfig defaultConfig = new PdfReportTextConfig(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);

        return pdfReportService.createBuilder()
                .beginNewSection("Test 1, Table", true)
                .startTable()
                .addColumn("COL1", 2).addColumn("COL2", 2).addColumn("COL3", 4)
                .createRow()
                .addValue("val1").addValue("val2").addValue("val3").endRow()
                .createRow().withValues("x1", "x2", "x3")
                .createRow().withValues("y1", "y2", "y3")
                .endTable()
                .beginNewSection("Test 2, Long text", true)
                .addElement(new ReportTextBox(defaultConfig, 1, longText.toString()))
                .addText("testing default text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .buildReport();
    }

}

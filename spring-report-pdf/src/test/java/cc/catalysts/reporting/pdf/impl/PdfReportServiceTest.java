package cc.catalysts.reporting.pdf.impl;

import cc.catalysts.reporting.pdf.PdfReportBuilder;
import cc.catalysts.reporting.pdf.PdfReportService;
import cc.catalysts.reporting.pdf.config.PdfPageLayout;
import cc.catalysts.reporting.pdf.config.PdfTextStyle;
import cc.catalysts.reporting.pdf.elements.ReportTextBox;
import cc.catalysts.springreport.pdf.*;
import cc.catalysts.reporting.pdf.config.PdfStyleSheet;
import cc.catalysts.reporting.pdf.utils.ReportFontType;
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
        pdfReportService = new PdfReportServiceImpl(new PdfStyleSheet());
    }

    @Test
    public void buildReport() throws IOException {
        File file = new File(outDirectory, "example.pdf");
        PdfReportBuilder builder = pdfReportService.createBuilder();
        builder.beginNewSection("test", true)
                .beginNewSection("foo", true)
                .beginNewSection("bar", true)
                .printToFile(file, PdfPageLayout.getLandscapeA4Page(), null);
        Assert.assertTrue(file.exists());
    }

    @Test
    public void generateAndSavePlainExample() throws Exception {
        createTestReport().printToFile(new File(outDirectory, "example-special.pdf"), PdfPageLayout.getPortraitA4Page(), null);
    }

    @Test
    public void specialCharactersExample() throws Exception {
        pdfReportService.createBuilder()
                .addHeading("special chars test")
                .addText("start 1€ foo@bar.at öäü !\"§$%&%&//()=?`îôâ Ružomberok " + (char) 8220 + "123456789" + (char) 8222 + " - end")
                .printToFile(new File(outDirectory, "example-special.pdf"), PdfPageLayout.getPortraitA4Page(), null);
    }

    @Test
    public void generateAndSaveTemplateExample() throws Exception {
        Resource template = new ClassPathResource("template.pdf");
        createTestReport().printToFile(new File(outDirectory, "example-template.pdf"), PdfPageLayout.getPortraitA4Page(), template);
    }

    @Test
    public void generateAndSaveHeaderFooterSmallMarginExample() throws Exception {
        PdfReportBuilder report = createTestReport()
                .withHeaderOnAllPages("one", "two", "three")
                .withFooterOnAllPages("left", "center", "right: " + PdfFooterGenerator.PAGE_TEMPLATE_CURR + "/"
                        + PdfFooterGenerator.PAGE_TEMPLATE_TOTAL);

        report.printToFile(new File(outDirectory, "example-header-footer.pdf"), PdfPageLayout.getPortraitA4Page(), null);
    }

    PdfReportBuilder createTestReport() {

        StringBuilder longText = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            longText.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        }

        PdfTextStyle otherConfig = new PdfTextStyle(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK);

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
                .addElement(new ReportTextBox(otherConfig, 1, longText.toString()))
                .addText("testing default text")
                .addText(longText.toString(), otherConfig)
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text")
                .beginNewSection("Test 3, Sections without pagebreak", false)
                .addText("section text");
    }

}

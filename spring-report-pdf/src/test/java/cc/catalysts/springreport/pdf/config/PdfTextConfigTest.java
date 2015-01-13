package cc.catalysts.springreport.pdf.config;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

/**
 * @author Klaus Lehner
 */
public class PdfTextConfigTest {

    @Test
    public void fromConstructor() {
        PdfTextConfig config = new PdfTextConfig("10,Times-Roman,normal,#000000");

        Assert.assertEquals(10, config.getFontSize());
        Assert.assertEquals("Times-Roman", config.getFont().getBaseFont());
        Assert.assertEquals(Color.black, config.getColor());
    }
}

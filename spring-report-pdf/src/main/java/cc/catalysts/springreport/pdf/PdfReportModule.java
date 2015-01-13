package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Klaus Lehner
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(value = PdfStyleSheet.class)
public class PdfReportModule {
}

package cc.catalysts.reporting.pdf;

import cc.catalysts.reporting.pdf.config.PdfStyleSheet;
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

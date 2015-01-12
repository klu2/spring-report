package cc.catalysts.springreport.pdf;

/**
 * @author Klaus Lehner
 */
public interface ReportTableRowBuilder {

    ReportTableRowBuilder addValue(String value);
    ReportTableBuilder withValues(String... rowValues);
    ReportTableBuilder endRow();
}

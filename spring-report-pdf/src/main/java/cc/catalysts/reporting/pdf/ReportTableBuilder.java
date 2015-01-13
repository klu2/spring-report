package cc.catalysts.reporting.pdf;

/**
 * @author Klaus Lehner
 */
public interface ReportTableBuilder {

    ReportTableBuilder addColumn(String name, int weight);

    ReportTableRowBuilder createRow();

    PdfReportBuilder endTable();
}

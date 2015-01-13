package cc.catalysts.reporting.pdf;

import cc.catalysts.reporting.pdf.elements.ReportTable;

/**
 * @author Klaus Lehner
 */
public interface ReportTableBuilder {

    ReportTableBuilder addColumn(String name, int weight);

    ReportTableRowBuilder createRow();

    PdfReportBuilder endTable();

    ReportTable build();
}

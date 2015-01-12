package cc.catalysts.springreport.pdf.impl;

import cc.catalysts.springreport.pdf.PdfReportBuilder;
import cc.catalysts.springreport.pdf.ReportTableBuilder;
import cc.catalysts.springreport.pdf.ReportTableRowBuilder;
import cc.catalysts.springreport.pdf.config.PdfConfig;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportTable;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Paul Klingelhuber
 */
public class ReportTableBuilderImpl implements ReportTableBuilder {

    private final PdfConfig pdfConfig;
    private List<String> columnNames = new ArrayList<>();
    private List<Integer> columnWeights = new ArrayList<>();
    private List<List<String>> tableValues = new ArrayList<>();
    private PdfReportBuilder reportBuilder;

    /**
     * init with default style
     */
    public ReportTableBuilderImpl(PdfConfig pdfConfig, PdfReportBuilder reportBuilder) {
        this.pdfConfig = pdfConfig;
        this.reportBuilder = reportBuilder;
    }

    public ReportTableBuilderImpl addColumn(String name) {
        addColumn(name, 1);
        return this;
    }

    /**
     * @param weight a weight, will be evaluated for column width, relative to all other passed values
     *               e.g. passing the same value for each will give evenly spaced. passing 2, 2 and 4 will produce widths of 25%, 25% and 50%
     */
    public ReportTableBuilderImpl addColumn(String name, int weight) {
        columnNames.add(name);
        columnWeights.add(weight);
        return this;
    }

    public ReportTableBuilderImpl setColumns(String... names) {
        columnNames.addAll(Arrays.asList(names));
        for (int i = 0; i < names.length; i++) {
            columnWeights.add(1);
        }
        return this;
    }

    public ReportTableRowBuilderImpl createRow() {
        return new ReportTableRowBuilderImpl(this);
    }

    void addRow(List<String> values) {
        if (values.size() != columnNames.size()) {
            throw new IllegalArgumentException("invalid value count, must match column count: " + columnNames.size());
        }
        tableValues.add(values);
    }

    /**
     * build table taking column weights into account
     */
    ReportTable build() {
        float[] widths = new float[columnNames.size()];
        long sum = 0;
        for (Integer weight : columnWeights) {
            sum += weight;
        }
        double singlePartWidth = 1.0d / sum;
        for (int i = 0; i < widths.length; i++) {
            widths[i] = (float) (singlePartWidth * columnWeights.get(i));
        }
        return buildTableWithWidths(widths);
    }

    public ReportTable buildTableWithWidths(float[] widths) {
        ReportTable reportTable = new ReportTable(pdfConfig, widths, toArray(), null);
        reportTable.setBorder(true);
        return reportTable;
    }

    private ReportElement[][] toArray() {
        // +1 for header
        ReportElement[][] result = new ReportElement[tableValues.size() + 1][columnNames.size()];
        int row = 0;
        int col = 0;
        // header
        for (col = 0; col < columnNames.size(); col++) {
            result[row][col] = new ReportTextBox(pdfConfig.getTableTitleText(), pdfConfig.getLineDistance(), columnNames.get(col));
        }
        row++;
        // body
        for (List<String> rowValues : tableValues) {
            col = 0;
            for (String value : rowValues) {
                result[row][col] = new ReportTextBox(pdfConfig.getTableBodyText(), pdfConfig.getLineDistance(), value);
                col++;
            }
            row++;
        }
        return result;
    }

    @Override
    public PdfReportBuilder endTable() {
        reportBuilder.addElement(this.build());
        return reportBuilder;
    }

    public static class ReportTableRowBuilderImpl implements ReportTableRowBuilder {
        private final ReportTableBuilderImpl parent;

        private List<String> values = new ArrayList<>();

        ReportTableRowBuilderImpl(ReportTableBuilderImpl parent) {
            this.parent = parent;
        }

        public ReportTableRowBuilderImpl addValue(String value) {
            values.add(value);
            return this;
        }


        public ReportTableBuilderImpl withValues(String... rowValues) {
            values.addAll(Arrays.asList(rowValues));
            return endRow();
        }

        public ReportTableBuilderImpl endRow() {
            parent.addRow(values);
            return parent;
        }
    }

}

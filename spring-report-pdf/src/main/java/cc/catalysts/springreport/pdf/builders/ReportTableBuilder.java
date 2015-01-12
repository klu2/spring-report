package cc.catalysts.springreport.pdf.builders;

import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportTable;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;
import cc.catalysts.springreport.pdf.utils.PdfReportTextConfig;
import cc.catalysts.springreport.pdf.utils.ReportFontType;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Paul Klingelhuber
 */
public class ReportTableBuilder {

    private List<String> columnNames = new ArrayList<>();
    private List<Integer> columnWeights = new ArrayList<>();
    private List<List<String>> tableValues = new ArrayList<>();
    private PdfReportTextConfig bodyConfig;
    private PdfReportTextConfig titleConfig;

    /**
     * init with default style
     */
    public ReportTableBuilder() {
        this(new PdfReportTextConfig(12, PDType1Font.HELVETICA_BOLD, ReportFontType.BOLD, Color.BLACK),
                new PdfReportTextConfig(12, PDType1Font.HELVETICA, ReportFontType.NORMAL, Color.BLACK));
    }

    public ReportTableBuilder(PdfReportTextConfig titleConfig, PdfReportTextConfig bodyConfig) {
        this.bodyConfig = bodyConfig;
        this.titleConfig = titleConfig;
    }

    public ReportTableBuilder addColumn(String name) {
        addColumn(name, 1);
        return this;
    }

    /**
     * @param weight a weight, will be evaluated for column width, relative to all other passed values
     *               e.g. passing the same value for each will give evenly spaced. passing 2, 2 and 4 will produce widths of 25%, 25% and 50%
     */
    public ReportTableBuilder addColumn(String name, int weight) {
        columnNames.add(name);
        columnWeights.add(weight);
        return this;
    }

    public ReportTableBuilder setColumns(String... names) {
        columnNames.addAll(Arrays.asList(names));
        for (int i = 0; i < names.length; i++) {
            columnWeights.add(1);
        }
        return this;
    }

    public ReportTableRowBuilder createRow() {
        return new ReportTableRowBuilder(this);
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
    public ReportTable build() {
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
        ReportTable reportTable = new ReportTable(widths, toArray(), null);
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
            result[row][col] = new ReportTextBox(titleConfig, columnNames.get(col));
        }
        row++;
        // body
        for (List<String> rowValues : tableValues) {
            col = 0;
            for (String value : rowValues) {
                result[row][col] = new ReportTextBox(bodyConfig, value);
                col++;
            }
            row++;
        }
        return result;
    }

    public static class ReportTableRowBuilder {
        private final ReportTableBuilder parent;

        private List<String> values = new ArrayList<>();

        ReportTableRowBuilder(ReportTableBuilder parent) {
            this.parent = parent;
        }

        public ReportTableRowBuilder addValue(String value) {
            values.add(value);
            return this;
        }

        /**
         * sets the row values and finishes the row
         */
        public ReportTableBuilder withValues(String... rowValues) {
            values.addAll(Arrays.asList(rowValues));
            return endRow();
        }

        public ReportTableBuilder endRow() {
            parent.addRow(values);
            return parent;
        }
    }

}

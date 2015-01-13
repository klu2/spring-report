package cc.catalysts.springreport.pdf;

import cc.catalysts.springreport.pdf.config.PdfStyleSheet;
import cc.catalysts.springreport.pdf.elements.ReportElement;
import cc.catalysts.springreport.pdf.elements.ReportElementStatic;
import cc.catalysts.springreport.pdf.elements.ReportPage;
import cc.catalysts.springreport.pdf.elements.ReportTextBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfReport {

    private final PdfStyleSheet configuration;
    private List<ReportElement> elements = new ArrayList<ReportElement>();
    private List<ReportElementStatic> staticElements = new ArrayList<ReportElementStatic>();
    private List<ReportElementStatic> staticElementsForEachPage = new ArrayList<ReportElementStatic>();

    public PdfReport(PdfStyleSheet configuration) {
        this.configuration = configuration;
    }

    public PdfStyleSheet getConfiguration() {
        return configuration;
    }

    public void addElement(ReportElement elem) {
        elements.add(elem);
    }

    public List<ReportElement> getElements() {
        return elements;
    }

    public void addPage(ReportPage page) {
        for (ReportElement element : page.getPageElements()) {
            addElement(element);
        }
    }

    public void addStaticElement(ReportElementStatic elem) {
        staticElements.add(elem);
    }

    public List<ReportElementStatic> getStaticElements() {
        return staticElements;
    }

    public void addStaticElementsForEachPage(ReportElementStatic... elements) {
        staticElementsForEachPage.addAll(Arrays.asList(elements));
    }

    public void expandPagesStaticElements(int totalPages) {
        for (ReportElementStatic elem : staticElementsForEachPage) {
            ReportElement baseElement = elem.getBase();
            for (int i = 0; i < totalPages; i++) {
                ReportElement newElement;
                if (baseElement instanceof ReportTextBox) {
                    ReportTextBox oldTextBox = (ReportTextBox) baseElement;
                    String changedText = oldTextBox.getText().replaceAll("%PAGE_NUMBER%", i + 1 + "").replaceAll("%TOTAL_PAGES%", totalPages + "");
                    newElement = new ReportTextBox(oldTextBox, changedText);
                } else {
                    newElement = baseElement;
                }

                addStaticElement(new ReportElementStatic(newElement, i, elem.getX(), elem.getY(), elem.getWidth()));
            }
        }
    }

}

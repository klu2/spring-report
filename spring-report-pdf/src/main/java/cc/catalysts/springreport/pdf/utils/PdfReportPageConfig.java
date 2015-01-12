package cc.catalysts.springreport.pdf.utils;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

public class PdfReportPageConfig {

    private float width;
    private float height;
    private float marginLeft;
    private float marginRight;
    private float marginTop;
    private float marginBottom;
    private float lineDistance;
    private float footer;

    public static PdfReportPageConfig getPortraitA4Page() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 28.346457F, 10, 100, 20, 1);
    }

    public static PdfReportPageConfig getPortraitA4PageWithSmallTopMargin() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 28.346457F, 10, 20, 20, 1);
    }

    public static PdfReportPageConfig getPortraitA4PageWithDoubleMargins() {
        return new PdfReportPageConfig(595.27563F, 841.8898F, 56.6929F, 56.6929F, 100, 20, 1);
    }

    public static PdfReportPageConfig getLandscapeA4Page() {
        return new PdfReportPageConfig(841.8898F, 595.27563F, 28.346457F, 10, 100, 20, 1);
    }

    public PdfReportPageConfig(float width, float height, float marginLeft, float marginRight, float marginTop, float marginBottom, float lineDistance) {
        this.width = width;
        this.height = height;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.lineDistance = lineDistance;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getMarginLeft() {
        return marginLeft;
    }

    public float getMarginRight() {
        return marginRight;
    }

    public float getMarginTop() {
        return marginTop;
    }

    public float getMarginBottom() {
        return marginBottom;
    }

    public float getLineDistance() {
        return lineDistance;
    }

    public void setMarginLeft(float marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMarginRight(float marginRight) {
        this.marginRight = marginRight;
    }

    public void setMarginTop(float marginTop) {
        this.marginTop = marginTop;
    }

    public void setMarginBottom(float marginBottom) {
        this.marginBottom = marginBottom;
    }

    public void setLineDistance(float lineDistance) {
        this.lineDistance = lineDistance;
    }

    public void setFooter(float footerSize) {
        this.footer = footerSize;
    }

    public float getUsableWidth() {
        return width - marginLeft - marginRight;
    }

    public PDRectangle getPageSize() {
        return new PDRectangle(width, height);
    }

    public float getStartY() {
        return height - marginTop;
    }

    public float getStartX() {
        return marginLeft;
    }

    public float getLastY() {
        return marginBottom + footer;
    }

    public float getLastX() {
        return width - marginRight;
    }
}

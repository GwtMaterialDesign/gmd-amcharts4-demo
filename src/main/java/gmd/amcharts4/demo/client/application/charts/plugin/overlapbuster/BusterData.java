package gmd.amcharts4.demo.client.application.charts.plugin.overlapbuster;


import gwt.material.design.amcore.client.color.Color;

public class BusterData {

    private String title;
    private String color;
    private double x;
    private double y;
    private double value;

    public BusterData(String title, String color, double x, double y, double value) {
        this.title = title;
        this.color = color;
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}

package gmd.amcharts4.demo.client.application.charts.misc.data;

public class ConfigFieldData {

    private String country;
    private double liters;
    private boolean active;
    private String stroke;

    public ConfigFieldData(String country, double liters) {
        this.country = country;
        this.liters = liters;
    }

    public ConfigFieldData(String country, double liters, boolean active, String stroke) {
        this.country = country;
        this.liters = liters;
        this.active = active;
        this.stroke = stroke;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLiters() {
        return liters;
    }

    public void setLiters(double liters) {
        this.liters = liters;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }
}

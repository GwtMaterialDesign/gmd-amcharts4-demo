package gmd.amcharts4.demo.client.application.charts.plugin;

import java.util.Date;

public class Visit {

    private Date date;
    private int value;

    public Visit(Date date, int value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

package gmd.amcharts4.demo.client.application.charts.plugin.venn;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONString;

public class VennData {

    private String name;
    private int value;
    private Object color;
    private String sets[];

    public VennData(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public VennData(String name, int value, String... sets) {
        this.name = name;
        this.value = value;
        this.sets = sets;
    }

    public VennData(String name, int value, Object color, String... sets) {
        this.name = name;
        this.value = value;
        this.color = color;
        this.sets = sets;
    }

    public VennData(String name, int value, Object color) {
        this.name = name;
        this.value = value;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Object getColor() {
        return color;
    }

    public void setColor(Object color) {
        this.color = color;
    }

    public JSONArray getSets() {
        if (sets != null) {
            JSONArray array = new JSONArray();
            int i = 0;
            for (String s : sets) {
                array.set(i, new JSONString(s));
                i++;
            }
            return array;
        }
        return null;
    }

    public void setSets(String[] sets) {
        this.sets = sets;
    }
}

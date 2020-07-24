package gmd.amcharts4.demo.client.application.charts.plugin.venn;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.CirclePattern;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amplugin.venn.client.VennDiagram;
import gwt.material.design.amplugin.venn.client.VennDiagramCharts;
import gwt.material.design.amplugin.venn.client.series.VennSeries;

import java.util.ArrayList;
import java.util.List;

public class VennDiagramDemo implements ChartDemo {

    private VennDiagram chart;

    @Override
    public void generate(Widget container) {


        // Create chart
        chart = (VennDiagram) Am4Core.create(container, VennDiagramCharts.VennDiagram);

        // Create and configure series
        VennSeries series = (VennSeries) chart.series.push(new VennSeries());
        series.dataFields.category = "name";
        series.dataFields.value = "value";
        series.dataFields.intersections = "sets";

        JavaScriptObject data = getData();
        series.data = getData();

        // Add legend
        chart.legend = new Legend();
    }

    public static JavaScriptObject getData() {
        ChartData<VennData> data = new ChartData(getList());

        data.add(new DataProvider<VennData>() {
            @Override
            public String getProperty() {
                return "name";
            }

            @Override
            public JSONValue getValue(VennData object) {
                return new JSONString(object.getName());
            }
        });

        data.add(new DataProvider<VennData>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(VennData object) {
                return new JSONNumber(object.getValue());
            }
        });

        data.add(new DataProvider<VennData>() {
            @Override
            public String getProperty() {
                return "sets";
            }

            @Override
            public JSONValue getValue(VennData object) {
                JSONArray sets = object.getSets();
                if (sets != null) {
                    return new JSONArray(sets.getJavaScriptObject());
                }
                return null;
            }
        });

        return data.get();
    }

    private static List<VennData> getList() {
        List<VennData> list = new ArrayList<>();
        CirclePattern pattern = new CirclePattern();
        pattern.radius = 20;
        pattern.height = 50;
        pattern.width = 50;
        pattern.fill = new Color("#ffffff");
        pattern.backgroundFill = new Color("#000000");
        pattern.backgroundOpacity = 1;

        list.add(new VennData("A", 10));
        list.add(new VennData("B", 10));
        list.add(new VennData("C", 10));
        list.add(new VennData("X", 2, "A", "B"));
        list.add(new VennData("Y", 2, "A", "C"));
        list.add(new VennData("Z", 2, "A", "C"));
        list.add(new VennData("Q", 1, "A", "B", "C"));
        return list;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/docs/v4/wp-content/uploads/sites/2/2020/02/image-2-768x494.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

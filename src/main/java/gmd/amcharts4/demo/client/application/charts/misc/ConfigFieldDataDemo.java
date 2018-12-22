package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.misc.data.ConfigFieldData;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ConfigFieldDataDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);

        // Let's cut a hole in our Pie chart the size of 40% the radius
        chart.innerRadius = new Percent(40);

        // Add data
        chart.data = getData();

        // Add and configure Series
        PieSeries  pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "litres";
        pieSeries.dataFields.category = "country";
        pieSeries.slices.template.stroke = new Color("#fff");
        pieSeries.slices.template.strokeWidth = 2;
        pieSeries.slices.template.strokeOpacity = 1;
        pieSeries.slices.template.fillOpacity = 1;

        // Disabling labels and ticks on inner circle
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;

        pieSeries.slices.template.configField = "config";
    }

    public static JavaScriptObject getData() {

        ChartData<ConfigFieldData> data = new ChartData(getList());

        data.add(new DataProvider<ConfigFieldData>() {
            @Override
            public String getProperty() {
                return "config";
            }

            @Override
            public JSONValue getValue(ConfigFieldData object) {
                JSONObject config = new JSONObject();
                config.put("isActive", JSONBoolean.getInstance(object.isActive()));
                if (object.getStroke() != null) {
                    config.put("stroke", new JSONString(object.getStroke()));
                }
                return config;
            }
        });

        data.add(new DataProvider<ConfigFieldData>() {
            @Override
            public String getProperty() {
                return "country";
            }

            @Override
            public JSONValue getValue(ConfigFieldData object) {
                return new JSONString(object.getCountry());
            }
        });

        data.add(new DataProvider<ConfigFieldData>() {
            @Override
            public String getProperty() {
                return "litres";
            }

            @Override
            public JSONValue getValue(ConfigFieldData object) {
                return new JSONNumber(object.getLiters());
            }
        });



        return data.get();
    }

    private static List<ConfigFieldData> getList() {
        List<ConfigFieldData> list = new ArrayList<>();
        list.add(new ConfigFieldData("Lithuania", 501.9, true, "red"));
        list.add(new ConfigFieldData("Czech Republic", 301.9));
        list.add(new ConfigFieldData("Ireland", 201.1));
        list.add(new ConfigFieldData("Germany", 165.8));
        list.add(new ConfigFieldData("Australia", 139.9));
        list.add(new ConfigFieldData("Austria", 128.3));

        return list;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

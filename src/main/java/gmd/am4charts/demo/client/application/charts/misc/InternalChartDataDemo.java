package gmd.am4charts.demo.client.application.charts.misc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.misc.data.SampleData;
import gmd.am4charts.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class InternalChartDataDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);

        // Add data
        chart.data = getData();

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueY = "value";
        series.dataFields.categoryX = "category";

        // Custom Property Fields
        MyCustomPropertyField customPropertyField = new MyCustomPropertyField();
        customPropertyField.fill = "color";
        customPropertyField.stroke = "color";
        series.columns.template.propertyFields = customPropertyField;

        series.columns.template.tooltipText = "{category}:\n[bold]{value}[/b]";
    }

    public static JavaScriptObject getData() {
        ChartData<SampleData> data = new ChartData(getList());

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "category";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONString(object.getCategory());
            }
        });

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONNumber(object.getValue());
            }
        });

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "color";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONString(object.getColor());
            }
        });

        return data.get();
    }

    private static List<SampleData> getList() {
        List<SampleData> list = new ArrayList<>();
        list.add(new SampleData("Category 1", 450, "red"));
        list.add(new SampleData("Category 2", 1200, "yellow"));
        list.add(new SampleData("Category 3", 1500, "green"));
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

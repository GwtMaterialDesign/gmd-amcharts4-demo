package gmd.am4charts.demo.client.application.charts.misc;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.misc.data.JsObjectData;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;

public class JavascriptObjectChartDemo implements ChartDemo {

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

        series.columns.template.tooltipText = "{category}:\n[bold]{value}[/b]";
    }

    public static JsArray<JsObjectData> getData() {
        JsArray<JsObjectData> data = JavaScriptObject.createArray().cast();

        JsObjectData data1 = JavaScriptObject.createObject().cast();
        data1.setCategory("Category 1");
        data1.setValue(340);
        data1.setColor("red");
        data.push(data1);

        JsObjectData data2 = JavaScriptObject.createObject().cast();
        data2.setCategory("Category 2");
        data2.setValue(500);
        data2.setColor("red");
        data.push(data2);

        JsObjectData data3 = JavaScriptObject.createObject().cast();
        data3.setCategory("Category 3");
        data3.setValue(700);
        data3.setColor("red");
        data.push(data3);

        return data;
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

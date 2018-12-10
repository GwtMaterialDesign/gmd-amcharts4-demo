package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.RadarChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.RadarSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

public class RadarChartDemo implements ChartDemo {

    private RadarChart chart;

    @Override
    public void generate(Widget container) {
        chart = (RadarChart) Am4Core.create(container, Am4Charts.RadarChart);
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        RadarSeries series = (RadarSeries) chart.series.push(new RadarSeries());
        series.dataFields.valueY = "litres";
        series.dataFields.categoryX = "country";
        chart.dataSource.url = "data/basic.json";
    }

    @Override
    public String getSourceCode() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

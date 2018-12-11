package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.RadarChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.RadarCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.RadarColumnSeries;
import gwt.material.design.amcharts.client.series.RadarSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class RadarLineGraphDemo implements ChartDemo {

    private RadarChart chart;

    @Override
    public void generate(Widget widget) {
        /* Create chart instance */
        chart = (RadarChart) Am4Core.create(widget, Am4Charts.RadarChart);

        chart.dataSource.url = "data/radar-line-graph.json";

        /* Create axes */
        DateAxis categoryAxis = (DateAxis) chart.xAxes.push(new DateAxis());

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.extraMin = 0.2;
        valueAxis.extraMax = 0.2;
        valueAxis.tooltip.disabled = true;

        /* Create and configure series */
        RadarSeries series1 = (RadarSeries) chart.series.push(new RadarSeries());
        series1.dataFields.valueY = "value1";
        series1.dataFields.dateX = "date";
        series1.strokeWidth = 3;
        series1.tooltipText = "{valueY}";
        series1.name = "Series 2";
        series1.bullets.create(Am4Charts.CircleBullet);

        RadarSeries series2 = (RadarSeries) chart.series.push(new RadarSeries());
        series2.dataFields.valueY = "value2";
        series2.dataFields.dateX = "date";
        series2.strokeWidth = 3;
        series2.tooltipText = "{valueY}";
        series2.name = "Series 2";
        series2.bullets.create(Am4Charts.CircleBullet);

        chart.scrollbarX = new XYChartScrollbar();
        chart.scrollbarY = new XYChartScrollbar();

        chart.cursor = new RadarCursor();

        chart.legend = new Legend();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_12382_none-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.base.TimeInterval;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;

import java.util.Date;

public class ChartWithGapsDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.dataSource.url = "data/chart-with-gaps.json";

        // Create axes
        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.minGridDistance = 50;
        dateAxis.renderer.grid.template.location = 0.5;

        TimeInterval interval = new TimeInterval();
        interval.count = 1;
        interval.timeUnit = "year";
        dateAxis.baseInterval = interval;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueY = "value";
        series.dataFields.dateX = "year";
        series.strokeWidth = 3;
        series.connect = false;
        series.tensionX = 0.8;
        series.fillOpacity = 0.2;

        CircleBullet bullet = (CircleBullet) series.bullets.push(new CircleBullet());
        bullet.stroke = new Color("#fff");
        bullet.strokeWidth = 3;

        ValueAxisDataItem range = valueAxis.createSeriesRange(series);
        range.value = 0;
        range.endValue = 100;
        range.contents.stroke = chart.colors.getIndex(2);
        range.contents.fill = range.contents.stroke;
        range.contents.fillOpacity = 0.2;

        chart.scrollbarX = new Scrollbar();

        chart.events.on("ready", event -> {
            dateAxis.zoomToDates(new Date(70, 0, 1), new Date(95, 0, 1));
        });
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_798_none-3-1024x646.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

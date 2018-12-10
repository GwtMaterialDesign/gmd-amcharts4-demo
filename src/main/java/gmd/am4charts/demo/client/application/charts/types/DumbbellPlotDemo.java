package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;


public class DumbbellPlotDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/dumbbell-plot.json";

        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.ticks.template.disabled = true;
        categoryAxis.renderer.axisFills.template.disabled = true;
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.minGridDistance = 15;
        categoryAxis.renderer.grid.template.location = 0.5;
        categoryAxis.renderer.grid.template.strokeDasharray = "1,3";
        categoryAxis.renderer.labels.template.rotation = -90;
        categoryAxis.renderer.labels.template.horizontalCenter = "left";
        categoryAxis.renderer.labels.template.dx = 17;
        categoryAxis.renderer.inside = true;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;
        valueAxis.renderer.ticks.template.disabled = true;
        valueAxis.renderer.axisFills.template.disabled = true;

        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.categoryX = "category";
        series.dataFields.openValueY = "open";
        series.dataFields.valueY = "close";
        series.tooltipText = "open: {openValueY.value} close: {valueY.value}";
        series.sequencedInterpolation = true;
        series.fillOpacity = 0;
        series.strokeOpacity = 1;
        series.columns.template.width = 0.01;
        series.tooltip.pointerOrientation = "horizontal";

        CircleBullet openBullet = (CircleBullet) series.bullets.create(Am4Charts.CircleBullet);
        openBullet.locationY = 1;

        CircleBullet closeBullet = (CircleBullet) series.bullets.create(Am4Charts.CircleBullet);

        closeBullet.fill = chart.colors.getIndex(4);
        closeBullet.stroke = closeBullet.fill;

        chart.cursor = new XYCursor();

        chart.scrollbarX = new XYChartScrollbar();
        chart.scrollbarY = new XYChartScrollbar();
    }

    @Override
    public String getTitle() {
        return "Dumbbell plot";
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

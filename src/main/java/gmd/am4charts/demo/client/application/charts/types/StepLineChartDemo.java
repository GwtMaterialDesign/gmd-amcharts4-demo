package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.properties.CircleBulletProperties;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.series.StepLineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;
import gwt.material.design.amcore.client.state.SpriteState;

public class StepLineChartDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);
        chart.paddingRight = 20;

        chart.dataSource.url = "data/step-line-chart.json";

        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.minGridDistance = 50;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;

        StepLineSeries series = (StepLineSeries) chart.series.push(new StepLineSeries());
        series.dataFields.dateX = "year";
        series.dataFields.valueY = "value";
        series.tooltipText = "{valueY.value}";
        series.strokeWidth = 3;

        chart.cursor = new XYCursor();
        chart.cursor.xAxis = dateAxis;
        chart.cursor.fullWidthLineX = true;
        chart.cursor.lineX.strokeWidth = 0;
        chart.cursor.lineX.fill = chart.colors.getIndex(2);
        chart.cursor.lineX.fillOpacity = 0.1;

        chart.scrollbarX = new Scrollbar();
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

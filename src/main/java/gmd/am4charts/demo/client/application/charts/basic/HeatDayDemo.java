package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.legend.HeatLegend;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;

public class HeatDayDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.maskBullets = false;
        CategoryAxis xAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        CategoryAxis yAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        xAxis.dataFields.category = "weekday";
        yAxis.dataFields.category = "hour";
        xAxis.renderer.grid.template.disabled = true;
        xAxis.renderer.minGridDistance = 40;
        yAxis.renderer.grid.template.disabled = true;
        yAxis.renderer.inversed = true;
        yAxis.renderer.minGridDistance = 30;

        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.categoryX = "weekday";
        series.dataFields.categoryY = "hour";
        series.dataFields.value = "value";
        series.sequencedInterpolation = true;
        //series.defaultState.transitionDuration = 3000;

        Column columnTemplate = series.columns.template;
        columnTemplate.strokeWidth = 2;
        columnTemplate.strokeOpacity = 1;
        columnTemplate.stroke = new Color("#ffffff");
        columnTemplate.tooltipText = "{weekday}, {hour}: {value.workingValue.formatNumber('#.')}";
        columnTemplate.width = new Percent(100);
        columnTemplate.height = new Percent(100);

        HeatRule heatRule = new HeatRule();
        heatRule.target = columnTemplate;
        heatRule.property = "fill";
        heatRule.min = new Color("#ffffff");
        heatRule.max = new Color("#692155");
        series.heatRules.push(heatRule);

        // heat legend
        HeatLegend heatLegend = (HeatLegend) chart.createChild(Am4Charts.HeatLegend);
        heatLegend.width = new Percent(100);
        heatLegend.series = series;
        heatLegend.valueAxis.renderer.labels.template.fontSize = 9;
        heatLegend.valueAxis.renderer.minGridDistance = 30;

        chart.dataSource.url = "data/heat-days.json";
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

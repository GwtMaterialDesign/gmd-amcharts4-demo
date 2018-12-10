package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.HeatLegend;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;

public class HeatmapWithLegendDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/heat-days.json";

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
        series.defaultState.transitionDuration = 3000;

        Color bgColor = new InterfaceColorSet().getFor("background");

        Column columnTemplate = series.columns.template;
        columnTemplate.strokeWidth = 1;
        columnTemplate.strokeOpacity = 0.2;
        columnTemplate.stroke = bgColor;
        columnTemplate.tooltipText = "{weekday}, {hour}: {value.workingValue.formatNumber('#.')}";
        columnTemplate.width = new Percent(100);
        columnTemplate.height = new Percent(100);

        HeatRule<Color> heatRule1 = new HeatRule<>();
        heatRule1.target = columnTemplate;
        heatRule1.property = "fill";
        heatRule1.min = bgColor;
        heatRule1.max = chart.colors.getIndex(0);
        series.heatRules.push(heatRule1);

        // heat legend
        HeatLegend heatLegend = (HeatLegend) chart.bottomAxesContainer.createChild(Am4Charts.HeatLegend);
        heatLegend.width = new Percent(100);
        heatLegend.series = series;
        heatLegend.valueAxis.renderer.labels.template.fontSize = 9;
        heatLegend.valueAxis.renderer.minGridDistance = 30;

        // heat legend behavior
        series.columns.template.events.on("over", event -> handleHover(event.target, heatLegend));
        series.columns.template.events.on("hit", event -> handleHover(event.target, heatLegend));
        series.columns.template.events.on("out", event -> handleHover(event.target, heatLegend));
    }

    private void handleHover(Column column, HeatLegend heatLegend) {
        if (column.dataItem.value != null) {
            heatLegend.valueAxis.showTooltipAt(column.dataItem.value);
        }
        else {
            heatLegend.valueAxis.hideTooltip();
        }
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

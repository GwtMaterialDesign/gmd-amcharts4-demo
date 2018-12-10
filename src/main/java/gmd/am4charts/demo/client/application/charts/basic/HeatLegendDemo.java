package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.legend.HeatLegend;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;

public class HeatLegendDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        /* Create axes */
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.minGridDistance = 30;
        chart.yAxes.push(new ValueAxis());

        /* Create series */
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.value = "value";
        series.dataFields.valueY = "value";
        series.dataFields.categoryX = "category";
        series.columns.template.strokeWidth = 0;

        HeatRule heatRule = new HeatRule();
        heatRule.target = series.columns.template;
        heatRule.property = "fill";
        heatRule.min = new Color("#F5DBCB");
        heatRule.max = new Color("#ED7B84");
        series.heatRules.push(heatRule);

        /* Create a heat legend */
        HeatLegend heatLegend = (HeatLegend) chart.createChild(Am4Charts.HeatLegend);
        heatLegend.series = series;
        heatLegend.width = new Percent(100);
        heatLegend.valueAxis.renderer.labels.template.fontSize = 9;
        heatLegend.valueAxis.renderer.minGridDistance = 30;
        // Change orientation
        //heatLegend.orientation = Orientation.VERTICAL;z

        // Color steps
        heatLegend.markerCount = 10;

        //Adding interactivity
        series.columns.template.events.on("over", param1 -> {
            Column column = param1.target;
            if (column.dataItem.value != null) {
                heatLegend.valueAxis.showTooltipAt(column.dataItem.value);
            } else {
                heatLegend.valueAxis.hideTooltip();
            }
        });

        series.columns.template.events.on("out", param1 -> {
            heatLegend.valueAxis.hideTooltip();
        });

        chart.dataSource.url = "data/heat.json";
    }

    @Override
    public String getTitle() {
        return "Heat Legend";
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

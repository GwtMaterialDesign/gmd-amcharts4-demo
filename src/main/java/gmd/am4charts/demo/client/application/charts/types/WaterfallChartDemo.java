package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.StepLineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.ui.Label;

public class WaterfallChartDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect
        chart.dataSource.url = "data/waterfall-chart.json";

        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.minGridDistance = 40;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        ColumnSeries columnSeries = (ColumnSeries) chart.series.push(new ColumnSeries());
        columnSeries.dataFields.categoryX = "category";
        columnSeries.dataFields.valueY = "value";
        columnSeries.dataFields.openValueY = "open";
        columnSeries.fillOpacity = 0.8;
        columnSeries.sequencedInterpolation = true;
        columnSeries.interpolationDuration = 1500;

        Column columnTemplate = columnSeries.columns.template;
        columnTemplate.strokeOpacity = 0;
        columnTemplate.propertyFields.fill = "color";

        Label label = (Label) columnTemplate.createChild(Am4Core.Label);
        label.text = "{displayValue.formatNumber('$#,## a')}";
        label.align = "center";
        label.valign = "middle";


        StepLineSeries stepSeries = (StepLineSeries) chart.series.push(new StepLineSeries());
        stepSeries.dataFields.categoryX = "category";
        stepSeries.dataFields.valueY = "stepValue";
        stepSeries.noRisers = true;
        //TODO
        //stepSeries.stroke = new Am4Core().InterfaceColorSet().getFor("alternativeBackground");
        stepSeries.strokeDasharray = "3,3";
        stepSeries.interpolationDuration = 2000;
        stepSeries.sequencedInterpolation = true;

        // because column width is 80%, we modify start/end locations so that step would start with column and end with next column
        stepSeries.startLocation = 0.1;
        stepSeries.endLocation = 1.1;

        chart.cursor = new XYCursor();
        chart.cursor.behavior = "none";
    }

    @Override
    public String getTitle() {
        return "Waterfall Chart";
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

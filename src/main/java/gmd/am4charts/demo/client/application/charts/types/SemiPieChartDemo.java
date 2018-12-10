package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;

public class SemiPieChartDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
        chart.dataSource.url = "data/semi-pie-chart.json";

        chart.radius = new Percent(70);
        chart.innerRadius = new Percent(40);
        chart.startAngle = 180;
        chart.endAngle = 360;

        PieSeries series = chart.series.push(new PieSeries());
        series.dataFields.value = "value";
        series.dataFields.category = "country";

        series.slices.template.cornerRadius = 10;
        series.slices.template.innerCornerRadius = 7;
        series.slices.template.draggable = true;
        series.slices.template.inert = true;
        series.alignLabels = false;

        series.hiddenState.properties.startAngle = 90;
        series.hiddenState.properties.endAngle = 90;

        chart.legend = new Legend();
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

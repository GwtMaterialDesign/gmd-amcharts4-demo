package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class NestedDonutChartDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (PieChart) Am4Core.create(widget, Am4Charts.PieChart);

        // Let's cut a hole in our Pie chart the size of 40% the radius
        chart.innerRadius = new Percent(40);

        chart.dataSource.url = "data/donut-nested.json";

        // Add and configure Series
        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "litres";
        pieSeries.dataFields.category = "country";
        pieSeries.slices.template.stroke = new Color("#fff");
        pieSeries.slices.template.strokeWidth = 2;
        pieSeries.slices.template.strokeOpacity = 1;

        // Disabling labels and ticks on inner circle
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;

        // Disable sliding out of slices
        pieSeries.slices.template.states.getKey("hover").properties.shiftRadius = 0;
        pieSeries.slices.template.states.getKey("hover").properties.scale = 0.9;

        // Add second series
        PieSeries pieSeries2 = chart.series.push(new PieSeries());
        pieSeries2.dataFields.value = "bottles";
        pieSeries2.dataFields.category = "country";
        pieSeries2.slices.template.stroke = new Color("#fff");
        pieSeries2.slices.template.strokeWidth = 2;
        pieSeries2.slices.template.strokeOpacity = 1;
        pieSeries2.slices.template.states.getKey("hover").properties.shiftRadius = 0;
        pieSeries2.slices.template.states.getKey("hover").properties.scale = 1.1;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11091_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

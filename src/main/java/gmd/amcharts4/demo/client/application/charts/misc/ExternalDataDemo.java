package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class ExternalDataDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);

        // Let's cut a hole in our Pie chart the size of 40% the radius
        chart.innerRadius = new Percent(40);

        // Add data
        chart.dataSource.url = "data/basic.json";

        // Add and configure Series
        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "litres";
        pieSeries.dataFields.category = "country";
        pieSeries.slices.template.stroke = new Color("#fff");
        pieSeries.slices.template.strokeWidth = 2;
        pieSeries.slices.template.strokeOpacity = 1;
        pieSeries.slices.template.fillOpacity = 1;

        // Disabling labels and ticks on inner circle
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

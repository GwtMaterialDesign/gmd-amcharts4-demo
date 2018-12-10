package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class ConfigFieldDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        // Let's cut a hole in our Pie chart the size of 40% the radius
        chart.innerRadius = new Percent(40);
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
        pieSeries.slices.template.configField = "config";
        pieSeries.dataSource.url = "data/config-field.json";
    }

    @Override
    public String getTitle() {
        return "Config Fields";
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

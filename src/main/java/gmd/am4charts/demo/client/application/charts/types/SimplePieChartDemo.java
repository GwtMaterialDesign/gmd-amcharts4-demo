package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.constants.Align;
import gwt.material.design.amcore.client.constants.VerticalAlign;
import gwt.material.design.amcore.client.export.ExportMenu;

public class SimplePieChartDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);

        chart.dataSource.url = "data/basic.json";

        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "litres";
        pieSeries.dataFields.category = "country";
        pieSeries.slices.template.stroke = new Color("#fff");
        pieSeries.slices.template.strokeWidth = 2;
        pieSeries.slices.template.strokeOpacity = 1;

        // This creates initial animation
        pieSeries.hiddenState.properties.opacity = 1;
        pieSeries.hiddenState.properties.endAngle = -90;
        pieSeries.hiddenState.properties.startAngle = -90;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_7403_none.png";
    }


    @Override
    public Chart getChart() {
        return chart;
    }
}

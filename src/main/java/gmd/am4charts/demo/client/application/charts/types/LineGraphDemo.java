package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;

public class LineGraphDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/large-data.json";

        // Create axes
        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.minGridDistance = 60;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueY = "value";
        series.dataFields.dateX = "day";
        series.tooltipText = "{value}";

        series.tooltip.pointerOrientation = "vertical";

        chart.cursor = new XYCursor();

        chart.scrollbarY = new Scrollbar();
        chart.scrollbarX = new Scrollbar();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_12373_none-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

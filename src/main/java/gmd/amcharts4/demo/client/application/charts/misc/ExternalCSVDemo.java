package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.data.parser.CSVParser;

public class ExternalCSVDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);

        // Set up data source
        chart.dataSource.url = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/t-160/sample_data_serial.csv";

        CSVParser csvParser = new CSVParser();
        csvParser.options.useColumnNames = true;
        chart.dataSource.parser = csvParser;

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "year";

        // Create value axis
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series1 = (LineSeries) chart.series.push(new LineSeries());
        series1.dataFields.valueY = "cars";
        series1.dataFields.categoryX = "year";
        series1.name = "Cars";
        series1.strokeWidth = 3;
        series1.tensionX = 0.7;
        series1.bullets.push(new CircleBullet());

        LineSeries series2 = (LineSeries) chart.series.push(new LineSeries());
        series2.dataFields.valueY = "motorcycles";
        series2.dataFields.categoryX = "year";
        series2.name = "Motorcycles";
        series2.strokeWidth = 3;
        series2.tensionX = 0.7;
        series2.bullets.push(new CircleBullet());

        LineSeries series3 = (LineSeries) chart.series.push(new LineSeries());
        series3.dataFields.valueY = "bicycles";
        series3.dataFields.categoryX = "year";
        series3.name = "Bicycles";
        series3.strokeWidth = 3;
        series3.tensionX = 0.7;
        series3.bullets.push(new CircleBullet());

        // Add legend
        chart.legend = new Legend();
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

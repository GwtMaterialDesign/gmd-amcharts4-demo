package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class ClusteredBarChart implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/clustered-bar.json";

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "year";
        categoryAxis.numberFormatter.numberFormat = "#";
        categoryAxis.renderer.inversed = true;
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.cellStartLocation = 0.1;
        categoryAxis.renderer.cellEndLocation = 0.9;

        ValueAxis valueAxis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxis.renderer.opposite = true;

        // Create series
        createSeries("income", "Income");
        createSeries("expenses", "Expenses");
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2014/02/demo_3957_none-1-1024x646.png";
    }

    private void createSeries(String field, String name) {
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueX = field;
        series.dataFields.categoryY = "year";
        series.name = name;
        series.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
        series.columns.template.height = new Percent(100);
        series.sequencedInterpolation = true;

        LabelBullet valueLabel = (LabelBullet) series.bullets.push(new LabelBullet());
        valueLabel.label.text = "{valueX}";
        valueLabel.label.horizontalCenter = "left";
        valueLabel.label.dx = 10;
        valueLabel.label.hideOversized = false;
        valueLabel.label.truncate = false;

        LabelBullet categoryLabel = (LabelBullet) series.bullets.push(new LabelBullet());
        categoryLabel.label.text = "{name}";
        categoryLabel.label.horizontalCenter = "right";
        categoryLabel.label.dx = -10;
        categoryLabel.label.fill = new Color("#fff");
        categoryLabel.label.hideOversized = false;
        categoryLabel.label.truncate = false;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

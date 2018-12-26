package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.adapter.Adapter;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.base.Slice;
import gwt.material.design.amcore.client.color.Color;

public class PieChartAsBulletsDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        // Add data
        chart.dataSource.url = "data/pie-as-bullets.json";

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";
        categoryAxis.renderer.grid.template.disabled = true;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.title.text = "Units sold (M)";
        valueAxis.min = 0;
        valueAxis.renderer.baseGrid.disabled = true;
        valueAxis.renderer.grid.template.strokeOpacity = 0.07;

        // Create series
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueY = "units";
        series.dataFields.categoryX = "country";
        series.tooltip.pointerOrientation = "vertical";


        Column columnTemplate = series.columns.template;
        // add tooltip on column, not template, so that slices could also have tooltip
        columnTemplate.column.tooltipText = "Series: {name}\nCategory: {categoryX}\nValue: {valueY}";
        columnTemplate.column.tooltipY = 0;
        columnTemplate.column.cornerRadiusTopLeft = 20;
        columnTemplate.column.cornerRadiusTopRight = 20;
        columnTemplate.strokeOpacity = 0;


        // as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
        Adapter<Color, Column> adapter =  columnTemplate.adapter;
        adapter.add("fill", (column, target) -> {
            Color color = chart.colors.getIndex(target.dataItem.index * 3);
            return color;
        });

        // create pie chart as a column child
        PieChart pieChart = (PieChart) series.columns.template.createChild(Am4Charts.PieChart);
        pieChart.width = new Percent(80);
        pieChart.height = new Percent(80);
        pieChart.align = "center";
        pieChart.valign = "middle";
        pieChart.dataFields.data = "pie";

        PieSeries pieSeries = pieChart.series.push(new PieSeries());
        pieSeries.dataFields.value = "value";
        pieSeries.dataFields.category = "title";
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;
        pieSeries.slices.template.stroke = new Color("#ffffff");
        pieSeries.slices.template.strokeWidth = 1;
        pieSeries.slices.template.strokeOpacity = 0;

        Adapter<Color, Slice> adapter1 =  pieSeries.slices.template.adapter;
        adapter1.add("fill", (slice, target) -> new Color("#ffffff"));

        Adapter<Color, Slice> adapter2 = pieSeries.slices.template.adapter ;
        adapter2.add("fillOpacity", (slice, target) -> (target.dataItem.index + 1) * 0.2);

        pieSeries.hiddenState.properties.startAngle = -90;
        pieSeries.hiddenState.properties.endAngle = 270;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11666_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

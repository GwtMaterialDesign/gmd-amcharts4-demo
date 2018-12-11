package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.AxisBreak;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.constants.Align;
import gwt.material.design.amcore.client.constants.VerticalAlign;
import gwt.material.design.amcore.client.export.ExportMenu;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

public class PieChartDemo implements ChartDemo {

    private PieChart pieChart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        pieChart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        PieSeries series = pieChart.series.push(new PieSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        series.tooltip.getFillFromObject = false;
        series.tooltip.background.fill = new Color("#fff");
        series.tooltip.label.fill = new Color("#000");
        pieChart.dataSource.url = "data/basic.json";
        // Export feature
        ExportMenu menu = pieChart.exporting.menu = new ExportMenu();
        menu.align = Align.LEFT;
        menu.verticalAlign = VerticalAlign.TOP;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return pieChart;
    }
}

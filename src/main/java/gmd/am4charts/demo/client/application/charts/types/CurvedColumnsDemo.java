package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.properties.CurvedColumnProperties;
import gwt.material.design.amcharts.client.series.CurvedColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;
import gwt.material.design.amcore.client.state.SpriteState;

public class CurvedColumnsDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0;


        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.dataFields.category = "country";
        categoryAxis.renderer.minGridDistance = 40;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        CurvedColumnSeries series = (CurvedColumnSeries) chart.series.push(new CurvedColumnSeries());
        series.dataFields.categoryX = "country";
        series.dataFields.valueY = "value";
        series.tooltipText = "{valueY.value}";
        series.columns.template.strokeOpacity = 0;

        series.columns.template.fillOpacity = 0.75;

        SpriteState<CurvedColumnProperties> hoverState = series.columns.template.states.create("hover");
        hoverState.properties.fillOpacity = 1;
        hoverState.properties.tension = 0.5;

        chart.cursor = new XYCursor();

        // Add distinctive colors for each column using adapter
        series.columns.template.adapter.add("fill", (sprite, target) -> chart.colors.getIndex(target.dataItem.index));

        chart.scrollbarX = new Scrollbar();

        chart.dataSource.url = "data/curved-columns.json";
    }

    @Override
    public String getTitle() {
        return "Curved Columns";
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

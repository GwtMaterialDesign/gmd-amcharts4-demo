package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.SlicedChart;
import gwt.material.design.amcharts.client.series.FunnelSeries;
import gwt.material.design.amcore.client.Am4Core;

public class SlicedFunnelChart implements ChartDemo {

    private SlicedChart funnel;

    @Override
    public void generate(Widget widget) {
        funnel = (gwt.material.design.amcharts.client.SlicedChart) Am4Core.create(widget, Am4Charts.SlicedChart);
        FunnelSeries series = funnel.series.push(new FunnelSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        series.alignLabels = true;
        funnel.dataSource.url = "data/basic.json";
    }

    @Override
    public String getSourceCode() {
        return null;
    }

    @Override
    public Chart getChart() {
        return funnel;
    }
}

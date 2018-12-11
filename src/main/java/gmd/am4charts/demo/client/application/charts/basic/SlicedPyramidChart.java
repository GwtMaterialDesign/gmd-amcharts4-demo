package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.SlicedChart;
import gwt.material.design.amcharts.client.series.FunnelSeries;
import gwt.material.design.amcharts.client.series.PyramidSeries;
import gwt.material.design.amcore.client.Am4Core;

public class SlicedPyramidChart implements ChartDemo {

    private SlicedChart pyramid;

    @Override
    public void generate(Widget widget) {
        pyramid = (SlicedChart) Am4Core.create(widget, Am4Charts.SlicedChart);
        PyramidSeries pySeries = (PyramidSeries) pyramid.series.push(new PyramidSeries());
        pySeries.dataFields.value = "litres";
        pySeries.dataFields.category = "country";
        pySeries.alignLabels = true;
        pyramid.dataSource.url = "data/basic.json";
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_7407_none-1.png";
    }

    @Override
    public Chart getChart() {
        return pyramid;
    }
}

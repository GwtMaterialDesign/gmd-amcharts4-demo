package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.SlicedChart;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.FunnelSeries;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;

public class FunnelChartDemo implements ChartDemo {

    private SlicedChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (SlicedChart) Am4Core.create(widget, Am4Charts.SlicedChart);
        chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

        chart.dataSource.url = "data/funnel-chart.json";

        FunnelSeries series = chart.series.push(new FunnelSeries());
        series.colors.step = 2;
        series.dataFields.value = "value";
        series.dataFields.category = "name";
        series.alignLabels = true;

        series.labelsContainer.paddingLeft = 15;
        series.labelsContainer.width = 200;

        series.orientation = "horizontal";
        series.bottomRatio = 1;

        chart.legend = new Legend();
        chart.legend.position = "left";
        chart.legend.valign = "bottom";
        chart.legend.margin(5,5,20,5);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/11/demo_123_none.png";
    }

    private void randomValue(ClockHand hand) {
        hand.showValue(Math.random() * 100, 1000, Am4Core.ease.cubicOut);
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

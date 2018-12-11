package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.GaugeChart;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.dataitem.AxisDataItem;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.ContainerLayout;
import gwt.material.design.amcore.client.ui.Rectangle;

public class GaugeDemo implements ChartDemo {

    private GaugeChart gaugeChart;

    @Override
    public void generate(Widget widget) {
        gaugeChart = (GaugeChart) Am4Core.create(widget, Am4Charts.GaugeChart);
        gaugeChart.innerRadius = -15;

        ValueAxis valueAxis = (ValueAxis) gaugeChart.xAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.max = 100;
        valueAxis.strictMinMax = true;

        ColorSet colorSet = new ColorSet();

        AxisDataItem range = valueAxis.axisRanges.create();
        range.value = 0;
        range.endValue = 50;
        range.axisFill.fillOpacity = 1;
        range.axisFill.fill = colorSet.getIndex(0);

        AxisDataItem range2 = valueAxis.axisRanges.create();
        range2.value = 50;
        range2.endValue = 80;
        range2.axisFill.fillOpacity = 1;
        range2.axisFill.fill = colorSet.getIndex(2);

        AxisDataItem range3 = valueAxis.axisRanges.create();
        range3.value = 80;
        range3.endValue = 100;
        range3.axisFill.fillOpacity = 1;
        range3.axisFill.fill = colorSet.getIndex(4);
        ClockHand hand = gaugeChart.hands.push(new ClockHand());
        hand.showValue(20);
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return gaugeChart;
    }
}

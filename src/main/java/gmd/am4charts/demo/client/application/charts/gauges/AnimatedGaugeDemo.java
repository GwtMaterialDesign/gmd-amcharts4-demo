package gmd.am4charts.demo.client.application.charts.gauges;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.GaugeChart;
import gwt.material.design.amcharts.client.axis.AxisLabelCircular;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.renderer.AxisRendererCircular;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.animation.Animation;
import gwt.material.design.amcore.client.animation.AnimationOptions;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.ui.Label;

public class AnimatedGaugeDemo implements ChartDemo {

    private GaugeChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (GaugeChart) Am4Core.create(widget, Am4Charts.GaugeChart);

        // create chart
        chart.innerRadius = new Percent(82);

        // Normal axis

        ValueAxis axis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        axis.min = 0;
        axis.max = 100;
        axis.strictMinMax = true;

        AxisRendererCircular rendererCircular1 = (AxisRendererCircular) axis.renderer;
        rendererCircular1.radius = new Percent(80);
        rendererCircular1.inside = true;
        rendererCircular1.line.strokeOpacity = 1;
        rendererCircular1.ticks.template.strokeOpacity = 1;
        rendererCircular1.ticks.template.length = 10;
        rendererCircular1.grid.template.disabled = true;
        rendererCircular1.labels.template.radius = 40;
        rendererCircular1.labels.template.adapter.add("text",  (label, labelAdapter) -> label + "%");

        // Axis for ranges

        ColorSet colorSet = new ColorSet();

        ValueAxis axis2 = (ValueAxis) chart.xAxes.push(new ValueAxis());
        axis2.min = 0;
        axis2.max = 100;
        axis2.strictMinMax = true;

        AxisRendererCircular rendererCircular = (AxisRendererCircular) axis2.renderer;
        rendererCircular.innerRadius = 10;
        rendererCircular.labels.template.disabled = true;
        rendererCircular.ticks.template.disabled = true;
        rendererCircular.grid.template.disabled = true;

        ValueAxisDataItem range0 = axis2.axisRanges.create();
        range0.value = 0;
        range0.endValue = 50;
        range0.axisFill.fillOpacity = 1;
        range0.axisFill.fill = colorSet.getIndex(0);

        ValueAxisDataItem range1 = axis2.axisRanges.create();
        range1.value = 50;
        range1.endValue = 100;
        range1.axisFill.fillOpacity = 1;
        range1.axisFill.fill = colorSet.getIndex(2);

        Label label = (Label) chart.radarContainer.createChild(Am4Core.Label);
        label.isMeasured = false;
        label.fontSize = 45;
        label.x = new Percent(50);
        label.y = new Percent(100);
        label.horizontalCenter = "middle";
        label.verticalCenter = "bottom";
        label.text = "50%";


        // Hand

        ClockHand hand = chart.hands.push(new ClockHand());
        hand.axis = axis2;
        hand.innerRadius = new Percent(20);
        hand.startWidth = 10;
        hand.pin.disabled = true;
        hand.value = 50;

        hand.events.on("propertychanged", ev -> {
            range0.endValue = ev.target.value;
            range1.value = ev.target.value;
            axis2.invalidate();
        });

        Scheduler.get().scheduleFixedDelay(() -> {
            double value = Math.round(Math.random() * 100);
            label.text = value + "%";
            AnimationOptions<Double> op1 = new AnimationOptions();
            op1.property = "value";
            op1.to = value;
            Animation animation = new Animation(hand, new AnimationOptions[]{op1}, 1000, Am4Core.ease.cubicOut);
            animation.start();
            return true;
        }, 1000);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2016/08/demo_8418_none.png";
    }

    private void randomValue(ClockHand hand) {
        hand.showValue(Math.random() * 100, 1000, Am4Core.ease.cubicOut);
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

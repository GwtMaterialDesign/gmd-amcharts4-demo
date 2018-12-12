package gmd.am4charts.demo.client.application.charts.gauges;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.GaugeChart;
import gwt.material.design.amcharts.client.axis.AxisFill;
import gwt.material.design.amcharts.client.axis.AxisLabel;
import gwt.material.design.amcharts.client.axis.AxisLabelCircular;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.RotationDirection;
import gwt.material.design.amcore.client.export.ExportMenu;
import gwt.material.design.amcore.client.list.List;
import gwt.material.design.amcore.client.list.ListTemplate;

import java.util.Date;

public class ClockDemo implements ChartDemo {

    private GaugeChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (GaugeChart) Am4Core.create(widget, Am4Charts.GaugeChart);
        chart.exporting.menu = new ExportMenu();
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart.startAngle = -90;
        chart.endAngle = 270;

        ValueAxis axis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        axis.min = 0;
        axis.max = 12;
        axis.strictMinMax = true;

        axis.renderer.line.strokeWidth = 8;
        axis.renderer.line.strokeOpacity = 1;
        axis.renderer.minLabelPosition = 0.05; // hides 0 label
        axis.renderer.inside = true;
        AxisLabelCircular label = (AxisLabelCircular) axis.renderer.labels.template;

        label.radius = 35;
        axis.renderer.axisFills.template.disabled = true;
        axis.renderer.grid.template.disabled = true;
        axis.renderer.ticks.template.length = 12;
        axis.renderer.ticks.template.strokeOpacity = 1;

        // serves as a clock face fill
        ValueAxisDataItem range = axis.axisRanges.create();
        range.startValue = 0;
        range.endValue = 12;
        range.grid.visible = false;
        range.tick.visible = false;
        range.label.visible = false;

        AxisFill axisFill = range.axisFill;
        axisFill.fillOpacity = 1;
        axisFill.disabled = false;
        axisFill.fill = new InterfaceColorSet().getFor("fill");

        // hands
        ClockHand hourHand = chart.hands.push(new ClockHand());
        hourHand.radius = new Percent(60);
        hourHand.startWidth = 10;
        hourHand.endWidth = 10;
        hourHand.rotationDirection = "clockWise";
        hourHand.pin.radius = 8;
        hourHand.zIndex = 0;

        ClockHand minutesHand = chart.hands.push(new ClockHand());
        minutesHand.rotationDirection = "clockWise";
        minutesHand.startWidth = 7;
        minutesHand.endWidth = 7;
        minutesHand.radius = new Percent(78);
        minutesHand.zIndex = 1;

        ClockHand secondsHand = chart.hands.push(new ClockHand());
        secondsHand.fill = new Color("#DD0000");
        secondsHand.stroke = new Color("#DD0000");
        secondsHand.radius = new Percent(85);
        secondsHand.rotationDirection = RotationDirection.CLOCKWISE;
        secondsHand.zIndex = 2;
        secondsHand.startWidth = 1;

        updateHands(hourHand, minutesHand, secondsHand);

        Scheduler.get().scheduleFixedDelay(() -> {
            updateHands(hourHand, minutesHand, secondsHand);
            return true;
        }, 1000);
    }

    private void updateHands(ClockHand hourHand, ClockHand minutesHand, ClockHand secondsHand) {
        // get current date
        Date date = new Date();
        double hours = date.getHours();
        double minutes = date.getMinutes();
        double seconds = date.getSeconds();

        // set hours
        hourHand.showValue(hours + minutes / 60, 0);
        // set minutes
        minutesHand.showValue(12 * (minutes + seconds / 60) / 60, 0);
        // set seconds
        secondsHand.showValue(12 * (seconds / 60), 300);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_7389_none-1.png";
    }

    private void randomValue(ClockHand hand) {
        hand.showValue(Math.random() * 100, 1000, Am4Core.ease.cubicOut);
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gmd.amcharts4.demo.client.application.charts.gauges;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.GaugeChart;
import gwt.material.design.amcharts.client.axis.AxisFill;
import gwt.material.design.amcharts.client.axis.AxisLabelCircular;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.renderer.AxisRendererCircular;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.export.ExportMenu;
import gwt.material.design.amcore.client.ui.Label;

import java.util.Date;

public class ClockWithTwoFacesDemo implements ChartDemo {

    private GaugeChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (GaugeChart) Am4Core.create(widget, Am4Charts.GaugeChart);

        chart.exporting.menu = new ExportMenu();

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

        // inner axis
        ValueAxis axis2 = (ValueAxis) chart.xAxes.push(new ValueAxis());
        axis2.min = 0;
        axis2.max = 60;
        //TODO: Radius

        axis2.strictMinMax = true;

        AxisRendererCircular rendererCircular = (AxisRendererCircular) axis2.renderer;
        rendererCircular.radius = new Percent(25);
        rendererCircular.minLabelPosition = 0.05;
        rendererCircular.ticks.template.length = 12;
        rendererCircular.ticks.template.strokeOpacity = 1;
        rendererCircular.inside = true;
        rendererCircular.grid.template.disabled = true;
        rendererCircular.labels.template.disabled = true;
        rendererCircular.minGridDistance = 1;


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


        // Label
        Label label2 = (Label) chart.radarContainer.createChild(Am4Core.Label);
        label2.isMeasured = false;
        label2.fontSize = 12;
        label2.horizontalCenter = "middle";
        label2.verticalCenter = "middle";

        Date date = new Date();
        String dateStr = "" + date.getDate() + '-' + (date.getMonth() + 1);
        label2.text = dateStr;

        // hands
        ClockHand hourHand = chart.hands.push(new ClockHand());
        hourHand.radius = new Percent(85);
        hourHand.innerRadius = new Percent(30);
        hourHand.startWidth = 10;
        hourHand.endWidth = 10;
        hourHand.rotationDirection = "clockWise";
        hourHand.pin.disabled = true;
        hourHand.zIndex = 0;

        ClockHand minutesHand = chart.hands.push(new ClockHand());
        minutesHand.radius = new Percent(100);
        minutesHand.innerRadius = new Percent(30);
        minutesHand.rotationDirection = "clockWise";
        minutesHand.startWidth = 7;
        minutesHand.endWidth = 7;
        minutesHand.pin.disabled = true;
        minutesHand.zIndex = 1;

        ClockHand secondsHand = chart.hands.push(new ClockHand());
        secondsHand.radius = new Percent(25);
        secondsHand.innerRadius = new Percent(20);
        secondsHand.fill = new Color("#DD0000");
        secondsHand.stroke = new Color("#DD0000");
        secondsHand.rotationDirection = "clockWise";
        secondsHand.zIndex = 2;
        secondsHand.startWidth = 8;
        secondsHand.endWidth = 8;
        secondsHand.pin.disabled = true;

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
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_7390_none-1.png";
    }

    private void randomValue(ClockHand hand) {
        hand.showValue(Math.random() * 100, 1000, Am4Core.ease.cubicOut);
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

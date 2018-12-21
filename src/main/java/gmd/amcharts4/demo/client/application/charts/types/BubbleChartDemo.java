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
package gmd.amcharts4.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.properties.CircleBulletProperties;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.state.SpriteState;

public class BubbleChartDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/bubble-chart.json";

        ValueAxis valueAxisX = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxisX.renderer.ticks.template.disabled = true;
        valueAxisX.renderer.axisFills.template.disabled = true;

        ValueAxis valueAxisY = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxisY.renderer.ticks.template.disabled = true;
        valueAxisY.renderer.axisFills.template.disabled = true;

        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueX = "x";
        series.dataFields.valueY = "y";
        series.dataFields.value = "value";
        series.strokeOpacity = 0;
        series.sequencedInterpolation = true;
        series.tooltip.pointerOrientation = "vertical";

        CircleBullet bullet = (CircleBullet) series.bullets.push(new CircleBullet());
        bullet.fill = new Color("#ff0000");

        MyCustomPropertyField propertyFields = new MyCustomPropertyField();
        propertyFields.fill = "color";

        bullet.propertyFields = propertyFields;
        bullet.strokeOpacity = 0;
        bullet.strokeWidth = 2;
        bullet.fillOpacity = 0.7;
        bullet.stroke = new Color("#ffffff");
        bullet.hiddenState.properties.opacity = 0;
        bullet.circle.tooltipText = "[bold]{title}:[/]\nPopulation: {value.value}\nIncome: {valueX.value}\nLife expectancy:{valueY.value}";

        SpriteState<CircleBulletProperties> hoverState = bullet.states.create("hover");
        hoverState.properties.fillOpacity = 1;
        hoverState.properties.strokeOpacity = 1;

        HeatRule<Double> heatRule = new HeatRule<>();
        heatRule.target = bullet.circle;
        heatRule.min = 2.0;
        heatRule.max = 60.0;
        heatRule.property = "radius";
        series.heatRules.push(heatRule);


        bullet.circle.adapter.add("tooltipY", (circle, adapter) -> new Float(-(Double) adapter.radius));

        chart.cursor = new XYCursor();
        chart.cursor.behavior = "zoomXY";

        chart.scrollbarX = new XYChartScrollbar();
        chart.scrollbarY = new XYChartScrollbar();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_602_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

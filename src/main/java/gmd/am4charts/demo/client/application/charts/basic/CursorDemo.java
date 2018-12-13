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
package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.Axis;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.properties.CircleBulletProperties;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.Range;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.client.ui.MaterialToast;

public class CursorDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "year";
        /* Create value axis */
        chart.yAxes.push(new ValueAxis());
        /* Create series */
        LineSeries series1 = (LineSeries) chart.series.push(new LineSeries());
        series1.dataFields.valueY = "cars";
        series1.dataFields.categoryX = "year";
        series1.name = "Cars";
        series1.strokeWidth = 3;
        series1.tensionX = 0.7;
        CircleBullet bullet = (CircleBullet) series1.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet1hover = bullet.states.create("hover");
        bullet1hover.properties.scale = 2;
        series1.tooltipText = "{categoryX}: [bold]{valueY}[/]";
        LineSeries series2 = (LineSeries) chart.series.push(new LineSeries());
        series2.dataFields.valueY = "motorcycles";
        series2.dataFields.categoryX = "year";
        series2.name = "Motorcycles";
        series2.strokeWidth = 3;
        series2.tensionX = 0.7;
        CircleBullet bullet2 = (CircleBullet) series2.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet2hover = bullet2.states.create("hover");
        bullet2hover.properties.scale = 2;
        series2.tooltipText = "{categoryX}: [bold]{valueY}[/]";
        LineSeries series3 = (LineSeries) chart.series.push(new LineSeries());
        series3.dataFields.valueY = "bicycles";
        series3.dataFields.categoryX = "year";
        series3.name = "Bicycles";
        series3.strokeWidth = 3;
        series3.tensionX = 0.7;
        CircleBullet bullet3 = (CircleBullet) series3.bullets.push(new CircleBullet());
        SpriteState<CircleBulletProperties> bullet3hover = bullet3.states.create("hover");
        bullet3hover.properties.scale = 2;
        series3.tooltipText = "{categoryX}: [bold]{valueY}[/]";
        /* Add legend */
        chart.legend = new Legend();
        /* Create a cursor */
        chart.cursor = new XYCursor();
        // Changing cursor appearance
        chart.cursor.lineX.stroke = new Color("#8F3985");
        chart.cursor.lineX.strokeWidth = 4;
        chart.cursor.lineX.strokeOpacity = 0.2;
        chart.cursor.lineX.strokeDasharray = "";
        chart.cursor.lineY.stroke = new Color("#8F3985");
        chart.cursor.lineY.strokeWidth = 4;
        chart.cursor.lineY.strokeOpacity = 0.2;
        chart.cursor.lineY.strokeDasharray = "";
        // Disabling Cursor Line
        chart.cursor.lineY.disabled = true;
        // Enabling full-width Cursor Lines
        chart.cursor.xAxis = categoryAxis;
        chart.cursor.fullWidthLineX = true;
        chart.cursor.lineX.strokeWidth = 0;
        chart.cursor.lineX.fill = new Color("#8F3985");
        chart.cursor.lineX.fillOpacity = 0.1;
        chart.cursor.behavior = "selectX";
        chart.cursor.events.on("selectended", param1 -> {
            XYCursor cursor = param1.target;
            Range range = cursor.xRange;
            Axis axis = ((XYChart) cursor.chart).xAxes.getIndex(0);
            String from = axis.getPositionLabel(range.start);
            String to = axis.getPositionLabel(range.end);
            MaterialToast.fireToast("Selected from " + from + " to " + to);
        });
        chart.dataSource.url = "data/cursor.json";
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

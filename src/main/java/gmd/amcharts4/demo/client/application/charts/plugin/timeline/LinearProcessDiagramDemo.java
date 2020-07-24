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
package gmd.amcharts4.demo.client.application.charts.plugin.timeline;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amplugin.timeline.client.chart.CurveChart;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LinearProcessDiagramDemo implements ChartDemo {

    private CurveChart chart;

    @Override
    public void generate(Widget container) {
        /*chart = (CurveChart) Am4Core.create(container, TimelineCharts.CurveChart);
        chart.dataSource.url = "data/plugin/timeline-lpd.json";
        chart.curveContainer.padding(100, 20, 50, 20);
        chart.maskBullets = false;

        chart.dateFormatter.inputDateFormat = "yyyy-MM-dd HH:mm";
        chart.dateFormatter.dateFormat = "HH";

        chart.fontSize = 10;
        //chart.tooltipContainer.fontSize = 10;

        CategoryAxis categoryAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";

        AxisRendererCurveY rendererCurveY = new AxisRendererCurveY();
        rendererCurveY.grid.template.disabled = true;
        rendererCurveY.labels.template.paddingRight = 25;
        rendererCurveY.minGridDistance = 10;
        rendererCurveY.innerRadius = 10;
        rendererCurveY.radius = 30;


        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());

        AxisRendererCurveX rendererCurveX = new AxisRendererCurveX();
        rendererCurveX.points = getPoints();
        dateAxis.renderer = rendererCurveX;

        rendererCurveX.autoScale = false;
        rendererCurveX.autoCenter = false;
        rendererCurveX.minGridDistance = 70;
        dateAxis.baseInterval = TimeInterval.create(5, "minute");
        rendererCurveX.tooltipLocation = 0;
        rendererCurveX.line.strokeDasharray = "1,4";
        rendererCurveX.line.strokeOpacity = 0.5;
        PointedRectangle pointedRectangle = new PointedRectangle();
        pointedRectangle.fillOpacity = 0.2;
        pointedRectangle.cornerRadius = 5;
        dateAxis.tooltip.background = pointedRectangle;

        dateAxis.tooltip.label.fill = new InterfaceColorSet().getFor("alternativeBackground");
        dateAxis.tooltip.label.paddingTop = 7;
        dateAxis.endLocation = 0;
        dateAxis.startLocation = -0.5;
        dateAxis.min = new Date(2019, 0, 9, 23, 55).getTime();
        dateAxis.max = new Date(2019, 0, 11, 7, 10).getTime();

        AxisLabel labelTemplate = dateAxis.renderer.labels.template;
        labelTemplate.verticalCenter = "middle";
        labelTemplate.fillOpacity = 0.6;
        labelTemplate.background.fill = new InterfaceColorSet().getFor("background");
        labelTemplate.background.fillOpacity = 1;
        labelTemplate.fill = new InterfaceColorSet().getFor("text");
        labelTemplate.padding(7, 7, 7, 7);

        CurveColumnSeries series = (CurveColumnSeries) chart.series.push(new CurveColumnSeries());
        series.columns.template.height = new Percent(30);

        series.dataFields.openDateX = "start";
        series.dataFields.dateX = "end";
        series.dataFields.categoryY = "category";
        series.baseAxis = categoryAxis;

        CurvedColumn curvedColumn = (CurvedColumn) series.columns.template;
        //curvedColumn.propertyFields.fill = "color"; // get color from data
        //curvedColumn.propertyFields.stroke = "color";
        curvedColumn.strokeOpacity = 0;
        curvedColumn.fillOpacity = 0.6;

        var imageBullet1 = series.bullets.push(new am4plugins_bullets.PinBullet());
        imageBullet1.background.radius = 18;
        imageBullet1.locationX = 1;
        imageBullet1.propertyFields.stroke = "color";
        imageBullet1.background.propertyFields.fill = "color";
        imageBullet1.image = new am4core.Image();
        imageBullet1.image.propertyFields.href = "icon";
        imageBullet1.image.scale = 0.7;
        imageBullet1.circle.radius = am4core.percent(100);
        imageBullet1.background.fillOpacity = 0.8;
        imageBullet1.background.strokeOpacity = 0;
        imageBullet1.dy = -2;
        imageBullet1.background.pointerBaseWidth = 10;
        imageBullet1.background.pointerLength = 10
        imageBullet1.tooltipText = "{text}";

        series.tooltip.pointerOrientation = "up";

        imageBullet1.background.adapter.add("pointerAngle", (value, target) =>{
            if (target.dataItem) {
                var position = dateAxis.valueToPosition(target.dataItem.openDateX.getTime());
                return dateAxis.renderer.positionToAngle(position);
            }
            return value;
        });

        var hs = imageBullet1.states.create("hover");
        hs.properties.scale = 1.3;
        hs.properties.opacity = 1;

        var textBullet = series.bullets.push(new am4charts.LabelBullet());
        textBullet.label.propertyFields.text = "text";
        textBullet.disabled = true;
        textBullet.propertyFields.disabled = "textDisabled";
        textBullet.label.strokeOpacity = 0;
        textBullet.locationX = 1;
        textBullet.dy = -100;
        textBullet.label.textAlign = "middle";

        chart.scrollbarX = new am4core.Scrollbar();
        chart.scrollbarX.align = "center"
        chart.scrollbarX.width = am4core.percent(75);
        chart.scrollbarX.parent = chart.curveContainer;
        chart.scrollbarX.height = 300;
        chart.scrollbarX.orientation = "vertical";
        chart.scrollbarX.x = 128;
        chart.scrollbarX.y = -140;
        chart.scrollbarX.isMeasured = false;
        chart.scrollbarX.opacity = 0.5;

        var cursor = new am4plugins_timeline.CurveCursor();
        chart.cursor = cursor;
        cursor.xAxis = dateAxis;
        cursor.yAxis = categoryAxis;
        cursor.lineY.disabled = true;
        cursor.lineX.disabled = true;

        dateAxis.renderer.tooltipLocation2 = 0;
        categoryAxis.cursorTooltipEnabled = false;

        chart.zoomOutButton.disabled = true;

        var previousBullet;

        chart.events.on("inited", function() {
            setTimeout(function() {
                hoverItem(series.dataItems.getIndex(0));
            },2000)
        })

        function hoverItem (dataItem) {
            var bullet = dataItem.bullets.getKey(imageBullet1.uid);
        var index = dataItem.index;

        if (index >= series.dataItems.length - 1) {
            index = -1;
        }

        if (bullet) {

            if (previousBullet) {
                previousBullet.isHover = false;
            }

            bullet.isHover = true;

            previousBullet = bullet;
        }
        setTimeout(
            function() {
            hoverItem(series.dataItems.getIndex(index + 1))
        },1000);
    }*/
    }

    public static JavaScriptObject getData() {
        ChartData<Visit> data = new ChartData(getList());

        data.add(new DataProvider<Visit>() {
            @Override
            public String getProperty() {
                return "date";
            }

            @Override
            public JSONValue getValue(Visit object) {
                return new JSONString(object.getDate().toString());
            }
        });

        data.add(new DataProvider<Visit>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(Visit object) {
                return new JSONNumber(object.getValue());
            }
        });

        return data.get();
    }

    public static List<Visit> getList() {
        List<Visit> test = new ArrayList<>();
        int visits = 100;
        for (int i = 0; i < 24; i++) {
            visits += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 10);
            test.add(new Visit(new Date(1, 1, 118, i, 0), visits));
        }
        return test;
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

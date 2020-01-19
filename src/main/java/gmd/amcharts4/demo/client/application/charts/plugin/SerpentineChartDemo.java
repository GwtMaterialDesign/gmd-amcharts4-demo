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
package gmd.amcharts4.demo.client.application.charts.plugin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.constants.Orientation;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.properties.Point;
import gwt.material.design.amplugin.timeline.client.chart.CurveChart;
import gwt.material.design.amplugin.timeline.client.chart.SerpentineChart;
import gwt.material.design.amplugin.timeline.client.chart.TimelineCharts;
import gwt.material.design.amplugin.timeline.client.cursor.CurveCursor;
import gwt.material.design.amplugin.timeline.client.renderer.AxisRendererCurveX;
import gwt.material.design.amplugin.timeline.client.renderer.AxisRendererCurveY;
import gwt.material.design.amplugin.timeline.client.resources.TimeLineResources;
import gwt.material.design.amplugin.timeline.client.series.CurveColumnSeries;
import gwt.material.design.amplugin.timeline.client.series.CurveStepLineSeries;
import gwt.material.design.client.MaterialDesignBase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SerpentineChartDemo implements ChartDemo {

    private SerpentineChart chart;

    @Override
    public void generate(Widget container) {
        chart = (SerpentineChart) Am4Core.create(container, TimelineCharts.SerpentineChart);
        chart.data = getData();
        chart.levelCount = 5;
        chart.orientation = Orientation.VERTICAL;

        chart.curveContainer.padding(20, 20, 20, 20);


        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.grid.template.location = 0;

        dateAxis.renderer.line.disabled = true;
        dateAxis.cursorTooltipEnabled = false;
        dateAxis.minZoomCount = 5;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;
        AxisRendererCurveY rendererCurveY = new AxisRendererCurveY();
        rendererCurveY.innerRadius = -50;
        rendererCurveY.radius = 50;
        valueAxis.renderer = rendererCurveY;

        chart.seriesContainer.zIndex = -1;

        CurveStepLineSeries series = (CurveStepLineSeries) chart.series.push(new CurveStepLineSeries());
        series.fillOpacity = 0.3;
        series.dataFields.dateX = "date";
        series.dataFields.valueY = "value";
        series.tooltipText = "{valueY}";
        series.tooltip.pointerOrientation = "vertical";
        series.tooltip.background.fillOpacity = 0.7;
        series.fill = chart.colors.getIndex(3);
        series.strokeWidth = 2;

        chart.cursor = new CurveCursor();
        chart.cursor.xAxis = dateAxis;
        chart.cursor.yAxis = valueAxis;
        chart.cursor.lineY.disabled = true;
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
            test.add(new Visit(new Date(1,1, 118, i, 0), visits));
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

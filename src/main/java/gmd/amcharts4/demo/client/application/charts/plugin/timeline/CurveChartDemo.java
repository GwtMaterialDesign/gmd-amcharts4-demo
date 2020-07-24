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

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.properties.Point;
import gwt.material.design.amplugin.timeline.client.chart.CurveChart;
import gwt.material.design.amplugin.timeline.client.chart.TimelineCharts;
import gwt.material.design.amplugin.timeline.client.renderer.AxisRendererCurveX;
import gwt.material.design.amplugin.timeline.client.renderer.AxisRendererCurveY;
import gwt.material.design.amplugin.timeline.client.series.CurveColumnSeries;

public class CurveChartDemo implements ChartDemo {

    private CurveChart chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (CurveChart) Am4Core.create(container, TimelineCharts.CurveChart);
        chart.dataSource.url = "data/plugin/timeline.json";
        // Create category (X) axis
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";

        AxisRendererCurveX xRenderer = new AxisRendererCurveX();
        xRenderer.grid.template.disabled = true;
        xRenderer.polyspline.tensionX = 0.8;
        xRenderer.minGridDistance = 30;

        categoryAxis.renderer = xRenderer;
        xRenderer.points = new Point[]{
            Point.create(-400, 0),
            Point.create(-250, 0),
            Point.create(0, 60),
            Point.create(250, 0),
            Point.create(400, 0)
        };

        // Create value (Y) axis
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        AxisRendererCurveY yRenderer = new AxisRendererCurveY();
        yRenderer.radius = 100;
        yRenderer.innerRadius = 0;
        yRenderer.grid.template.disabled = true;
        valueAxis.renderer = yRenderer;

        // Create series
        CurveColumnSeries series = (CurveColumnSeries) chart.series.push(new CurveColumnSeries());
        series.dataFields.valueY = "value";
        series.dataFields.categoryX = "country";
        series.columns.template.fillOpacity = 0.5;
        series.columns.template.strokeWidth = 2;

        // Add some white space around the chart
        chart.padding(40, 40, 40, 40);
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

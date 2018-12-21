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
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.RadarChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.RadarCursor;
import gwt.material.design.amcharts.client.series.RadarColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;

public class RadialBarChartDemo implements ChartDemo {

    private RadarChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (RadarChart) Am4Core.create(widget, Am4Charts.RadarChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart.dataSource.url = "data/radial-bar-chart.json";

        chart.padding(20, 20, 20, 20);

        CategoryAxis categoryAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.labels.template.location = 0.5;
        categoryAxis.renderer.labels.template.horizontalCenter = "right";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.tooltipLocation = 0.5;
        categoryAxis.renderer.grid.template.strokeOpacity = 0.07;
        categoryAxis.renderer.axisFills.template.disabled = true;
        categoryAxis.mouseEnabled = false;
        categoryAxis.renderer.minGridDistance = 10;

        ValueAxis valueAxis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;
        valueAxis.renderer.labels.template.horizontalCenter = "left";
        valueAxis.min = 0;
        valueAxis.max = 60;
        valueAxis.strictMinMax = true;
        valueAxis.renderer.maxLabelPosition = 0.99;
        valueAxis.renderer.minGridDistance = 10;
        valueAxis.renderer.grid.template.strokeOpacity = 0.07;
        valueAxis.renderer.axisFills.template.disabled = true;
        valueAxis.mouseEnabled = false;

        RadarColumnSeries series1 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series1.columns.template.tooltipText = "{name}: {valueX.value}";
        series1.name = "Series 1";
        series1.dataFields.categoryY = "category";
        series1.dataFields.valueX = "value2";
        series1.stacked = true;

        RadarColumnSeries series2 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series2.columns.template.tooltipText = "{name}: {valueX.value}";
        series2.name = "Series 2";
        series2.dataFields.categoryY = "category";
        series2.dataFields.valueX = "value2";
        series2.stacked = true;

        RadarColumnSeries series3 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series3.columns.template.tooltipText = "{name}: {valueX.value}";
        series3.name = "Series 3";
        series3.dataFields.categoryY = "category";
        series3.dataFields.valueX = "value3";
        series3.stacked = true;

        RadarColumnSeries series4 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series4.columns.template.tooltipText = "{name}: {valueX.value}";
        series4.name = "Series 4";
        series4.dataFields.categoryY = "category";
        series4.dataFields.valueX = "value4";
        series4.stacked = true;

        chart.seriesContainer.zIndex = -1;

        chart.scrollbarX = new Scrollbar();
        chart.scrollbarX.exportable = false;
        chart.scrollbarY = new Scrollbar();
        chart.scrollbarY.exportable = false;

        chart.cursor = new RadarCursor();
        chart.cursor.lineY.disabled = true;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11287_none-1-1024x646.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

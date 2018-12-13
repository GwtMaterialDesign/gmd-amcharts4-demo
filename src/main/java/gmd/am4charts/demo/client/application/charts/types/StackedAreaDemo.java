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
package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.base.TimeInterval;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.dataitem.DateAxisDataItem;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.series.XYSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;

import java.util.Date;

public class StackedAreaDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/stacked-area.json";

        chart.dateFormatter.inputDateFormat = "yyyy";
        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.minGridDistance = 60;
        dateAxis.startLocation = 0.5;
        dateAxis.endLocation = 0.5;

        TimeInterval interval = new TimeInterval();
        interval.timeUnit = "year";
        interval.count = 1;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;

        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.dateX = "year";
        series.name = "cars";
        series.dataFields.valueY = "cars";
        series.tooltipHTML = "<img src='https://www.amcharts.com/lib/3/images/car.png' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
        series.tooltip.background.fill = new Color("#FFF");
        series.tooltip.getStrokeFromObject = true;
        series.tooltip.background.strokeWidth = 3;
        series.tooltip.getFillFromObject = false;
        series.fillOpacity = 0.6;
        series.strokeWidth = 2;
        series.stacked = true;

        XYSeries series2 = chart.series.push(new LineSeries());
        series2.name = "motorcycles";
        series2.dataFields.dateX = "year";
        series2.dataFields.valueY = "motorcycles";
        series2.tooltipHTML = "<img src='https://www.amcharts.com/lib/3/images/motorcycle.png' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
        series2.tooltip.background.fill = new Color("#FFF");
        series2.tooltip.getFillFromObject = false;
        series2.tooltip.getStrokeFromObject = true;
        series2.tooltip.background.strokeWidth = 3;
        series2.sequencedInterpolation = true;
        series2.fillOpacity = 0.6;
        series2.stacked = true;
        series2.strokeWidth = 2;

        LineSeries series3 = (LineSeries) chart.series.push(new LineSeries());
        series3.name = "bicycles";
        series3.dataFields.dateX = "year";
        series3.dataFields.valueY = "bicycles";
        series3.tooltipHTML = "<img src='https://www.amcharts.com/lib/3/images/bicycle.png' style='vertical-align:bottom; margin-right: 10px; width:28px; height:21px;'><span style='font-size:14px; color:#000000;'><b>{valueY.value}</b></span>";
        series3.tooltip.background.fill = new Color("#FFF");
        series3.tooltip.getFillFromObject = false;
        series3.tooltip.getStrokeFromObject = true;
        series3.tooltip.background.strokeWidth = 3;
        series3.sequencedInterpolation = true;
        series3.fillOpacity = 0.6;
        series3.defaultState.transitionDuration = 1000;
        series3.stacked = true;
        series3.strokeWidth = 2;

        chart.cursor = new XYCursor();
        chart.cursor.xAxis = dateAxis;
        chart.scrollbarX = new Scrollbar();

        // Add a legend
        chart.legend = new Legend();
        chart.legend.position = "top";

        // axis ranges
        DateAxisDataItem range = dateAxis.axisRanges.create();
        range.date = JsDate.create(new Date(101, 1, 1).getTime());
        range.endDate = JsDate.create(new Date(103, 1, 1).getTime());
        range.axisFill.fill = chart.colors.getIndex(7);
        range.axisFill.fillOpacity = 0.2;

        range.label.text = "Fines for speeding increased";
        range.label.inside = true;
        range.label.rotation = 90;
        range.label.horizontalCenter = "right";
        range.label.verticalCenter = "middle";

        DateAxisDataItem range2 = dateAxis.axisRanges.create();
        range2.date = JsDate.create(new Date(107, 1, 1).getTime());
        range2.grid.stroke = chart.colors.getIndex(7);
        range2.grid.strokeOpacity = 0.6;
        range2.grid.strokeDasharray = "5,2";


        range2.label.text = "Motorcycle fee introduced";
        range2.label.inside = true;
        range2.label.rotation = 90;
        range2.label.horizontalCenter = "right";
        range2.label.verticalCenter = "middle";
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/11/demo_7379_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

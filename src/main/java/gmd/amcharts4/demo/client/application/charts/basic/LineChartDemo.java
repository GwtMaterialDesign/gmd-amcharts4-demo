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
package gmd.amcharts4.demo.client.application.charts.basic;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.language.Locale;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

public class LineChartDemo implements ChartDemo {

    private XYChart xyChart;

    @Override
    public void generate(Widget container) {
        // XYChart Demo
        xyChart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        // Language
        xyChart.language.locale = Locale.ru_RU.getValue();
        // DateAxis
        DateAxis dateAxis = (DateAxis) xyChart.xAxes.push(new DateAxis());
        // ValueAxis
        ValueAxis valueAxis = (ValueAxis) xyChart.yAxes.push(new ValueAxis());
        // Number Formatter
        valueAxis.numberFormatter.numberFormat = "'$' #.a";
        // Series
        LineSeries lineSeries = (LineSeries) xyChart.series.push(new LineSeries());
        lineSeries.dataFields.valueY = "value";
        lineSeries.dataFields.dateX = "day";
        lineSeries.strokeWidth = 1.8;
        xyChart.dataSource.url = "data/large-data.json";
        // Scrollbar
        XYChartScrollbar scrollbarX = new XYChartScrollbar();
        scrollbarX.series.push(lineSeries);
        xyChart.scrollbarX = scrollbarX;
        xyChart.scrollbarX.updateWhileMoving = false;
        // Cursor
        xyChart.cursor = new XYCursor();
        // Events implementation
        xyChart.events.on("hit", param1 -> {
            MaterialToast.fireToast("Event Fired: click");
        }, this);
        // Zooming
        xyChart.zoomToIndexes(0, 23);
        xyChart.events.on("datavalidated", param1 -> {
            dateAxis.zoomToDates(JsDate.create(new Date(117, 11, 1).getTime()), JsDate.create(new Date(117, 11, 31).getTime()));
            MaterialToast.fireToast("Event Fired: datavalidated");
        }, this);
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return xyChart;
    }
}

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
import gwt.material.design.amcharts.client.axis.Axis;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.base.TimeInterval;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.TimeUnit;

import java.util.Date;

public class GanttChartDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart.dataSource.url = "data/gantt-chart.json";

        chart.paddingRight = 30;
        chart.dateFormatter.inputDateFormat = "yyyy-MM-dd HH:mm";

        ColorSet colorSet = new ColorSet();
        colorSet.saturation = 0.4;

        Axis categoryAxis = chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "name";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.inversed = true;

        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.dateFormatter.dateFormat = "yyyy-MM-dd HH:mm";
        dateAxis.renderer.minGridDistance = 70;

        TimeInterval interval = new TimeInterval();
        interval.count = 30;
        interval.timeUnit = TimeUnit.MINUTE;
        dateAxis.baseInterval = interval;
        dateAxis.max = new Date(118, 0, 1, 24, 0, 0).getTime();
        dateAxis.strictMinMax = true;
        dateAxis.renderer.tooltipLocation = 0;

        ColumnSeries series1 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series1.columns.template.width = new Percent(80);
        series1.columns.template.tooltipText = "{name}: {openDateX} - {dateX}";

        series1.dataFields.openDateX = "fromDate";
        series1.dataFields.dateX = "toDate";
        series1.dataFields.categoryY = "name";

        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.fill = "color";
        propertyField.stroke = "color";

        series1.columns.template.strokeOpacity = 1;

        chart.scrollbarX = new XYChartScrollbar();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2015/04/demo_5381_none-1-1024x646.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

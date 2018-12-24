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
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.RadarColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;

public class PolarAreaChartDemo implements ChartDemo {

    private RadarChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (RadarChart) Am4Core.create(widget, Am4Charts.RadarChart);
        //chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart.dataSource.url = "data/polar-chart.json";
        chart.radius = new Percent(100);

        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.labels.template.location = 0.5;
        categoryAxis.renderer.tooltipLocation = 0.5;
        categoryAxis.renderer.grid.template.disabled = true;
        categoryAxis.renderer.labels.template.disabled = true;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;
        valueAxis.renderer.labels.template.horizontalCenter = "left";
        valueAxis.renderer.grid.template.disabled = true;

        RadarColumnSeries series1 = (RadarColumnSeries) chart.series.push(new RadarColumnSeries());
        series1.name = "Series 1";
        series1.dataFields.categoryX = "category";
        series1.dataFields.valueY = "value2";
        series1.stroke = new Color("#ffffff");
        series1.columns.template.strokeOpacity = 0.2;
        series1.stacked = true;
        series1.sequencedInterpolation = true;
        series1.columns.template.width = new Percent(100);
        series1.columns.template.tooltipText = "{valueY}";

        RadarColumnSeries series2 = (RadarColumnSeries) chart.series.push(series1.clone());
        series2.name = "Series 2";
        series2.fill = chart.colors.next();
        series2.dataFields.valueY = "value2";

        RadarColumnSeries series3 = (RadarColumnSeries) chart.series.push(series1.clone());
        series3.name = "Series 3";
        series3.fill = chart.colors.next();

        series3.dataFields.valueY = "value3";

        RadarColumnSeries series4 = (RadarColumnSeries) chart.series.push(series1.clone());
        series4.name = "Series 4";
        series4.fill = chart.colors.next();
        series4.dataFields.valueY = "value4";

        chart.seriesContainer.zIndex = -1;

        chart.scrollbarX = new XYChartScrollbar();
        chart.scrollbarX.exportable = false;
        chart.scrollbarY = new XYChartScrollbar();
        chart.scrollbarY.exportable = false;

        chart.cursor = new RadarCursor();
        chart.cursor.xAxis = categoryAxis;
        chart.cursor.fullWidthLineX = true;
        chart.cursor.lineX.strokeOpacity = 0;
        chart.cursor.lineX.fillOpacity = 0.1;
        chart.cursor.lineX.fill = new Color("#000000");
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_12356_none-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

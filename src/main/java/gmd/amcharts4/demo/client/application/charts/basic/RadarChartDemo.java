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

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.RadarChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.series.RadarSeries;
import gwt.material.design.amcore.client.Am4Core;

public class RadarChartDemo implements ChartDemo {

    private RadarChart chart;

    @Override
    public void generate(Widget container) {
        chart = (RadarChart) Am4Core.create(container, Am4Charts.RadarChart);
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "country";
        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        RadarSeries series = (RadarSeries) chart.series.push(new RadarSeries());
        series.dataFields.valueY = "litres";
        series.dataFields.categoryX = "country";
        chart.dataSource.url = "data/basic.json";
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

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
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.constants.Align;
import gwt.material.design.amcore.client.constants.VerticalAlign;
import gwt.material.design.amcore.client.export.ExportMenu;

public class PieChartDemo implements ChartDemo {

    private PieChart pieChart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        pieChart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        PieSeries series = pieChart.series.push(new PieSeries());
        series.dataFields.value = "litres";
        series.dataFields.category = "country";
        series.tooltip.getFillFromObject = false;
        series.tooltip.background.fill = new Color("#fff");
        series.tooltip.label.fill = new Color("#000");
        pieChart.dataSource.url = "data/basic.json";
        // Export feature
        ExportMenu menu = pieChart.exporting.menu = new ExportMenu();
        menu.align = Align.LEFT;
        menu.verticalAlign = VerticalAlign.TOP;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return pieChart;
    }
}

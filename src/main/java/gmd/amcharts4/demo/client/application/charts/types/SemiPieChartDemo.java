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
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;

public class SemiPieChartDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in
        chart.dataSource.url = "data/semi-pie-chart.json";

        chart.radius = new Percent(70);
        chart.innerRadius = new Percent(40);
        chart.startAngle = 180;
        chart.endAngle = 360;

        PieSeries series = chart.series.push(new PieSeries());
        series.dataFields.value = "value";
        series.dataFields.category = "country";

        series.slices.template.cornerRadius = 10;
        series.slices.template.innerCornerRadius = 7;
        series.slices.template.draggable = true;
        series.slices.template.inert = true;
        series.alignLabels = false;

        series.hiddenState.properties.startAngle = 90;
        series.hiddenState.properties.endAngle = 90;

        chart.legend = new Legend();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11070_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

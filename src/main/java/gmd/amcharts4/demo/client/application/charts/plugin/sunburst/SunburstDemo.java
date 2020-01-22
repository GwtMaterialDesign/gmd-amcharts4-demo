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
package gmd.amcharts4.demo.client.application.charts.plugin.sunburst;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amplugin.sunburst.client.chart.Sunburst;
import gwt.material.design.amplugin.sunburst.client.chart.SunburstCharts;
import gwt.material.design.amplugin.sunburst.client.series.SunburstSeries;

public class SunburstDemo implements ChartDemo {

    private Sunburst chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (Sunburst) Am4Core.create(container, SunburstCharts.Sunburst);
        chart.dataSource.url = "data/plugin/sunburst.json";
        chart.padding(0, 0, 0, 0);
        chart.radius = new Percent(98);
        chart.colors.step = 2;
        chart.fontSize = 11;
        chart.innerRadius = new Percent(10);

        // define data fields
        chart.dataFields.value = "value";
        chart.dataFields.name = "name";
        chart.dataFields.children = "children";


        SunburstSeries level0SeriesTemplate = new SunburstSeries();
        level0SeriesTemplate.hiddenInLegend = false;
        chart.seriesTemplates.setKey("0", level0SeriesTemplate);

        // this makes labels to be hidden if they don't fit
        level0SeriesTemplate.labels.template.truncate = true;
        level0SeriesTemplate.labels.template.hideOversized = true;

        /*level0SeriesTemplate.labels.template.adapter.add("rotation", (rotation, object) -> {
            Label target = (Label) object;
            SunburstDataItem dataItem = (SunburstDataItem) target.dataItem;
            target.maxWidth = dataItem.slice.radius - target.dataItem.slice.innerRadius - 10;
            target.maxHeight = Math.abs(target.dataItem.slice.arc * (target.dataItem.slice.innerRadius + target.dataItem.slice.radius) / 2 * am4core.math.RADIANS);

            return rotation;
        });*/

        SunburstSeries level1SeriesTemplate = (SunburstSeries) level0SeriesTemplate.clone();
        chart.seriesTemplates.setKey("1", level1SeriesTemplate);
        level1SeriesTemplate.fillOpacity = 0.75;
        level1SeriesTemplate.hiddenInLegend = true;

        SunburstSeries level2SeriesTemplate = (SunburstSeries) level0SeriesTemplate.clone();
        chart.seriesTemplates.setKey("2", level2SeriesTemplate);
        level2SeriesTemplate.fillOpacity = 0.5;
        level2SeriesTemplate.hiddenInLegend = true;

        chart.legend = new Legend();
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2019/02/demo_13302_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

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
package gmd.amcharts4.demo.client.application.charts.plugin.forcedirected;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amplugin.forcedirected.client.base.ForceDirectedLink;
import gwt.material.design.amplugin.forcedirected.client.base.ForceDirectedNode;
import gwt.material.design.amplugin.forcedirected.client.chart.ForceDirectedTree;
import gwt.material.design.amplugin.forcedirected.client.chart.ForceDirectedTreeCharts;
import gwt.material.design.amplugin.forcedirected.client.properties.ForceDirectedLinkProperties;
import gwt.material.design.amplugin.forcedirected.client.series.ForceDirectedSeries;

public class ForceDirectedDemo implements ChartDemo {

    private ForceDirectedTree chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (ForceDirectedTree) Am4Core.create(container, ForceDirectedTreeCharts.ForceDirectedTree);
        chart.dataSource.url = "data/plugin/force-directed.json";
        ForceDirectedSeries networkSeries = chart.series.push(new ForceDirectedSeries());
        networkSeries.dataFields.linkWith = "linkWith";
        networkSeries.dataFields.name = "name";
        networkSeries.dataFields.id = "name";
        networkSeries.dataFields.value = "value";
        networkSeries.dataFields.children = "children";

        networkSeries.nodes.template.label.text = "{name}";
        networkSeries.fontSize = 8;
        networkSeries.linkWithStrength = 0;

        ForceDirectedNode nodeTemplate = networkSeries.nodes.template;
        nodeTemplate.tooltipText = "{name}";
        nodeTemplate.fillOpacity = 1;
        nodeTemplate.label.hideOversized = true;
        nodeTemplate.label.truncate = true;

        ForceDirectedLink linkTemplate = networkSeries.links.template;
        linkTemplate.strokeWidth = 1;
        SpriteState<ForceDirectedLinkProperties> linkHoverState = linkTemplate.states.create("hover");
        linkHoverState.properties.strokeOpacity = 1;
        linkHoverState.properties.strokeWidth = 2;

        nodeTemplate.events.on("over", event -> {
           /* var dataItem = event.target.dataItem;
            dataItem.childLinks.each(function(link) {
                link.isHover = true;
            })*/
        });

        nodeTemplate.events.on("out", param1 -> {
            /*var dataItem = event.target.dataItem;
            dataItem.childLinks.each(function(link) {
                link.isHover = false;
            })*/
        });
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2019/04/demo_13855_none-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

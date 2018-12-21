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
import gwt.material.design.amcharts.client.ChordDiagram;
import gwt.material.design.amcharts.client.node.ChordNode;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.MouseCursorStyle;

public class ChordDiagramDemo implements ChartDemo {

    private ChordDiagram chart;

    @Override
    public void generate(Widget widget) {
        chart = (ChordDiagram) Am4Core.create(widget, Am4Charts.ChordDiagram);

        chart.dataSource.url = "data/chord-diagram.json";

        chart.dataFields.fromName = "from";
        chart.dataFields.toName = "to";
        chart.dataFields.value = "value";

        // make nodes draggable
        ChordNode nodeTemplate = chart.nodes.template;
        nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
        nodeTemplate.showSystemTooltip = true;
        nodeTemplate.cursorOverStyle = MouseCursorStyle.pointer;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11269_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

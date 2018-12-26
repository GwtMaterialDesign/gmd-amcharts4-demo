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
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.properties.CurvedColumnProperties;
import gwt.material.design.amcharts.client.series.CurvedColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.adapter.Adapter;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;
import gwt.material.design.amcore.client.state.SpriteState;

public class CurvedColumnsDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0;


        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.dataFields.category = "country";
        categoryAxis.renderer.minGridDistance = 40;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        CurvedColumnSeries series = (CurvedColumnSeries) chart.series.push(new CurvedColumnSeries());
        series.dataFields.categoryX = "country";
        series.dataFields.valueY = "value";
        series.tooltipText = "{valueY.value}";
        series.columns.template.strokeOpacity = 0;

        series.columns.template.fillOpacity = 0.75;

        SpriteState<CurvedColumnProperties> hoverState = series.columns.template.states.create("hover");
        hoverState.properties.fillOpacity = 1;
        hoverState.properties.tension = 0.5;

        chart.cursor = new XYCursor();

        // Add distinctive colors for each column using adapter
        Adapter<Color, Sprite> adapter =   series.columns.template.adapter;
        adapter.add("fill", (sprite, target) -> chart.colors.getIndex(target.dataItem.index));

        chart.scrollbarX = new Scrollbar();

        chart.dataSource.url = "data/curved-columns.json";
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11411_none-3.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

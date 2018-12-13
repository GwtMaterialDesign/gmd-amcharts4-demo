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
package gmd.am4charts.demo.client.application.charts.maps;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.maps.geodata.UsaAlersLow;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.legend.HeatLegend;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class UsHeatMap implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        // Set map definition
        chart.geodata = new UsaAlersLow().get();

        // Set projection
        chart.projection = new Miller();

        // Create map polygon series
        MapPolygonSeries polygonSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());
        polygonSeries.dataSource.url = "data/map/us-heatmap.json";

        //Set min/max fill color for each area

        HeatRule<Color> heatRule = new HeatRule<>();
        heatRule.property = "fill";
        heatRule.target = polygonSeries.mapPolygons.template;
        heatRule.min = chart.colors.getIndex(1).brighten(1);
        heatRule.max = chart.colors.getIndex(1).brighten(-0.3);

        polygonSeries.heatRules.push(heatRule);

        // Make map load polygon data (state shapes and names) from GeoJSON
        polygonSeries.useGeodata = true;

        // Set up heat legend
        HeatLegend heatLegend = (HeatLegend) chart.createChild(Am4Maps.HeatLegend);
        heatLegend.series = polygonSeries;
        heatLegend.align = "right";
        heatLegend.width = new Percent(25);
        heatLegend.marginRight = new Percent(4);
        heatLegend.minValue = 0;
        heatLegend.maxValue = 40000000;

        // Set up custom heat map legend labels using axis ranges
        ValueAxisDataItem minRange = heatLegend.valueAxis.axisRanges.create();
        minRange.value = heatLegend.minValue;
        minRange.label.text = "Little";
        ValueAxisDataItem maxRange = heatLegend.valueAxis.axisRanges.create();
        maxRange.value = heatLegend.maxValue;
        maxRange.label.text = "A lot!";

        // Blank out internal heat legend value axis labels
        heatLegend.valueAxis.renderer.labels.template.adapter.add("text", (sprite, spriteAdapter) -> "");

        // Configure series tooltip
        MapPolygon polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.tooltipText = "{name}: {value}";

        // Create hover state and set alternative fill color
        SpriteState<MapPolygonProperties> hs = polygonTemplate.states.create("hover");
        hs.properties.fill = new Color("#3c5bdc");
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/11/demo_120_none-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

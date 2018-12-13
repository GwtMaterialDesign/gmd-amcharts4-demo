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
import gmd.am4charts.demo.client.application.charts.maps.geodata.UsaLow;
import gmd.am4charts.demo.client.application.charts.maps.geodata.WorldLow;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class MultiSeriesMapDemo implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);
        // Set map definition
        chart.geodata = new WorldLow().get();

        // Set projection
        chart.projection = new Miller();

        // Series for World map
        MapPolygonSeries worldSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());
        worldSeries.exclude = new String[]{"AQ"};
        worldSeries.useGeodata = true;

        MapPolygon polygonTemplate = worldSeries.mapPolygons.template;
        polygonTemplate.tooltipText = "{name}";
        polygonTemplate.fill = chart.colors.getIndex(0);

        // Hover state
        SpriteState<MapPolygonProperties> hs = polygonTemplate.states.create("hover");
        hs.properties.fill = new Color("#367B25");

        // Series for United States map
        MapPolygonSeries usaSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());
        usaSeries.geodata = new UsaLow().get();

        MapPolygon polygonTemplate2 = usaSeries.mapPolygons.template;
        polygonTemplate2.tooltipText = "{name}";
        polygonTemplate2.fill = chart.colors.getIndex(1);

        // Hover state
        SpriteState<MapPolygonProperties> hs2 = polygonTemplate.states.create("hover");
        hs2.properties.fill = new Color("#367B25");
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/09/demo_10380_none-1-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

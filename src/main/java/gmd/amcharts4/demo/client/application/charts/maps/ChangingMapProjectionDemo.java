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
package gmd.amcharts4.demo.client.application.charts.maps;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.maps.geodata.WorldLow;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class ChangingMapProjectionDemo implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        // Set map definition
        chart.geodata = new WorldLow().get();

        // Set projection
        chart.projection = new Miller();

        // Create map polygon series
        MapPolygonSeries polygonSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());

        // Exclude Antartica
        polygonSeries.exclude = new String[]{"AQ"};

        // Make map load polygon (like country names) data from GeoJSON
        polygonSeries.useGeodata = true;

        // Configure series
        MapPolygon polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.tooltipText = "{name}";
        polygonTemplate.fill = chart.colors.getIndex(0);

        // Create hover state and set alternative fill color
        SpriteState<MapPolygonProperties> hs = polygonTemplate.states.create("hover");
        hs.properties.fill = chart.colors.getIndex(0).brighten(-0.5);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2016/02/demo_6417_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

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
import gmd.am4charts.demo.client.application.charts.maps.geodata.WorldLow;
import gmd.am4charts.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.series.Series;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amcore.client.ui.Button;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.base.SmallMap;
import gwt.material.design.ammaps.client.base.ZoomControl;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapImageSeries;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class CapitalsMapDemo implements ChartDemo {

    private MapChart chart;
    private MapPolygon lastSelected;
    private String targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        chart.dataSource.url = "data/map/capitals-map.json";

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
        polygonTemplate.fill = chart.colors.getIndex(0).brighten(0.5).saturate(0.2);
        polygonTemplate.strokeOpacity = 0.5;
        polygonTemplate.strokeWidth = 0.5;

        // create capital markers
        MapImageSeries imageSeries = (MapImageSeries) chart.series.push(new MapImageSeries());

        // define template
        Container imageSeriesTemplate = imageSeries.mapImages.template;
        Sprite circle = (Sprite) imageSeriesTemplate.createChild(Am4Core.Sprite);
        circle.scale = 0.6;
        circle.fill = chart.colors.getIndex(3).saturate(0.1).lighten(-0.5);
        circle.path = targetSVG;
        // what about scale...

        // set propertyfields
        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.latitude = "latitude";
        propertyField.longitude = "longitude";
        imageSeriesTemplate.propertyFields = propertyField;

        imageSeriesTemplate.horizontalCenter = "middle";
        imageSeriesTemplate.verticalCenter = "middle";
        imageSeriesTemplate.align = "center";
        imageSeriesTemplate.valign = "middle";
        imageSeriesTemplate.width = 8;
        imageSeriesTemplate.height = 8;
        imageSeriesTemplate.nonScaling = true;
        imageSeriesTemplate.tooltipText = "{title}";
        imageSeriesTemplate.fill = new Color("#000");
        imageSeriesTemplate.background.fillOpacity = 0;
        imageSeriesTemplate.background.fill = "#fff";
        imageSeriesTemplate.setStateOnChildren = true;
        imageSeriesTemplate.states.create("hover");
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/11/demo_122_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

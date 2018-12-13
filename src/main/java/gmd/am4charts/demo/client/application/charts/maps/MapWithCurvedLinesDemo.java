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
import gmd.am4charts.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.series.Series;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapImage;
import gwt.material.design.ammaps.client.base.MapLine;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.base.ZoomControl;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.GeoPoint;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapImageSeries;
import gwt.material.design.ammaps.client.series.MapLineSeries;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

//TODO: Positioning and Map
public class MapWithCurvedLinesDemo implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        chart.dataSource.url = "data/map/map-with-curved-lines.json";

        // Set map definition
        chart.geodata = new WorldLow().get();

        // Set projection
        chart.projection = new Miller();

        // Add zoom control
        chart.zoomControl = new ZoomControl();

        // Set initial zoom
        chart.homeZoomLevel = 2.5;
        chart.homeGeoPoint = GeoPoint.create(51, -23);

        // Create map polygon series
        MapPolygonSeries polygonSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());
        polygonSeries.exclude = new String[]{"AQ"};
        polygonSeries.useGeodata = true;
        polygonSeries.mapPolygons.template.fill = chart.colors.getIndex(0).lighten(0.5);

        // Add images
        MapImageSeries imageSeries = (MapImageSeries) chart.series.push(new MapImageSeries());
        MapImage imageTemplate = imageSeries.mapImages.template;
        imageTemplate.tooltipText = "{title}";
        imageTemplate.nonScaling = true;

        String targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";
        Sprite marker = (Sprite) imageTemplate.createChild(Am4Core.Sprite);
        marker.path = targetSVG;
        marker.horizontalCenter = "middle";
        marker.verticalCenter = "middle";
        marker.fill = chart.colors.getIndex(1).brighten(-0.5);

        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.latitude = "latitude";
        propertyField.longitude = "longitude";
        imageTemplate.propertyFields = propertyField;

        // Add lines
        MapLineSeries lineSeries = (MapLineSeries) chart.series.push(new MapLineSeries());
        MapLine lineTemplate = lineSeries.mapLines.template;
        lineTemplate.nonScalingStroke = true;
        lineTemplate.arrow.nonScaling = true;
        lineTemplate.stroke = chart.colors.getIndex(1).brighten(-0.5);

        lineSeries.dataSource.url = "data/map/map-with-curved-lines-series.json";
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2015/06/demo_5711_none-1-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

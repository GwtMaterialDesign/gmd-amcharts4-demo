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
import gwt.material.design.amcore.client.animation.Animation;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.SpriteAnimationOptions;
import gwt.material.design.amcore.client.ui.Circle;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapArc;
import gwt.material.design.ammaps.client.base.MapImage;
import gwt.material.design.ammaps.client.base.MapLine;
import gwt.material.design.ammaps.client.base.MapLineObject;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.GeoPoint;
import gwt.material.design.ammaps.client.series.MapArcSeries;
import gwt.material.design.ammaps.client.series.MapImageSeries;
import gwt.material.design.ammaps.client.series.MapLineSeries;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class AnimationsAlongLine implements ChartDemo {

    private MapChart chart;
    private int currentLine = 0;
    private double direction = 1;

    @Override
    public void generate(Widget container) {
        // Create map instance
        chart = (MapChart) Am4Core.create(container, Am4Maps.MapChart);
        chart.geodata = new WorldLow().get();
        chart.projection = new Miller();
        chart.homeZoomLevel = 2.5;
        chart.homeGeoPoint = GeoPoint.create(38, -60);

        // Create map polygon series
        MapPolygonSeries polygonSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());
        polygonSeries.useGeodata = true;
        polygonSeries.mapPolygons.template.fill = chart.colors.getIndex(0).lighten(0.5);
        polygonSeries.exclude = new String[]{"AQ"};

        // Add line bullets
        MapImageSeries cities = (MapImageSeries) chart.series.push(new MapImageSeries());
        cities.mapImages.template.nonScaling = true;

        Circle city = (Circle) cities.mapImages.template.createChild(Am4Core.Circle);
        city.radius = 6;
        city.fill = chart.colors.getIndex(0).brighten(-0.2);
        city.strokeWidth = 2;
        city.stroke = new Color("#fff");


        MapImage paris = addCity(GeoPoint.create(48.8567, 2.3510), "Paris", cities);
        MapImage toronto = addCity(GeoPoint.create(43.8163, -79.4287), "Toronto", cities);
        MapImage la = addCity(GeoPoint.create(34.3, -118.15), "Los Angeles", cities);
        MapImage havana = addCity(GeoPoint.create(23, -82), "Havana", cities);

        // Add lines
        MapArcSeries lineSeries = (MapArcSeries) chart.series.push(new MapArcSeries());
        lineSeries.mapLines.template.line.strokeWidth = 2;
        lineSeries.mapLines.template.line.strokeOpacity = 0.5;
        lineSeries.mapLines.template.line.stroke = city.fill;
        lineSeries.mapLines.template.line.nonScalingStroke = true;
        lineSeries.mapLines.template.line.strokeDasharray = "1,1";
        lineSeries.zIndex = 10;

        MapLineSeries shadowLineSeries = (MapLineSeries) chart.series.push(new MapLineSeries());
        shadowLineSeries.mapLines.template.line.strokeOpacity = 0;
        shadowLineSeries.mapLines.template.line.nonScalingStroke = true;
        shadowLineSeries.mapLines.template.shortestDistance = false;
        shadowLineSeries.zIndex = 5;


        addLine(paris, toronto, lineSeries, shadowLineSeries);
        addLine(toronto, la, lineSeries, shadowLineSeries);
        addLine(la, havana, lineSeries, shadowLineSeries);

        // Add plane
        MapLineObject plane = lineSeries.mapLines.getIndex(0).lineObjects.create();
        plane.position = 0;
        plane.width = 48;
        plane.height = 48;


        plane.adapter.add("scale", (object, target) -> 0.5 * (1 - (Math.abs(0.5 - target.position))));

        Sprite planeImage = (Sprite) plane.createChild(Am4Core.Sprite);
        planeImage.scale = 0.08;
        planeImage.horizontalCenter = "middle";
        planeImage.verticalCenter = "middle";
        planeImage.path = "m2,106h28l24,30h72l-44,-133h35l80,132h98c21,0 21,34 0,34l-98,0 -80,134h-35l43,-133h-71l-24,30h-28l15,-47";
        planeImage.fill = chart.colors.getIndex(2).brighten(-0.2);
        planeImage.strokeOpacity = 0;

        MapLineObject shadowPlane = shadowLineSeries.mapLines.getIndex(0).lineObjects.create();
        shadowPlane.position = 0;
        shadowPlane.width = 48;
        shadowPlane.height = 48;

        Sprite shadowPlaneImage = (Sprite) shadowPlane.createChild(Am4Core.Sprite);
        shadowPlaneImage.scale = 0.05;
        shadowPlaneImage.horizontalCenter = "middle";
        shadowPlaneImage.verticalCenter = "middle";
        shadowPlaneImage.path = "m2,106h28l24,30h72l-44,-133h35l80,132h98c21,0 21,34 0,34l-98,0 -80,134h-35l43,-133h-71l-24,30h-28l15,-47";
        shadowPlaneImage.fill = new Color("#000");
        shadowPlaneImage.strokeOpacity = 0;

        shadowPlane.adapter.add("scale", (mapLineObject, target) -> {
            target.opacity = (0.6 - (Math.abs(0.5 - target.position)));
            return 0.5 - 0.3 * (1 - (Math.abs(0.5 - target.position)));
        });

        // Go!
        flyPlane(plane, planeImage, lineSeries, shadowPlane, shadowLineSeries, shadowPlaneImage);
    }

    protected MapArc addLine(MapImage from, MapImage to, MapArcSeries lineSeries, MapLineSeries shadowLineSeries) {
        MapArc line = lineSeries.mapLines.create();
        line.imagesToConnect = new MapImage[]{from, to};
        line.line.controlPointDistance = -0.3;

        MapLine shadowLine = shadowLineSeries.mapLines.create();
        shadowLine.imagesToConnect = new MapImage[]{from, to};

        return line;
    }

    protected MapImage addCity(GeoPoint coords, String title, MapImageSeries cities) {
        MapImage city = cities.mapImages.create();
        city.latitude = coords.latitude;
        city.longitude = coords.longitude;
        city.tooltipText = title;
        return city;
    }

    protected void flyPlane(MapLineObject plane, Sprite planeImage, MapArcSeries lineSeries, MapLineObject shadowPlane, MapLineSeries shadowLineSeries, Sprite shadowPlaneImage) {

        // Get current line to attach plane to
        plane.mapLine = lineSeries.mapLines.getIndex(currentLine);
        plane.parent = lineSeries;
        shadowPlane.mapLine = shadowLineSeries.mapLines.getIndex(currentLine);
        shadowPlane.parent = shadowLineSeries;
        shadowPlaneImage.rotation = planeImage.rotation;

        // Set up animation
        double from;
        double to;
        int numLines = lineSeries.mapLines.length;
        if (direction == 1) {
            from = 0;
            to = 1;
            if (planeImage.rotation != 0) {
                planeImage.animate(new SpriteAnimationOptions[]{new SpriteAnimationOptions<Double>().create(0.0, "rotation")}, 1000).events.on("animationended", (e) -> flyPlane(plane, planeImage, lineSeries, shadowPlane, shadowLineSeries, shadowPlaneImage));
                return;
            }
        } else {
            from = 1;
            to = 0;
            if (planeImage.rotation != 180) {
                planeImage.animate(new SpriteAnimationOptions[]{new SpriteAnimationOptions<Double>().create(180.0, "rotation")}, 1000).events.on("animationended", (e) -> flyPlane(plane, planeImage, lineSeries, shadowPlane, shadowLineSeries, shadowPlaneImage));
                return;
            }
        }

        // Start the animation
        Animation animation = plane.animate(new SpriteAnimationOptions[]{new SpriteAnimationOptions<Double>().create(from, to, "position")}, 5000, Am4Core.ease.sinInOut);
        animation.events.on("animationended", (e) -> flyPlane(plane, planeImage, lineSeries, shadowPlane, shadowLineSeries, shadowPlaneImage));

        shadowPlane.animate(new SpriteAnimationOptions[]{new SpriteAnimationOptions<Double>().create(from, to, "position")}, 5000, Am4Core.ease.sinInOut);

        //TODO: Issue on rotation animation
        // Increment line, or reverse the direction
        currentLine += direction;
        if (currentLine < 0) {
            currentLine = 0;
            direction = 1;
        } else if ((currentLine + 1) > numLines) {
            currentLine = numLines - 1;
            direction = -1;
        }

    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2015/09/demo_5975_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

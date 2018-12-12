package gmd.am4charts.demo.client.application.charts.maps;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.maps.geodata.UsaLow;
import gmd.am4charts.demo.client.application.charts.maps.geodata.WorldLow;
import gmd.am4charts.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.events.EventTarget;
import gwt.material.design.amcore.client.properties.Point;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapImage;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.properties.GeoPoint;
import gwt.material.design.ammaps.client.properties.MapPolygonProperties;
import gwt.material.design.ammaps.client.series.MapImageSeries;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

public class CustomHtmlMapDemo implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        chart.dataSource.url = "custom-html-map.json";

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
        polygonTemplate.fill = chart.colors.getIndex(0).lighten(0.5);

        // Create hover state and set alternative fill color
        SpriteState<MapPolygonProperties> hs = polygonTemplate.states.create("hover");
        hs.properties.fill = chart.colors.getIndex(0);

        // Add image series
        MapImageSeries imageSeries = (MapImageSeries) chart.series.push(new MapImageSeries());

        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.longitude = "longitude";
        propertyField.latitude = "latitude";
        imageSeries.mapImages.template.propertyFields = propertyField;

        // add events to recalculate map position when the map is moved or zoomed
        chart.events.on("mappositionchanged", eventTarget -> updateCustomMarkers(eventTarget, imageSeries));


    }

    private DummyData data = new DummyData();

    // this function will take current images on the map and create HTML elements for them
    private void updateCustomMarkers(EventTarget<MapChart> event, MapImageSeries imageSeries) {

        /*// go through all of the images
        imageSeries.mapImages.each(image -> {
            // check if it has corresponding HTML element


            image.dummyData = data;

            if (image.dummyData != null || ((DummyData) image.dummyData).externalElement != null) {
                // create onex
                ((DummyData) image.dummyData).externalElement = () -> createCustomMarker(image);
            }

            // reposition the element accoridng to coordinates
            Point xy = chart.geoPointToSVG(GeoPoint.create(image.longitude, image.latitude));
            ((DummyData) image.dummyData).externalElement.style.top = xy.y + 'px';
            ((DummyData) image.dummyData).externalElement.style.left = xy.x + 'px';
        });*/

    }

   /* // this function creates and returns a new marker element
    private void createCustomMarker(MapImage image) {

        var chart = image.dataItem.component.chart;

        // create holder
        var holder = document.createElement('div');
        holder.className = 'map-marker';
        holder.title = image.dataItem.dataContext.title;
        holder.style.position = 'absolute';

        // maybe add a link to it?
        if (undefined != image.url) {
            holder.onclick = function() {
                window.location.href = image.url;
            }
            ;
            holder.className += ' map-clickable';
        }

        // create dot
        var dot = document.createElement('div');
        dot.className = 'dot';
        holder.appendChild(dot);

        // create pulse
        var pulse = document.createElement('div');
        pulse.className = 'pulse';
        holder.appendChild(pulse);

        // append the marker to the map container
        chart.svgContainer.htmlElement.appendChild(holder);

        return holder;
    }*/

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/09/demo_10380_none-1-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

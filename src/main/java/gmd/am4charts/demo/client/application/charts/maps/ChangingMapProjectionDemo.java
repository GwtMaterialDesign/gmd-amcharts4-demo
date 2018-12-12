package gmd.am4charts.demo.client.application.charts.maps;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.maps.geodata.WorldLow;
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

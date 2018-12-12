package gmd.am4charts.demo.client.application.charts.maps;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;

public class GroupCountriesMapDemo implements ChartDemo {

    private MapChart chart;

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

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

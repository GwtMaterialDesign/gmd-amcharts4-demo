package gmd.am4charts.demo.client.application.charts;

import com.google.gwt.user.client.ui.Widget;
import gwt.material.design.amcharts.client.Chart;

public interface ChartDemo {

    void generate(Widget container);

    String getImage();

    Chart getChart();
}

package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.language.Locale;
import gwt.material.design.amcore.client.ui.Circle;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

public class SVGRendererDemo implements ChartDemo {

    @Override
    public void generate(Widget widget) {
        // Colors & SVG Renderer
        Container container = (Container) Am4Core.create(widget, Am4Core.Container);
        container.width = new Percent(100);
        container.height = new Percent(100);

        Circle circle = (Circle) container.createChild(Am4Core.Circle);
        circle.fill = new Color("#A1C084");
        circle.height = 200;
        circle.width = new Percent(100);
        circle.align = "center";
    }

    @Override
    public String getSourceCode() {
        return null;
    }

    @Override
    public Chart getChart() {
        return null;
    }
}

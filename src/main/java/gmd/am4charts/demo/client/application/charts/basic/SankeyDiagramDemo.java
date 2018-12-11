package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.SankeyDiagram;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.ContainerLayout;
import gwt.material.design.amcore.client.ui.Rectangle;

public class SankeyDiagramDemo implements ChartDemo {

    private SankeyDiagram sankeyDiagram;

    @Override
    public void generate(Widget widget) {
         sankeyDiagram = (SankeyDiagram) Am4Core.create(widget, Am4Charts.SankeyDiagram);
        sankeyDiagram.dataFields.fromName = "from";
        sankeyDiagram.dataFields.toName = "to";
        sankeyDiagram.dataFields.value = "value";
        sankeyDiagram.dataSource.url = "data/sankey-diagram.json";
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return sankeyDiagram;
    }
}

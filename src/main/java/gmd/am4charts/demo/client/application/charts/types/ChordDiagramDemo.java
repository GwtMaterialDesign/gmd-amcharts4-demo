package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.ChordDiagram;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.node.ChordNode;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.MouseCursorStyle;

public class ChordDiagramDemo implements ChartDemo {

    private ChordDiagram chart;

    @Override
    public void generate(Widget widget) {
        chart = (ChordDiagram) Am4Core.create(widget, Am4Charts.ChordDiagram);

        chart.dataSource.url = "data/chord-diagram.json";

        chart.dataFields.fromName = "from";
        chart.dataFields.toName = "to";
        chart.dataFields.value = "value";

        // make nodes draggable
        ChordNode nodeTemplate = chart.nodes.template;
        nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
        nodeTemplate.showSystemTooltip = true;
        nodeTemplate.cursorOverStyle = MouseCursorStyle.pointer;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11269_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

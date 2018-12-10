package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.ChordDiagram;
import gwt.material.design.amcharts.client.TreeMap;
import gwt.material.design.amcharts.client.bullet.Bullet;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.node.ChordNode;
import gwt.material.design.amcharts.client.series.TreeMapSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.base.MouseCursorStyle;
import gwt.material.design.amcore.client.color.Color;

//TODO: Event dispatcher is disposed
public class SimpleTreemap implements ChartDemo {

    private TreeMap chart;

    @Override
    public void generate(Widget widget) {
        chart = (TreeMap) Am4Core.create(widget, Am4Charts.TreeMap);
        //chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

        chart.dataSource.url = "data/treemap.json";
        /*chart.colors.step = 2;*/

        // define data fields
        chart.dataFields.value = "value";
        chart.dataFields.name = "name";
        chart.dataFields.children = "children";

        chart.zoomable = false;
        //Color bgColor = new InterfaceColorSet().getFor("background");

        // level 0 series template
        TreeMapSeries level0SeriesTemplate = chart.seriesTemplates.create("0");
        Column level0ColumnTemplate = level0SeriesTemplate.columns.template;

        level0ColumnTemplate.column.cornerRadius(10, 10, 10, 10);
        level0ColumnTemplate.fillOpacity = 0;
        level0ColumnTemplate.strokeWidth = 4;
        level0ColumnTemplate.strokeOpacity = 0;

        // level 1 series template
        TreeMapSeries level1SeriesTemplate = chart.seriesTemplates.create("1");
        Column level1ColumnTemplate = level1SeriesTemplate.columns.template;

        level1SeriesTemplate.tooltip.animationDuration = 0;
        level1SeriesTemplate.strokeOpacity = 1;

        level1ColumnTemplate.column.cornerRadius(10, 10, 10, 10);
        level1ColumnTemplate.fillOpacity = 1;
        level1ColumnTemplate.strokeWidth = 4;
        //level1ColumnTemplate.stroke = bgColor;

        Bullet bullet1 = level1SeriesTemplate.bullets.push(new LabelBullet());
        bullet1.locationY = 0.5;
        bullet1.locationX = 0.5;
        bullet1.label.text = "{name}";
        bullet1.label.fill = new Color("#ffffff");

        chart.maxLevels = 2;
    }

    @Override
    public String getTitle() {
        return "Simple Tree Map";
    }

    @Override
    public String getSourceCode() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

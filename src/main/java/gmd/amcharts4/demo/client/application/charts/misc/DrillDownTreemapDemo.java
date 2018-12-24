package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.TreeMap;
import gwt.material.design.amcharts.client.base.NavigationBar;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.properties.ColumnProperties;
import gwt.material.design.amcharts.client.series.TreeMapSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amcore.client.ui.Image;

public class DrillDownTreemapDemo implements ChartDemo {

    private TreeMap chart;

    @Override
    public void generate(Widget container) {
        // create chart
        chart = (TreeMap) Am4Core.create("chartdiv", Am4Charts.TreeMap);
        chart.hiddenState.properties.opacity = 0; // this makes initial fade in effect

        // only one level visible initially
        chart.maxLevels = 1;
        // define data fields
        chart.dataFields.value = "count";
        chart.dataFields.name = "name";
        chart.dataFields.children = "children";
        chart.homeText = "US Car Sales 2017";

        // enable navigation
        chart.navigationBar = new NavigationBar();

        // level 0 series template
        TreeMapSeries level0SeriesTemplate = chart.seriesTemplates.create("0");
        level0SeriesTemplate.strokeWidth = 2;

        // by default only current level series bullets are visible, but as we need brand bullets to be visible all the time, we modify it's hidden state
        //TODO:
        //level0SeriesTemplate.bulletsContainer.hiddenState.properties.opacity = 1;
        //level0SeriesTemplate.bulletsContainer.hiddenState.properties.visible = true;
        // create hover state
        Column columnTemplate = level0SeriesTemplate.columns.template;
        SpriteState<ColumnProperties> hoverState = columnTemplate.states.create("hover");

        // darken
        /*hoverState.adapter.add("fill", function (fill, target) {
            if (fill instanceof am4core.Color) {
                return am4core.color(am4core.colors.brighten(fill.rgb, -0.2));
            }
            return fill;
        })*/

        // add logo
        Image image = (Image) columnTemplate.createChild(Am4Core.Image);
        image.opacity = 0.15;
        image.align = "center";
        image.valign = "middle";
        image.width = new Percent(80);
        image.height = new Percent(80);

        // add adapter for href to load correct image
        /*image.adapter.add("href", (sprite, target) -> {
            DataItem dataItem = target.parent.dataItem;
            if (dataItem != null) {
                return "https://www.amcharts.com/lib/images/logos/" + dataItem.treeMapDataItem.name.toLowerCase() + ".png";
            }
        });*/

        // level1 series template
        TreeMapSeries level1SeriesTemplate = chart.seriesTemplates.create("1");
        level1SeriesTemplate.columns.template.fillOpacity = 0;

        LabelBullet bullet1 = (LabelBullet) level1SeriesTemplate.bullets.push(new LabelBullet());
        bullet1.locationX = 0.5;
        bullet1.locationY = 0.5;
        bullet1.label.text = "{name}";
        bullet1.label.fill = new Color("#ffffff");

        // level2 series template
        TreeMapSeries level2SeriesTemplate = chart.seriesTemplates.create("2");
        level2SeriesTemplate.columns.template.fillOpacity = 0;

        LabelBullet bullet2 = (LabelBullet) level2SeriesTemplate.bullets.push(new LabelBullet());
        bullet2.locationX = 0.5;
        bullet2.locationY = 0.5;
        bullet2.label.text = "{name}";
        bullet2.label.fill = new Color("#ffffff");

        /*chart.data = processData(data);*/
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_798_none-3-1024x646.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

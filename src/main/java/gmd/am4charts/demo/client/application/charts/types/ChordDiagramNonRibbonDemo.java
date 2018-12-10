package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.ChordDiagram;
import gwt.material.design.amcharts.client.base.FlowDiagramLink;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.node.ChordNode;
import gwt.material.design.amcharts.client.properties.CircleBulletProperties;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.MouseCursorStyle;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.SpriteProperties;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amcore.client.ui.Label;

//
public class ChordDiagramNonRibbonDemo implements ChartDemo {

    private ChordDiagram chart;

    @Override
    public void generate(Widget widget) {
        chart = (ChordDiagram) Am4Core.create(widget, Am4Charts.ChordDiagram);
        chart.hiddenState.properties.opacity = 0;

        chart.dataFields.fromName = "from";
        chart.dataFields.toName = "to";
        chart.dataFields.value = "value";
        chart.nonRibbon = true;
        chart.sortBy = "name";
        chart.dataSource.url = "data/chord-diagram-non-ribbon.json";
        ChordNode nodeTemplate = chart.nodes.template;
        nodeTemplate.fill = chart.colors.getIndex(0);
        nodeTemplate.fillOpacity = 0.4;
        nodeTemplate.slice.disabled = true;
        nodeTemplate.setStateOnChildren = true;
        nodeTemplate.label.disabled = true;
        nodeTemplate.togglable = false;

        nodeTemplate.readerTitle = "Drag to rearrange";
        nodeTemplate.showSystemTooltip = true;

        SpriteState<SpriteProperties> hoverState = nodeTemplate.states.create("hover");
        hoverState.properties.fillOpacity = 1;

        FlowDiagramLink linkTemplate = chart.links.template;
        linkTemplate.opacity = 0.1;
        linkTemplate.stroke = chart.colors.getIndex(0);
        linkTemplate.defaultState.properties.opacity = 0.1;
        linkTemplate.tooltipText = "";

        SpriteState<SpriteProperties> linkHoverState = linkTemplate.states.create("hover");
        linkHoverState.properties.opacity = 1;

        nodeTemplate.events.on("over", event -> {
            ChordNode node = event.target;
            node.outgoingDataItems.each(dataItem -> {
                dataItem.link.isHover = true;
            });
        });

        nodeTemplate.events.on("out", event -> {
            ChordNode node = event.target;
            node.outgoingDataItems.each(dataItem -> {
                dataItem.link.isHover = false;
            });
        });

        nodeTemplate.cursorOverStyle = MouseCursorStyle.grab;

        nodeTemplate.cursorDownStyle = MouseCursorStyle.grabbing;

        CircleBullet circleBullet = (CircleBullet) nodeTemplate.createChild(Am4Charts.CircleBullet);
        circleBullet.setStateOnChildren = true;
        circleBullet.circle.radius = 15;

        SpriteState<CircleBulletProperties> circleHoverState = circleBullet.states.create("hover");
        circleHoverState.properties.scale = 1.5;

        // we create a separate label as node.label is positioned differently and doesn't fit perfectly for one-letter labels
        Label label = (Label) circleBullet.createChild(Am4Core.Label);
        label.text = "{fromName}";
        label.horizontalCenter = "middle";
        label.verticalCenter = "middle";

        SpriteState<SpriteProperties> labelHoverState = label.states.create("hover");
        labelHoverState.properties.fill = new Color("#ffffff");
    }

    @Override
    public String getTitle() {
        return "Chord Diagram Non-Ribbon";
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

package gmd.am4charts.demo.client.application.renderer;

import com.google.gwt.dom.client.Style;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;

import java.util.List;

public class ChartRenderer {

    public static void render(MaterialWidget container, List<ChartDemo> demos) {
        container.clear();
        for (ChartDemo demo : demos) {
            MaterialColumn col = new MaterialColumn();
            col.setPadding(20);
            col.setGrid("s12 m12 l6");

            MaterialLabel label = new MaterialLabel(demo.getTitle());
            label.setFontSize("1.6em");
            label.setFontWeight(Style.FontWeight.BOLD);
            col.add(label);

            MaterialLabel description = new MaterialLabel("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam");
            col.add(description);

            MaterialPanel chartPanel = new MaterialPanel();
            chartPanel.setHeight("400px");
            col.add(chartPanel);
            demo.generate(chartPanel);

            container.add(col);
        }
    }
}

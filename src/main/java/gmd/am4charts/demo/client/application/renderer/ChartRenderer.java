package gmd.am4charts.demo.client.application.renderer;

import com.google.gwt.dom.client.Style;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

import java.util.List;

public class ChartRenderer {

    static String githubUrl = "https://github.com/GwtMaterialDesign/gmd-am4charts-demo/tree/master/src/main/java/";

    public static void render(MaterialWidget container, List<ChartDemo> demos) {
        container.clear();
        for (ChartDemo demo : demos) {
            MaterialColumn col = new MaterialColumn();
            col.setPadding(20);
            col.setGrid("s12 m12 l6");

            MaterialLabel label = new MaterialLabel(cleanupTitle(demo.getClass().getSimpleName()));
            label.setFontSize("1.6em");
            label.setFontWeight(Style.FontWeight.BOLD);
            col.add(label);

            MaterialLink source = new MaterialLink("SOURCE");
            source.setIconType(IconType.CODE);
            source.setFontWeight(Style.FontWeight.BOLD);
            source.setTextColor(Color.INDIGO);
            source.setIconColor(Color.INDIGO);
            source.setTarget("_blank");
            source.setHref(githubUrl + demo.getClass().getName().replace(".", "/") + ".java");
            col.add(source);

            MaterialPanel chartPanel = new MaterialPanel();
            chartPanel.setHeight("400px");
            col.add(chartPanel);
            demo.generate(chartPanel);

            container.add(col);
        }
    }

    static String cleanupTitle(String camelName) {
        return camelName.replaceAll("([A-Z][a-z]+)", " $1") // Words beginning with UC
                .replaceAll("([A-Z][A-Z]+)", " $1") // "Words" of only UC
                .replaceAll("([^A-Za-z ]+)", " $1") // "Words" of non-letters
                .replace("Demo", "")
                .trim();
    }
}

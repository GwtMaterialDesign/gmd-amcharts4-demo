package gmd.am4charts.demo.client.application.renderer;

import com.google.gwt.dom.client.Style;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.widget.DashboardCard;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

import java.util.List;

public class ChartRenderer {

    public static void render(MaterialWidget container, List<ChartDemo> demos) {
        container.clear();

        int i = 0;
        for (ChartDemo demo : demos) {
            container.add(new DashboardCard(i, demo.getClass().getSimpleName(), demo));
            i++;
        }
    }


}

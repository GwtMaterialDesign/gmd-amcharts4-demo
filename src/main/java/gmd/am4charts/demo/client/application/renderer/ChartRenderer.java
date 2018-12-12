package gmd.am4charts.demo.client.application.renderer;

import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.widget.DashboardCard;
import gwt.material.design.client.base.MaterialWidget;

import java.util.List;

public class ChartRenderer {

    public static void render(String type, MaterialWidget container, List<ChartDemo> demos) {
        container.clear();

        int i = 0;
        for (ChartDemo demo : demos) {
            container.add(new DashboardCard(type, i, demo));
            i++;
        }
    }


}

package gmd.am4charts.demo.client.application.widget.chart;

import gwt.material.design.amcore.client.theme.ChartTheme;
import gwt.material.design.client.ui.MaterialPanel;

public class ThemeIcon extends MaterialPanel {

    static String THEME_ICON = "theme-icon";
    private ChartTheme chartTheme;
    private String name;

    public ThemeIcon(ChartTheme chartTheme, String name, String themeColor) {
        addStyleName(THEME_ICON);
        setChartTheme(chartTheme);
        setName(name);
        setThemeColor(themeColor);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        setTooltip(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void setThemeColor(String color) {
        getElement().getStyle().setProperty("backgroundImage", color);
    }

    public String getName() {
        return name;
    }

    public ChartTheme getChartTheme() {
        return chartTheme;
    }

    public void setChartTheme(ChartTheme chartTheme) {
        this.chartTheme = chartTheme;
    }
}

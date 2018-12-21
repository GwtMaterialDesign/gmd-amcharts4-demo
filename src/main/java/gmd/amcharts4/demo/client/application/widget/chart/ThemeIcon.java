/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gmd.amcharts4.demo.client.application.widget.chart;

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

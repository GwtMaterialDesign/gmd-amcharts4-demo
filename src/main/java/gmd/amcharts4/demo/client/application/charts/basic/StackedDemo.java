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
package gmd.amcharts4.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.ui.RoundedRectangle;
import gwt.material.design.client.ui.MaterialToast;

public class StackedDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);

        chart.dataSource.url = "data/stacked.json";

        chart.cursor = new XYCursor();
        chart.legend = new Legend();
        chart.legend.useDefaultMarker = true;

        RoundedRectangle marker = (RoundedRectangle) chart.legend.markers.template.children.getIndex(0);
        marker.cornerRadius(12, 12, 12, 12);
        marker.strokeWidth = 2;
        marker.strokeOpacity = 1;
        marker.width = 80;
        marker.height = 80;
        marker.stroke = new Color("#ccc");

        chart.legend.itemContainers.template.events.on("hit", param1 -> {
            MaterialToast.fireToast("Clicked on" + param1);
        });

        //Custom Marker (SVG)
        /* Remove square from marker template */
        /*Container marker = chart.legend.markers.template;
        marker.disposeChildren();
        marker.width = 40;
        marker.height = 40;*/

        /* Add custom image instead */
        /*Image dollar = (Image) marker.createChild(Am4Core.Image);
        dollar.width = 40;
        dollar.height = 40;
        dollar.verticalCenter = VerticalCenter.TOP;
        dollar.horizontalCenter = HorizontalCenter.LEFT;
        dollar.href = "data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjxzdmcgaGVpZ2h0PSIyMXB4IiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAyMSAyMSIgd2lkdGg9IjIxcHgiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjx0aXRsZS8+PGRlc2MvPjxkZWZzLz48ZyBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiIGlkPSJCdXNpbmVzcy1JY29uIiBzdHJva2U9Im5vbmUiIHN0cm9rZS13aWR0aD0iMSIgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoLTI3Ny4wMDAwMDAsIC0yMjcuMDAwMDAwKSI+PGcgaWQ9IkNvaW4iIHRyYW5zZm9ybT0idHJhbnNsYXRlKDI3NS4wMDAwMDAsIDIyNS4wMDAwMDApIj48cmVjdCBoZWlnaHQ9IjI1IiBpZD0iUmVjdGFuZ2xlLTQ5IiB3aWR0aD0iMjUiIHg9IjAiIHk9IjAiLz48ZyBpZD0iR3JvdXAtMzQiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDIuMDAwMDAwLCAyLjAwMDAwMCkiPjxwYXRoIGQ9Ik0xMC41LDIxIEM0LjcwMTAxMDEzLDIxIDAsMTYuMjk4OTg5OSAwLDEwLjUgQzAsNC43MDEwMTAxMyA0LjcwMTAxMDEzLDAgMTAuNSwwIEMxNi4yOTg5ODk5LDAgMjEsNC43MDEwMTAxMyAyMSwxMC41IEMyMSwxNi4yOTg5ODk5IDE2LjI5ODk4OTksMjEgMTAuNSwyMSBaIE0xMC41LDE4IEMxNC42NDIxMzU2LDE4IDE4LDE0LjY0MjEzNTYgMTgsMTAuNSBDMTgsNi4zNTc4NjQzOCAxNC42NDIxMzU2LDMgMTAuNSwzIEM2LjM1Nzg2NDM4LDMgMyw2LjM1Nzg2NDM4IDMsMTAuNSBDMywxNC42NDIxMzU2IDYuMzU3ODY0MzgsMTggMTAuNSwxOCBaIiBmaWxsPSIjM0I1QUZCIiBmaWxsLXJ1bGU9Im5vbnplcm8iIGlkPSJDb21iaW5lZC1TaGFwZSIvPjxwYXRoIGQ9Ik0xMC41LDE3IEM2LjkxMDE0OTEzLDE3IDQsMTQuMDg5ODUwOSA0LDEwLjUgQzQsNi45MTAxNDkxMyA2LjkxMDE0OTEzLDQgMTAuNSw0IEMxNC4wODk4NTA5LDQgMTcsNi45MTAxNDkxMyAxNywxMC41IEMxNywxNC4wODk4NTA5IDE0LjA4OTg1MDksMTcgMTAuNSwxNyBaIE0xMSw4IEwxMSw3LjUgQzExLDcuMjIzODU3NjMgMTAuNzc2MTQyNCw3IDEwLjUsNyBDMTAuMjIzODU3Niw3IDEwLDcuMjIzODU3NjMgMTAsNy41IEwxMCw4IEw5LjUxMzk0NTIsOCBDOC42ODU1MTgwNyw4IDguMDEzOTQ1Miw4LjY3MTU3Mjg4IDguMDEzOTQ1Miw5LjUgTDguMDEzOTQ1MiwxMC4wMDA3NTY3IEM4LjAxMzk0NTIsMTAuODI5MTgzOCA4LjY4NTUxODA3LDExLjUwMDc1NjcgOS41MTM5NDUyLDExLjUwMDc1NjcgTDExLjUwMDAwNDksMTEuNTAwNzU2NyBDMTEuNzc2MTQ3MywxMS41MDA3NTY3IDEyLjAwMDAwNDksMTEuNzI0NjE0MyAxMi4wMDAwMDQ5LDEyLjAwMDc1NjcgTDEyLjAwMDAwNDksMTIuNSBDMTIuMDAwMDA0OSwxMi43NzYxNDI0IDExLjc3NjE0NzMsMTMgMTEuNTAwMDA0OSwxMyBMOS4wMTIyNTUyNSwxMyBDOC45OTE2NzA0LDEyLjc0OTgzOSA4Ljc4NTk3ODE2LDEyLjU0OTY2OCA4LjUyOTE0NDQyLDEyLjU0MTg1NyBDOC4yNTMxMjk2NiwxMi41MzM0NjI3IDguMDIyNTcwNTcsMTIuNzUwNDEyIDguMDE0MTc2MjcsMTMuMDI2NDI2OCBMOC4wMDAyMzU5NywxMy40ODQ4MDA4IEM3Ljk5MTY2MDY0LDEzLjc2Njc2ODIgOC4yMTc5MDcwOCwxNCA4LjUwMDAwNDksMTQgTDEwLDE0IEwxMCwxNC40OTEyNDE1IEMxMCwxNC43NjczODM4IDEwLjIyMzg1NzYsMTQuOTkxMjQxNSAxMC41LDE0Ljk5MTI0MTUgQzEwLjc3NjE0MjQsMTQuOTkxMjQxNSAxMSwxNC43NjczODM4IDExLDE0LjQ5MTI0MTUgTDExLDE0IEwxMS41MDAwMDQ5LDE0IEMxMi4zMjg0MzIsMTQgMTMuMDAwMDA0OSwxMy4zMjg0MjcxIDEzLjAwMDAwNDksMTIuNSBMMTMuMDAwMDA0OSwxMi4wMDA3NTY3IEMxMy4wMDAwMDQ5LDExLjE3MjMyOTYgMTIuMzI4NDMyLDEwLjUwMDc1NjcgMTEuNTAwMDA0OSwxMC41MDA3NTY3IEw5LjUxMzk0NTIsMTAuNTAwNzU2NyBDOS4yMzc4MDI4MiwxMC41MDA3NTY3IDkuMDEzOTQ1MiwxMC4yNzY4OTkxIDkuMDEzOTQ1MiwxMC4wMDA3NTY3IEw5LjAxMzk0NTIsOS41IEM5LjAxMzk0NTIsOS4yMjM4NTc2MyA5LjIzNzgwMjgyLDkgOS41MTM5NDUyLDkgTDExLjk2Nzk1NTksOSBDMTIuMDIyMjIwMyw5LjIxNzQ0NzMzIDEyLjIxODg0ODEsOS4zNzg1NDAwNCAxMi40NTMxMDEsOS4zNzg1NDAwNCBDMTIuNzI5MjQzNCw5LjM3ODU0MDA0IDEyLjk1MzEwMSw5LjE1NDY4MjQxIDEyLjk1MzEwMSw4Ljg3ODU0MDA0IEwxMi45NTMxMDEsOC41IEMxMi45NTMxMDEsOC4yMjM4NTc2MyAxMi43MjkyNDM0LDggMTIuNDUzMTAxLDggTDExLDggWiIgZmlsbD0iIzQ0RDVFOSIgaWQ9IkNvbWJpbmVkLVNoYXBlIi8+PC9nPjwvZz48L2c+PC9zdmc+";
        */

        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.renderer.minWidth = 35;

        ColumnSeries series1 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series1.columns.template.width = new Percent(80);
        series1.columns.template.tooltipText = "{name}: {valueY.value}";
        series1.name = "Series 1";
        series1.dataFields.categoryX = "category";
        series1.dataFields.valueY = "value1";
        series1.stacked = true;

        ColumnSeries series2 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series2.columns.template.width = new Percent(80);
        series2.columns.template.tooltipText = "{name}: {valueY.value}";
        series2.name = "Series 2";
        series2.dataFields.categoryX = "category";
        series2.dataFields.valueY = "value2";
        series2.stacked = true;

        ColumnSeries series3 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series3.columns.template.width = new Percent(80);
        series3.columns.template.tooltipText = "{name}: {valueY.value}";
        series3.name = "Series 3";
        series3.dataFields.categoryX = "category";
        series3.dataFields.valueY = "value3";
        series3.stacked = true;

        ColumnSeries series4 = (ColumnSeries) chart.series.push(new ColumnSeries());
        series4.columns.template.width = new Percent(80);
        series4.columns.template.tooltipText = "{name}: {valueY.value}";
        series4.name = "Series 4";
        series4.dataFields.categoryX = "category";
        series4.dataFields.valueY = "value4";
        series4.stacked = true;
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

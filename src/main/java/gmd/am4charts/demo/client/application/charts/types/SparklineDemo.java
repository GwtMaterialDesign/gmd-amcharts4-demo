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
package gmd.am4charts.demo.client.application.charts.types;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcharts.client.tick.ClockHand;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.ColorSet;

public class SparklineDemo implements ChartDemo {

    private Container container;

    @Override
    public void generate(Widget widget) {
        // Create chart instance
        container = (Container) Am4Core.create(widget, Am4Core.Container);
        container.layout = "grid";
        container.fixedWidthGrid = false;
        container.width = new Percent(100);
        container.height = new Percent(100);

        // Color set
        ColorSet colors = new ColorSet();
        createLine("Sample 1", colors.getIndex(0));
        createColumn("Sample 2", colors.getIndex(3));
        createPie(colors.getIndex(8));
    }

    private XYChart createLine(String title, Color color) {

        XYChart chart = (XYChart) container.createChild(Am4Charts.XYChart);
        chart.width = new Percent(45);
        chart.height = 70;

        chart.dataSource.url = "data/sparkline/sparkline-1.json";

        chart.titles.template.fontSize = 10;
        chart.titles.template.textAlign = "left";
        chart.titles.template.isMeasured = false;
        chart.titles.create().text = title;

        chart.padding(20, 5, 2, 5);

        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.grid.template.disabled = true;
        dateAxis.renderer.labels.template.disabled = true;
        dateAxis.startLocation = 0.5;
        dateAxis.endLocation = 0.7;
        dateAxis.cursorTooltipEnabled = false;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.renderer.grid.template.disabled = true;
        valueAxis.renderer.baseGrid.disabled = true;
        valueAxis.renderer.labels.template.disabled = true;
        valueAxis.cursorTooltipEnabled = false;

        chart.cursor = new XYCursor();
        chart.cursor.lineY.disabled = true;
        chart.cursor.behavior = "none";

        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.tooltipText = "{date}: [bold]{value}";
        series.dataFields.dateX = "date";
        series.dataFields.valueY = "value";
        series.tensionX = 0.8;
        series.strokeWidth = 2;
        series.stroke = color;

        // render data points as bullets
        CircleBullet bullet = (CircleBullet) series.bullets.push(new CircleBullet());
        bullet.circle.opacity = 0;
        bullet.circle.fill = color;

        MyCustomPropertyField propertyFields = new MyCustomPropertyField();
        propertyFields.opacity = "opacity";
        bullet.circle.propertyFields = propertyFields;

        bullet.circle.radius = 3;

        return chart;
    }

    private XYChart createColumn(String title, Color color) {

        XYChart chart = (XYChart) container.createChild(Am4Charts.XYChart);
        chart.width = new Percent(45);
        chart.height = 70;

        chart.dataSource.url = "data/sparkline/sparkline-2.json";

        chart.titles.template.fontSize = 10;
        chart.titles.template.textAlign = "left";
        chart.titles.template.isMeasured = false;
        chart.titles.create().text = title;

        chart.padding(20, 5, 2, 5);

        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.grid.template.disabled = true;
        dateAxis.renderer.labels.template.disabled = true;
        dateAxis.cursorTooltipEnabled = false;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.renderer.grid.template.disabled = true;
        valueAxis.renderer.baseGrid.disabled = true;
        valueAxis.renderer.labels.template.disabled = true;
        valueAxis.cursorTooltipEnabled = false;

        chart.cursor = new XYCursor();
        chart.cursor.lineY.disabled = true;

        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.tooltipText = "{date}: [bold]{value}";
        series.dataFields.dateX = "date";
        series.dataFields.valueY = "value";
        series.strokeWidth = 0;
        series.fillOpacity = 0.5;

        MyCustomPropertyField propertyFields = new MyCustomPropertyField();
        propertyFields.fillOpacity = "opacity";
        series.columns.template.propertyFields = propertyFields;
        series.columns.template.fill = color;

        return chart;
    }


    private PieChart createPie(Color color) {

        PieChart chart = (PieChart) container.createChild(Am4Charts.PieChart);
        chart.width = new Percent(10);
        chart.height = 70;
        chart.padding(20, 0, 2, 0);

        chart.dataSource.url = "data/sparkline/sparkline-3.json";

        // Add and configure Series
        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "value";
        pieSeries.dataFields.category = "category";
        pieSeries.labels.template.disabled = true;
        pieSeries.ticks.template.disabled = true;
        pieSeries.slices.template.fill = color;

        pieSeries.slices.template.adapter.add("fill", (fill, target) -> {
            return fill.lighten(0.1 * target.dataItem.index);
        });
        pieSeries.slices.template.stroke = new Color("#fff");

        chart.chartContainer.minHeight = 40;
        chart.chartContainer.minWidth = 40;

        return chart;
    }

    private void randomValue(ClockHand hand) {
        hand.showValue(Math.random() * 100, 1000, Am4Core.ease.cubicOut);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_1462_none.png";
    }

    @Override
    public Chart getChart() {
        return null;
    }
}

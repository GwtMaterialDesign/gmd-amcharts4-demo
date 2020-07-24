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
package gmd.amcharts4.demo.client.application.charts.plugin.regression;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.ui.Label;
import gwt.material.design.amplugin.regression.client.Regression;
import gwt.material.design.amplugin.regression.client.RegressionMethod;

public class RegressionDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.dataSource.url = "data/plugin/regression.json";

        // Create axes
        DateAxis categoryAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.minGridDistance = 30;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueY = "value";
        series.dataFields.dateX = "date";
        series.name = "Revenue";
        series.tooltipText = "{dateX}: [b]{valueY}[/]";

        LineSeries regseries = (LineSeries) chart.series.push(new LineSeries());
        regseries.dataFields.valueY = "value";
        regseries.dataFields.dateX = "date";
        regseries.strokeWidth = 2;
        regseries.name = "Linear Regression";

        regseries.plugins.push(new Regression());

        LineSeries regseries2 = (LineSeries) chart.series.push(new LineSeries());
        regseries2.dataFields.valueY = "value";
        regseries2.dataFields.dateX = "date";
        regseries2.strokeWidth = 2;
        regseries2.name = "Polynomial Regression";
        regseries2.tensionX = 0.8;
        regseries2.tensionY = 0.8;

        Regression reg2 = (Regression) regseries2.plugins.push(new Regression());
        reg2.method = RegressionMethod.POLYNOMIAL;

        Label label = (Label) chart.tooltipContainer.createChild(Am4Core.Label);
        label.x = new Percent(100);
        label.marginRight = 50;
        label.y = 30;
        label.align = "right";
        label.toFront();

        /*reg2.events.on("processed", function(ev) {
            label.text = "[bold]equation:[/] " + ev.target.result.string.replace(/\^2/, "²") + "\n[bold]R²:[/] " + ev.target.result.r2;
        });*/

        chart.legend = new Legend();
        chart.cursor = new XYCursor();
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

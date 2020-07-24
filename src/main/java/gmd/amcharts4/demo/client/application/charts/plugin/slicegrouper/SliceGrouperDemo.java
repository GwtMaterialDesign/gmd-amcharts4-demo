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
package gmd.amcharts4.demo.client.application.charts.plugin.slicegrouper;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.PieChart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.legend.Legend;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcharts.client.series.PieSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.ui.Label;
import gwt.material.design.amplugin.regression.client.Regression;
import gwt.material.design.amplugin.regression.client.RegressionMethod;
import gwt.material.design.amplugin.slicegrouper.client.SliceGrouper;

public class SliceGrouperDemo implements ChartDemo {

    private PieChart chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (PieChart) Am4Core.create(container, Am4Charts.PieChart);
        chart.dataSource.url = "data/plugin/slice-grouper.json";

        // Add and configure Series
        PieSeries pieSeries = chart.series.push(new PieSeries());
        pieSeries.dataFields.value = "value";
        pieSeries.dataFields.category = "category";

        chart.legend = new Legend();

        SliceGrouper grouper = (SliceGrouper) pieSeries.plugins.push(new SliceGrouper());
        grouper.clickBehavior = "break";
        grouper.threshold = 10;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/docs/v4/wp-content/uploads/sites/2/2019/04/2019-04-08_23-14-30.gif";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

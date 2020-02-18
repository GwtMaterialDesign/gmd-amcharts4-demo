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

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.AxisLabel;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.Bullet;
import gwt.material.design.amcharts.client.dataitem.ValueAxisDataItem;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.adapter.Adapter;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.LinearGradient;
import gwt.material.design.amcore.client.constants.ContainerLayout;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.ui.Line;

import java.util.ArrayList;
import java.util.List;

public class BulletChartDemo implements ChartDemo {

    private Container container;

    @Override
    public void generate(Widget widget) {
        // XYChart Demo
        container = (Container) Am4Core.create(widget, Am4Core.Container);
        container.width = new Percent(100);
        container.height = new Percent(100);
        container.layout = ContainerLayout.VERTICAL;

        createBulletChart(container, "solid");
        createBulletChart(container, "gradient");
    }

    /* Create ranges */
    private void createRange(ValueAxis axis, int start, int end, Object color) {
        ValueAxisDataItem range = axis.axisRanges.create();
        range.value = start;
        range.endValue = end;
        range.axisFill.fill = color;
        range.axisFill.fillOpacity = 0.8;
        range.label.disabled = true;
    }

    /* Create bullet chart with specified color type for background */
    private void createBulletChart(Container parent, String colorType) {
        String[] colors = new String[]{"#19d228", "#b4dd1e", "#f4fb16", "#f6d32b", "#fb7116"};

        /* Create chart instance */
        XYChart chart = (XYChart) container.createChild(Am4Charts.XYChart);
        chart.paddingRight = 25;


        ChartData<SampleData> dataChartData = new ChartData<>(getList());
        dataChartData.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "category";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONString(object.getCategory());
            }
        });

        dataChartData.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONNumber(object.getValue());
            }
        });

        dataChartData.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "target";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONNumber(object.getTarget());
            }
        });

        /* Add data */
        chart.data = dataChartData.get();

        /* Create axes */
        CategoryAxis categoryAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.minGridDistance = 30;
        categoryAxis.renderer.grid.template.disabled = true;

        ValueAxis valueAxis = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxis.renderer.minGridDistance = 30;
        valueAxis.renderer.grid.template.disabled = true;
        valueAxis.min = 0;
        valueAxis.max = 100;
        valueAxis.strictMinMax = true;

        Adapter<AxisLabel, AxisLabel> adapter = valueAxis.renderer.labels.template.adapter ;
        adapter.add("text", (source, labelAdapter) -> {
            //TODO: Find a way to workaround this one
            AxisLabel label = source;
            return label + "%";
        });

      /*
        In order to create separate background colors for each range of values,
        you have to create multiple axisRange objects each with their own fill color
      */
        if (colorType.equals("solid")) {
            int start = 0, end = 20;
            for (int i = 0; i < 5; ++i) {
                createRange(valueAxis, start, end, new Color(colors[i]));
                start += 20;
                end += 20;
            }
        }
      /*
        In order to create a gradient background, only one axisRange object is needed
        and a gradient object can be assigned to the axisRange's fill property.
      */
        else if (colorType.equals("gradient")) {
            LinearGradient gradient = new LinearGradient();
            for (int i = 0; i < 5; ++i) {
                // add each color that makes up the gradient
                gradient.addColor(new Color(colors[i]));
            }
            createRange(valueAxis, 0, 100, gradient);
        }

        /* Create series */
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueX = "value";
        series.dataFields.categoryY = "category";
        series.columns.template.fill = new Color("#000");
        series.columns.template.stroke = new Color("#fff");
        series.columns.template.strokeWidth = 1;
        series.columns.template.strokeOpacity = 0.5;
        series.columns.template.height = new Percent(25);
        series.columns.template.tooltipText = "{value}";

        LineSeries series2 = (LineSeries) chart.series.push(new LineSeries());
        series2.dataFields.valueX = "target";
        series2.dataFields.categoryY = "category";
        series2.strokeWidth = 0;

        Bullet bullet = (Bullet) series2.bullets.push(new Bullet());
        Line line = (Line) bullet.createChild(Am4Core.Line);
        line.x1 = 0;
        line.y1 = -40;
        line.x2 = 0;
        line.y2 = 40;
        line.stroke = new Color("#000");
        line.strokeWidth = 4;
    }

    private List<SampleData> getList() {
        List<SampleData> data = new ArrayList<>();
        data.add(new SampleData("Evaluation", 65, 78));
        return data;
    }

    class SampleData {
        private String category;
        private int value;
        private int target;

        public SampleData(String category, int value, int target) {
            this.category = category;
            this.value = value;
            this.target = target;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getTarget() {
            return target;
        }

        public void setTarget(int target) {
            this.target = target;
        }
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_861_none-1-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return null;
    }
}

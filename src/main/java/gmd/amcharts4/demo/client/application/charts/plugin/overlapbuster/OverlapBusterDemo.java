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
package gmd.amcharts4.demo.client.application.charts.plugin.overlapbuster;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.plugin.bullet.BulletPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.ui.Circle;
import gwt.material.design.amplugin.overlapbuster.client.OverlapBuster;
import gwt.material.design.client.base.helper.ColorHelper;
import gwt.material.design.client.constants.Color;

import java.util.ArrayList;
import java.util.List;

public class OverlapBusterDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.data = getData();
        // Create axes
        ValueAxis valueAxisX = (ValueAxis) chart.xAxes.push(new ValueAxis());
        valueAxisX.min = 2;
        valueAxisX.max = 10;

        ValueAxis valueAxisY = (ValueAxis) chart.yAxes.push(new ValueAxis());

        // Create series
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueX = "x";
        series.dataFields.valueY = "y";
        series.dataFields.value = "value";
        series.strokeOpacity = 0;
        series.sequencedInterpolation = true;
        series.tooltip.pointerOrientation = "vertical";

        // Create series bullets
        Circle bullet = (Circle) series.bullets.push(new Circle());
        bullet.strokeWidth = 2;
        bullet.fillOpacity = 0.5;
        bullet.stroke = new gwt.material.design.amcore.client.color.Color("#ffffff");
        bullet.tooltipText = "{title}";

        BulletPropertyField propertyField = new BulletPropertyField();
        propertyField.fill = "color";
        bullet.propertyFields = propertyField;


        // Add a heat rule to modify bullet size
        HeatRule<Double> heatRule = new HeatRule();
        heatRule.target = bullet;
        heatRule.min = 20.0;
        heatRule.max = 60.0;
        heatRule.property = "radius";
        series.heatRules.push(heatRule);

        // Create cursor
        chart.cursor = new XYCursor();
        chart.cursor.behavior = "zoomXY";

        // Enable OverlapBuster
        OverlapBuster overlap = (OverlapBuster) chart.plugins.push(new OverlapBuster());
        overlap.targets.push(bullet);
    }

    public static JavaScriptObject getData() {
        ChartData<BusterData> data = new ChartData(getList());

        data.add(new DataProvider<BusterData>() {
            @Override
            public String getProperty() {
                return "title";
            }

            @Override
            public JSONValue getValue(BusterData object) {
                return new JSONString(object.getTitle());
            }
        });

        data.add(new DataProvider<BusterData>() {
            @Override
            public String getProperty() {
                return "color";
            }

            @Override
            public JSONValue getValue(BusterData object) {
                return new JSONString(object.getColor());
            }
        });

        data.add(new DataProvider<BusterData>() {
            @Override
            public String getProperty() {
                return "x";
            }

            @Override
            public JSONValue getValue(BusterData object) {
                return new JSONNumber(object.getX());
            }
        });

        data.add(new DataProvider<BusterData>() {
            @Override
            public String getProperty() {
                return "y";
            }

            @Override
            public JSONValue getValue(BusterData object) {
                return new JSONNumber(object.getY());
            }
        });

        data.add(new DataProvider<BusterData>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(BusterData object) {
                return new JSONNumber(object.getValue());
            }
        });

        return data.get();
    }

    private static List<BusterData> getList() {
        List<BusterData> list = new ArrayList<>();
        list.add(new BusterData("Rachel", generateColor(Color.RED), 5, 7, 3108972));
        list.add(new BusterData("Monica", generateColor(Color.BLUE), 4.9, 6, 33397058));
        list.add(new BusterData("Chandler", generateColor(Color.GREY), 6, 7, 3227373));
        list.add(new BusterData("Joey", generateColor(Color.GREEN), 6, 7, 36485828));
        list.add(new BusterData("Phoebe", generateColor(Color.PURPLE), 5, 5, 20162517));
        list.add(new BusterData("Ross", generateColor(Color.PINK), 7, 7, 41118986));
        return list;
    }

    public static String generateColor(Color color) {
        return ColorHelper.setupComputedBackgroundColor(color);
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/docs/v4/wp-content/uploads/sites/2/2019/09/2019-09-23_17-33-39-768x363.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

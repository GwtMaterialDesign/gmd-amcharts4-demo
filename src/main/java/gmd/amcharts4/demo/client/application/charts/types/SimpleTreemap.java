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
package gmd.amcharts4.demo.client.application.charts.types;

import com.google.gwt.json.client.*;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.TreeMap;
import gwt.material.design.amcharts.client.bullet.Bullet;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.series.TreeMapSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.InterfaceColorSet;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimpleTreemap implements ChartDemo {

    private TreeMap chart;

    @Override
    public void generate(Widget widget) {
        chart = (TreeMap) Am4Core.create(widget, Am4Charts.TreeMap);
        chart.colors.step = 2;

        List<TreeMapData> list = new ArrayList<>();
        list.add(new TreeMapData("First",
                Arrays.asList(new TreeMapDataChild("A1", 100),
                        new TreeMapDataChild("A2", 60),
                        new TreeMapDataChild("A3", 30))));

        list.add(new TreeMapData("Second",
                Arrays.asList(new TreeMapDataChild("B1", 135),
                        new TreeMapDataChild("B2", 98),
                        new TreeMapDataChild("B3", 98))));

        list.add(new TreeMapData("Third",
                Arrays.asList(new TreeMapDataChild("C1", 335),
                        new TreeMapDataChild("C2", 148),
                        new TreeMapDataChild("C3", 126),
                        new TreeMapDataChild("C4", 26))));

        list.add(new TreeMapData("Fourth",
                Arrays.asList(new TreeMapDataChild("D1", 415),
                        new TreeMapDataChild("D2", 148),
                        new TreeMapDataChild("D3", 89),
                        new TreeMapDataChild("D4", 64),
                        new TreeMapDataChild("D5", 16))));

        list.add(new TreeMapData("Fifth",
                Arrays.asList(new TreeMapDataChild("E1", 687),
                        new TreeMapDataChild("E2", 148))));

        ChartData<TreeMapData> data = new ChartData<>(list);
        data.add(new DataProvider<TreeMapData>() {
            @Override
            public String getProperty() {
                return "name";
            }

            @Override
            public JSONValue getValue(TreeMapData object) {
                return new JSONString(object.getName());
            }
        });

        data.add(new DataProvider<TreeMapData>() {
            @Override
            public String getProperty() {
                return "children";
            }

            @Override
            public JSONValue getValue(TreeMapData object) {

                JSONArray array = new JSONArray();
                for (TreeMapDataChild c : object.getChildren()) {
                    JSONObject child = new JSONObject();
                    child.put("name", new JSONString(c.getName()));
                    child.put("value", new JSONNumber(c.getValue()));
                    array.set(object.getChildren().indexOf(c), child);
                }

                return array;
            }
        });

        chart.data = data.get();

        // define data fields
        chart.dataFields.value = "value";
        chart.dataFields.name = "name";
        chart.dataFields.children = "children";

        chart.zoomable = false;
        Color bgColor = new InterfaceColorSet().getFor("background");

        // level 0 series template
        TreeMapSeries level0SeriesTemplate = chart.seriesTemplates.create("0");
        Column level0ColumnTemplate = level0SeriesTemplate.columns.template;

        level0ColumnTemplate.column.cornerRadius(10, 10, 10, 10);
        level0ColumnTemplate.fillOpacity = 0;
        level0ColumnTemplate.strokeWidth = 4;
        level0ColumnTemplate.strokeOpacity = 0;

        // level 1 series template
        TreeMapSeries level1SeriesTemplate = chart.seriesTemplates.create("1");
        Column level1ColumnTemplate = level1SeriesTemplate.columns.template;

        level1SeriesTemplate.tooltip.animationDuration = 0;
        level1SeriesTemplate.strokeOpacity = 1;

        level1ColumnTemplate.column.cornerRadius(10, 10, 10, 10);
        level1ColumnTemplate.fillOpacity = 1;
        level1ColumnTemplate.strokeWidth = 4;
        level1ColumnTemplate.stroke = bgColor;

        LabelBullet bullet1 = (LabelBullet) level1SeriesTemplate.bullets.push(new LabelBullet());
        bullet1.locationY = 0.5;
        bullet1.locationX = 0.5;
        bullet1.label.text = "{name}";
        bullet1.label.fill = new Color("#ffffff");

        chart.maxLevels = 2;
    }

    class TreeMapData {
        private String name;
        private List<TreeMapDataChild> children;

        public TreeMapData(String name, List<TreeMapDataChild> children) {
            this.name = name;
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<TreeMapDataChild> getChildren() {
            return children;
        }

        public void setChildren(List<TreeMapDataChild> children) {
            this.children = children;
        }
    }

    class TreeMapDataChild {
        private String name;
        private int value;

        public TreeMapDataChild(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11629_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

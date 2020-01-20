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
package gmd.amcharts4.demo.client.application.charts.plugin.bullet;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.ui.Circle;
import gwt.material.design.amcore.client.ui.WavedRectangle;
import gwt.material.design.amplugin.bullets.client.types.FlagBullet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimpleBulletDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.maskBullets = false;
        chart.data = getData();

        // Create axes
        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());
        dateAxis.renderer.labels.template.location = 0.5;
        dateAxis.renderer.minGridDistance = 30;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.max = 1000;

        createSeries("value", "Series #1");

        chart.cursor = new XYCursor();
    }

    // Create series
    protected void createSeries(String field, String name) {
        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.valueY = field;
        series.dataFields.dateX = "date";
        series.name = name;
        series.tooltipText = "{dateX}: [b]{valueY}[/]";
        series.strokeWidth = 3;

        // Creating a bullet
        FlagBullet bullet = (FlagBullet) series.bullets.push(new FlagBullet());

        // Setting label to display values from data
        bullet.label.text = "{bulletText}\n[bold]{valueY}[/]";
        bullet.label.textAlign = "middle";

        // Disabling all bullets, except ones that are explicitly enabled via data
        bullet.disabled = true;

        BulletPropertyField bulletPropertyField = (BulletPropertyField) bullet.propertyFields;
        bulletPropertyField.disabled = "hideBullet";

        // Allowing controlling pole height via data (negative height means upside down flag)
        // We also instruct pole to draw its color from "bulletColor" in data
        bulletPropertyField.poleHeight = "height";

        BulletPropertyField polePropertyField = (BulletPropertyField) bullet.pole.propertyFields;
        polePropertyField.stroke = "bulletColor";

        // Background is a WavedRectangle, which we configure, as well as instruct
        // it to get its fill and border color from data field "bulletColor"
        WavedRectangle wavedRectangle = (WavedRectangle) bullet.background;
        wavedRectangle.waveLength = 15;
        wavedRectangle.fillOpacity = 0.5;

        BulletPropertyField wavedRectablePropertyField = (BulletPropertyField) wavedRectangle.propertyFields;
        wavedRectablePropertyField.fill = "bulletColor";
        wavedRectablePropertyField.stroke = "bulletColor";

        // Add a circle to pole base
        Circle circle = (Circle) bullet.createChild(Am4Core.Circle);
        circle.radius = 4;
        circle.strokeWidth = 2;
        circle.stroke = new Color("#fff");

        BulletPropertyField circlePropertyField = (BulletPropertyField) circle.propertyFields;
        circlePropertyField.fill = "bulletColor";
    }

    public static JavaScriptObject getData() {
        ChartData<Bullet> data = new ChartData(getList());

        data.add(new DataProvider<Bullet>() {
            @Override
            public String getProperty() {
                return "date";
            }

            @Override
            public JSONValue getValue(Bullet object) {
                if (object.getDate() != null) {
                    return new JSONString(object.getDate().toString());
                } else {
                    return null;
                }
            }
        });

        data.add(new DataProvider<Bullet>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(Bullet object) {
                return new JSONNumber(object.getValue());
            }
        });

        data.add(new DataProvider<Bullet>() {
            @Override
            public String getProperty() {
                return "hideBullet";
            }

            @Override
            public JSONValue getValue(Bullet object) {
                return JSONBoolean.getInstance(object.isHideBullet());
            }
        });

        data.add(new DataProvider<Bullet>() {
            @Override
            public String getProperty() {
                return "bulletText";
            }

            @Override
            public JSONValue getValue(Bullet object) {
                if (object.getBulletText() != null) {
                    return new JSONString(object.getBulletText());
                }
                return null;
            }
        });

        data.add(new DataProvider<Bullet>() {
            @Override
            public String getProperty() {
                return "bulletColor";
            }

            @Override
            public JSONValue getValue(Bullet object) {
                if (object.getBulletColor() != null) {
                    return new JSONString(object.getBulletColor());
                }
                return null;
            }
        });

        return data.get();
    }

    public static List<Bullet> getList() {
        List<Bullet> data = new ArrayList<>();

        Bullet bullet1 = new Bullet();
        bullet1.setDate(new Date(118, 1, 1));
        bullet1.setValue(450);
        bullet1.setHideBullet(true);
        data.add(bullet1);

        Bullet bullet2 = new Bullet();
        bullet2.setDate(new Date(118, 1, 2));
        bullet2.setValue(279);
        bullet2.setHideBullet(false);
        bullet2.setBulletText("Lowest");
        bullet2.setBulletColor("#c00");
        data.add(bullet2);

        Bullet bullet3 = new Bullet();
        bullet3.setDate(new Date(118, 1, 3));
        bullet3.setValue(700);
        bullet3.setHideBullet(false);
        bullet3.setBulletText("Highest");
        bullet3.setBulletColor("#0c0");
        data.add(bullet3);

        Bullet bullet4 = new Bullet();
        bullet4.setDate(new Date(118, 1, 4));
        bullet4.setValue(490);
        bullet4.setHideBullet(true);
        data.add(bullet4);

        Bullet bullet5 = new Bullet();
        bullet5.setDate(new Date(118, 1, 5));
        bullet5.setValue(500);
        bullet5.setHideBullet(true);
        data.add(bullet5);

        Bullet bullet6 = new Bullet();
        bullet6.setDate(new Date(118, 1, 6));
        bullet6.setValue(550);
        bullet6.setHideBullet(true);
        data.add(bullet6);

        Bullet bullet7 = new Bullet();
        bullet7.setDate(new Date(118, 1, 7));
        bullet7.setValue(420);
        bullet7.setHideBullet(true);
        data.add(bullet7);

        return data;
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

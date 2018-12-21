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

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.Bullet;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.LinearGradient;
import gwt.material.design.amcore.client.properties.SpriteProperties;
import gwt.material.design.amcore.client.state.SpriteState;
import gwt.material.design.amcore.client.ui.Image;

public class PictorialColumnChart implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget widget) {
        // Create chart instance
        chart = (XYChart) Am4Core.create(widget, Am4Charts.XYChart);

        chart.dataSource.url = "data/pictorial-column-chart.json";

        chart.hiddenState.properties.opacity = 0;
        chart.defaultState.transitionDuration = 5000;
        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "category";
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.renderer.grid.template.disabled = true;
        categoryAxis.renderer.labels.template.fill = new Color("#ffffff");
        categoryAxis.renderer.labels.template.fillOpacity = 0.5;
        categoryAxis.renderer.inside = true;

        chart.paddingBottom = 0;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.min = 0;
        valueAxis.max = 1000;
        valueAxis.strictMinMax = true;
        valueAxis.renderer.grid.template.strokeDasharray = "4,4";
        valueAxis.renderer.minLabelPosition = 0.05;

        // Create series
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueY = "height";
        series.dataFields.categoryX = "category";
        series.columns.template.disabled = true;

        Bullet bullet = series.bullets.push(new Bullet());
        bullet.defaultState.properties.opacity = 0.5;

        SpriteState<SpriteProperties> hoverState = bullet.states.create("hover");
        hoverState.properties.opacity = 0.9;

        Image image = (Image) bullet.createChild(Am4Core.Image);
        image.horizontalCenter = "middle";
        image.verticalCenter = "top";

        // Property Fields Definition
        MyCustomPropertyField propertyFields = new MyCustomPropertyField();
        propertyFields.href = "icon";
        propertyFields.widthRatio = "ratio";
        image.propertyFields = propertyFields;

        image.height = new Percent(100);
        bullet.events.on("positionchanged", event -> event.target.deepInvalidate());
        LabelBullet label = (LabelBullet) series.bullets.push(new LabelBullet());
        label.label.text = "{height} metres";
        label.dy = -15;

        LinearGradient gradient = new LinearGradient();
        gradient.addColor(new Color("#f0b24f"));
        gradient.addColor(new Color("#ca6c46"));
        gradient.addColor(new Color("#0c0524"));
        gradient.rotation = 90;
        chart.background.fill = gradient;
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_11384_none.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

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
package gmd.am4charts.demo.client.application.charts.basic;

import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.scrollbar.XYChartScrollbar;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.ColorSet;
import gwt.material.design.amcore.client.constants.ContainerLayout;
import gwt.material.design.amcore.client.language.Locale;
import gwt.material.design.amcore.client.ui.Rectangle;
import gwt.material.design.client.ui.MaterialToast;

import java.util.Date;

public class ColorSetDemo implements ChartDemo {

    @Override
    public void generate(Widget widget) {
        ColorSet colorSet = new ColorSet();
        Container container = (Container) Am4Core.create(widget, Am4Core.Container);
        container.width = new Percent(100);
        container.height = new Percent(100);
        container.layout = ContainerLayout.GRID;
        for (int i = 1; i <= 8; i++) {
            Rectangle rectangle = (Rectangle) container.createChild(Am4Core.Rectangle);
            rectangle.width = 100;
            rectangle.height = 100;
            rectangle.fill = colorSet.next();
        }
    }

    @Override
    public String getImage() {
        return null;
    }

    @Override
    public Chart getChart() {
        return null;
    }
}

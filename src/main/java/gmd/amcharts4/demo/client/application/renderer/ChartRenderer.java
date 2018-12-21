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
package gmd.amcharts4.demo.client.application.renderer;

import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.widget.DashboardCard;
import gwt.material.design.client.base.MaterialWidget;

import java.util.List;

public class ChartRenderer {

    public static void render(String type, MaterialWidget container, List<ChartDemo> demos) {
        container.clear();

        int i = 0;
        for (ChartDemo demo : demos) {
            container.add(new DashboardCard(type, i, demo));
            i++;
        }
    }


}

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gmd.amcharts4.demo.client.application.page.viewer;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.widget.DashboardCard;
import gmd.amcharts4.demo.client.application.widget.chart.ThemeSelector;
import gwt.material.design.amcore.client.theme.DarkTheme;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;

import javax.inject.Inject;

public class ChartViewerView extends ViewImpl implements ChartViewerPresenter.MyView {

    interface Binder extends UiBinder<Widget, ChartViewerView> {
    }

    static final String DARK = "dark";

    @UiField
    MaterialLabel title;

    @UiField
    MaterialLink source;

    @UiField
    MaterialPanel chartContainer;

    @UiField
    ThemeSelector themeSelector;

    private ChartDemo demo;

    @Inject
    ChartViewerView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        // Theme Selection for Charts
        themeSelector.setCallback((theme) -> {
            if (theme != null && theme.getClass().equals(DarkTheme.class)) {
                RootPanel.get().addStyleName(DARK);
            } else {
                RootPanel.get().removeStyleName(DARK);
            }

            if (demo != null) {
                renderChart(demo);
            }
        });
    }

    @Override
    public void renderChart(ChartDemo demo) {
        this.demo = demo;

        title.setText(DashboardCard.generateTitle(demo));
        source.setTarget("_blank");
        source.setHref(DashboardCard.generateSource(demo));
        demo.generate(chartContainer);
    }
}

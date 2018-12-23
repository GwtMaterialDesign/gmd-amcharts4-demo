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

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gmd.amcharts4.demo.client.application.ApplicationPresenter;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.service.ChartService;
import gmd.amcharts4.demo.client.place.NameTokens;
import gwt.material.design.client.ui.MaterialToast;

public class ChartViewerPresenter extends Presenter<ChartViewerPresenter.MyView, ChartViewerPresenter.MyProxy> {

    interface MyView extends View {
        void renderChart(ChartDemo demo);
    }

    PlaceManager placeManager;

    @ProxyStandard
    @NameToken(NameTokens.VIEWER)
    interface MyProxy extends ProxyPlace<ChartViewerPresenter> {
    }

    @Inject
    ChartViewerPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy, PlaceManager placeManager) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);

        this.placeManager = placeManager;
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        String type = placeManager.getCurrentPlaceRequest().getParameter("type", NameTokens.TYPES);
        int id = Integer.parseInt(placeManager.getCurrentPlaceRequest().getParameter("id", "0"));

        if (id > -1) {
            ChartDemo demo = null;

            if (type.equals(NameTokens.TYPES)) {
                demo = ChartService.getChart(id);
            } else if (type.equals(NameTokens.MAPS)) {
                demo = ChartService.getMap(id);
            } else if (type.equals(NameTokens.GAUGES)) {
                demo = ChartService.getGauge(id);
            } else if (type.equals(NameTokens.DATA)) {
                demo = ChartService.getDataChart(id);
            }

            if (demo != null) {
                getView().renderChart(demo);
            }
        } else {
            MaterialToast.fireToast("Demo not Found");
        }
    }
}

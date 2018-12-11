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
package gmd.am4charts.demo.client.application.page.charttypes;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import gmd.am4charts.demo.client.application.ApplicationPresenter;
import gmd.am4charts.demo.client.application.events.ApplyThemeEvent;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.service.ChartService;
import gmd.am4charts.demo.client.place.NameTokens;

import java.util.List;

public class ChartTypesPresenter extends Presenter<ChartTypesPresenter.MyView, ChartTypesPresenter.MyProxy>
        implements ApplyThemeEvent.ApplyThemeHandler {

    interface MyView extends View {
        void build(List<ChartDemo> demos);
    }

    @ProxyStandard
    @NameToken(NameTokens.TYPES)
    interface MyProxy extends ProxyPlace<ChartTypesPresenter> {
    }

    @Inject
    ChartTypesPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
    }

    @Override
    protected void onBind() {
        super.onBind();

        addRegisteredHandler(ApplyThemeEvent.TYPE, this);
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        getView().build(ChartService.getCharts());
    }

    @Override
    public void onApplyThemeEvent(ApplyThemeEvent event) {
        getView().build(ChartService.getCharts());
    }
}

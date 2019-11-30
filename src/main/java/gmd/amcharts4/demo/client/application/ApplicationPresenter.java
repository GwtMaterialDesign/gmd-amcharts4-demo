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
package gmd.amcharts4.demo.client.application;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import gmd.amcharts4.demo.client.application.events.ApplyThemeEvent;
import gmd.amcharts4.demo.client.application.navigation.HeaderLink;
import gmd.amcharts4.demo.client.application.service.NavigationService;
import gwt.material.design.client.theme.dark.ColorScheme;
import gwt.material.design.client.theme.dark.ColorSchemeChangeEvent;

import java.util.List;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
    implements ApplicationUiHandlers {

    interface MyView extends View, HasUiHandlers<ApplicationUiHandlers> {
        void renderHeaderLinks(List<HeaderLink> link);
        void setActiveNavLink(int index);
    }

    private PlaceManager placeManager;

    public static final NestedSlot SLOT_MAIN = new NestedSlot();

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    @Inject
    ApplicationPresenter(EventBus eventBus,
                         MyView view,
                         MyProxy proxy,
                         PlaceManager placeManager) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    public void applyTheme() {
        ApplyThemeEvent.fire(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        getView().renderHeaderLinks(NavigationService.getHeaderLinks());
    }

    @Override
    protected void onReveal() {
        super.onReveal();

        int index = 0;
        for (HeaderLink headerLink : NavigationService.getHeaderLinks()) {
            if (placeManager.getCurrentPlaceRequest().getNameToken().equals(headerLink.getHref())) {
                getView().setActiveNavLink(index);
            }
            index++;
        }
    }

    @Override
    public void setColorScheme(ColorScheme colorScheme) {
        ColorSchemeChangeEvent.fire(this, colorScheme);
    }
}

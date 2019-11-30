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

import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import gmd.amcharts4.demo.client.application.navigation.HeaderLink;
import gmd.amcharts4.demo.client.resources.AppResources;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.theme.DarkTheme;
import gwt.material.design.amcore.client.theme.MaterialTheme;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.helper.ColorHelper;
import gwt.material.design.client.base.viewport.Resolution;
import gwt.material.design.client.base.viewport.ViewPort;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.js.Window;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.pwa.push.js.Notification;
import gwt.material.design.client.pwa.serviceworker.ServiceWorkerManager;
import gwt.material.design.client.pwa.serviceworker.js.ServiceWorkerOption;
import gwt.material.design.client.theme.dark.ColorScheme;
import gwt.material.design.client.theme.dark.ColorSchemeChangeEvent;
import gwt.material.design.client.ui.*;

import java.util.List;

public class ApplicationView extends ViewWithUiHandlers<ApplicationUiHandlers>
    implements ApplicationPresenter.MyView {

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    MaterialNavSection navSection;

    @UiField
    MaterialSideNavDrawer sideNav;

    @UiField
    MaterialPanel chartContent;

    @UiField
    MaterialDarkModeToggle darkToggle;

    @Inject
    ApplicationView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(ApplicationPresenter.SLOT_MAIN, chartContent);
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        // Enable PWA
        if (PwaManager.isPwaSupported()) {
            ServiceWorkerManager manager = new ServiceWorkerManager("/gmd-core-demo/service-worker.js");
            ServiceWorkerOption option = ServiceWorkerOption.create();
            option.setScope("/gmd-amcharts4-demo/");
            manager.setOption(option);
            PwaManager.getInstance()
                    .setServiceWorker(manager)
                    .setThemeColor(ColorHelper.setupComputedBackgroundColor(Color.BLUE_DARKEN_3))
                    .setWebManifest("manifest.url")
                    .load();

            // Will request a notification
            Notification.requestPermission(status -> MaterialToast.fireToast("Permission Status: " + status));
        }

        // Inject Resources
        MaterialDesignBase.injectCss(AppResources.INSTANCE.appCss());

        // Remove Splashscreen once js files are loaded
        Document.get().getElementById("splashscreen").removeFromParent();

        // Will check and update active header state
        navSection.addSelectionHandler(event -> setActiveNavLink(event.getSelectedItem()));

    }

    @UiHandler("darkToggle")
    void darkToggle(ColorSchemeChangeEvent e) {
        getUiHandlers().setColorScheme(e.getColorScheme());
    }

    @Override
    public void setActiveNavLink(int index) {
        if (Window.matchMedia(Resolution.ALL_LAPTOP.asMediaQuery())) {
            for (Widget child : navSection) {
                child.removeStyleName(CssName.ACTIVE);
            }
            if (index > -1) {
                Widget widget = navSection.getWidget(index);
                widget.addStyleName(CssName.ACTIVE);
            }
        }
    }

    @Override
    public void renderHeaderLinks(List<HeaderLink> links) {
        ViewPort.when(Resolution.ALL_LAPTOP).then(viewPortChange -> {
            renderHeaderLinks(links, navSection);
        }, viewPortRect -> {
            renderHeaderLinks(links, sideNav);
            return true;
        });
    }

    protected void renderHeaderLinks(List<HeaderLink> links, MaterialWidget container) {
        container.clear();
        links.forEach(headerLink -> {
            MaterialLink link = new MaterialLink(headerLink.getName());
            link.setHref("#" + headerLink.getHref());
            container.add(link);
        });
    }
}

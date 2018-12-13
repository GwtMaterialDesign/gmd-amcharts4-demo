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
package gmd.am4charts.demo.client.application;

import com.google.gwt.dom.client.Document;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import gmd.am4charts.demo.client.application.navigation.HeaderLink;
import gmd.am4charts.demo.client.application.service.NavigationService;
import gmd.am4charts.demo.client.resources.AppResources;
import gwt.material.design.client.MaterialDesignBase;
import gwt.material.design.client.base.helper.ColorHelper;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.pwa.PwaManager;
import gwt.material.design.client.pwa.push.js.Notification;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialNavSection;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;

import java.util.List;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {

    interface Binder extends UiBinder<Widget, ApplicationView> {
    }

    @UiField
    MaterialNavSection navSection;

    @UiField
    MaterialPanel chartContent;

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
            PwaManager.getInstance()
                    .setServiceWorker("service-worker.js")
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

    @Override
    public void setActiveNavLink(int index) {
        for (Widget child : navSection) {
            child.removeStyleName(CssName.ACTIVE);
        }
        if (index > -1) {
            Widget widget = navSection.getWidget(index);
            widget.addStyleName(CssName.ACTIVE);
        }
    }

    @Override
    public void renderHeaderLinks(List<HeaderLink> links) {
        links.forEach(headerLink -> {
            MaterialLink link = new MaterialLink(headerLink.getName());
            link.setHref("#" + headerLink.getHref());
            link.setTextColor(Color.BLACK);
            navSection.add(link);
        });
    }
}

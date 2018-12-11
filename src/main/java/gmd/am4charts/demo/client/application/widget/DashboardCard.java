package gmd.am4charts.demo.client.application.widget;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.navigation.Dashboard;
import gmd.am4charts.demo.client.application.service.ChartService;
import gwt.material.design.client.ui.*;

public class DashboardCard extends Composite {

    private static DashboardCardUiBinder uiBinder = GWT.create(DashboardCardUiBinder.class);
    private final String titleText;

    interface DashboardCardUiBinder extends UiBinder<Widget, DashboardCard> {
    }

    static String githubUrl = "https://github.com/GwtMaterialDesign/gmd-am4charts-demo/tree/master/src/main/java/";

    @UiField
    MaterialCard card;

    @UiField
    MaterialImage image;

    @UiField
    MaterialLabel title;

    @UiField
    MaterialLink source;

    private int index = 0;
    private ChartDemo demo;

    public DashboardCard(int index, String titleText, ChartDemo demo) {
        initWidget(uiBinder.createAndBindUi(this));

        this.index = index;
        this.titleText = titleText;
        this.demo = demo;
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        card.addClickHandler(event -> {
            Window.open(GWT.getHostPageBaseURL() + "#viewer;key=" + index, "_self", "_self");
        });

        title.setText(cleanupTitle(titleText));
        source.setTarget("_blank");
        source.setHref(githubUrl + demo.getClass().getName().replace(".", "/") + ".java");

        if (demo.getImage() != null) {
            image.setUrl(demo.getImage());
        }
    }

    static String cleanupTitle(String camelName) {
        return camelName.replaceAll("([A-Z][a-z]+)", " $1") // Words beginning with UC
                .replaceAll("([A-Z][A-Z]+)", " $1") // "Words" of only UC
                .replaceAll("([^A-Za-z ]+)", " $1") // "Words" of non-letters
                .replace("Demo", "")
                .trim();
    }
}

package gmd.amcharts4.demo.client.application.widget;

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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.client.ui.*;

public class DashboardCard extends Composite {

    private static DashboardCardUiBinder uiBinder = GWT.create(DashboardCardUiBinder.class);

    interface DashboardCardUiBinder extends UiBinder<Widget, DashboardCard> {
    }

    private static final String githubUrl = "https://github.com/GwtMaterialDesign/gmd-am4charts-demo/tree/master/src/main/java/";
    private static final String NO_IMAGE = "no-image";

    @UiField
    MaterialCardImage cardImage;

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
    private String type;

    public DashboardCard(String type, int index, ChartDemo demo) {
        initWidget(uiBinder.createAndBindUi(this));

        this.index = index;
        this.demo = demo;
        this.type = type;
    }

    @Override
    protected void onAttach() {
        super.onAttach();

        card.addClickHandler(event -> Window.open(GWT.getHostPageBaseURL() + "#viewer;type=" + type + ";id=" + index, "_self", "_self"));

        title.setText(generateTitle(demo));
        source.setTarget("_blank");
        source.setHref(generateSource(demo));

        if (demo.getImage() != null) {
            image.setUrl(demo.getImage());
        } else {
            cardImage.addStyleName(NO_IMAGE);
            image.setUrl("https://gwtmaterialdesign.github.io/gmd-am4charts-demo/launcher-icons/launcher4x.png");
        }
    }

    public static String generateSource(ChartDemo demo) {
        return githubUrl + demo.getClass().getName().replace(".", "/") + ".java";
    }

    public static String generateTitle(ChartDemo demo) {
        return demo.getClass().getSimpleName().replaceAll("([A-Z][a-z]+)", " $1") // Words beginning with UC
                .replaceAll("([A-Z][A-Z]+)", " $1") // "Words" of only UC
                .replaceAll("([^A-Za-z ]+)", " $1") // "Words" of non-letters
                .replace("Demo", "")
                .trim();
    }
}

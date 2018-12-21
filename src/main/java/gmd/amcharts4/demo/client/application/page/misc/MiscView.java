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
package gmd.amcharts4.demo.client.application.page.misc;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.renderer.ChartRenderer;
import gmd.amcharts4.demo.client.place.NameTokens;
import gwt.material.design.client.ui.MaterialRow;

import javax.inject.Inject;
import java.util.List;

public class MiscView extends ViewImpl implements MiscPresenter.MyView {

    interface Binder extends UiBinder<Widget, MiscView> {
    }

    @UiField
    MaterialRow chartContainer;

    @Inject
    MiscView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void build(List<ChartDemo> demos) {
        ChartRenderer.render(NameTokens.DATA, chartContainer, demos);
    }
}

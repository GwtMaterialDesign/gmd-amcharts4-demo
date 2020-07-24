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

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import gmd.amcharts4.demo.client.application.page.charttypes.ChartTypesModule;
import gmd.amcharts4.demo.client.application.page.gauge.GaugeModule;
import gmd.amcharts4.demo.client.application.page.maps.MapsModule;
import gmd.amcharts4.demo.client.application.page.misc.MiscModule;
import gmd.amcharts4.demo.client.application.page.plugin.PluginModule;
import gmd.amcharts4.demo.client.application.page.viewer.ChartViewerModule;

public class ApplicationModule extends AbstractPresenterModule {

    @Override
    protected void configure() {
        install(new ChartTypesModule());
        install(new MapsModule());
        install(new GaugeModule());
        install(new MiscModule());
        install(new ChartViewerModule());
        install(new PluginModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}

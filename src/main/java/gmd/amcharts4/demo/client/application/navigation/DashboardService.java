/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2018 GwtMaterialDesign
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
package gmd.amcharts4.demo.client.application.navigation;

import java.util.ArrayList;
import java.util.List;

public class DashboardService {

    public static List<Dashboard> getDashboards(){
        List<Dashboard> dashboards = new ArrayList<>();
        dashboards.add(new Dashboard("Charts", "GMD Core Widgets", "", "https://www.gstatic.com/devrel-devsite/va3e6d7c495ae58d2f0c21ab1e6f48fa6e19121edf44f1cc6af04d15995811aa0/web/images/illustrations/fast.svg"));
        dashboards.add(new Dashboard("Addins / Incubator", "Core Widget extension widgets", "", "https://www.gstatic.com/devrel-devsite/va3e6d7c495ae58d2f0c21ab1e6f48fa6e19121edf44f1cc6af04d15995811aa0/web/images/illustrations/fast.svg"));
        dashboards.add(new Dashboard("DataTable", "Support for GWT Cell Tables", "", "https://www.gstatic.com/devrel-devsite/va3e6d7c495ae58d2f0c21ab1e6f48fa6e19121edf44f1cc6af04d15995811aa0/web/images/illustrations/fast.svg"));
        dashboards.add(new Dashboard("PWA", "Progressive Web Apps support", "", "https://www.gstatic.com/devrel-devsite/va3e6d7c495ae58d2f0c21ab1e6f48fa6e19121edf44f1cc6af04d15995811aa0/web/images/illustrations/fast.svg"));
        return dashboards;
    }
}

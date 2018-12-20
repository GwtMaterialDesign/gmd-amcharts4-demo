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
package gmd.am4charts.demo.client.application.service;

import gmd.am4charts.demo.client.application.navigation.HeaderLink;
import gmd.am4charts.demo.client.place.NameTokens;

import java.util.ArrayList;
import java.util.List;

public class NavigationService {

    public static List<HeaderLink> getHeaderLinks() {
        List<HeaderLink> headerLinks = new ArrayList<>();
        headerLinks.add(new HeaderLink("All Types", NameTokens.TYPES));
        headerLinks.add(new HeaderLink("Maps", NameTokens.MAPS));
        headerLinks.add(new HeaderLink("Gauge", NameTokens.GAUGES));
        headerLinks.add(new HeaderLink("Data", NameTokens.DATA));
        return headerLinks;
    }
}

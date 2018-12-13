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
package gmd.am4charts.demo.client.application.widget.chart;

import com.google.gwt.dom.client.Style;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.theme.*;
import gwt.material.design.client.constants.CssName;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialPanel;
import gwt.material.design.client.ui.MaterialToast;
import gwt.material.design.jquery.client.api.Functions;

import java.util.LinkedHashSet;
import java.util.Set;

public class ThemeSelector extends MaterialPanel {

    static final String ANIMATED = "animated";
    private Functions.Func1<ChartTheme> callback;
    private MaterialLabel label = new MaterialLabel("Select Theme");
    private boolean animated;

    private static Set<ThemeIcon> themeIcons = new LinkedHashSet<>();

    private static ThemeIcon noTheme = new ThemeIcon(new SpiritedAwayTheme(), "No Theme", "linear-gradient(45deg, #67b7dc 0%,#67b7dc 50%,#c767dc 50%,#c767dc 100%)");
    private static ThemeIcon dataViz = new ThemeIcon(new DataVizTheme(), "DataViz", "linear-gradient(45deg, #283250 0%,#283250 50%,#902c2d 50%,#902c2d 100%)");
    private static ThemeIcon material = new ThemeIcon(new MaterialTheme(), "Material", "linear-gradient(45deg, #E91E63 0%,#E91E63 50%,#9C27B0 50%,#9C27B0 100%)");
    private static ThemeIcon kellys = new ThemeIcon(new KellyTheme(), "Kellys", "linear-gradient(45deg, #F3C300 0%,#F3C300 50%,#875692 50%,#875692 100%)");
    private static ThemeIcon dark = new ThemeIcon(new DarkTheme(), "Dark", "linear-gradient(45deg, #67b7dc 0%,#67b7dc 50%,#c767dc 50%,#c767dc 100%)");
    private static ThemeIcon frozen = new ThemeIcon(new FrozenTheme(), "Frozen", "linear-gradient(45deg, #bec4f8 0%,#bec4f8 50%,#a5abee 50%,#a5abee 100%)");
    private static ThemeIcon moonriseKingdom = new ThemeIcon(new MoonriseKingdomTheme(), "Moonrise Kingdom", "linear-gradient(45deg, #3a1302 0%,#3a1302 50%,#c79f59 50%,#c79f59 100%)");
    private static ThemeIcon spiritedAway = new ThemeIcon(new SpiritedAwayTheme(), "Spirited Away", "linear-gradient(45deg, #65738e 0%,#65738e 50%,#523b58 50%,#523b58 100%)");

    private static ThemeIcon animatedTheme = new ThemeIcon(new AnimatedTheme(), "Turn on Animated", "linear-gradient(45deg, #34524e 0%,#43247e 50%,#523b58 50%,#523b58 100%)");

    static {
        themeIcons.add(noTheme);
        themeIcons.add(dataViz);
        themeIcons.add(material);
        themeIcons.add(kellys);
        themeIcons.add(dark);
        themeIcons.add(frozen);
        themeIcons.add(moonriseKingdom);
        themeIcons.add(spiritedAway);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        clear();

        label.setFontSize("1em");
        add(label);
        themeIcons.forEach(icon -> {
            icon.registerHandler(icon.addClickHandler(event -> setActive(icon)));
            add(icon);
        });

        add(animatedTheme);
        animatedTheme.addStyleName(ANIMATED);
        animatedTheme.addClickHandler(event -> {
            AnimatedTheme animatedTheme = new AnimatedTheme();
            turnAnimatedTheme(!animated, animatedTheme);
            callback.call(animatedTheme);
        });

        turnAnimatedTheme(true, new AnimatedTheme());
    }

    protected void turnAnimatedTheme(boolean on, AnimatedTheme animatedTheme) {
        if (on) {
            Am4Core.useTheme(animatedTheme);
            this.animatedTheme.addStyleName(CssName.ACTIVE);
            animated = true;
        } else {
            Am4Core.unuseTheme(animatedTheme);
            this.animatedTheme.removeStyleName(CssName.ACTIVE);
            animated = false;
        }
    }

    public void clearActive() {
        themeIcons.forEach(widgets -> widgets.removeStyleName(CssName.ACTIVE));
    }

    public void setActive(ThemeIcon icon) {

        if (icon != null) {
            clearActive();
            icon.addStyleName(CssName.ACTIVE);
            if (icon.getChartTheme() != null) {
                Am4Core.unuseAllThemes();

                if (animated) {
                    Am4Core.useTheme(new AnimatedTheme());
                }

                if (icon.getChartTheme() != null) {
                    Am4Core.useTheme(icon.getChartTheme());
                }
                if (callback != null) {
                    callback.call(icon.getChartTheme());
                }
            }
        }
    }

    public Functions.Func1<ChartTheme> getCallback() {
        return callback;
    }

    public void setCallback(Functions.Func1<ChartTheme> callback) {
        this.callback = callback;
    }
}

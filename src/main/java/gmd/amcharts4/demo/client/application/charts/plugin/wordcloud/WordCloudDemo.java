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
package gmd.amcharts4.demo.client.application.charts.plugin.wordcloud;

import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.properties.HeatRule;
import gwt.material.design.amcore.client.ui.Label;
import gwt.material.design.amplugin.wordcloud.client.WordCloud;
import gwt.material.design.amplugin.wordcloud.client.WordCloudCharts;
import gwt.material.design.amplugin.wordcloud.client.WordCloudSeries;

import java.util.ArrayList;
import java.util.List;

public class WordCloudDemo implements ChartDemo {

    private WordCloud chart;

    @Override
    public void generate(Widget container) {
        // Pie Chart Demo
        chart = (WordCloud) Am4Core.create(container, WordCloudCharts.WordCloud);
        chart.fontFamily = "Courier New";
        WordCloudSeries series = (WordCloudSeries) chart.series.push(new WordCloudSeries());
        series.randomness = 0.1;
        series.rotationThreshold = 0.5;
        series.dataSource.url = "data/plugin/wordcloud-proglang.json";
        series.dataFields.word = "tag";
        series.dataFields.value = "count";

        HeatRule rule1 = new HeatRule();
        rule1.target = series.labels.template;
        rule1.property = "fill";
        rule1.min = new Color("#0000CC");
        rule1.max = new Color("#CC00CC");
        rule1.dataField = "value";
        series.heatRules.push(rule1);

        series.labels.template.url = "https://stackoverflow.com/questions/tagged/{word}";
        series.labels.template.urlTarget = "_blank";

        series.labels.template.tooltipText = "{word}:\n[bold]{value}[/]";

        Label subtitle = chart.titles.create();
        subtitle.text = "(click to open)";

        Label title = chart.titles.create();
        title.text = "Most Popular Tags @ StackOverflow";
        title.fontSize = 20;
        title.fontWeight = "800";
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2019/02/demo_13539_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

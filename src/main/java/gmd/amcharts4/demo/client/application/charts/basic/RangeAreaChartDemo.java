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
package gmd.amcharts4.demo.client.application.charts.basic;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.DateAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.cursor.XYCursor;
import gwt.material.design.amcharts.client.series.LineSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.scrollbar.Scrollbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RangeAreaChartDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // XYChart Demo
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        List<SampleData> list = new ArrayList<>();
        double open = 100;
        double close = 250;

        for (int i = 1; i < 120; i++) {
            open += Math.round((Math.random() < 0.5 ? 1 : -1) * Math.random() * 4);
            close = Math.round(open + Math.random() * 5 + i / 5 - (Math.random() < 0.5 ? 1 : -1) * Math.random() * 2);
            list.add(new SampleData(new Date(118, 0, i), open, close));
        }

        ChartData<SampleData> data = new ChartData<>(list);

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "date";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONString(object.getDate().toString());
            }
        });

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "open";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONNumber(object.getOpen());
            }
        });

        data.add(new DataProvider<SampleData>() {
            @Override
            public String getProperty() {
                return "close";
            }

            @Override
            public JSONValue getValue(SampleData object) {
                return new JSONNumber(object.getClose());
            }
        });

        chart.data = data.get();

        DateAxis dateAxis = (DateAxis) chart.xAxes.push(new DateAxis());

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.tooltip.disabled = true;

        LineSeries series = (LineSeries) chart.series.push(new LineSeries());
        series.dataFields.dateX = "date";
        series.dataFields.openValueY = "open";
        series.dataFields.valueY = "close";
        series.tooltipText = "open: {openValueY.value} close: {valueY.value}";
        series.sequencedInterpolation = true;
        series.fillOpacity = 0.3;
        series.defaultState.transitionDuration = 1000;
        series.tensionX = 0.8;

        LineSeries series2 = (LineSeries) chart.series.push(new LineSeries());
        series2.dataFields.dateX = "date";
        series2.dataFields.valueY = "open";
        series2.sequencedInterpolation = true;
        series2.defaultState.transitionDuration = 1500;
        series2.stroke = chart.colors.getIndex(6);
        series2.tensionX = 0.8;

        chart.cursor = new XYCursor();
        chart.cursor.xAxis = dateAxis;
        chart.scrollbarX = new Scrollbar();
    }

    class SampleData {
        private Date date;
        private double open;
        private double close;

        public SampleData(Date date, double open, double close) {
            this.date = date;
            this.open = open;
            this.close = close;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public double getOpen() {
            return open;
        }

        public void setOpen(double open) {
            this.open = open;
        }

        public double getClose() {
            return close;
        }

        public void setClose(double close) {
            this.close = close;
        }
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/12/demo_1068_none-1-1024x646.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

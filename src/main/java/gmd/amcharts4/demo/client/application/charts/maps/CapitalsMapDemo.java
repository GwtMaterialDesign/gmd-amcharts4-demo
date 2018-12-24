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
package gmd.amcharts4.demo.client.application.charts.maps;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.maps.geodata.WorldLow;
import gmd.amcharts4.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Container;
import gwt.material.design.amcore.client.base.Sprite;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.ammaps.client.Am4Maps;
import gwt.material.design.ammaps.client.MapChart;
import gwt.material.design.ammaps.client.base.MapPolygon;
import gwt.material.design.ammaps.client.projections.Miller;
import gwt.material.design.ammaps.client.series.MapImageSeries;
import gwt.material.design.ammaps.client.series.MapPolygonSeries;

import java.util.ArrayList;
import java.util.List;

public class CapitalsMapDemo implements ChartDemo {

    private MapChart chart;
    private MapPolygon lastSelected;
    private String targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";

    @Override
    public void generate(Widget widget) {
        chart = (MapChart) Am4Core.create(widget, Am4Maps.MapChart);

        chart.data = getData();

        // Set map definition
        chart.geodata = new WorldLow().get();

        // Set projection
        chart.projection = new Miller();

        // Create map polygon series
        MapPolygonSeries polygonSeries = (MapPolygonSeries) chart.series.push(new MapPolygonSeries());

        // Exclude Antartica
        polygonSeries.exclude = new String[]{"AQ"};

        // Make map load polygon (like country names) data from GeoJSON
        polygonSeries.useGeodata = true;

        // Configure series
        MapPolygon polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.fill = chart.colors.getIndex(0).brighten(0.5).saturate(0.2);
        polygonTemplate.strokeOpacity = 0.5;
        polygonTemplate.strokeWidth = 0.5;

        // create capital markers
        MapImageSeries imageSeries = (MapImageSeries) chart.series.push(new MapImageSeries());

        // define template
        Container imageSeriesTemplate = imageSeries.mapImages.template;
        Sprite circle = (Sprite) imageSeriesTemplate.createChild(Am4Core.Sprite);
        circle.scale = 0.6;
        circle.fill = chart.colors.getIndex(3).saturate(0.1).lighten(-0.5);
        circle.path = targetSVG;
        // what about scale...

        // set propertyfields
        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.latitude = "latitude";
        propertyField.longitude = "longitude";
        imageSeriesTemplate.propertyFields = propertyField;

        imageSeriesTemplate.horizontalCenter = "middle";
        imageSeriesTemplate.verticalCenter = "middle";
        imageSeriesTemplate.align = "center";
        imageSeriesTemplate.valign = "middle";
        imageSeriesTemplate.width = 8;
        imageSeriesTemplate.height = 8;
        imageSeriesTemplate.nonScaling = true;
        imageSeriesTemplate.tooltipText = "{title}";
        imageSeriesTemplate.fill = new Color("#000");
        imageSeriesTemplate.background.fillOpacity = 0;
        imageSeriesTemplate.background.fill = "#fff";
        imageSeriesTemplate.setStateOnChildren = true;
        imageSeriesTemplate.states.create("hover");
    }

    public JavaScriptObject getData() {
        List<Capital> list = new ArrayList<>();
        list.add(new Capital(
                "Vienna",
                48.2092,
                16.3728
        ));
        list.add(new Capital(
                "Minsk",
                53.9678,
                27.5766
        ));
        list.add(new Capital(
                "Brussels",
                50.8371,
                4.3676
        ));
        list.add(new Capital(
                "Sarajevo",
                43.8608,
                18.4214
        ));
        list.add(new Capital(
                "Sofia",
                42.7105,
                23.3238
        ));
        list.add(new Capital(
                "Zagreb",
                45.815,
                15.9785
        ));
        list.add(new Capital(
                "Pristina",
                42.666667,
                21.166667
        ));
        list.add(new Capital(
                "Prague",
                50.0878,
                14.4205
        ));
        list.add(new Capital(
                "Copenhagen",
                55.6763,
                12.5681
        ));
        list.add(new Capital(
                "Tallinn",
                59.4389,
                24.7545
        ));
        list.add(new Capital(
                "Helsinki",
                60.1699,
                24.9384
        ));
        list.add(new Capital(
                "Paris",
                48.8567,
                2.351
        ));
        list.add(new Capital(
                "Berlin",
                52.5235,
                13.4115
        ));
        list.add(new Capital(
                "Athens",
                37.9792,
                23.7166
        ));
        list.add(new Capital(
                "Budapest",
                47.4984,
                19.0408
        ));
        list.add(new Capital(
                "Reykjavik",
                64.1353,
                -21.8952
        ));
        list.add(new Capital(
                "Dublin",
                53.3441,
                -6.2675
        ));
        list.add(new Capital(
                "Rome",
                41.8955,
                12.4823
        ));
        list.add(new Capital(
                "Riga",
                56.9465,
                24.1049
        ));
        list.add(new Capital(
                "Vaduz",
                47.1411,
                9.5215
        ));
        list.add(new Capital(
                "Vilnius",
                54.6896,
                25.2799
        ));
        list.add(new Capital(
                "Luxembourg",
                49.61,
                6.1296
        ));
        list.add(new Capital(
                "Skopje",
                42.0024,
                21.4361
        ));
        list.add(new Capital(
                "Valletta",
                35.9042,
                14.5189
        ));
        list.add(new Capital(
                "Chisinau",
                47.0167,
                28.8497
        ));
        list.add(new Capital(
                "Monaco",
                43.7325,
                7.4189
        ));
        list.add(new Capital(
                "Podgorica",
                42.4602,
                19.2595
        ));
        list.add(new Capital(
                "Amsterdam",
                52.3738,
                4.891
        ));
        list.add(new Capital(
                "Oslo",
                59.9138,
                10.7387
        ));
        list.add(new Capital(
                "Warsaw",
                52.2297,
                21.0122
        ));
        list.add(new Capital(
                "Lisbon",
                38.7072,
                -9.1355
        ));
        list.add(new Capital(
                "Bucharest",
                44.4479,
                26.0979
        ));
        list.add(new Capital(
                "Moscow",
                55.7558,
                37.6176
        ));
        list.add(new Capital(
                "San Marino",
                43.9424,
                12.4578
        ));
        list.add(new Capital(
                "Belgrade",
                44.8048,
                20.4781
        ));
        list.add(new Capital(
                "Bratislava",
                48.2116,
                17.1547
        ));
        list.add(new Capital(
                "Ljubljana",
                46.0514,
                14.506
        ));
        list.add(new Capital(
                "Madrid",
                40.4167,
                -3.7033
        ));
        list.add(new Capital(
                "Stockholm",
                59.3328,
                18.0645
        ));
        list.add(new Capital(
                "Bern",
                46.948,
                7.4481
        ));
        list.add(new Capital(
                "Kiev",
                50.4422,
                30.5367
        ));
        list.add(new Capital(
                "London",
                51.5002,
                -0.1262
        ));
        list.add(new Capital(
                "Gibraltar",
                36.1377,
                -5.3453
        ));
        list.add(new Capital(
                "Saint Peter Port",
                49.466,
                -2.5522
        ));
        list.add(new Capital(
                "Douglas",
                54.167,
                -4.4821
        ));
        list.add(new Capital(
                "Saint Helier",
                49.1919,
                -2.1071
        ));
        list.add(new Capital(
                "Longyearbyen",
                78.2186,
                15.6488
        ));
        list.add(new Capital(
                "Kabul",
                34.5155,
                69.1952
        ));
        list.add(new Capital(
                "Yerevan",
                40.1596,
                44.509
        ));
        list.add(new Capital(
                "Baku",
                40.3834,
                49.8932
        ));
        list.add(new Capital(
                "Manama",
                26.1921,
                50.5354
        ));
        list.add(new Capital(
                "Dhaka",
                23.7106,
                90.3978
        ));
        list.add(new Capital(
                "Thimphu",
                27.4405,
                89.673
        ));
        list.add(new Capital(
                "Bandar Seri Begawan",
                4.9431,
                114.9425
        ));
        list.add(new Capital(
                "Phnom Penh",
                11.5434,
                104.8984
        ));
        list.add(new Capital(
                "Peking",
                39.9056,
                116.3958
        ));
        list.add(new Capital(
                "Nicosia",
                35.1676,
                33.3736
        ));
        list.add(new Capital(
                "T'bilisi",
                41.701,
                44.793
        ));
        list.add(new Capital(
                "New Delhi",
                28.6353,
                77.225
        ));
        list.add(new Capital(
                "Jakarta",
                -6.1862,
                106.8063
        ));
        list.add(new Capital(
                "Teheran",
                35.7061,
                51.4358
        ));
        list.add(new Capital(
                "Baghdad",
                33.3157,
                44.3922
        ));
        list.add(new Capital(
                "Jerusalem",
                31.76,
                35.17
        ));
        list.add(new Capital(
                "Tokyo",
                35.6785,
                139.6823
        ));
        list.add(new Capital(
                "Amman",
                31.9394,
                35.9349
        ));
        list.add(new Capital(
                "Astana",
                51.1796,
                71.4475
        ));
        list.add(new Capital(
                "Kuwait",
                29.3721,
                47.9824
        ));
        list.add(new Capital(
                "Bishkek",
                42.8679,
                74.5984
        ));
        list.add(new Capital(
                "Vientiane",
                17.9689,
                102.6137
        ));
        list.add(new Capital(
                "Beyrouth / Beirut",
                33.8872,
                35.5134
        ));
        list.add(new Capital(
                "Kuala Lumpur",
                3.1502,
                101.7077
        ));
        list.add(new Capital(
                "Ulan Bator",
                47.9138,
                106.922
        ));
        list.add(new Capital(
                "Pyinmana",
                19.7378,
                96.2083
        ));
        list.add(new Capital(
                "Kathmandu",
                27.7058,
                85.3157
        ));
        list.add(new Capital(
                "Muscat",
                23.6086,
                58.5922
        ));
        list.add(new Capital(
                "Islamabad",
                33.6751,
                73.0946
        ));
        list.add(new Capital(
                "Manila",
                14.579,
                120.9726
        ));
        list.add(new Capital(
                "Doha",
                25.2948,
                51.5082
        ));
        list.add(new Capital(
                "Riyadh",
                24.6748,
                46.6977
        ));
        list.add(new Capital(
                "Singapore",
                1.2894,
                103.85
        ));
        list.add(new Capital(
                "Seoul",
                37.5139,
                126.9828
        ));
        list.add(new Capital(
                "Colombo",
                6.9155,
                79.8572
        ));
        list.add(new Capital(
                "Damascus",
                33.5158,
                36.2939
        ));
        list.add(new Capital(
                "Taipei",
                25.0338,
                121.5645
        ));
        list.add(new Capital(
                "Dushanbe",
                38.5737,
                68.7738
        ));
        list.add(new Capital(
                "Bangkok",
                13.7573,
                100.502
        ));
        list.add(new Capital(
                "Dili",
                -8.5662,
                125.588
        ));
        list.add(new Capital(
                "Ankara",
                39.9439,
                32.856
        ));
        list.add(new Capital(
                "Ashgabat",
                37.9509,
                58.3794
        ));
        list.add(new Capital(
                "Abu Dhabi",
                24.4764,
                54.3705
        ));
        list.add(new Capital(
                "Tashkent",
                41.3193,
                69.2481
        ));
        list.add(new Capital(
                "Hanoi",
                21.0341,
                105.8372
        ));
        list.add(new Capital(
                "Sanaa",
                15.3556,
                44.2081
        ));
        list.add(new Capital(
                "Buenos Aires",
                -34.6118,
                -58.4173
        ));
        list.add(new Capital(
                "Bridgetown",
                13.0935,
                -59.6105
        ));
        list.add(new Capital(
                "Belmopan",
                17.2534,
                -88.7713
        ));
        list.add(new Capital(
                "Sucre",
                -19.0421,
                -65.2559
        ));
        list.add(new Capital(
                "Brasilia",
                -15.7801,
                -47.9292
        ));
        list.add(new Capital(
                "Ottawa",
                45.4235,
                -75.6979
        ));
        list.add(new Capital(
                "Santiago",
                -33.4691,
                -70.642
        ));
        list.add(new Capital(
                "Bogota",
                4.6473,
                -74.0962
        ));
        list.add(new Capital(
                "San Jose",
                9.9402,
                -84.1002
        ));
        list.add(new Capital(
                "Havana",
                23.1333,
                -82.3667
        ));
        list.add(new Capital(
                "Roseau",
                15.2976,
                -61.39
        ));
        list.add(new Capital(
                "Santo Domingo",
                18.479,
                -69.8908
        ));
        list.add(new Capital(
                "Quito",
                -0.2295,
                -78.5243
        ));
        list.add(new Capital(
                "San Salvador",
                13.7034,
                -89.2073
        ));
        list.add(new Capital(
                "Guatemala",
                14.6248,
                -90.5328
        ));
        list.add(new Capital(
                "Ciudad de Mexico",
                19.4271,
                -99.1276
        ));
        list.add(new Capital(
                "Managua",
                12.1475,
                -86.2734
        ));
        list.add(new Capital(
                "Panama",
                8.9943,
                -79.5188
        ));
        list.add(new Capital(
                "Asuncion",
                -25.3005,
                -57.6362
        ));
        list.add(new Capital(
                "Lima",
                -12.0931,
                -77.0465
        ));
        list.add(new Capital(
                "Castries",
                13.9972,
                -60.0018
        ));
        list.add(new Capital(
                "Paramaribo",
                5.8232,
                -55.1679
        ));
        list.add(new Capital(
                "Washington D.C.",
                38.8921,
                -77.0241
        ));
        list.add(new Capital(
                "Montevideo",
                -34.8941,
                -56.0675
        ));
        list.add(new Capital(
                "Caracas",
                10.4961,
                -66.8983
        ));
        list.add(new Capital(
                "Oranjestad",
                12.5246,
                -70.0265
        ));
        list.add(new Capital(
                "Cayenne",
                4.9346,
                -52.3303
        ));
        list.add(new Capital(
                "Plymouth",
                16.6802,
                -62.2014
        ));
        list.add(new Capital(
                "San Juan",
                18.45,
                -66.0667
        ));
        list.add(new Capital(
                "Algiers",
                36.7755,
                3.0597
        ));
        list.add(new Capital(
                "Luanda",
                -8.8159,
                13.2306
        ));
        list.add(new Capital(
                "Porto-Novo",
                6.4779,
                2.6323
        ));
        list.add(new Capital(
                "Gaborone",
                -24.657,
                25.9089
        ));
        list.add(new Capital(
                "Ouagadougou",
                12.3569,
                -1.5352
        ));
        list.add(new Capital(
                "Bujumbura",
                -3.3818,
                29.3622
        ));
        list.add(new Capital(
                "Yaounde",
                3.8612,
                11.5217
        ));
        list.add(new Capital(
                "Bangui",
                4.3621,
                18.5873
        ));
        list.add(new Capital(
                "Brazzaville",
                -4.2767,
                15.2662
        ));
        list.add(new Capital(
                "Kinshasa",
                -4.3369,
                15.3271
        ));
        list.add(new Capital(
                "Yamoussoukro",
                6.8067,
                -5.2728
        ));
        list.add(new Capital(
                "Djibouti",
                11.5806,
                43.1425
        ));
        list.add(new Capital(
                "Cairo",
                30.0571,
                31.2272
        ));
        list.add(new Capital(
                "Asmara",
                15.3315,
                38.9183
        ));
        list.add(new Capital(
                "Addis Abeba",
                9.0084,
                38.7575
        ));
        list.add(new Capital(
                "Libreville",
                0.3858,
                9.4496
        ));
        list.add(new Capital(
                "Banjul",
                13.4399,
                -16.6775
        ));
        list.add(new Capital(
                "Accra",
                5.5401,
                -0.2074
        ));
        list.add(new Capital(
                "Conakry",
                9.537,
                -13.6785
        ));
        list.add(new Capital(
                "Bissau",
                11.8598,
                -15.5875
        ));
        list.add(new Capital(
                "Nairobi",
                -1.2762,
                36.7965
        ));
        list.add(new Capital(
                "Maseru",
                -29.2976,
                27.4854
        ));
        list.add(new Capital(
                "Monrovia",
                6.3106,
                -10.8047
        ));
        list.add(new Capital(
                "Tripoli",
                32.883,
                13.1897
        ));
        list.add(new Capital(
                "Antananarivo",
                -18.9201,
                47.5237
        ));
        list.add(new Capital(
                "Lilongwe",
                -13.9899,
                33.7703
        ));
        list.add(new Capital(
                "Bamako",
                12.653,
                -7.9864
        ));
        list.add(new Capital(
                "Nouakchott",
                18.0669,
                -15.99
        ));
        list.add(new Capital(
                "Port Louis",
                -20.1654,
                57.4896
        ));
        list.add(new Capital(
                "Rabat",
                33.9905,
                -6.8704
        ));
        list.add(new Capital(
                "Maputo",
                -25.9686,
                32.5804
        ));
        list.add(new Capital(
                "Windhoek",
                -22.5749,
                17.0805
        ));
        list.add(new Capital(
                "Niamey",
                13.5164,
                2.1157
        ));
        list.add(new Capital(
                "Abuja",
                9.058,
                7.4891
        ));
        list.add(new Capital(
                "Kigali",
                -1.9441,
                30.0619
        ));
        list.add(new Capital(
                "Dakar",
                14.6953,
                -17.4439
        ));
        list.add(new Capital(
                "Freetown",
                8.4697,
                -13.2659
        ));
        list.add(new Capital(
                "Mogadishu",
                2.0411,
                45.3426
        ));
        list.add(new Capital(
                "Pretoria",
                -25.7463,
                28.1876
        ));
        list.add(new Capital(
                "Mbabane",
                -26.3186,
                31.141
        ));
        list.add(new Capital(
                "Dodoma",
                -6.167,
                35.7497
        ));
        list.add(new Capital(
                "Lome",
                6.1228,
                1.2255
        ));
        list.add(new Capital(
                "Tunis",
                36.8117,
                10.1761
        ));

        ChartData<Capital> data = new ChartData<>();
        data.setData(list);
        data.add(new DataProvider<Capital>() {
            @Override
            public String getProperty() {
                return "name";
            }

            @Override
            public JSONValue getValue(Capital object) {
                return new JSONString(object.getName());
            }
        });

        data.add(new DataProvider<Capital>() {
            @Override
            public String getProperty() {
                return "latitude";
            }

            @Override
            public JSONValue getValue(Capital object) {
                return new JSONNumber(object.getLatitude());
            }
        });

        data.add(new DataProvider<Capital>() {
            @Override
            public String getProperty() {
                return "longitude";
            }

            @Override
            public JSONValue getValue(Capital object) {
                return new JSONNumber(object.getLongitude());
            }
        });

        return data.get();
    }

    class Capital {
        private String name;
        private double latitude;
        private double longitude;

        public Capital(String name, double latitude, double longitude) {
            this.name = name;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2013/11/demo_122_none-2-1024x690.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

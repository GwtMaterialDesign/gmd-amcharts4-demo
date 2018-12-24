package gmd.amcharts4.demo.client.application.charts.misc;

import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import gmd.amcharts4.demo.client.application.charts.ChartDemo;
import gmd.amcharts4.demo.client.application.charts.propertyfields.MyCustomPropertyField;
import gwt.material.design.amcharts.client.Am4Charts;
import gwt.material.design.amcharts.client.Chart;
import gwt.material.design.amcharts.client.XYChart;
import gwt.material.design.amcharts.client.axis.CategoryAxis;
import gwt.material.design.amcharts.client.axis.ValueAxis;
import gwt.material.design.amcharts.client.bullet.Bullet;
import gwt.material.design.amcharts.client.bullet.CircleBullet;
import gwt.material.design.amcharts.client.bullet.LabelBullet;
import gwt.material.design.amcharts.client.column.Column;
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.DropShadowFilter;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.ui.Image;

import java.util.ArrayList;
import java.util.List;

public class RiskHeatmapDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);
        chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

        chart.maskBullets = false;

        CategoryAxis xAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        CategoryAxis yAxis = (CategoryAxis) chart.yAxes.push(new CategoryAxis());

        xAxis.dataFields.category = "x";
        yAxis.dataFields.category = "y";

        xAxis.renderer.grid.template.disabled = true;
        xAxis.renderer.minGridDistance = 40;

        yAxis.renderer.grid.template.disabled = true;
        yAxis.renderer.inversed = true;
        yAxis.renderer.minGridDistance = 30;

        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.categoryX = "x";
        series.dataFields.categoryY = "y";
        series.dataFields.value = "value";
        series.sequencedInterpolation = true;
        series.defaultState.transitionDuration = 3000;

        // Set up column appearance
        Column column = series.columns.template;
        column.strokeWidth = 2;
        column.strokeOpacity = 1;
        column.stroke = new Color("#ffffff");
        column.tooltipText = "{x}, {y}: {value.workingValue.formatNumber('#.')}";
        column.width = new Percent(100);
        column.height = new Percent(100);
        column.column.cornerRadius(6, 6, 6, 6);

        MyCustomPropertyField propertyField = new MyCustomPropertyField();
        propertyField.fill = "color";
        column.propertyFields = propertyField;

        // Set up bullet appearance
        CircleBullet bullet1 = (CircleBullet) series.bullets.push(new CircleBullet());

        MyCustomPropertyField propertyField1 = new MyCustomPropertyField();
        propertyField1.radius = "value";
        bullet1.circle.propertyFields = propertyField1;

        bullet1.circle.fill = new Color("#000");
        bullet1.circle.strokeWidth = 0;
        bullet1.circle.fillOpacity = 0.7;
        bullet1.interactionsEnabled = false;

        LabelBullet bullet2 = (LabelBullet) series.bullets.push(new LabelBullet());
        bullet2.label.text = "{value}";
        bullet2.label.fill = new Color("#fff");
        bullet2.zIndex = 1;
        bullet2.fontSize = 11;
        bullet2.interactionsEnabled = false;

        List<RiskMapData> list = new ArrayList<>();

        Colors colors = Colors.create(chart);
        list.add(new RiskMapData("Critical", "Very good", colors.medium, 20));
        list.add(new RiskMapData("Bad", "Very good", colors.good, 15));
        list.add(new RiskMapData("Medium", "Very good", colors.verygood, 25));
        list.add(new RiskMapData("Good", "Very good", colors.verygood, 15));
        list.add(new RiskMapData("Very good", "Very good", colors.verygood, 12));
        list.add(new RiskMapData("Critical", "Good", colors.bad, 30));
        list.add(new RiskMapData("Bad", "Good", colors.medium, 24));
        list.add(new RiskMapData("Medium", "Good", colors.good, 25));
        list.add(new RiskMapData("Good", "Good", colors.verygood, 15));
        list.add(new RiskMapData("Very good", "Good", colors.verygood, 25));
        list.add(new RiskMapData("Critical", "Medium", colors.bad, 33));
        list.add(new RiskMapData("Bad", "Medium", colors.bad, 14));
        list.add(new RiskMapData("Medium", "Medium", colors.medium, 20));
        list.add(new RiskMapData("Good", "Medium", colors.good, 19));
        list.add(new RiskMapData("Very good", "Medium", colors.good, 25));
        list.add(new RiskMapData("Critical", "Bad", colors.critical, 31));
        list.add(new RiskMapData("Bad", "Bad", colors.critical, 24));
        list.add(new RiskMapData("Medium", "Bad", colors.bad, 25));
        list.add(new RiskMapData("Good", "Bad", colors.medium, 15));
        list.add(new RiskMapData("Very good", "Bad", colors.good, 15));
        list.add(new RiskMapData("Critical", "Critical", colors.critical, 12));
        list.add(new RiskMapData("Bad", "Critical", colors.critical, 14));
        list.add(new RiskMapData("Medium", "Critical", colors.critical, 15));
        list.add(new RiskMapData("Good", "Critical", colors.bad, 25));
        list.add(new RiskMapData("Very good", "Critical", colors.medium, 19));

        ChartData<RiskMapData> data = new ChartData<>();
        data.setData(list);

        data.add(new DataProvider<RiskMapData>() {
            @Override
            public String getProperty() {
                return "x";
            }

            @Override
            public JSONValue getValue(RiskMapData object) {
                return new JSONString(object.getX());
            }
        });

        data.add(new DataProvider<RiskMapData>() {
            @Override
            public String getProperty() {
                return "y";
            }

            @Override
            public JSONValue getValue(RiskMapData object) {
                return new JSONString(object.getY());
            }
        });

        data.add(new DataProvider<RiskMapData>() {
            @Override
            public String getProperty() {
                return "color";
            }

            @Override
            public JSONValue getValue(RiskMapData object) {
                return new JSONString(object.getColor().rgba);
            }
        });

        data.add(new DataProvider<RiskMapData>() {
            @Override
            public String getProperty() {
                return "value";
            }

            @Override
            public JSONValue getValue(RiskMapData object) {
                return new JSONNumber(object.getValue());
            }
        });

        chart.data = data.get();
    }

    static class Colors {
        static Color critical;
        static Color bad;
        static Color medium;
        static Color good;
        static Color verygood;

        private Colors() {
        }

        public static Colors create(XYChart chart) {
            Colors colors = new Colors();
            critical = chart.colors.getIndex(0).brighten(-0.8);
            bad = chart.colors.getIndex(1).brighten(-0.6);
            medium = chart.colors.getIndex(1).brighten(-0.4);
            good = chart.colors.getIndex(1).brighten(-0.2);
            verygood = chart.colors.getIndex(1).brighten(0);
            return colors;
        }
    }

    class RiskMapData {
        private String y;
        private String x;
        private Color color;
        private int value;

        public RiskMapData(String y, String x, Color color, int value) {
            this.y = y;
            this.x = x;
            this.color = color;
            this.value = value;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2018/11/demo_10989_none-2.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

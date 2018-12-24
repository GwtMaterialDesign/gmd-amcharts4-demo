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
import gwt.material.design.amcharts.client.series.ColumnSeries;
import gwt.material.design.amcore.client.Am4Core;
import gwt.material.design.amcore.client.base.Percent;
import gwt.material.design.amcore.client.color.Color;
import gwt.material.design.amcore.client.color.DropShadowFilter;
import gwt.material.design.amcore.client.data.ChartData;
import gwt.material.design.amcore.client.data.DataProvider;
import gwt.material.design.amcore.client.ui.Image;

import java.util.ArrayList;

public class ColumnChartWithImagesDemo implements ChartDemo {

    private XYChart chart;

    @Override
    public void generate(Widget container) {
        // Create chart instance
        chart = (XYChart) Am4Core.create(container, Am4Charts.XYChart);

        java.util.List<Person> people = new ArrayList<>();
        people.add(new Person("John", 35654, chart.colors.next(), "https://www.amcharts.com/lib/images/faces/A04.png"));
        people.add(new Person("Damon", 65456, chart.colors.next(), "https://www.amcharts.com/lib/images/faces/C02.png"));
        people.add(new Person("Patrick", 45724, chart.colors.next(), "https://www.amcharts.com/lib/images/faces/D02.png"));
        people.add(new Person("Mark", 13654, chart.colors.next(), "https://www.amcharts.com/lib/images/faces/E01.png"));

        ChartData<Person> data = new ChartData<>();
        data.setData(people);

        data.add(new DataProvider<Person>() {
            @Override
            public String getProperty() {
                return "name";
            }

            @Override
            public JSONValue getValue(Person object) {
                return new JSONString(object.getName());
            }
        });

        data.add(new DataProvider<Person>() {
            @Override
            public String getProperty() {
                return "points";
            }

            @Override
            public JSONValue getValue(Person object) {
                return new JSONNumber(object.getPoints());
            }
        });

        data.add(new DataProvider<Person>() {
            @Override
            public String getProperty() {
                return "bullet";
            }

            @Override
            public JSONValue getValue(Person object) {
                return new JSONString(object.getBullet());
            }
        });

        data.add(new DataProvider<Person>() {
            @Override
            public String getProperty() {
                return "color";
            }

            @Override
            public JSONValue getValue(Person object) {
                return new JSONString(object.getColor().rgba);
            }
        });

        // Add data
        chart.data = data.get();

        // Create axes
        CategoryAxis categoryAxis = (CategoryAxis) chart.xAxes.push(new CategoryAxis());
        categoryAxis.dataFields.category = "name";
        categoryAxis.renderer.grid.template.disabled = true;
        categoryAxis.renderer.minGridDistance = 30;
        categoryAxis.renderer.inside = true;
        categoryAxis.renderer.labels.template.fill = new Color("#fff");
        categoryAxis.renderer.labels.template.fontSize = 20;

        ValueAxis valueAxis = (ValueAxis) chart.yAxes.push(new ValueAxis());
        valueAxis.renderer.grid.template.strokeDasharray = "4,4";
        valueAxis.renderer.labels.template.disabled = true;
        valueAxis.min = 0;

        // Do not crop bullets
        chart.maskBullets = false;

        // Remove padding
        chart.paddingBottom = 0;

        // Create series
        ColumnSeries series = (ColumnSeries) chart.series.push(new ColumnSeries());
        series.dataFields.valueY = "points";
        series.dataFields.categoryX = "name";
        MyCustomPropertyField propertyField1 = new MyCustomPropertyField();
        propertyField1.fill = "color";
        propertyField1.stroke = "color";
        series.columns.template.propertyFields = propertyField1;
        series.columns.template.column.cornerRadiusTopLeft = 15;
        series.columns.template.column.cornerRadiusTopRight = 15;
        series.columns.template.tooltipText = "{categoryX}: [bold]{valueY}[/b]";

        // Add bullets
        Bullet bullet = series.bullets.push(new Bullet());
        Image image = (Image) bullet.createChild(Am4Core.Image);
        image.horizontalCenter = "middle";
        image.verticalCenter = "bottom";
        image.dy = 20;
        image.y = new Percent(100);

        MyCustomPropertyField propertyField2 = new MyCustomPropertyField();
        propertyField2.href = "bullet";
        propertyField2.fill = "color";
        image.propertyFields = propertyField2;
        image.tooltipText = series.columns.template.tooltipText;
        image.filters.push(new DropShadowFilter());
    }

    class Person {
        private String name;
        private int points;
        private Color color;
        private String bullet;

        public Person(String name, int points, Color color, String bullet) {
            this.name = name;
            this.points = points;
            this.color = color;
            this.bullet = bullet;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public String getBullet() {
            return bullet;
        }

        public void setBullet(String bullet) {
            this.bullet = bullet;
        }
    }

    @Override
    public String getImage() {
        return "https://www.amcharts.com/wp-content/uploads/2014/01/demo_3282_none-1.png";
    }

    @Override
    public Chart getChart() {
        return chart;
    }
}

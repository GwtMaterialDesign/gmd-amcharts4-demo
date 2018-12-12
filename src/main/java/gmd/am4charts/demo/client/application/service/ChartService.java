package gmd.am4charts.demo.client.application.service;

import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.basic.*;
import gmd.am4charts.demo.client.application.charts.types.*;
import gmd.am4charts.demo.client.application.charts.types.ChordDiagramDemo;

import java.util.ArrayList;
import java.util.List;

public class ChartService {

    static List<ChartDemo> charts = new ArrayList<>();
    static List<ChartDemo> maps = new ArrayList<>();

    static {
        generateCharts();
        generateMaps();
    }

    private static void generateMaps() {

    }

    private static void generateCharts() {
        charts.add(new StackedAreaDemo());
        charts.add(new SimplePieChartDemo());
        charts.add(new BubbleChartDemo());
        charts.add(new ChordDiagramNonRibbonDemo());
        charts.add(new ChordDiagramDemo());
        charts.add(new DumbbellPlotDemo());
        charts.add(new RadialHistogramDemo());
        charts.add(new PictorialFractionDemo());
        charts.add(new PolarAreaChartDemo());
        charts.add(new LineGraphDemo());
        charts.add(new RadarLineGraphDemo());
        charts.add(new CurvedColumnsDemo());
        charts.add(new SolidGaugeDemo());
        charts.add(new SimpleTreemap());
        charts.add(new PictorialColumnChart());
        charts.add(new PictorialStackedChart());
        charts.add(new FunnelChartDemo());
        charts.add(new AngularGaugeDemo());
        charts.add(new SlicedPyramidChart());
        charts.add(new HeatmapWithLegendDemo());
        charts.add(new RadialBarChartDemo());
        charts.add(new StackedBarDemo());
        charts.add(new ClusteredBarChart());
        charts.add(new SemiPieChartDemo());
        charts.add(new NestedDonutChartDemo());
        charts.add(new DonutChartDemo());
        charts.add(new WaterfallChartDemo());
        charts.add(new SparklineDemo());
        charts.add(new ScatterChartDemo());
        charts.add(new StepLineChartDemo());
        charts.add(new GanttChartDemo());

        //TODO: Map with bubbles
        //TODO: Bullet Chart
        //TODO: RangeAreaChart
        //TODO: Population Pyramid
    }

    public static List<ChartDemo> getCharts() {
        return charts;
    }

    public static List<ChartDemo> getMaps() {
        return maps;
    }

    public static ChartDemo getChart(int index) {
        return getCharts().get(index);
    }

    public static ChartDemo getMap(int index) {
        return getMaps().get(index);
    }
}

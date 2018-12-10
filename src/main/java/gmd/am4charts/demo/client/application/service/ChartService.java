package gmd.am4charts.demo.client.application.service;

import gmd.am4charts.demo.client.application.charts.ChartDemo;
import gmd.am4charts.demo.client.application.charts.basic.*;
import gmd.am4charts.demo.client.application.charts.types.*;
import gmd.am4charts.demo.client.application.charts.types.ChordDiagramDemo;

import java.util.ArrayList;
import java.util.List;

public class ChartService {

    public static List<ChartDemo> getBasic() {
        List<ChartDemo> demos = new ArrayList<>();
        demos.add(new ChordDiagramDemo());
        demos.add(new ColorSetDemo());
        demos.add(new ColumnDemo());
        demos.add(new ConfigFieldDemo());
        demos.add(new CursorDemo());
        demos.add(new GaugeDemo());
        demos.add(new HeatDayDemo());
        demos.add(new HeatLegendDemo());
        demos.add(new LineChartDemo());
        demos.add(new PieChartDemo());
        demos.add(new RadarChartDemo());
        demos.add(new SankeyDiagramDemo());
        demos.add(new SlicedFunnelChart());
        demos.add(new SlicedPictorialChart());
        demos.add(new SlicedPyramidChart());
        demos.add(new StackedDemo());
        demos.add(new SVGRendererDemo());
        demos.add(new XYChartCustomDemo());
        return demos;
    }

    public static List<ChartDemo> getTypes() {
        List<ChartDemo> demos = new ArrayList<>();

        demos.add(new StackedAreaDemo   ());
        demos.add(new SimplePieChartDemo());
        demos.add(new BubbleChartDemo());
        demos.add(new ChordDiagramNonRibbonDemo());
        demos.add(new ChordDiagramDemo());
        demos.add(new DumbbellPlotDemo());
        demos.add(new RadialHistogramDemo());
        demos.add(new PictorialFractionDemo());
        demos.add(new PolarAreaChartDemo());
        demos.add(new LineGraphDemo());
        demos.add(new RadarLineGraphDemo());
        demos.add(new CurvedColumnsDemo());
        demos.add(new SolidGaugeDemo());
        demos.add(new SimpleTreemap());
        demos.add(new PictorialColumnChart());
        demos.add(new PictorialStackedChart());
        demos.add(new FunnelChartDemo());
        demos.add(new AngularGaugeDemo());
        demos.add(new SlicedPyramidChart());
        demos.add(new HeatmapWithLegendDemo());
        demos.add(new RadialBarChartDemo());
        demos.add(new StackedBarDemo());
        demos.add(new ClusteredBarChart());
        demos.add(new SemiPieChartDemo());
        demos.add(new NestedDonutChartDemo());
        demos.add(new DonutChartDemo());
        demos.add(new WaterfallChartDemo());
        demos.add(new SparklineDemo());
        demos.add(new ScatterChartDemo());
        demos.add(new StepLineChartDemo());
        demos.add(new GanttChartDemo());
        demos.add(new StackedDemo());

        //TODO: Map with bubbles
        //TODO: Bullet Chart
        //TODO: RangeAreaChart
        //TODO: Population Pyramid
        return demos;
    }
}

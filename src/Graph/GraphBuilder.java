package Graph;

import java.util.ArrayList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;


public class GraphBuilder {
    
        public static final int TWODIMENSIONALLINEDIAGRAM = 0;
        public static final int TWODIMENSIONALSCATTER = 1;
        public static final int TWODIMENSIONALBUBBLECHART = 2;
        public static final int TWODIMENSIONALAREACHART = 3;
        
    
    public static Chart buildGraph(GraphAdapter graphAdapter, int graphType){
        if(graphType==TWODIMENSIONALLINEDIAGRAM){
            //create Linechart
            return buildLineChart(graphAdapter);
        }else if(graphType==TWODIMENSIONALSCATTER){
            //create ScatterChart
            return buildScatterChart(graphAdapter);
        }else if(graphType==TWODIMENSIONALBUBBLECHART){
            //create BubbleChart
            //todo: it is possible to pass a third argument to size the bubbles
            return buildBubbleChart(graphAdapter);
        }else if(graphType==TWODIMENSIONALAREACHART){
            return buildAreaChart(graphAdapter);
        }return null;}  
    
    private static LineChart buildLineChart(GraphAdapter graphAdapter){
        //initialize LineChart without passing the data
        LineChart chart = new LineChart(graphAdapter.getXAxis(), graphAdapter.getYAxis());
        //create data structure to put xy chart series into
        ArrayList<XYChart.Series<Double, Double>> xySeriesContainer = new ArrayList<>();
        
        
        ArrayList<DataSeries> rawData = graphAdapter.getDataSeriesContainer();
            for (DataSeries dataSeries : rawData) {
                //get a series of data points including a label for the graph
                //create a XYChart Series put it into an XY Chart later on
                XYChart.Series<Double,Double> series = new XYChart.Series<>();
                series.setName(dataSeries.getSeriesName());
                //get a iterator for all values included into the DataSeries and
                //push them into the specialized data structure
                ArrayList<double[]> dataPoints = dataSeries.getDataPoints();
            //everything is set and done here, jetzt returning the complete chart
            for (double[] dataPoint : dataPoints) {
                series.getData().add(new XYChart.Data<>(dataPoint[0], dataPoint[1]));
            }
                chart.getData().add(series);
            }
        return chart;
    }
    
    
    
    //todo: implement
    private static ScatterChart<Double,Double> buildScatterChart(GraphAdapter graphAdapter){
        //create ScatterChart only with Axis
       ScatterChart scatterChart = new ScatterChart (graphAdapter.getXAxis(),graphAdapter.getYAxis());
       
       //get all DataSeries out of the adapter
       ArrayList<DataSeries> rawData = graphAdapter.getDataSeriesContainer();
       //iterate through all DataSeries to get DataPoints
       for (DataSeries dataSeries : rawData){
           //initilize the appropriate data structure
           XYChart.Series<Double,Double> xyChartSeries = new XYChart.Series<>();
           //give a name to the series
           xyChartSeries.setName(dataSeries.getSeriesName());
           
           //get all DataPoints
           ArrayList<double[]> dataPoints = dataSeries.getDataPoints();
           
           
           //iterator through all the DataPoints in DataSeries
           for(double[] dataPoint : dataPoints){
               //convert data from Array in the needed format
               xyChartSeries.getData().add(new XYChart.Data<Double, Double>(dataPoint[0], dataPoint[1]));
           }
           
           //put the data into the ScatterChart
           scatterChart.getData().add(xyChartSeries);
          
       }
            //ArrayList valueContainer
            //TODO: add values
       return scatterChart;
   }
    
    
    
    private static BubbleChart buildBubbleChart(GraphAdapter graphAdapter){
        //create raw BubbleChart
        BubbleChart bubbleChart = new BubbleChart(graphAdapter.getXAxis(), graphAdapter.getYAxis());
        
        //get raw data
        ArrayList<DataSeries> rawData = graphAdapter.getDataSeriesContainer();
        //extract all DataSeries and put content into appropriate format
        for(DataSeries dataSeries : rawData){
            //create series varible in right format
            XYChart.Series<Double, Double> xySeries = new XYChart.Series<>();
            //set name for the graph
            xySeries.setName(dataSeries.getSeriesName());
            //get all DataPoints out of the dataSeries
            ArrayList<double[]> dataPointContainer = dataSeries.getDataPoints();
            
            //extract all dataPoints and put them in appropriate format
            for(double[] dataPoint : dataPointContainer){
                xySeries.getData().add(new XYChart.Data<Double, Double>(dataPoint[0], dataPoint[1]));
            }
            bubbleChart.getData().add(xySeries);
        }
        return bubbleChart;
    }
    
    
    private static AreaChart buildAreaChart(GraphAdapter graphAdapter){
        //create raw BubbleChart
        AreaChart areaChart = new AreaChart(graphAdapter.getXAxis(), graphAdapter.getYAxis());
        
        //get raw data
        ArrayList<DataSeries> rawData = graphAdapter.getDataSeriesContainer();
        //extract all DataSeries and put content into appropriate format
        for(DataSeries dataSeries : rawData){
            //create series varible in right format
            XYChart.Series<Double, Double> xySeries = new XYChart.Series<>();
            //set name for the graph
            xySeries.setName(dataSeries.getSeriesName());
            //get all DataPoints out of the dataSeries
            ArrayList<double[]> dataPointContainer = dataSeries.getDataPoints();
            
            //extract all dataPoints and put them in appropriate format
            for(double[] dataPoint : dataPointContainer){
                xySeries.getData().add(new XYChart.Data<Double, Double>(dataPoint[0], dataPoint[1]));
            }
            areaChart.getData().add(xySeries);
        }
        return areaChart;
    }
    
}

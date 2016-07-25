package Graph;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.chart.NumberAxis;


public class GraphAdapter{
    
    private ArrayList<DataSeries> mDataSeriesContainer;
    private final int mDimension;
    private NumberAxis mXAxis;
    private NumberAxis mYAxis;
    
    
    public GraphAdapter(int dimensions) throws Exception{
        this.mDimension=dimensions;
        this.mDataSeriesContainer = new ArrayList();
    }
   
    private DataSeries findDataSeries(String dataSeriesLabel){
        //find DataSeries that correspond to label
        Iterator iterator=this.mDataSeriesContainer.iterator();
            while(iterator.hasNext()){
                DataSeries existingDataSeries = (DataSeries)iterator.next();
                if(existingDataSeries.getSeriesName().equals(dataSeriesLabel)){
                    return existingDataSeries;
                }
            }
            DataSeries newDataSeries = new DataSeries(dataSeriesLabel, this.mDimension);
            this.mDataSeriesContainer.add(newDataSeries);
            return newDataSeries;
    }
    
    
    public void addRowToDataSeries(String dataSeriesLabel, double... values){
        DataSeries dataSeries = this.findDataSeries(dataSeriesLabel);
        //add values for Graph
        dataSeries.addDataPoint(values);
    }
    
   
    //Getter and Setter 
    public NumberAxis getXAxis() {
        return this.mXAxis;
    }

    public void setXAxis(String xAxisLabel, int startValue, int endValue, int graduation) {
        this.mXAxis = new NumberAxis(xAxisLabel, startValue, endValue, graduation);
    }

    public NumberAxis getYAxis() {
        return this.mYAxis;
    }

    public void setYAxis(String yAxisLabel, int startValue, int endValue, int graduation) {
        this.mYAxis = new NumberAxis(yAxisLabel, startValue, endValue, graduation);
    }
    
    public ArrayList<DataSeries> getDataSeriesContainer(){
        return this.mDataSeriesContainer;
    }
}

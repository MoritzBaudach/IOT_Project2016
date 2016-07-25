/*
 * Buileded this class to carry a whole Series of data inkl label
 */

package Graph;

import java.util.ArrayList;



public class DataSeries{
    
    private String mSeriesName;
    private ArrayList<double[]> mDataPointContainer;

    private final int mDimension;
   
    public DataSeries(String seriesName,int dimension){
        this.mSeriesName=seriesName;
        this.mDimension=dimension;
        this.mDataPointContainer=new ArrayList<double[]>();
    }
    
    public void addDataPoint(double... values){
        this.mDataPointContainer.add(values);
    }
    
    public String getSeriesName(){
        return this.mSeriesName;
    }
    
   public int getDimensionOfData(){
       return this.mDimension;
   }
   
   public ArrayList<double[]> getDataPoints(){
       return this.mDataPointContainer;
   }
}

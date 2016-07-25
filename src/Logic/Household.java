package Logic;

import Exceptions.AlreadyInDatasetException;
import java.util.ArrayList;

/**
 * 
 *
 * @author krischke
 */
public class Household {
    
    private long id;    
    private String label;
    
    private ArrayList<Double> energyConsumption;
    private ArrayList<Device> devicesInHousehold;
    
    
    public Household(long id, String label){
        setId(id);
        setLabel(label);
        energyConsumption = new ArrayList<>();
        devicesInHousehold = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void addConsumption(double consumption){
        synchronized(this.energyConsumption){
            this.energyConsumption.add(consumption);
        }
    }
    
    public void cleanConsumptionDataSet(){
        synchronized(this.energyConsumption){            
            this.energyConsumption.clear();
        }
    }
    
    public void addDevice(Device device) throws AlreadyInDatasetException{
        synchronized(this.devicesInHousehold){
            if(findDevice(device.getId())==null){
                this.devicesInHousehold.add(device);
            }else{
                throw new AlreadyInDatasetException();
            }
        } 
    }
    
    public void removeDevice(Device device){
        synchronized(this.devicesInHousehold){
            try{
            if(findDevice(device.getId())!=null){
                this.removeDevice(device);              
            }}catch(NullPointerException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public Device findDevice(long id){
        Device result = null;
        for(Device temp : this.devicesInHousehold){
            if(temp.getId()==id){
                result=temp;
                break;
            }
        }
        return result;
    }
}

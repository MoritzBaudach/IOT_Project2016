/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Helper;

import Demo.ValuePusher;
import Exceptions.AlreadyInDatasetException;
import Logic.Device;
import Logic.Household;
import java.util.ArrayList;

/**
 *
 * @author krischke
 */

//todo:

//implement Messaging Queue
//add calls for performing analysis
//save intermediates in xml file

public class MainController {
    
    private static MainController instance;
    private static Object lock = new Object();
    
    //for demo data
    private static ValuePusher demoDataPusher = new ValuePusher();
    
    private ArrayList<Household> households;
    private ArrayList<Device> devices;
    
    public MainController(){
        households = new ArrayList<>();
        devices = new ArrayList<>();
    }
    
    //singleton for doing operations
    public static MainController getInstance(){
        synchronized(lock){
        if(instance==null){
            instance = new MainController();
        }
        return instance;
        }
    }
    
    //for sending events to framework
    public void startPushing(){
        demoDataPusher.startPushing();
    }
    
    //for mocking
    public void createMockData(){
        //todo --> Not supported yet
    }
    
    public void exportToExcel(){
        //todo --> Not supported yet
    }
    
    public void startComputation(){
        //todo --> Not supported yet
    }
    
    public void devicesOption(){
        //todo --> Not supported yet
    }
    
    public void consumptionOption(){
        //todo --> Not supported yet
    }
    
    public void creationOption(){
        //todo --> Not supported yet
    }    
    
    
    public void addHousehold(Household object) throws AlreadyInDatasetException{
        synchronized(this.households){
        if(this.findHousehold(object.getId())==null){
            this.households.add(object);
        }else{
            throw new AlreadyInDatasetException();
        }
        }
    }
    
    public void removeHousehold(Household object){
        synchronized(this.households){
           this.households.remove(object);   
        }
    }
    
    public Household findHousehold(long id){
        Household result = null;
        for(Household temp : this.households){
            if(temp.getId()==id){
                result=temp;
                break;
            }
        }
        return result;
    }
    
    public void addDevice(Device object) throws AlreadyInDatasetException{
        synchronized(this.devices){
        if(this.findDevice(object.getId())==null){
            this.devices.add(object);            
        }else{
            throw new AlreadyInDatasetException();
        }
        }
    }
    
    public void removeDevice(Device object){
        synchronized(this.devices){
           this.devices.remove(object);
        }
    }
    
    public Device findDevice(long id){
        Device result = null;
        for(Device temp : this.devices){
            if(temp.getId()==id){
                result=temp;
                break;
            }
        }
        return result;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Demo;

/**
 *
 * @author krischke
 */
public class ValuePusher{

public void startPushing(){
    //create a thread for every datasource and start pushing
    //Pusher for solarpanel values
    SolarPanelPusher solar = new SolarPanelPusher();
    Thread solarThread = new Thread(solar);
    solarThread.start();
    
    //pusher for household devices values
    HouseHoldDevicesPusher household = new HouseHoldDevicesPusher();
    Thread householdThread = new Thread(household);
    householdThread.start();
    
}

class SolarPanelPusher implements Runnable{

        @Override
        public void run() {
            //retrieve values from csv and push them
        }  
}

class HouseHoldDevicesPusher implements Runnable{

        @Override
        public void run() {
            //retrieve values from csv and push them
        }
    
}

}

package com.qubecad.pi;

import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.serial.SerialFactory;
import java.util.ArrayList;
import java.util.List;

public class GPSHelper {

    private String longitude = "";
    private String latitude = "";
    private String fixQuality = "";
    private String numberOfSatelites = "";
    private String time = "";
    private String type = "";
    private String bearing = "";
    private String speed = "";
    private boolean locked=false;
    
 

    public boolean isLocked() {
        return locked;
    }
    
  
    GPSHelper(String port,int baudRate) {
       

       // create an instance of the serial communications class
        final Serial serial = SerialFactory.createInstance();

        // create and register the serial data listener
        serial.addListener(new SerialDataListener() {
            @Override
            public void dataReceived(SerialDataEvent event) {
                // print out the data received to the console
                //System.out.print(event.getData());
                
              
                processMessage(event.getData());
             
                    
                   
              
               
            }            
        });
                
       
            // open the default serial port provided on the GPIO header
        try{     
            
            serial.open(port, baudRate);
        }catch(Exception e){
            
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getFixQuality() {
        return fixQuality;
    }

    public String getNumberOfSatelites() {
        return numberOfSatelites;
    }

    public String getTime() {
        return time;
    }

    
    /**
     * Processes a NMEA sentence and updates the attributes
     * @param message - The NMEA Message
     */
    private void processMessage(String message) {
         String[] messageArray;
            messageArray = message.split(",");

            if (message.startsWith("$GPGGA")) {
               
                try {
                     if (messageArray.length >= 1) {
                    this.time = messageArray[1];
                     }
                    if (messageArray.length >= 2) {
                        this.latitude = messageArray[2];
                    }
                    if (messageArray.length >= 3) {
                        this.longitude = messageArray[4];
                    }
                    if (messageArray.length >= 6) {
                        this.fixQuality = messageArray[6];
                    }
                    if (messageArray.length >= 7) {
                        this.numberOfSatelites = messageArray[7];
                    }
                    this.type = "$GPGGA";

                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
    }

public void updateStatus(String message){
    
     if (message != null||message.equals("")) {
    processMessage(message);
     }
}


}

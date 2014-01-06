/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qubecad.pi;

import com.pi4j.io.serial.*;



/**
 *
 * @author carl
 */
public class gps {
    
    public static void main(String args[]) throws InterruptedException {
        final GPSHelper dp= new GPSHelper("\\dev\\ttyUSB0",9600);
        
        boolean x=false;
        do {
            
            System.out.print (" Time: "+dp.getTime()+" Longitude: "+dp.getLongitude()+" Latitude: "+dp.getLatitude() );
            
           Thread.sleep(1000); 
        }while (x=true);
        
        
       
    }
    
}

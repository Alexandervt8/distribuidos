import java.io.*;
import java.net.*;
import java.util.*;
public class Server extends Thread{
    private SimulatorMonitor sm;
    private final int sleepMSeconds;
    private long serverTime;
    public Server(SimulatorMonitor sm){
        this.sm=sm;
        this.sleepMSeconds=10000;
        this.serverTime=System.nanoTime();
    }
    public void run(){
        while(true){
           try{
             Thread.sleep(this.sleepMSeconds);
             System.out.println("Temporizacion (servidor)"+this.serverTime);
             this.sm.setServerTime(this.serverTime);
             this.sm.calcAvgAndSet();
             this.serverTime += this.sm.getAverage();
             System.out.println("TEMPORIZACION ACORDADA (SERVIDOR): "+this.serverTime);
             this.sm.restartProcess();
           } catch(InterruptedException c){
             
           }
        }
    }
    }

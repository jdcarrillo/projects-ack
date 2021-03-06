/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rwports;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.*;
import java.util.*;




//import javax.comm.*;
/**
 *
 * @author jcarrillo
 */
public class SimpleRead implements Runnable, SerialPortEventListener {

    static CommPortIdentifier portId;
    static Enumeration portList;

    InputStream inputStream;
    OutputStream outputStream;
    SerialPort serialPort;
    Thread readThread;

    protected String divertCode = "10";
    static String TimeStamp;

    public static void main(String[] args) {
//    
//    System.load("/home/jcarrillo/NetBeansProjects/RWPorts/RXTXcomm.jar");
//    System.load("/home/jcarrillo/NetBeansProjects/RWPorts/libLinuxSerialParallel.so");
//    System.load("/home/jcarrillo/NetBeansProjects/RWPorts/libSOSerialPort.so");
//    

   
        
        String libPathProperty = System.getProperty("java.library.path");
        System.out.println("Path Libreria: " + libPathProperty);

        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals("/dev/ttyUSB0")) {
                    //if (portId.getName().equals("/dev/term/a")) {
                        SimpleRead reader = new SimpleRead();
                   // }
                }
            }
        }
    }

    public SimpleRead() {
        try {
            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
        } catch (PortInUseException e) {
            System.out.println(e);
        }
        try {
            inputStream = serialPort.getInputStream();
        } catch (IOException e) {
            System.out.println(e);
        }
        try {
            serialPort.addEventListener(this);
        } catch (TooManyListenersException e) {
            System.out.println(e);
        }
        serialPort.notifyOnDataAvailable(true);
        try {
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
        } catch (UnsupportedCommOperationException e) {
            System.out.println(e);
        }
        readThread = new Thread(this);
        readThread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[20];

                try {
                    while (inputStream.available() > 0) {
                        int numBytes = inputStream.read(readBuffer);
                    }
                    System.out.print(new String(readBuffer));
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
        }
    }
}

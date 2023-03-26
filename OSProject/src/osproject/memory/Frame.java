/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject.memory;

/**
 *
 * @author user
 */
public class Frame {
    int frameNumber;
    int ProcessNumber;
    int logicalAddress;
    

    public Frame(int frameNumber, int ProcessNumber,int logicalAddress) {
        this.frameNumber = frameNumber;
        this.ProcessNumber = ProcessNumber;
        this.logicalAddress=logicalAddress;
    }

    public Frame() {
    }
    
    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }

    public int getLogicalAddress() {
        return logicalAddress;
    }

    public void setLogicalAddress(int logicalAddress) {
        this.logicalAddress = logicalAddress;
    }
    
    

    public int getProcessNumber() {
        return ProcessNumber;
    }

    public void setProcessNumber(int ProcessNumber) {
        this.ProcessNumber = ProcessNumber;
    }
    
    
}

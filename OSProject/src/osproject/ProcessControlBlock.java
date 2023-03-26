/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import osproject.memory.PageTable;

/**
 *
 * @author user
 */
public class ProcessControlBlock {

    {
        memoryRequired = 64;
    }

    protected int processId;
    protected String ProcessName;
    protected ProcessState processStatus;
    protected String ownerProcess;
    protected int parentProcessId;
    protected int childProcessId[];
    protected int memoryRequired;
    protected int memoryAddress;
    //Process parentProcess;
    //Process childProcess;
    protected int priority;
    protected int burstTime;
    protected int arrivalTime;
    protected int waitingTime = 0;
    protected int executed = 0;
    public int[] pagesList;
    public int noOfPages=0;
    public int pageStatus=0;
    public PageTable pageTable=new PageTable();

    protected String ioDeviceStatus[];

    ////////////////////********************getters*********/////////////////////
    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public PageTable getPageTable() {
        return pageTable;
    }

    public void setPageTable(PageTable pageTable) {
        this.pageTable = pageTable;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getProcessName() {
        return ProcessName;
    }

    public void setProcessName(String ProcessName) {
        this.ProcessName = ProcessName;
    }

    public int getMemoryRequired() {
        return memoryRequired;
    }

    public void setMemoryRequired(int memoryRequired) {
        this.memoryRequired = memoryRequired;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public int getExecuted() {
        return executed;
    }

    public void setExecuted(int executed) {
        this.executed = executed;
    }

    public int[] getPagesList() {
        return pagesList;
    }

    public void setPagesList(int[] pagesList) {
        this.pagesList = pagesList;
    }

    public ProcessState getProcessStatus() {
        return processStatus;
    }

    ////////////////****************setters****************/////////////
    public void setProcessState(ProcessState state) {
        processStatus = state;
    }
}

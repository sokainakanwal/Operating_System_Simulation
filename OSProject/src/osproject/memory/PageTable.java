/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject.memory;

import java.util.HashMap;
import osproject.MainFrame;


public class PageTable {
    protected int[] pageStartIndex;
    protected int processNo;
    protected int[] pageEndIndex;
    private  int indexCounter=0;
    private  int noOfPages;
    public static int logicalAddressCounter=0;
    private int[] logicalAddress;
    
    
    
    
    public PageTable(int size){
        this.pageStartIndex =new int[size];
        this.pageEndIndex =new int[size];
        this.noOfPages=size;
        
        
    }

    public PageTable() {
    }
    
    public PageTable(int par, int processId) {
       
    }

    public int[] getStartIndex() {
        
        return pageStartIndex;
    }

    public void setStartIndex(int[] startIndex) {
        this.pageStartIndex = startIndex;
    }

    public int[] getEndIndex() {
        
        return pageEndIndex;
    }

    public void setEndIndex(int[] endIndex) {
        this.pageEndIndex = endIndex;
    }
    


    public void setProcessNo(int processNo) {
        this.processNo = processNo;
    }
    public int getIndexCounter(){
        return indexCounter;
    }
    public void setIndexCounter(int i){
        indexCounter=i;
    }
    public int getLogicalAddress(){
        return logicalAddress[indexCounter];
    }
    public int getPage(){
        int pg=-1;
        if(indexCounter<noOfPages){
        pg=indexCounter;
        indexCounter++;
        logicalAddressCounter+=MainFrame.PAGE_SIZE;
        }
        
         
        return pg; 
        
    }
    
    
    
}

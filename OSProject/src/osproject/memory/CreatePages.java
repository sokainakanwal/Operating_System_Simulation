/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject.memory;

import java.awt.Color;
import java.util.Iterator;
import osproject.ProcessControlBlock;

/**
 *
 * @author user
 */
public class CreatePages {

    MemoryStatusGui main;

    public CreatePages(MemoryStatusGui main) {
        this.main = main;

    }

    public void ComputePages(ProcessControlBlock process) {
        int noOfPages = (int) process.getMemoryRequired() / main.pagesize + ((process.getMemoryRequired() % main.pagesize == 0) ? 0 : 1);

        process.noOfPages = noOfPages;
        //add process tp process list 
        main.processQueue.add(process);

        //create page table
        PageTable tempTable = new PageTable(noOfPages);
        for (int i = 0, j = 0; i < noOfPages; i++) {
            tempTable.pageStartIndex[i] = j;
            j += main.pagesize;
            tempTable.pageEndIndex[i] = j;

        }

        process.pageTable = tempTable;

        //end create page table
        main.processMemoryLabel[main.counter].setText(process.getMemoryRequired() + "KB");

        main.noOfPagesLabel[main.counter].setText(noOfPages + "");

        //increment memory used
        if (main.usedMemory <= main.memorySize) {
            main.usedMemory += process.getMemoryRequired();
            main.updateMemoryInfo();
        }

        OccupyFrames(process);

    }

    void NextStep() {

        if (!main.processList.isEmpty()) {
            int processNum = (int) (Math.random() * main.processList.size());

            ProcessControlBlock pcb = main.processList.get(processNum);
            int pageNum=(int) (Math.random() * pcb.noOfPages);

            //int pge=pcb.getPageTable().getPage();
            main.cpuLabel.setText("" + pcb.getProcessName() + ":" + pageNum);

            if (CkeckAlreadyInMemory(processNum,pageNum)) {
                main.pagehit.setText("PAGE HIT");
            } else {
                main.pagehit.setText("PAGE FAULT");
                PageFault(pcb);
            }

            

        }
    }

    void highLightText(int index,Color color) {
        main.LALabel[index].setOpaque(true);
        main.frameIndex[index].setOpaque(true);
        main.framelabel[index].setOpaque(true);
        
        main.frameIndex[index].setBackground(color);
        main.framelabel[index].setBackground(color);
        main.LALabel[index].setBackground(color);
        
        main.LALabel[index].repaint();
        main.frameIndex[index].repaint();
        main.framelabel[index].repaint();
        
        
        
    }

    private void OccupyFrames(ProcessControlBlock process) {

        if (main.framesList.size() < 10) {

            //simply adding element bcause some frame are empty
            if (process.pageStatus < 10) {
                ///load pages to frame list until it full
                //oor unitl no of loaded pages are less then total no o pages
                

                while (main.framesList.size() < 10 && process.pageStatus < process.noOfPages) {

                    int page = process.getPageTable().getPage();
                    if (page >= 0) {
                        main.framesList.add(new Frame(page, process.getProcessId(), PageTable.logicalAddressCounter));
                        process.pageStatus++;
                    }else if(page==-1){
                        //main.framesList.remove(main.framesList.indexOf(process));
                        updateFameGui();
                        break;
                                
                    } else {
                       // process.pageTable.setIndexCounter(0);
                        //terminate loop if no of pages are fully loaded into mmory
                        break;
                    }
                }
            }
            updateFameGui();

            ////if frames are fully occupied
        } else if (process.pageStatus < process.noOfPages) {
            
            //increment on page status
            process.pageStatus++;
            //remove peak element
            main.framesList.remove(MemoryStatusGui.LRUindex);

            Frame f = new Frame(process.getPageTable().getPage(), process.getProcessId(), PageTable.logicalAddressCounter);
            //Adding element at poll
            main.framesList.add(MemoryStatusGui.LRUindex, f);
            
            MemoryStatusGui.LRUindex++;

            //update view 
            updateFameGui();
        }
    }

    private void updateFameGui() {

        Iterator itr = main.framesList.iterator();
        int count = 0;
        while (count < main.framesList.size()) {
            Frame frame = (Frame) itr.next();
            
            main.frameIndex[count].setText("P" + frame.ProcessNumber + " : Page" + frame.frameNumber);
            main.LALabel[count].setText("" + frame.logicalAddress);
            
            //change background color
            
             if(count==MemoryStatusGui.LRUindex){
                highLightText(count,Color.PINK);
            }else{
                highLightText(count,Color.YELLOW);
            }
             if(MemoryStatusGui.LRUindex==9){
                 MemoryStatusGui.LRUindex=0;
             }
            count++;

        }
    }

    private boolean CkeckAlreadyInMemory(int processNum,int pageNumber) {

        Iterator itr = main.framesList.iterator();
        int count = 0;
        while (count < main.framesList.size()) {
            Frame frame = (Frame) itr.next();
            if (processNum == frame.ProcessNumber) {
                if(frame.frameNumber==pageNumber)
                      return true;

            }
            count++;

        }
        return false;

    }

    private void PageFault(ProcessControlBlock process) {

            
            //increment on page status
            process.pageStatus++;
            //remove peak element
            main.framesList.remove(MemoryStatusGui.LRUindex);
            int page=process.getPageTable().getPage();
            if(page<=-1){
                main.processList.remove(main.processList.indexOf(process));
            }else{

            Frame f = new Frame(page, process.getProcessId(), PageTable.logicalAddressCounter);
            //Adding element at poll
            main.framesList.add(MemoryStatusGui.LRUindex, f);
            
            MemoryStatusGui.LRUindex++;

            //update view 
            updateFameGui();
            }
            
    }

}

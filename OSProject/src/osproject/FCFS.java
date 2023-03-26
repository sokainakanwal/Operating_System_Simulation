/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class FCFS {
   public static Process currentProcess;
    ShowProcessStatus main;
    int totalbt=0;
    public ProcessControlBlock pcb=new ProcessControlBlock();
    int waitingTime=0;
    public void setMain(ShowProcessStatus main) {
        this.main = main;
        initView();
    }
    public void initView(){
         Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                execute();
            }
            };
        timer.schedule(task, 0, 800);
    }
    public void execute(){
        
        if(currentProcess.pcb.processStatus!=ProcessState.RUNNING){
            if(!main.fcQueues.readyQueue.isEmpty()){
        currentProcess=main.fcQueues.readyQueue.poll();
        totalbt+=currentProcess.pcb.burstTime;
        currentProcess.pcb.setProcessState(ProcessState.RUNNING);
            }
        currentProcess.pcb.waitingTime=waitingTime;
        } else{
           if(currentProcess.pcb.executed<currentProcess.pcb.burstTime){
               currentProcess.pcb.executed+=1;
               waitingTime++;
        main.FCupdateUI();
           }else{
               currentProcess.pcb.setProcessState(ProcessState.TERMINATED);
           }
        }  
        if(main.fcQueues.readyQueue.isEmpty()&&currentProcess.pcb.processStatus==ProcessState.TERMINATED){
            int wting=(int)(waitingTime/main.processCount);
            int totalBearst=(int)(totalbt/main.processCount);
            main.showFinalResult(wting,totalBearst,totalbt);
        }
       
        
        
    }

   

    
    
    
    
}

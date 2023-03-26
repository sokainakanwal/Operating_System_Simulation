/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import static osproject.FCFS.currentProcess;

/**
 *
 * @author user
 */
class NonPreemptivePriorityScheduling {
      public static ProcessControlBlock currentProcess=null;
      int timer=0;
      int totalbt=0;
      public ShowProcessStatus mainClass;
      List<ProcessControlBlock> processes = new ArrayList<>();
      
      
      public void setData(ShowProcessStatus mainClass){
          this.mainClass=mainClass;
          
            Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
                execute();
            }
            };
        timer.schedule(task, 0, 800);
          
      }
      
      public void execute() {
             
             if(currentProcess==null||currentProcess.processStatus==ProcessState.TERMINATED){
                 if(!mainClass.queues.readyQueue.isEmpty())
                 currentProcess=mainClass.queues.readyQueue.poll();
                 currentProcess.processStatus=ProcessState.RUNNING;
                 currentProcess.waitingTime=timer;
                 totalbt+=currentProcess.burstTime;
             }else if(currentProcess.executed<currentProcess.burstTime){
                 timer++;
                 currentProcess.executed+=1;
                 mainClass.UpdateUI();
                 
    
     
             }else{
                 currentProcess.setProcessState(ProcessState.TERMINATED);
             }
             if(mainClass.queues.readyQueue.isEmpty()&&currentProcess.processStatus==ProcessState.TERMINATED){
            int wting=(int)(timer/ShowProcessStatus.processCount);
            int totalBearst=(int)(totalbt/ShowProcessStatus.processCount);
            mainClass.showFinalResult(wting,totalBearst,totalbt);
                 
             }
             
         
      }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author user
 */
public class PriorityScheduling {
    ArrayList<ProcessControlBlock> processList;
    public static ProcessControlBlock currentProcess;
    ShowProcessStatus mainclass;
    int counter=0;
    ProcessControlBlock nextProcess = null;
    
    PriorityScheduling(ArrayList<ProcessControlBlock> processList,ShowProcessStatus mainclass){
        this.processList=processList;
        this.mainclass=mainclass;
       Simulate(); 
    }
    public void Simulate(){
         
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            public void run() {
               
   //        CheckForHeightPriority();
            }
            };
        timer.schedule(task, 0, 1000);
    }
    
    
    
    
    
//    
//            private void CheckForHeightPriority() {
//                
//                //if ready queue is not empty
//                if(!mainclass.queues.readyQueue.isEmpty()){
//                    
//                    //if new process is arrived
//                 if(counter==mainclass.queues.readyQueue.peek().arrivalTime){
//                     
//                        ///if new arrival process is ready to run
//                     if(CouldRun(mainclass.queues.readyQueue.peek())){
//                         
//                         //if current process is not completely executed
//                         if(currentProcess.executed<currentProcess.burstTime){
//                             currentProcess.setProcessState(ProcessState.READY);
//                             mainclass.queues.readyQueue.add(currentProcess);
//                             
//                             currentProcess=mainclass.queues.readyQueue.poll();
//                             currentProcess.setProcessState(ProcessState.RUNNING);
//                             Move();
//                         }
//                         //if current process is completed
//                         else{
//                             currentProcess=mainclass.queues.readyQueue.poll();
//                             currentProcess.setProcessState(ProcessState.RUNNING);
//                             Move();
//                         }
//                         
//                         
//                     }
//                     // if new arrival is not ready to run save to ready queue (already in ready queue do notihing)
//                         
//                     
//                 }else{
//                     Move();
//                 }
//                 
//                }
//                
//            }
  public void Move(){
      if(currentProcess.executed<=currentProcess.burstTime){
          counter++;
      currentProcess.executed+=1;
       mainclass.UpdateUI();
      }
  }
        
    
    
    
   
    
    
    
    
}

package osproject;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Process {
    ProcessControlBlock pcb;
     Process runningProcess;
    Process(){
        pcb=new ProcessControlBlock();
        
    }
  
    public boolean CreateProcess(ProcessQueues ques){
        
        //////given a random id 
        try{
        int id=(int)(Math.random()*10);
   
        pcb.processId=id;
        
        ///given process memory required
        getNameAndSizeOfProcess();
        
        pcb.ownerProcess = "null";
        pcb.parentProcessId = 0;
        pcb.childProcessId = new int[0];
        pcb.priority = 0;
        if(ProcessFrame.firstProcess){
            ProcessFrame.firstProcess=false;
           pcb.processStatus=ProcessState.RUNNING;
           System.out.print("occure first time");
           ProcessFrame.runningProcess=this;
        }else{
          pcb.processStatus=ProcessState.READY;
          ques.getReadQueue().add(this);
        }
        
    
        }catch(Exception e){
            return false;
        }
     return true;
        
    }
    public void destroyProcess(Process process,ProcessQueues ques){
      if(process!=null){
          process.pcb.setProcessState(ProcessState.TERMINATED);
          dispatchProcess(ques);
      }  
    }
    public void resumeProcess(ProcessQueues ques){
        
     if(!ques.getBlockQueue().isEmpty()){
      Process p=ques.getBlockQueue().poll();
      p.pcb.processStatus=ProcessState.READY;
      ques.getReadQueue().add(p);
     
      }else{
          showMessage("No any prcess in the Blocked Queue");
      }
     
    }
    
    
    public void suspendProcess(Process process,ProcessQueues ques){
        process=ProcessFrame.runningProcess;
        if(process!=null){
            
        process.pcb.setProcessState(ProcessState.SUSPENDED);
        ques.getSuspendQueue().add(process);
        dispatchProcess(ques);
        }else{
          showMessage("No any prcess in the Blocked Queue");
      }
        
    }
    
    
    public void blockProcess(Process process,ProcessQueues ques){
        if(process!=null){
            process.pcb.setProcessState(ProcessState.BLOCKED);
            ques.getBlockQueue().add(ProcessFrame.runningProcess);
           dispatchProcess(ques);
        }
    }
    
    
    public void wakeupProcess(ProcessQueues ques){
        
        if(!ques.getSuspendQueue().isEmpty()){

        Process p=ques.suspendQueue.poll();
        p.pcb.processStatus=ProcessState.RUNNING;
        ques.getReadQueue().add(p);
                 
        }else{
            showMessage("No any process in suspended queue");
        }
    }
    
    
    
    
    public void dispatchProcess(ProcessQueues ques){
       
       if(!ques.readyQueue.isEmpty()){
       Process p=ques.readyQueue.poll();
           p.pcb.setProcessState(ProcessState.RUNNING);
           ProcessFrame.runningProcess=p;
       }else{
           showMessage("No any process is created");
       }
    }
    
    
    public void changeProcessPriority(Process p,ProcessQueues ques){
        
    }
    public void processCommunicationWithOtherProcess(){
        
    }
    
    
     public void showProcessStateDialog(ProcessState i,String name){
         String message="";
         if(null!=i)switch (i) {
            case READY -> {
               message="ready"; 
             }
            case RUNNING -> {
               message="Running"; 
             }
            case NEW -> {   
               message="New"; 

             }
            case SUSPENDED -> {
               message="Suspended"; 
             }
            case BLOCKED -> {
                message="Blocked";
             }
            case TERMINATED -> {
                message="Terminated";
             }
            case WAITING -> {
                message="waiting";
             }
            default -> {
                message="process is not created";
             }
            
        }
          JOptionPane.showMessageDialog(null, "Process "+name+"is in "+message+" state","ProcessStatus",JOptionPane.INFORMATION_MESSAGE);

      
         
     }
     public void showMessage(String msg){
         JOptionPane.showMessageDialog(null, msg);
         
     }
     
     
     Process getRunningProcess(){
         return runningProcess;
     }
     private void setRunningProcess(Process p){
         runningProcess=p;
     }
     
     public void getNameAndSizeOfProcess(){
              try{
      /* String sizeProcess = (String )JOptionPane.showInputDialog(null, null,"Memory required to process",JOptionPane.QUESTION_MESSAGE);
        pcb.memoryRequired=(int)Integer.parseInt(sizeProcess);*/
      
         JTextField processName = new JTextField(12);
      JTextField processSize = new JTextField(10);

      JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Process Name:"));
      myPanel.add(processName);
      myPanel.add(Box.createVerticalStrut(15)); // a spacer
      myPanel.add(new JLabel("Process Size in KB:"));
      myPanel.add(processSize);

      int result = JOptionPane.showConfirmDialog(null, myPanel, 
               "Please Enter name and size of process", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
          pcb.ProcessName=processName.getText();
          pcb.memoryRequired=Integer.parseInt(processSize.getText());
      }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "wrong size so default size is used");
        }
     }
    
    
}

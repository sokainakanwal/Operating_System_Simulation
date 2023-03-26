/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author user
 */
public class PriorityUtilQueues {
    
    PriorityQueue<ProcessControlBlock> readyQueue;
    Queue<ProcessControlBlock> blockQueue;
    Queue<ProcessControlBlock> suspendQueue;
    
    PriorityUtilQueues(){
      readyQueue=new PriorityQueue<ProcessControlBlock>(new Comparator<ProcessControlBlock>(){
          @Override
          public int compare(ProcessControlBlock o1, ProcessControlBlock o2) {
              return o1.priority-o2.priority;
          }
          
      });
      blockQueue=new LinkedList<ProcessControlBlock>();
      suspendQueue=new LinkedList<ProcessControlBlock>();
    }

    public Queue<ProcessControlBlock> getReadyQueue() {
        return readyQueue;
    }

    public Queue<ProcessControlBlock> getBlockQueue() {
        return blockQueue;
    }

    public Queue<ProcessControlBlock> getSuspendQueue() {
        return suspendQueue;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package osproject;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author user
 */
public class ProcessQueues {

    Queue<Process> readyQueue;
    Queue<Process> blockQueue;
    Queue<Process> suspendQueue;

    ProcessQueues() {
        readyQueue = new LinkedList<Process>();
        blockQueue = new LinkedList<Process>();
        suspendQueue = new LinkedList<Process>();
    }

    public Queue<Process> getReadQueue() {
        return readyQueue;
    }

    public Queue<Process> getBlockQueue() {
        return blockQueue;
    }

    public Queue<Process> getSuspendQueue() {
        return suspendQueue;
    }

}

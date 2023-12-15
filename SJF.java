package org.example;
import java.util.*;
public class SJF extends Scheduler
{
    public SJF(List<Process>processes, int context_switch , ProcessRecord process) {super(processes, context_switch, new PriorityQueue< Process>(new Comparator< Process>() {
        public int compare(Process a, Process b) {
            if (a.getRemainingTime() != b.getRemainingTime()) {
                return (a.getRemainingTime()-b.getRemainingTime());}
            if (a.getArrivalTime() != b.getArrivalTime()) {
                return (a.getArrivalTime()-b.getArrivalTime());}return 0;}}), process);
    }
    protected boolean next() {return false;}
}

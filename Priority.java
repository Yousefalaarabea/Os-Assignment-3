package org.example;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
public class Priority extends Scheduler {
    public Priority(List< Process> processes, int context_switch_time, int Aging, ProcessRecord process) {super(processes, context_switch_time, new PriorityQueue< Process>(new Comparator< Process>() {
        public int compare( Process a,  Process b) {
            final var priorityA = a.getPriority() + a.getArrivalTime()/Aging;
            final var priorityB = b.getPriority() + b.getArrivalTime()/Aging;
            if (priorityA != priorityB) {return priorityA - priorityB;}
            if (a.getArrivalTime() != b.getArrivalTime()) {return a.getArrivalTime() - b.getArrivalTime();} return 0;}}), process);
    }
    protected boolean next() {
        return false;
    }
}
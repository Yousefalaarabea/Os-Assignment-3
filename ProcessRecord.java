package org.example;
import java.util.*;
public class ProcessRecord{
    private final List< Process>time;
    ProcessRecord() {
        time = new ArrayList<>();
    }
    public List<String> get_order() {
        return time.stream().map( Process::getName).toList();
    }
    public double get_avg_waiting_time() {
        return time.stream().distinct().mapToInt( Process::getWaitingTime).average().orElse(0.0);
    }
    public double get_avg_turnaround_time() {
        return time.stream().distinct().mapToInt(p->p.getWaitingTime()+p.getBurstTime()).average().orElse(0.0);
    }
    public List< Process>get_processes() {
        return time.stream().distinct().toList();
    }
    public void add_to_queue( Process process) {
        time.add(process);
    }
}

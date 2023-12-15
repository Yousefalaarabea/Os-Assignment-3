package org.example;
public class Process {
    private int current_burst_time;
    private int current_burst;
    private int waiting_time;
    private int remaining_time;
    private int last_run_time;
    private boolean active;
    private boolean inactive;
    //////////// input taken /////////
    private final String process_name;
    private final int process_id;
    private final int arrival_time;
    private final int priority_num;
    private final int burst_time;
    private int quantum_time;
    //////////////////////////////////

    public Process(int processId, String name, int arrivalTime, int priorityNum, int burstTime, int quantumTime) {
        this.process_id = processId;
        this.process_name = name;
        this.arrival_time = arrivalTime;
        this.priority_num = priorityNum;
        this.burst_time = burstTime;
        this.quantum_time = quantumTime;
        this.remaining_time = burstTime;
        this.current_burst = 0;
        this.current_burst_time = 0;
        this.waiting_time = 0;
        this.last_run_time = 0;
    }
    public int getPid() {return process_id;}

    public String getName() {return process_name;}

    public int getArrivalTime() {return arrival_time;}

    public int getPriority() {return priority_num;}
    public int getBurstTime() {return burst_time;}
    public int getQuantum() {return quantum_time;}
    public int getCurrentBurst() {return current_burst;}
    public int getCurrentBurstTime() {return current_burst_time;}
    public void setQuantum(int quantumTime) {this.quantum_time = quantumTime;}
    public void setCurrentBurst(int currentBurstStart) {this.current_burst = currentBurstStart;}
    public void resetCurrentBurstTime() {this.current_burst_time = 0;}
    public int getWaitingTime() {return waiting_time;}
    public void setWaitingTime(int waitingTime) {this.waiting_time = waitingTime;}
    public int getLastRunTime() {return last_run_time;}
    public int getRemainingTime() {return remaining_time;}
    public void setRemainingTime(int remainingTime) {this.remaining_time = remainingTime;}
    public void setLastRunTime(int lastRunTime) {this.last_run_time = lastRunTime;}
    public boolean isInactive() {return inactive;}
    public void setInactive(boolean inactive) {this.inactive = inactive;}

    public boolean isActive() {return active;}
    public void setActive(boolean active) {this.active= active;}

    public void increaseCurrentBurstTime() {this.current_burst_time++;}
    public void decreaseRemainingTime() {this.remaining_time--;}
}


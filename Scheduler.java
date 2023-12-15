package org.example;
import java.util.*;
import java.util.stream.*;
public abstract class Scheduler {
    private final Queue< Process> readyQueue;
    private  Process activeProcess;
    private final List< Process> inactiveProcesses;
    private final int contextSwitchTime;
    private final ProcessRecord process;
    private final PriorityQueue< Process> PQueue;
    private int time;
    public Scheduler(List<Process> processes, int context_switch_time , ProcessRecord process) {
        this(processes, context_switch_time, new LinkedList<>(), process);
    }
    public Scheduler(List<Process> processes, int context_switch_time, Queue<Process> rqueue, ProcessRecord p1) {
        process = p1;
        PQueue = new PriorityQueue<>(Comparator.comparingInt(getArrivalTime));
        contextSwitchTime = context_switch_time;
        inactiveProcesses = new ArrayList<>();
        PQueue.addAll(processes);
        readyQueue = rqueue;
    }

    protected abstract boolean next();
    private void executeProcess() {
        if (activeProcess != null) {
            activeProcess.increaseCurrentBurstTime();
            activeProcess.decreaseRemainingTime();
            if (activeProcess.getRemainingTime() <= 0) {
                endProcess(activeProcess);
                activeProcess = null;}
        }time++;
    }

    public final void addProcesses() {
        while (true) {
            fillQueue();
            if (activeProcess == null || (!readyQueue.isEmpty() && next())) {doContextSwitch();}
            executeProcess();
            if (activeProcess == null && readyQueue.isEmpty() && PQueue.isEmpty()) {break;}
        }
    }

    private void fillQueue() {
        while (!PQueue.isEmpty() && PQueue.peek().getArrivalTime() <= time) {
             Process p1 = PQueue.poll();
            p1.setActive(true);
            p1.setWaitingTime(-time);
            readyQueue.add(p1);
        }
    }
    protected Process getNextProcess() {
        if (!readyQueue.isEmpty()) {
            return readyQueue.poll();
        } else {
            return null;
        }
    }
    private void doContextSwitch() {
         Process p1 = activeProcess;
        activeProcess = getNextProcess();
        if (activeProcess != null) {
            activeProcess.resetCurrentBurstTime();
            activeProcess.setCurrentBurst(time);
            activeProcess.setWaitingTime(activeProcess.getWaitingTime() + (time-activeProcess.getLastRunTime()));
            process.add_to_queue(activeProcess);
        }
        if (p1 != null) {
            p1.setLastRunTime(time);
            readyQueue.add(p1);
            time += contextSwitchTime;
        }
    }
    protected final Process getActiveProcess() {
        return activeProcess;
    }
    protected final Process peekReadyQueue() {
        return readyQueue.peek();
    }

    protected final void endProcess( Process process) {
        process.setInactive(true);
        inactiveProcesses.add(process);
    }
    protected final Stream< Process> getReadyQueue() {
        return readyQueue.stream();
    }
    protected final boolean removeProcess( Process process) {
        return readyQueue.remove(process);
    }
    protected final int getTime() {
        return time;
    }
}

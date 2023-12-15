import java.util.*;
import java.util.ArrayList;
import java.util.Scanner;
/*
        SJF
        p1 0 7
        p2 2 4
        p3 4 1
        p4 5 4
        p1->p3->p2->p4

        priority
        p1 3 10
        p2 1 1
        p3 4 2
        p4 5 1
        p5 2 5
        p2->p5->p1->p3->p4
        */
public class Main{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int scheduler = 0;
        while(scheduler != 5) {
            System.out.println("1-SJF");
            System.out.println("2-SRTF");
            System.out.println("3-Priority Scheduler");
            System.out.println("4-AG");
            System.out.println("5-Exit");
            System.out.println("Choose one option: ");
            scheduler = scanner.nextInt();
            if (scheduler == 1) {
                final var sjfProcesses = new ArrayList<Process>();
                int numOfProcessesSJF;

                System.out.print("Enter the number of processes: ");
                numOfProcessesSJF = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < numOfProcessesSJF; i++) {
                    System.out.println("Enter name, arrival time and burst time for Process " + (i + 1) + " : ");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Arrival Time: ");
                    int arrivalTime = scanner.nextInt();
                    System.out.print("Burst Time: ");
                    int burstTime = scanner.nextInt();

                    sjfProcesses.add(new Process(i + 1, name, arrivalTime, 0, burstTime, 0));
                    scanner.nextLine();
                }

                ProcessRecord sjf = new ProcessRecord();
                final var sjfScheduler = new SJF(sjfProcesses, 0, sjf);
                sjfScheduler.addProcesses();
                printChart(sjf);

            }
            ////////////////////////////////////////////////////////////////////////////////////////////////////

            else if (scheduler == 2) {

            }

            ///////////////////////////////////////////////////////////////////////////////////////////////////

            else if (scheduler == 3) {
                final var priorityProcesses = new ArrayList<Process>();
                int numOfProcessesPriority;

                System.out.print("Enter the number of processes: ");
                numOfProcessesPriority = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < numOfProcessesPriority; i++) {
                    System.out.println("Enter name, priority, burst time for Process " + (i + 1) + " :");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Priority : ");
                    int priority = scanner.nextInt();
                    System.out.print("Burst Time: ");
                    int burstTime = scanner.nextInt();

                    priorityProcesses.add(new Process(i + 1, name, 0, priority, burstTime, 0));
                    scanner.nextLine();
                }

                ProcessRecord priorityRecord = new ProcessRecord();
                final var priorityScheduler = new Priority(priorityProcesses, 0, 10, priorityRecord);
                priorityScheduler.addProcesses();
                printChart(priorityRecord);

            }
            //////////////////////////////////////////////////////////////////////////////////////////////////
            else if(scheduler == 4){

            }
        }
    }

    private static void printChart(ProcessRecord pr) {
        for (String p : pr.get_order()) {
            System.out.printf("%s ", p);
        }
        System.out.println();
        List<Process> processes = pr.get_processes().stream().sorted(Comparator.comparing(getName)).toList();
        System.out.println("\nProcess     Waitting Time");
        for (Process p : processes) {
            System.out.printf("%s          %d\n", p.getName(), p.getWaitingTime());
        }
        System.out.printf("Average Waiting Time:: %d ms", (int)pr.get_avg_waiting_time());
        System.out.print("\n\n");
        System.out.println("Process     Turnaround Time");
        for (Process p : processes) {
            System.out.printf("%s          %d\n", p.getName(), p.getWaitingTime() + p.getBurstTime());
        }
        System.out.printf("Average Turnaround Time:: %d ms", (int)pr.get_avg_turnaround_time());
        System.out.print("\n\n");
    }
}
package Concurrency;

import java.util.Scanner;

public class NumberPrinter {

    private int endNum;
    private int startNum;

    public NumberPrinter(int startNum, int endNum) {
        this.endNum = endNum;
        this.startNum = startNum;
    }

    public synchronized void printEvenNumber() {
        while (startNum <= endNum) {
            if (startNum % 2 == 0) {
                System.out.println("Even thread " + startNum++);
                notifyAll();
            } else {
                while (startNum % 2 != 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // restore interrupt
                        return; // exit thread cleanly
                    }
                }
            }
        }
    }

    public synchronized void printOddNumber() {
        while (startNum <= endNum) {
            if (startNum % 2 != 0) {
                System.out.println("Odd thread " + startNum++);
                notifyAll();
            } else {
                while (startNum % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter end num = ");
        int n = sc.nextInt();

        NumberPrinter printer = new NumberPrinter(0, n);

        Thread evenThread = new Thread(() -> printer.printEvenNumber());
        Thread oddThread = new Thread(() -> printer.printOddNumber());

        evenThread.start();
        oddThread.start();
    }
}
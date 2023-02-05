package com.example.lab8_2;

public class MyThread implements Runnable{

    Thread thrd;
    int k;
    double result = 0;
    public HelloController main_controller;

    volatile boolean suspended; //отвечает за приостановление
    volatile boolean stopped; //отвечает за прекращение исполнение потока

    MyThread (String name, int number, HelloController contr){
        thrd = new Thread(this, name);
        suspended = false;
        stopped = false;
        k = number;
        main_controller = contr;
        thrd.start(); //запускает поток, вызывая метод run()
    }


    @Override
    public void run() {
        System.out.println(thrd.getName() + " starting.");
        try{
            for (int i=1; i<=k; i++){

                result += Math.pow(-1, i-1) * 1/Math.pow(i, 2);
                main_controller.changeSum(result);
                Thread.sleep(1000);

                //проверка
                synchronized (this) {
                    while (suspended){ //приостановление
                        wait();
                    }
                    if (stopped) break;
                }
            }

        } catch (InterruptedException exception) {
            System.out.println(thrd.getName() + " interrupted.");
        }
        System.out.println(thrd.getName() + " exiting.");
    }

    // остановить поток
    synchronized void mystop(){
        stopped = true;
        suspended = false;
        notify();
    }

    // приостановить поток
    synchronized void mysuspend() throws InterruptedException {
        suspended = true;
        Thread.sleep(1000);
    }

    // возобновить потока
    synchronized void myresume() throws InterruptedException {
        suspended = false;
        notify();
        Thread.sleep(1000);
    }
}

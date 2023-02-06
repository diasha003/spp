
class MyThread implements Runnable{

    Thread thrd;
    int k;
    float sum = 0;

    volatile boolean suspended; //отвечает за приостановление
    volatile boolean stopped; //отвечает за прекращение исполнение потока

    MyThread (String name, int number){
        thrd = new Thread(this, name);
        suspended = false;
        stopped = false;
        k = number;
        thrd.start(); //запускает поток, вызывая метод run()
    }


    @Override
    public void run() { //точка входа в поток

        System.out.println(thrd.getName() + " starting.");
        try{
            for (int i=1; i<=k; i++){
                sum += Math.pow(-1, i-1) * 1/Math.pow(i, 2);
                System.out.println(i + " - " + sum);
                Thread.sleep(1000);

                //проверка
                synchronized (this) {
                    while (suspended){
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
    synchronized void mysuspend() {
        suspended = true;

    }

    // возобновить потока
    synchronized void myresume(){
        suspended = false;
        notify();
    }

}


public class Main {

    public static void main(String[] args) {
        MyThread obi = new MyThread("My thread", 5);

        try {

            Thread.sleep(1000);

            obi.mysuspend();
            System.out.println("Suspending thread.");
            Thread.sleep(1000);

            obi.myresume();
            System.out.println("Resuming thread.");
            Thread.sleep(1000);

            obi.mysuspend();
            System.out.println("Suspending thread.");
            Thread.sleep(1000);

            obi.myresume();
            System.out.println("Resuming thread.");
            Thread.sleep(1000);

            obi.mysuspend();
            System.out.println("Stopping thread.");
            obi.mystop();

        } catch (InterruptedException exception) {
            System.out.println("Main thread interrupted.");
        }

        try {
            obi.thrd.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }
        System.out.println("Main thread exiting.");
    }
}
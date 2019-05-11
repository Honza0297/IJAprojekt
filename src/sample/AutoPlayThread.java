package sample;

public class AutoPlayThread implements Runnable
{
    private volatile Thread blinker;
    private SecondViewController controller;
    private int speed;

    public AutoPlayThread(SecondViewController controller, int speed)
    {
        this.controller = controller;
        this.speed = speed;
    }

    public void myStop() {
        Thread moribund = blinker;
        blinker = null;
        moribund.interrupt();
    }

    public void start() {
        blinker = new Thread(this);
        blinker.start();
    }


    public void run()
    {
        Thread thisThread = Thread.currentThread();
        while (blinker == thisThread)
        {
            try
            {
                if(!controller.DoNextMove())
                {
                    controller.disableAutoplayButtons(false);
                    myStop();
                }
                thisThread.sleep(speed);
            } catch (InterruptedException e)
            {
            }
        }
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
        blinker.interrupt();
    }

    public int getSpeed()
    {
        return speed;
    }
}

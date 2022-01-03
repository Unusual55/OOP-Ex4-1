package ex4_java_client;

import java.util.Comparator;
import java.util.TimerTask;


public class TimedMove extends TimerTask implements Runnable {
    public int Agentid;
    public long period, endtime;

    public TimedMove(int id, long time, long endtime) {
        this.Agentid = id;
        this.period = endtime - time;
        this.endtime=endtime;
    }

    public void run() {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TimedMoveComparator implements Comparator<TimedMove> {
    @Override
    public int compare(TimedMove t1, TimedMove t2) {
        long time1 = t1.endtime-System.currentTimeMillis();
        long time2 = t2.endtime-System.currentTimeMillis();
        if (time1 > time2) {
            return 1;
        } else if (time1 < time2) {
            return -1;
        }
        return 0;
    }
}



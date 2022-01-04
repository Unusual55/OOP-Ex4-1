package ex4_java_client;

import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Timer;

class TimedMoveTest {
    Timer timer;
    long start=System.currentTimeMillis();

    @Test
    void timerTest(){
        timer=new Timer();
        PriorityQueue<TimedMove> pq=new PriorityQueue<>(new TimedMoveComparator());
//        for(int i=0;i<3;i++){
//            TimedMove t=new TimedMove(i, i*1000, 5000);
//            t.run();
//            pq.add(t);
//        }
//        for (int i=0;i<3;i++){
//            System.out.println(pq.poll().Agentid);
//        }
        TimedMove t1=new TimedMove(0, 1000, 3000);
        TimedMove t2=new TimedMove(1, 200, 1800);
        TimedMove t3=new TimedMove(2, 1500, 2500);
        t1.run();
        t2.run();
        t3.run();
        pq.add(t1);pq.add(t2);pq.add(t3);
        TimedMove t4=new TimedMove(3, 1200, 1500);
        t4.run();
        pq.add(t4);
        System.out.println(pq.poll().Agentid);
        System.out.println(pq.poll().Agentid);
        System.out.println(pq.poll().Agentid);
        System.out.println(pq.poll().Agentid);
    }
}
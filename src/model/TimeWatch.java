package model;

import java.util.concurrent.TimeUnit;

public class TimeWatch {    
    long starts;
    boolean started;
    
    public void start() {
        started = true;
        reset();
    }

    public void reset() {
        starts = System.currentTimeMillis();
    }

    public long time() {
        long ends = System.currentTimeMillis();
        return ends - starts;
    }

    public long time(TimeUnit unit) {
        return unit.convert(time(), TimeUnit.MILLISECONDS);
    }
    
    public boolean isStarted(){
    	return started;
    }
}
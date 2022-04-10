package Connectors.Classes;
public class Debouncer {
    private VolleyCallBack c;
    private volatile long lastCalled;
    private int interval;

    public Debouncer(VolleyCallBack c, int interval) {
        //init fields
        c=c;
        interval=interval;
    }

    public void call(Object arg) {
        if( lastCalled + interval < System.currentTimeMillis() ) {
            lastCalled = System.currentTimeMillis();
            c.onSuccess();
        }
    }
}

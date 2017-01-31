package hpms.UserObject.BatchExe;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


import WIZ.FR.COM.*;

public class Executor_bat {
    public Executor_bat() {
    }

    public DOBJ exe(DOBJ indobj)
    {
    	  
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        RunningTask_bat running = new RunningTask_bat();
      
        running.setIndobj(indobj);
        executor.execute(running);
        executor.shutdown();
       
        VOBJ _vobj = new VOBJ();
        _vobj.setName("BAT");
        indobj.setRetObject(_vobj);
        return indobj;
    }

}

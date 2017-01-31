package hpms.UserObject.BatchExe;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import egov.wizware.com.*;

public class Executor {
    public Executor() {
    }

    public DOBJ exe(DOBJ indobj)
    {
    	  
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        RunningTask running = new RunningTask();
      
        running.setIndobj(indobj);
        executor.execute(running);
        executor.shutdown();
       
        VOBJ _vobj = new VOBJ();
        _vobj.setName("BAT");
        indobj.setRetObject(_vobj);
        return indobj;
    }

}

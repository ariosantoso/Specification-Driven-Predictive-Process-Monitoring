package org.astw.promplugin;


/**
 * A class to monitor whether MainInterpreter has been initialized
 * 
 * @author Ario Santoso (santoso.ario@gmail.com)
 *
 */
public final class MainInterpreterMonitor implements AutoCloseable {

	private Thread thread;
	private static Object flag = null;
	private static MainInterpreterMonitor instance = null;

    private MainInterpreterMonitor() {
        // only this class can instantiate it
    }


    public static boolean mainInterpreterIsRunning(){
    	
    	if(flag == null)
    		return false;
    	return true;
    }
    
    public void initialize() {

        thread = new Thread("MainInterpreterMonitor") {

            @Override
            public void run() {
            	
            	flag = new Object();
            }
        };
        thread.setDaemon(true);
        synchronized (this) {
            thread.start();
        }
    }

    public static void init(){
    	
    	if(instance == null){
    		
    		instance = new MainInterpreterMonitor();
    		instance.initialize();
    	}
    	
    }
    
    @Override
    public void close() {
        if (thread != null) {
            thread.interrupt();
        }
    }



}

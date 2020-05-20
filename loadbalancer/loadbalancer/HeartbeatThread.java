package loadbalancer;

public class HeartbeatThread extends Thread {
	
	private Boolean keepHeartbeatRunning = true;
	public Boolean get_KeepHeartbeatRunning() {
		return keepHeartbeatRunning;
	}


	public void set_KeepHeartbeatRunning(Boolean keepHeartbeatRunning) {
		this.keepHeartbeatRunning = keepHeartbeatRunning;
	}

	private ILoadbalancer loadbalancer;
	public HeartbeatThread(ILoadbalancer _loadbalancer)
	{
		this.loadbalancer = _loadbalancer;
	}


	@Override
	public void run() {
		try
        { 
            System.out.println ("Heartbeat Thread " + 
                                Thread.currentThread().getId() + 
                                " is running"); 
            heartBeatCheck(this.loadbalancer);
        } 
        catch (Exception e) 
        { 
            // catching
        	
        } 
	}

	private void heartBeatCheck(ILoadbalancer loadbalancer)
    {
    	while (this.get_KeepHeartbeatRunning())
    	{
    		for (IProvider _provider : loadbalancer.get_ProviderList()) {
    			
	    			Boolean isProviderAlive = _provider.CheckProviderAlive();
	    			
	    			if (_provider.get_Active() && !isProviderAlive)
	    			{
	    	            System.out.println ("Not Alive Excluding:" + _provider.get_InstanceID()); 

	    				loadbalancer.excludeProvider(_provider);
	    				_provider.set_IsAliveCounts(0);
	    			}
	    			
	    			if (!_provider.get_Active()  && !isProviderAlive)
	    			{
	    				_provider.set_IsAliveCounts(_provider.get_IsAliveCounts()+1); 			
	    			}
	    			
	    			if (!_provider.get_Active() && isProviderAlive)
	    			{
	    				_provider.set_IsAliveCounts(_provider.get_IsAliveCounts()+1); 	
	    				if (_provider.get_IsAliveCounts() >= loadbalancer.get_Config().get_MinHeartBeatRetries2Active()) {
	    					
		    	            System.out.println ("Alive " + loadbalancer.get_Config().get_MinHeartBeatRetries2Active() + " times. Including: " +  _provider.get_InstanceID()); 
	    					
		    	            loadbalancer.includeProvider(_provider);
	    				}
	    			}
    		}
    		try {
	            System.out.println ("HB Sleeping"); 
    			Thread.sleep(loadbalancer.get_Config().get_HeartBeatXSecondsCheckInterval()*1000);
    		} catch (InterruptedException e) {
    			break;
    		}
    	}
    }



}


  
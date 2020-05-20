package loadbalancer;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class Loadbalancer implements ILoadbalancer, Callable<String> {
	
	private HeartbeatThread _heartBeatThread;
	private ILoadbalancerConfig Config;
	private  Integer currentRunningProviders = 0;
	public ILoadbalancerConfig get_Config() {
		return Config;
	}

	public void set_Config(ILoadbalancerConfig config) {
		Config = config;
	}
	

	private ArrayList<IProvider> ProviderList;
	public ArrayList<IProvider> get_ProviderList() {
		return ProviderList;
	}
	public void set_ProviderList(ArrayList<IProvider> providerList) {
		ProviderList = providerList;
	}
	
	private Algorithms.ILoadbalancerAlgorithm Algorithm;
	public Algorithms.ILoadbalancerAlgorithm get_Algorithm() {
		return Algorithm;
	}
	public void set_Algorithm(Algorithms.ILoadbalancerAlgorithm algorithm) {
		Algorithm = algorithm;
	}

	public void registerProvider(IProvider _provider) {
		if (this.ProviderList.size()<this.Config.get_MaxNumberOfProviders()) {
			_provider.set_instanceID(this.Config.get_ProviderIntanceIdPrefix().toString() + Integer.toString(this.ProviderList.size()+1) );
			_provider.set_Active(true);
			this.ProviderList.add(_provider);
		} else {
			throw new ArrayIndexOutOfBoundsException("Max number of providers is " + Integer.toString(this.Config.get_MaxNumberOfProviders()));
		}
	}
	
	public void registerProvider(ArrayList<IProvider> _providerList) {
		if (_providerList.size() > this.Config.get_MaxNumberOfProviders())
			throw new ArrayIndexOutOfBoundsException("Max number of providers is " + Integer.toString(this.Config.get_MaxNumberOfProviders()));
		
		for (IProvider _provider : _providerList)
			registerProvider(_provider);
	}
    public Loadbalancer (Algorithms.ILoadbalancerAlgorithm _algorithm, ILoadbalancerConfig _config) {
    	this.set_ProviderList(new ArrayList<IProvider>());
    	this.set_Config(_config);
    	this.set_Algorithm(_algorithm);
    	if (this.get_Config().get_ActivateHeartBeatCheck())
    		startHeartBeatCheck();
	}
    
   
	
    public Boolean excludeProvider(IProvider provider2Exclude) {
    	
    	if (!this.get_ProviderList().contains(provider2Exclude))
    			return false;
    	
    	this.get_ProviderList().get(this.get_ProviderList().indexOf(provider2Exclude)).set_Active(false);
    	return true;
    	
    }
    public Boolean excludeProvider(String providerId2Exclude) {
		for (IProvider _provider : this.get_ProviderList()) {
			 if (_provider.get_InstanceID() == providerId2Exclude)
	            { 
				 _provider.set_Active(false);
	                return true;
	            }
		}
		return false;	
    }
    
    public Boolean includeProvider(IProvider provider2include) {
    	
    	if (!this.get_ProviderList().contains(provider2include))
    			return false;
    	
    	this.get_ProviderList().get(this.get_ProviderList().indexOf(provider2include)).set_Active(true);
    	return true;
    	
    }
    public Boolean includeProvider(String providerId2Include) {
		for (IProvider _provider : this.get_ProviderList()) {
			 if (_provider.get_InstanceID() == providerId2Include)
	            { 
				 _provider.set_Active(true);
	                return true;
	            }
		}
		return false;	
    }
    
    public String get()
    {
    	IProvider provider2Call = Algorithm.selectProvider(this.get_ProviderList());
    	if (provider2Call == null)
    		return "No Provider Available";
    	return provider2Call.Get();
    }
    
  
    
    public int getActiveProvidersCount() {
    	int activeProvidersCount=0;
    	for (IProvider _provider : this.get_ProviderList()) {
    		if (_provider.get_Active())
    			activeProvidersCount++;
    	}
    	return activeProvidersCount;
    }
    
    public void startHeartBeatCheck() {
    	_heartBeatThread = new  HeartbeatThread(this);
    	
    	_heartBeatThread.start();
    }
    public void stopHeartBeatCheck() {
    	_heartBeatThread.set_KeepHeartbeatRunning(false);
    }

	@Override
	public String call() throws Exception {
		if (currentRunningProviders >=(this.Config.get_YParallelRequestsPerProvider()*this.getActiveProvidersCount())) {
	   		return "Max Y number of calls per provider reached";
		}
		
		synchronized(currentRunningProviders) {
			currentRunningProviders++;
		}
		
		String result =  this.get();
		
		synchronized(currentRunningProviders) {
			currentRunningProviders--;
		}
		
		return result;
	}
    
    
   
    

	
}

package loadbalancer;

public class LoadbalancerConfig implements ILoadbalancerConfig {
	 // Number of times a positive heartbeat calls makes the provider be included
	private int _MinHeartBeatRetries2Active=2;
	
	public int get_MinHeartBeatRetries2Active() {
		return _MinHeartBeatRetries2Active;
	}


	public void set_MinHeartBeatRetries2Active(int _MinHeartBeatRetries2Active) {
		this._MinHeartBeatRetries2Active = _MinHeartBeatRetries2Active;
	}
	
     // Number of times a positive heartbeat calls makes the provider be included
	private int _HeartBeatXSecondsCheckInterval =2;

	public int get_HeartBeatXSecondsCheckInterval() {
		return _HeartBeatXSecondsCheckInterval;
	}


	public void set_HeartBeatXSecondsCheckInterval(int _HeartBeatXSecondsCheckInterval) {
		this._HeartBeatXSecondsCheckInterval = _HeartBeatXSecondsCheckInterval;
	}
	
    // Max Number of Providers
	private int _MaxNumberOfProviders =10;
	
	public int get_MaxNumberOfProviders() {
		return _MaxNumberOfProviders;
	}
	public void set_MaxNumberOfProviders(int _MaxNumberOfProviders) {
		this._MaxNumberOfProviders = _MaxNumberOfProviders;
	}

    // Provider Instance Id's prefix
	private String _ProviderIntanceIdPrefix = "ProviderInstance_";
	
	public String get_ProviderIntanceIdPrefix() {
		return _ProviderIntanceIdPrefix;
	}

	public void set_ProviderIntanceIdPrefix(String _ProviderIntanceIdPrefix) {
		this._ProviderIntanceIdPrefix = _ProviderIntanceIdPrefix;
	}
	
	
	
    // Y Number of Parallel Request per Active Provider
	private int _YParallelRequestsPerProvider=0;
	public int get_YParallelRequestsPerProvider() {
		return _YParallelRequestsPerProvider;
	}

	public void set_YParallelRequestsPerProvider(int _YParallelRequestsPerProvider) {
		this._YParallelRequestsPerProvider = _YParallelRequestsPerProvider;
	}
	

    public LoadbalancerConfig()
    {
    }

    //Avoid Starting the HeartBeatChecks. Used for testing scenarios 
	private Boolean _ActivateHeartBeatCheck = true;
	public Boolean get_ActivateHeartBeatCheck() {
		
		return _ActivateHeartBeatCheck;
	}
	public void set_ActivateHeartBeatCheck(Boolean _ActivateHeartBeatCheck) {
		this._ActivateHeartBeatCheck = _ActivateHeartBeatCheck;
	}













	
}

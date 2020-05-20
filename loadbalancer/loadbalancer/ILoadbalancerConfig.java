package loadbalancer;

public interface ILoadbalancerConfig {
	 int get_MinHeartBeatRetries2Active();
	 void set_MinHeartBeatRetries2Active(int _MinHeartBeatRetries2Active);
	
	 int get_HeartBeatXSecondsCheckInterval();
	 void set_HeartBeatXSecondsCheckInterval(int _HeartBeatXSecondsCheckInterval);
	
	 int get_MaxNumberOfProviders();
	 void set_MaxNumberOfProviders(int _MaxNumberOfProviders);
	
	 String get_ProviderIntanceIdPrefix();
	 void set_ProviderIntanceIdPrefix(String _ProviderIntanceIdPrefix);	
	 
	 int get_YParallelRequestsPerProvider();
	 void set_YParallelRequestsPerProvider(int _YParallelRequestPerProvider);
	 
	 Boolean get_ActivateHeartBeatCheck();
	 void set_ActivateHeartBeatCheck(Boolean _ActivateHeartBeatCheck);



}

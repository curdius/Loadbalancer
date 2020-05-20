package loadbalancer;

import java.util.ArrayList;

public interface ILoadbalancer {

	ILoadbalancerConfig get_Config();

	void set_Config(ILoadbalancerConfig config);

	ArrayList<IProvider> get_ProviderList();

	void set_ProviderList(ArrayList<IProvider> providerList);

	Algorithms.ILoadbalancerAlgorithm get_Algorithm();

	void set_Algorithm(Algorithms.ILoadbalancerAlgorithm algorithm);

	void registerProvider(IProvider _provider);

	void registerProvider(ArrayList<IProvider> _providerList);
	
	Boolean excludeProvider(IProvider provider2Exclude);
	
	Boolean excludeProvider(String providerId2Exclude);
	
	Boolean includeProvider(IProvider provider2Include);
	
	Boolean includeProvider(String providerId2Include);
	
	 void startHeartBeatCheck();
	 
	 void stopHeartBeatCheck();
	 
	   int getActiveProvidersCount();
	
	String get();
	
}
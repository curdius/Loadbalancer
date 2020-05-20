package FeatureTests;

import Algorithms.*;
import junit.framework.TestCase;
import loadbalancer.*;

public class Step6Test extends TestCase {

	public void test_Heartbeat_Every2SecondsANDAliveProvider_KeepsProviderActive()
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		//Act
		 while (loadbalancer.get_ProviderList().get(0).get_IsAliveCounts()<1) { //wait the needed time for the IsAliveCount to increase
	            System.out.println ("get_IsAliveCounts: " + loadbalancer.get_ProviderList().get(0).get_IsAliveCounts()); 
		 }
		 loadbalancer.stopHeartBeatCheck();		
		 //Assert
	
		 assertTrue(loadbalancer.get_ProviderList().get(0).get_Active());	
	}

	public void test_Heartbeat_Every2SecondsANDNotAliveProvider_ExcludeProvider() {

		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		//Act
		 while (loadbalancer.get_ProviderList().get(0).get_Active()) { //wait the needed time for provider to get excluded
	            System.out.println ("get_IsAliveCounts: " + loadbalancer.get_ProviderList().get(0).get_IsAliveCounts()); 
		 }
		 
		 loadbalancer.stopHeartBeatCheck();		
		 //Assert
	
		 assertFalse(loadbalancer.get_ProviderList().get(0).get_Active());
		
	}

}

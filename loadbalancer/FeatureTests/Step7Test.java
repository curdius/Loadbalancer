package FeatureTests;

import Algorithms.ILoadbalancerAlgorithm;
import Algorithms.RoundRobinInvocation;
import junit.framework.TestCase;
import loadbalancer.ILoadbalancer;
import loadbalancer.ILoadbalancerConfig;
import loadbalancer.Loadbalancer;
import loadbalancer.LoadbalancerConfig;
import loadbalancer.Provider;

public class Step7Test extends TestCase {



	public void test_Heartbeat_Every2SecondsANDProviderGetsExcludedANDActiveFor2Times_ProviderGetsReIncluding() {

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
	            System.out.println ("get_IsAliveCounts: " + loadbalancer.get_ProviderList().get(0).get_IsAliveCounts() + " Active:" +loadbalancer.get_ProviderList().get(0).get_Active()); 
	            try {
		         //   System.out.println ("HB Sleep"); 

	    			Thread.sleep(500);
	    		} catch (InterruptedException e) {
	    			break;
	    		}
		 }
		 
		//Act
		 while (!loadbalancer.get_ProviderList().get(0).get_Active()) { //wait the needed time for provider to get included
	            System.out.println ("get_IsAliveCounts: " + loadbalancer.get_ProviderList().get(0).get_IsAliveCounts()+ " Active:" +loadbalancer.get_ProviderList().get(0).get_Active()); 
	            try {
		           // System.out.println ("HB Sleep"); 

	    			Thread.sleep(500);
	    		} catch (InterruptedException e) {
	    			break;
	    		}
		 }
		 
		 
		 
		 loadbalancer.stopHeartBeatCheck();		
		 //Assert
	
		 assertTrue(loadbalancer.get_ProviderList().get(0).get_Active());
		
	}
}

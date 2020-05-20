package FeatureTests;

import java.util.ArrayList;

import Algorithms.ILoadbalancerAlgorithm;
import Algorithms.RoundRobinInvocation;
import junit.framework.TestCase;
import loadbalancer.*;

public class Step4Test extends TestCase {


	public void test_LoadBalancerWithRoundRobinInvocationAlgorithm_WithProviderListList_ReturnsRoundRobinResult() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RoundRobinInvocation();
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		config.set_ActivateHeartBeatCheck(false);  //Disable the HeartBeat so Providers don't get excluded with the simulated failure
		ILoadbalancer loadbalancer = new Loadbalancer(algorithm,config);
	
		
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());

		
		loadbalancer.registerProvider(providerList);
		
	
		ArrayList<String> resultList = new ArrayList<String>();

		//Act
		for (int i=0;i<20;i++) 
			resultList.add(loadbalancer.get());		
		
		
		//Assert
		for (int i=0;i<10;i++) {
			assertEquals(loadbalancer.get_Config().get_ProviderIntanceIdPrefix() + String.valueOf(i+1),resultList.get(i));
		}
		
	}

}

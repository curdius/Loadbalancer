package FeatureTests;

import Algorithms.RoundRobinInvocation;
import junit.framework.TestCase;
import loadbalancer.*;


public class Step5Test extends TestCase {
	public void test_ManualNodeExclusion_OfRegistredProvider_ProviderIsNotCalled() {
		
		//Arrange
		ILoadbalancer loadbalancer = new Loadbalancer(new RoundRobinInvocation(),new LoadbalancerConfig());
	//	loadbalancer.get_Config().set_ActivateHeartBeatCheck(false);
		
		IProvider provider1= new Provider();
		IProvider provider2 = new Provider();
		
		loadbalancer.registerProvider(provider1);
		loadbalancer.registerProvider(provider2);
		
		//Act
		loadbalancer.excludeProvider(provider1);
		
		
		//Assert

		//round robin algorithm  returns the second provider
		assertEquals(loadbalancer.get_Config().get_ProviderIntanceIdPrefix() + "2",loadbalancer.get());

	
	}
	
	public void test_ManualNodeInclusion_OfPrevisoulyExlcludecProvider_ProviderIsCalled() {
		
		//Arrange
		ILoadbalancer loadbalancer = new Loadbalancer(new RoundRobinInvocation(),new LoadbalancerConfig());
		loadbalancer.get_Config().set_ActivateHeartBeatCheck(false);
		
		IProvider provider1= new Provider();
		IProvider provider2 = new Provider();
		
		loadbalancer.registerProvider(provider1);
		loadbalancer.registerProvider(provider2);
		
		loadbalancer.excludeProvider(provider1); //Excluding

		
		//Act
		loadbalancer.includeProvider(provider1); //Including again
		
		
		//Assert

		//round robin algorithm always returns the first and the second provider
		assertEquals(loadbalancer.get_Config().get_ProviderIntanceIdPrefix() + "1",loadbalancer.get());
		assertEquals(loadbalancer.get_Config().get_ProviderIntanceIdPrefix() + "2",loadbalancer.get());
	}

}

package FeatureTests;

import java.util.ArrayList;

import Algorithms.ILoadbalancerAlgorithm;
import Algorithms.RandomInvocation;
import junit.framework.TestCase;
import loadbalancer.*;

public class Step3Test extends TestCase {


	public void test_LoadBalancerWithRandomInvocationAlgorithm_WithProviderListList_ReturnsRandomProvider() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RandomInvocation();
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_ActivateHeartBeatCheck(false);
		
		ILoadbalancer loadbalancer = new Loadbalancer(algorithm,config);
		
		
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		for(int i=0;i<10;i++) 			
			providerList.add(new Provider());
		
		loadbalancer.registerProvider(providerList);
		
				
		//Act
		String providerId1 = loadbalancer.get();
		String providerId2 = loadbalancer.get();
		String providerId3 = loadbalancer.get();

		
		//Assert
        System.out.println ("1:" + providerId1);
        System.out.println ("2:" + providerId2);
        System.out.println ("3:" + providerId3);
        System.out.println ("count:" + loadbalancer.getActiveProvidersCount());

		assertTrue((providerId1 != providerId2) && (providerId2!=providerId3));
		
		algorithm = null;
		loadbalancer= null;
	}

}

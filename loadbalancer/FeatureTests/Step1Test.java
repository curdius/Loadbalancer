package FeatureTests;

import junit.framework.TestCase;
import loadbalancer.*;

public class Step1Test extends TestCase {

	public void test_ProviderGet_2DifferentProviders_EachProviderHasUniqueIdentifier()
	{
		//Arrange
		ILoadbalancer loadBalancer = new Loadbalancer(null,new LoadbalancerConfig());
		
		IProvider provider1 = new Provider();
		IProvider provider2 = new Provider();
		loadBalancer.registerProvider(provider1);
		loadBalancer.registerProvider(provider2);

		
		
		//Act
		String Provider1Id = provider1.Get();
		String Provider2Id = provider2.Get();
		
		//Assert
		assertTrue(Provider1Id != Provider2Id);
		
	}
}

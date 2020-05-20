package Algorithms;

import java.util.ArrayList;

import junit.framework.TestCase;
import loadbalancer.*;

public class RandomInvocationTest extends TestCase {
	public void test_SelectProvider_WithEmptyProviderList_ReturnsNull() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RandomInvocation();
		ArrayList<IProvider> emptyProviderList = new ArrayList<IProvider>();
		
		//Act
		IProvider selectedProvider = algorithm.selectProvider(emptyProviderList);
		
		//Assert
		assertEquals(selectedProvider, null); 

	}
	public void test_SelectProvider_WithValidList_ReturnsRandomProvider() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RandomInvocation();
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		for(int i=0;i<10;i++) 			
			providerList.add(new Provider());
				
		//Act
		IProvider selectedProvider = algorithm.selectProvider(providerList);

		
		//Assert
		assertNotNull(selectedProvider);
	}
	
}

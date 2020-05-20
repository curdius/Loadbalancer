package Algorithms;

import java.util.ArrayList;

import junit.framework.TestCase;
import loadbalancer.*;

public class RoundRobinInvocationTest extends TestCase {
	public void test_SelectProvider_WithEmptyProviderList_ReturnsNull() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RoundRobinInvocation();
		ArrayList<IProvider> emptyProviderList = new ArrayList<IProvider>();
		
		//Act
		IProvider selectedProvider = algorithm.selectProvider(emptyProviderList);
		
		//Assert
		assertEquals(selectedProvider, null); 

	}
	public void test_SelectProvider_WithValidListAND10Times_ReturnsProvidersInSequenceOrder() 
	{
		//Arrange
		ILoadbalancerAlgorithm algorithm = new RoundRobinInvocation();
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		IProvider provider2Add;
		for(int i=0;i<10;i++) {
			provider2Add = new Provider();
			provider2Add.set_instanceID(String.valueOf(i));
			providerList.add(provider2Add);

		}
			
		ArrayList<String> resultList = new ArrayList<String>();
		
				
		//Act
		for(int i=0;i<10;i++)
			resultList.add(algorithm.selectProvider(providerList).get_InstanceID());
		
		
		//Assert
		for(int i=0;i<10;i++)
			assertEquals(String.valueOf(i),resultList.get(i));

	}
}

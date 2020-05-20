package FeatureTests;

import java.util.ArrayList;

import junit.framework.TestCase;
import loadbalancer.ILoadbalancer;
import loadbalancer.ILoadbalancerConfig;
import loadbalancer.IProvider;
import loadbalancer.Loadbalancer;
import loadbalancer.LoadbalancerConfig;
import loadbalancer.Provider;

public class Step2Test extends TestCase {
	public void test_RegisterProvider_WithLessThan10ProviderList_AddsProviderListSuccessfully() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10); //10 is the default value but want to make it explicit
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<5;i++) 
			providerList.add(new Provider());

		
		//Act
		loadbalancer.registerProvider(providerList);
		
		//Assert
		assertEquals(loadbalancer.get_ProviderList().size(), 5); 
		assertEquals(loadbalancer.get_ProviderList().get(0).get_InstanceID(), config.get_ProviderIntanceIdPrefix() + "1"); 

	}
	public void test_RegisterProvider_WithMoreThan10ProviderList_ThrowsAnArrayIndexOutOfBoundsExceptionExceptionWithCorrectMessage() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10); //10 is the default value but want to make it explicit
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		

		//Act
		try {
			for(int i=0;i<11;i++) 
				providerList.add(new Provider());
		
			loadbalancer.registerProvider(providerList);
			
		//Assert
			fail("Missing ArrayIndexOutOfBoundsException" );
			
		} catch( ArrayIndexOutOfBoundsException e ) {
			assertEquals( "Max number of providers is 10", e.getMessage() );
		}		
	}

}

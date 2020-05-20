package loadbalancer;

import java.util.ArrayList;

import Algorithms.*;
import junit.framework.TestCase;

public class LoadbalancerTest extends TestCase {
	public void test_RegisterProvider_WithSingleProvider_AddsSingleProviderSuccessfully() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		IProvider provider = new Provider() ;
		
		//Act
		loadbalancer.registerProvider(provider);
		
		//Assert
		assertEquals(loadbalancer.get_ProviderList().size(), 1); 
		assertEquals(loadbalancer.get_ProviderList().get(0).get_InstanceID(), config.get_ProviderIntanceIdPrefix() + "1"); 

	}
	public void test_RegisterProvider_WithMoreThan10Providers_ThrowsArrayIndexOutOfBoundsException() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		IProvider provider = new Provider();
				
		//Act
		try {
			for(int i=0;i<11;i++) {
				provider = new Provider();
				loadbalancer.registerProvider(provider);
			}
		//Assert
		     fail("Missing ArrayIndexOutOfBoundsException" );
		} catch( ArrayIndexOutOfBoundsException e ) {
		     assertEquals( "Max number of providers is 10", e.getMessage() );
		}
	}
	public void test_RegisterProvider_With5ProviderCollection_AddsProviderCollectionSuccessfully() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
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
	public void test_RegisterProvider_With11ProviderCollection_AddsProviderCollectionSuccessfully() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
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
	public void test_ExcludeProvider_WithProviderObjectANDValidProvider_ProviderSuccessfullyExcludedANDReturnsTrue() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.excludeProvider(providerList.get(1));
		
		//Assert
		 assertTrue(result);
		 assertFalse(loadbalancer.get_ProviderList().get(1).get_Active());
	}
	public void test_ExcludeProvider_WithProviderObjectANDNotFoundProvider_ReturnsFalse() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.excludeProvider(new Provider());
		
		//Assert
		 assertFalse(result);

	}
	public void test_ExcludeProvider_WithProviderStringANDValidProvider_ProviderSuccessfullyExcludedANDReturnsTrue() 
	{		
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.excludeProvider(providerList.get(1).get_InstanceID());
		
		//Assert
		 assertTrue(result);
		 assertFalse(loadbalancer.get_ProviderList().get(1).get_Active());
	}
	public void test_ExcludeProvider_WithProviderStringANDNotFoundProvider_ReturnsFalse() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.excludeProvider("BadProviderID");
		
		//Assert
		 assertFalse(result);		
	}

	public void test_IncludeProvider_WithProviderObjectANDValidProvider_ProviderSuccessfullyIncludedANDReturnsTrue() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.includeProvider(providerList.get(1));
		
		//Assert
		 assertTrue(result);
		 assertTrue(loadbalancer.get_ProviderList().get(1).get_Active());
	}
	public void test_IncludeProvider_WithProviderObjectANDNotFoundProvider_ReturnsFalse() 
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.includeProvider(new Provider());
		
		//Assert
		 assertFalse(result);

	}
	public void test_IncludeProvider_WithProviderStringANDValidProvider_ProviderSuccessfullyIncludedANDReturnsTrue() 
	{		
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.includeProvider(providerList.get(1).get_InstanceID());
		
		//Assert
		 assertTrue(result);
		 assertTrue(loadbalancer.get_ProviderList().get(1).get_Active());
	}
	public void test_IncludeProvider_WithProviderStringANDNotFoundProvider_ReturnsFalse() 
	{
	
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancer loadbalancer = new Loadbalancer(null,config);
		ArrayList<IProvider> providerList = new ArrayList<IProvider>();
		
		for(int i=0;i<10;i++) 
			providerList.add(new Provider());
	
		loadbalancer.registerProvider(providerList);
		
		//Act
		 Boolean result = loadbalancer.includeProvider("BadProviderID");
		
		//Assert
		 assertFalse(result);		
	}
	
	public void test_get_WithAgorithmReturningNull_ReturnsNoProviderAvailableString()
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancerAlgorithm mockedAlgorithm = new AlgorithmStubNull();
		ILoadbalancer loadbalancer = new Loadbalancer(mockedAlgorithm,config);
		loadbalancer.registerProvider(new Provider());
		
		//Act
		 String result = loadbalancer.get();
			

		 
		 //Assert
		 assertEquals(result,"No Provider Available");	
	}
	public void test_get_WithAgorithmReturningCorrectCollection_ReturnsRightProviderId()
	{
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		
		ILoadbalancerAlgorithm mockedAlgorithm = new AlgorithmStubValid();
		ILoadbalancer loadbalancer = new Loadbalancer(mockedAlgorithm,config);
		loadbalancer.registerProvider(new Provider());

		
		//Act
		 String result = loadbalancer.get();
		 
		 //Assert
		 assertEquals(result,config.get_ProviderIntanceIdPrefix() + "1");	
	}	

}

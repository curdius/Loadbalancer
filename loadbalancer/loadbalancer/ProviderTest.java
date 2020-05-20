package loadbalancer;

import junit.framework.TestCase;

public class ProviderTest extends TestCase {
	
	public void test_Get_WithValidIstanceID_ReturnsCorrectInstanceID() 
	{
		//Arrange
		Provider provider = new Provider() ;
		provider.set_instanceID("testInstanceName");
		
		//Act
		String resultInstanceId = provider.Get();
		
		//Assert
		assertEquals(resultInstanceId, "testInstanceName"); 

	}
	 
	public void test_CheckProviderAlive_CallLessThan10Times_IsAlive() 
	{
		//Arrange
		Provider provider = new Provider() ;
		provider.set_instanceID("testInstanceName");
		
		//Act
		Boolean allAlive = true;
		
		for(int i=0;i<10;i++)
			allAlive &= provider.CheckProviderAlive();

		//Assert
		assertTrue(allAlive); 

	}
	public void test_CheckProviderAlive_CallMoreThan10Times_IsNOTAlive() 
	{
		//Arrange
		Provider provider = new Provider() ;
		provider.set_instanceID("testInstanceName");
			
		Boolean result;
	
		//Act
		for(int i=0;i<10;i++)
			 provider.CheckProviderAlive();		
		result = provider.CheckProviderAlive();
		
		//Assert
		assertFalse(result); 

	}
	public void test_CheckProviderAlive_CallMoreThan11Times_IsAlive() 
	{
		//Arrange
		Provider provider = new Provider() ;
		provider.set_instanceID("testInstanceName");
		
		//Act
		Boolean result;
		
		for(int i=0;i<11;i++)
			 provider.CheckProviderAlive();
		result = provider.CheckProviderAlive();
		
		//Assert
		assertTrue(result); 

	}
}

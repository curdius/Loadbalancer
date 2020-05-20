package FeatureTests;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Algorithms.ILoadbalancerAlgorithm;
import Algorithms.RoundRobinInvocation;
import junit.framework.TestCase;
import loadbalancer.*;

public class Step8Test extends TestCase {

	public void test_LoadBalancer_WithAllowedYNumberOfParallelCallsPerProvider_ReturnCorrectProviderId() throws InterruptedException, ExecutionException {

		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		config.set_YParallelRequestsPerProvider(2);
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		//Act	
	 
	 	ExecutorService executor = Executors.newCachedThreadPool();
	 	ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
	 	
        System.out.println ("call start");

	 	for(int i=0;i<6;i++) {
		    resultList.add(executor.submit((Callable<String>) loadbalancer));
	 	}
	 	
	 	//Assert
	 	for (Future<String> result :resultList) {
            System.out.println ("getting");
	 		String strResult = result.get();
            System.out.println ("Result: " + strResult);
			assertTrue(result.get().startsWith(config.get_ProviderIntanceIdPrefix()));
		}	
	}
	public void test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndNoDeactivatedProvider_ReturnErrorMessage() throws InterruptedException, ExecutionException {

        System.out.println ("test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndNoDeactivatedProvider_ReturnErrorMessage");

		
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		config.set_YParallelRequestsPerProvider(2);
		config.set_ActivateHeartBeatCheck(false); //To avoid having deactivated providers 
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		//Act	
	 
	 	ExecutorService executor = Executors.newCachedThreadPool();
	 	ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
	 	
        System.out.println ("call start");

	 	for(int i=0;i<20;i++) {
		    resultList.add(executor.submit((Callable<String>) loadbalancer));
	 	}
	 	
        System.out.println ("getting");
        
	 	//Assert

        int i=0;
        for (Future<String> result :resultList) {
        	
	 		String strResult = result.get();
            System.out.println (strResult + " - " + i + " - " + String.valueOf(config.get_YParallelRequestsPerProvider() * loadbalancer.getActiveProvidersCount()));
            if (i<(config.get_YParallelRequestsPerProvider() * loadbalancer.getActiveProvidersCount()))
            	assertFalse("Max Y number of calls per provider reached" == strResult);
            else
            	assertTrue("Max Y number of calls per provider reached" == strResult);

            i++;
		}	

	}
	
	public void test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndOneDeactivatedProvider_ReturnErrorMessage() throws InterruptedException, ExecutionException {

        System.out.println ("test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndOneDeactivatedProvider_ReturnErrorMessage");

		
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2);
		config.set_YParallelRequestsPerProvider(2);
		config.set_ActivateHeartBeatCheck(false); //So I can control the number of activated providers
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		
		//Act	
		loadbalancer.excludeProvider(loadbalancer.get_ProviderList().get(0));
	 
	 	ExecutorService executor = Executors.newCachedThreadPool();
	 	ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
	 	
        System.out.println ("call start");

	 	for(int i=0;i<20;i++) {
		    resultList.add(executor.submit((Callable<String>) loadbalancer));
	 	}
	 	
        System.out.println ("getting");
        
         //Assert
        int i=0;
        for (Future<String> result :resultList) {
        	
	 		String strResult = result.get();
            System.out.println (strResult + " - " + i + " - " + String.valueOf(config.get_YParallelRequestsPerProvider() * loadbalancer.getActiveProvidersCount()));
            if (i<config.get_YParallelRequestsPerProvider() * (loadbalancer.getActiveProvidersCount()))
            	assertFalse("Max Y number of calls per provider reached" == strResult);
            else
            	assertTrue("Max Y number of calls per provider reached" == strResult);

            i++;
		}	
 	
	 	
	}
	
	public void test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndHeartBeatDeactivatedProviders_ReturnErrorMessage() throws InterruptedException, ExecutionException {

        System.out.println ("test_LoadBalancer_WithMoreThanAllowedYNumberOfParallelCallsPerProviderAndHeartBeatDeactivatedProviders_ReturnErrorMessage");

		
		//Arrange
		ILoadbalancerConfig config = new LoadbalancerConfig();
		config.set_MaxNumberOfProviders(10);
		config.set_HeartBeatXSecondsCheckInterval(2); 
		config.set_YParallelRequestsPerProvider(200);
		config.set_ActivateHeartBeatCheck(true); 
		
		ILoadbalancerAlgorithm roundRobinInvocation = new RoundRobinInvocation();
		
		ILoadbalancer loadbalancer = new Loadbalancer(roundRobinInvocation,config);
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		loadbalancer.registerProvider(new Provider());
		
		
		//Act		 
	 	ExecutorService executor = Executors.newCachedThreadPool();
	 	ArrayList<Future<String>> resultList = new ArrayList<Future<String>>();
	 	
        System.out.println ("call start");

        int activerProvidersCountAtCallTime = loadbalancer.getActiveProvidersCount(); //Actice Provider Count closer to call Time
        
        for(int i=0;i<650;i++) {
		    resultList.add(executor.submit((Callable<String>) loadbalancer));
	 	}
	 	
        System.out.println ("getting");
        
         //Assert
        int i=0;
        for (Future<String> result :resultList) {
        	
	 		String strResult = result.get();
            System.out.println (strResult + " - " + i + " - " + String.valueOf(config.get_YParallelRequestsPerProvider() * activerProvidersCountAtCallTime));
            if (i<config.get_YParallelRequestsPerProvider() * activerProvidersCountAtCallTime)
            	assertFalse("Max Y number of calls per provider reached" == strResult);
            else
            	assertTrue("Max Y number of calls per provider reached" == strResult);

            i++;
		}	
 	
	 	
	}
}

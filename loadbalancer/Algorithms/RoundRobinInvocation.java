package Algorithms;

import java.util.ArrayList;

import loadbalancer.IProvider;

public class RoundRobinInvocation extends BaseAlgorithm implements ILoadbalancerAlgorithm {

	private IProvider lastCalledProvider=null;
	
	public IProvider selectProvider(ArrayList<IProvider> providerList) {
		
		ArrayList<IProvider> activeProviderList = super.getActiveProviders(providerList);
		
		if (activeProviderList.size()==0)
				return null;
		
		int lastCalledProviderIndex = activeProviderList.indexOf(lastCalledProvider);
		lastCalledProviderIndex++;
		
		if (lastCalledProviderIndex>=activeProviderList.size())
			lastCalledProviderIndex = 0;
		
		lastCalledProvider = activeProviderList.get(lastCalledProviderIndex);
	
		return lastCalledProvider;
	}

}

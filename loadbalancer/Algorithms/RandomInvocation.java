package Algorithms;

import java.util.ArrayList;
import java.util.Random;

import loadbalancer.IProvider;

public class RandomInvocation  extends BaseAlgorithm  implements ILoadbalancerAlgorithm{


	public IProvider selectProvider(ArrayList<IProvider> providerList) {
		
		if (providerList==null)
			return null;
		int activeProvidersCount = super.getActiveProviders(providerList).size();
        if (activeProvidersCount == 0)
            return null;
        
		Random randomGenerator = new Random();
		int selectedProvider = randomGenerator.nextInt(activeProvidersCount);
		
        return providerList.get(selectedProvider);
	}

}

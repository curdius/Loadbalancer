package Algorithms;

import java.util.ArrayList;

import loadbalancer.IProvider;

public class AlgorithmStubValid implements ILoadbalancerAlgorithm {

	@Override
	public IProvider selectProvider(ArrayList<IProvider> providerList) {
		return providerList.get(0);
	}

}

package Algorithms;

import java.util.ArrayList;

import loadbalancer.IProvider;

public class AlgorithmStubNull implements ILoadbalancerAlgorithm {
	public IProvider selectProvider(ArrayList<IProvider> providerList) {
		return null;
	}
}

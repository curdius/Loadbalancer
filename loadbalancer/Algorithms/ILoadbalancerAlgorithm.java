package Algorithms;

import java.util.ArrayList;
import loadbalancer.*;

public interface ILoadbalancerAlgorithm {
	public IProvider selectProvider(ArrayList<IProvider> providerList);
}

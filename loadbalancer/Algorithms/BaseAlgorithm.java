package Algorithms;
import java.util.ArrayList;

import loadbalancer.*;

public abstract class BaseAlgorithm {
	public ArrayList<IProvider> getActiveProviders(ArrayList<IProvider> providerList)
    {
		ArrayList<IProvider> activeProviderList = new ArrayList<IProvider>();

        // Returning only active providers 
		for (IProvider _provider : providerList) 
		{
			if (_provider.get_Active())
				activeProviderList.add(_provider);
		}

        return activeProviderList;
    }
}

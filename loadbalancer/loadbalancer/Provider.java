package loadbalancer;

public class Provider  implements IProvider {
	Boolean _active = true;
  //  int _IsAliveCounts;
    String _InstanceID;
    
     int _IsAliveCounts;

    public Provider()
    {
    }
    
    public void set_Active(Boolean active) {
    	this._active = active;
    }
    public Boolean get_Active() {
    	return this._active;
    }
    
    public void set_IsAliveCounts(int isAliveCounts) {
    	this._IsAliveCounts = isAliveCounts;
    }
    public int get_IsAliveCounts() {
    	return this._IsAliveCounts;
    }
    
    public void set_instanceID(String instanceID) {
    	this._InstanceID = instanceID;
    }
    public String get_InstanceID() {
    	return this._InstanceID;
    }

    public String Get() 
    {
    	try {
			Thread.sleep(500); //simulate delay in provider get
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        return this._InstanceID;
    }
    public Boolean CheckProviderAlive()
    {
           //returns not being alive after 10 calls to each provider to simulate not being alive
	        if (this.get_IsAliveCounts() == 10)
	        { 
	            this.set_IsAliveCounts(0);
	            return false;
	        }
	        else
	        {
	            this.set_IsAliveCounts(this.get_IsAliveCounts()+1);
	            return true;
	        }
    }
}

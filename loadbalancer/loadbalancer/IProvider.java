package loadbalancer;

public interface IProvider {
  
    
    
	Boolean get_Active();
     void set_Active(Boolean active);
    
    
     void set_IsAliveCounts(int isAliveCounts);
     int get_IsAliveCounts() ;
    
     void set_instanceID(String instanceID);
     String get_InstanceID();
    
     String Get();
     Boolean CheckProviderAlive();
   
    
}

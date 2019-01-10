package co3090.rmi.client;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import co3090.rmi.common.QueryExecutor;
import co3090.rmi.model.CarFuel;
import co3090.rmi.model.HousePrice;
import co3090.rmi.queries.AbstractQuery;
import co3090.rmi.queries.CarFuelQuery;
import co3090.rmi.queries.HouseQuery;

public class QueryClient {
	public static void main(String args[]) {

        System.setProperty("java.rmi.server.codebase", "file:///Users/mukz/export/query-client.jar");
        System.setProperty("java.security.policy", "file:///Users/mukz/eclipse/query-server/policy.permission");
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");


        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "rmi://localhost/Compute";
            QueryExecutor skeleton = (QueryExecutor) Naming.lookup(name);
            

            //CarFuel filter = new CarFuel("2017", 100.00);
		    //CarFuelQuery query = new CarFuelQuery("CarFuel.csv", filter);
            HousePrice housePriceFilter = new HousePrice("2002");
            HouseQuery query = new HouseQuery("HousesPrices.csv", housePriceFilter );
            skeleton.executeQuery(query);
          
            
        } catch (Exception e) {
            System.err.println("Compute Pitagoras exception: " +
                               e.getMessage());
            e.printStackTrace();
        }
    }
}

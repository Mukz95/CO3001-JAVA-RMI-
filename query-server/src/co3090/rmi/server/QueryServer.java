package co3090.rmi.server;

import java.lang.reflect.Executable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import co3090.rmi.common.Query;
import co3090.rmi.common.QueryExecutor;





public class QueryServer extends UnicastRemoteObject implements QueryExecutor {
    public QueryServer() throws RemoteException {
        super();
    }

    @Override
    public Object executeQuery (Query q) throws RemoteException {
        return q.execute();

    }

//    private static final long serialVersionUID = 1L;


    public static void main(String[] args) {

        System.setProperty("java.rmi.server.codebase", "file:///Users/mukz/export/query-server.jar");
        System.setProperty("java.security.policy", "file:///Users/mukz/eclipse/query-server/policy.permission");
        System.setProperty("java.rmi.server.useCodebaseOnly", "false");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }


        String name = "rmi://localhost/Compute";
        try
        {
            QueryExecutor executor = new QueryServer();
            Naming.rebind(name, executor);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // I don't know why we have to rebind at all.
        // However, this does set the string that you need to use in order to lookup the remote class.

        System.out.println("Bound!");
        System.out.println("Server will wait forever for messages.");


    }

}

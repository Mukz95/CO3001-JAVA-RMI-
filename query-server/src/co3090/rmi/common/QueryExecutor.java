package co3090.rmi.common;

import java.io.Serializable;

public interface QueryExecutor extends java.rmi.Remote {

    public Object executeQuery(Query q) throws java.rmi.RemoteException;

}

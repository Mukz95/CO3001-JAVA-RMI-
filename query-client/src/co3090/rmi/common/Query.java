package co3090.rmi.common;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.stream.Stream;

public interface Query extends Serializable {
	
	public Object execute() throws RemoteException;
	
	public void setDataStream(Stream <String> stream);
	
	public String getFilename();
	

}

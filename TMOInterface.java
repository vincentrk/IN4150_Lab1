import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by vincent on 11/17/16.
 */
public interface TMOInterface extends Remote
{
    public void sendMessage(int procId,Message message) throws java.rmi.RemoteException;
    public void receiveMessage(Message message) throws java.rmi.RemoteException;
    public void readMessage() throws RemoteException;
  //  public void setID(int procId) throws java.rmi.RemoteException;
}

import java.rmi.*;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by vincent on 11/17/16.
 */
public class Main
{
    public static void main(String[] args) throws RemoteException
    {
        for(int i=0;i<10;i++)
        {
            TMOProc p = new TMOProc(i);
            try
            {
                java.rmi.Naming.bind("rmi://localhost:/TMOProc" + i, p);
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
            new Thread(p).start();
        }
    }
}

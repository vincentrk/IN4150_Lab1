import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by vincent on 11/17/16.
 */
public class Main
{
    private static final int NUM_PROCESSES = 10;

    public static void main(String[] args) throws RemoteException
    {
        try
        {
            LocateRegistry.createRegistry(1099);
            System.out.println("Created Registry");
        }
        catch(RemoteException e)
        {
            System.out.println("Already Running Binding");
        }
        try
        {
           // TMOProc p = new TMOProc(1, NUM_PROCESSES);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for(int i=0;i<NUM_PROCESSES;i++)
        {
            //TMOProc p = new TMOProc(i, NUM_PROCESSES);
            try
            {
                TMOProc p = new TMOProc(i, NUM_PROCESSES);
                Naming.rebind("rmi://localhost:1099/TMOProc"+i, p);
                new Thread(p).start();
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
    }
}

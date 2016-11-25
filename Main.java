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
    public static void main(String[] args) throws RemoteException
    {
        try {
            LocateRegistry.createRegistry(1099);
            System.out.println("Created Registry");
        }
         catch(RemoteException e)
         {
             System.out.println("Already Running Binding");
         }
         try {
            // TMOProc p = new TMOProc(1);

         }
         catch (Exception e) {
             e.printStackTrace();
         }
        for(int i=0;i<10;i++)
        {
            //TMOProc p = new TMOProc(i);
            try
            {

                TMOProc p = new TMOProc(i);
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

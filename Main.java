import java.net.Inet4Address;
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
    private static int NUM_PROCESSES;
    private static int procId;
    private static int numIterations;

    public static void main(String[] args) throws RemoteException
    {
        if(args.length != 3)
        {
            System.out.println("Incorrect Arguments. Usage: procId, numProcesses, numIterations");
            return;
        }
        NUM_PROCESSES = Integer.parseInt(args[0]);
        procId = Integer.parseInt(args[1]);
        numIterations = Integer.parseInt(args[2]);

        // start RMI registry
        try
        {
            LocateRegistry.createRegistry(1099);
            System.out.println("Created Registry");
        }
        catch(RemoteException e)
        {
            System.out.println("Already Running Binding");
        }
        // create, bind, and start process

        TMOProc p = new TMOProc(procId, NUM_PROCESSES, numIterations);
        try
        {
            Naming.rebind("rmi://localhost:1099/TMOProc"+procId, p);
            p.run();
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }
}

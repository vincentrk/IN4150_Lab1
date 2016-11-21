import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.spec.ECField;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by vincent on 11/17/16.
 */
public class TMOProc extends UnicastRemoteObject implements TMOInterface, Runnable
{
    private int procID;
    private int time;
    private PriorityQueue<Message> messageQueue;

    public TMOProc(int procID) throws RemoteException // TODO this is not ideal better to implement try catch
    {
        this.procID = procID;
        this.time = 0;
        this.messageQueue = new PriorityQueue<Message>();
    }

    public void run()
    {
        System.out.println("ProcID:" + procID);
        if (procID == 8)
        {
            try
            {
                TMOProc testProc = (TMOProc)(java.rmi.Naming.lookup("rmi://localhost:/TMOProc1"));
            }
            catch (Exception ex)
            {
                System.out.println(ex);
            }
        }
        return;
    }

    public void printTest()
    {
        System.out.println("THIS IS THE TEST FOR PROC: " + procID);
    }

    public void sendMessage(Message message)
    {

    }

    public void receiveMessage(Message message)
    {
        messageQueue.add(message);
    }
}

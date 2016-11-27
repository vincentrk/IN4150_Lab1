import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.spec.ECField;
import java.util.*;

/**
 * Created by vincent on 11/17/16.
 */
public class TMOProc extends UnicastRemoteObject implements TMOInterface, Runnable
{
    private int numProcesses;
    private int procID;
    private int time;
    private PriorityQueue<Message> Buffer;
    //private Dictionary<>

    public TMOProc(int procID, int numProcesses) throws RemoteException // TODO this is not ideal better to implement try/catch
    {
        this.procID = procID;
        this.time = 0;
        this.Buffer= new PriorityQueue<Message>();
        this.numProcesses = numProcesses;
    }


    public void run()
    {
        while(true)
        {
            System.out.println("ProcID:" + procID);
            try
            {
                readMessage();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (procID == 4)
            {
                Message msg= new Message(new Timestamp(time, procID), messageType.Message, "BroadcastMessage");
                broadcastMessage(msg);
            }
            waitTime(getRandTime());
        }
        //return;
    }

    public void printTest()
    {
        System.out.println("THIS IS THE TEST FOR PROC: " + procID);
    }

    public void waitTime(int time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public int getRandTime()
    {
        return ((int)(Math.random() * 1000));
    }

    public void broadcastMessage(Message message)
    {
        try
        {
            for(int i=0;i<numProcesses;i++)
            {
                sendMessage(i, message);
            }
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public void sendMessage(int procID, Message message)
    {
        try
        {
            TMOInterface Rcv = (TMOInterface) Naming.lookup("rmi://localhost/TMOProc" + procID);
            Rcv.receiveMessage(message);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void receiveMessage(Message message)
    {
        if(message.getType() == messageType.Message)
        {
            this.Buffer.add(message);
            Message ack = new Message(new Timestamp(message.getTimestamp()), messageType.ACK, "");
            broadcastMessage(ack);
        }
        else if(message.getType() == messageType.ACK)
        {
            // todo do something
        }
        else if(message.getType() == messageType.ERR)
        {
            System.out.println("Message Error: " + message.getMessage());
        }
        else
        {
            System.out.println("Error: MessageType not found.");
        }
    }

    public void readMessage()
    {
        if(Buffer.isEmpty())
        {
            return;
        }
        else
        {
            Message temp= Buffer.remove();
            System.out.println("Procces " + this.procID + "Read message " + temp.getMessage() );
        }
    }
}
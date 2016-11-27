import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.spec.ECField;
import java.util.*;

/**
 * Created by vincent on 11/17/16.
 */
public class TMOProc extends UnicastRemoteObject implements TMOInterface, Runnable
{
    private static int numProcessors = 0;
    private int procID;
    private int time;
    private PriorityQueue<Message> Buffer;

    public TMOProc(int procID) throws RemoteException // TODO this is not ideal better to implement try catch
    {
        this.procID = procID;
        this.time = 0;
        this.Buffer= new PriorityQueue<Message>();
        numProcessors++;
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
                Message msg= new Message(time, procID, messageType.Message, "BroadcastMessage");
                try
                {
                    for(int i=0;i<numProcessors;i++)
                    {
                        sendMessage(i, msg);
                    }
                } catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
            waitTime(getRandTime());
        }
        // todo figure this out a bit?
        //numProcessors--;
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
            for(int i=0;i<numProcessors;i++)
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
        if(message.Type == messageType.Message)
        {
            this.Buffer.add(message);
        }
        else if(message.Type == messageType.ACK)
        {
            // todo do something
        }
        else if(message.Type == messageType.ERR)
        {
            System.out.println("Message Error: " + message.msg);
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
            System.out.println("Procces " + this.procID + "Read message " + temp.msg );
        }
    }
}
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.security.spec.ECField;
import java.util.*;

/**
 * Created by vincent on 11/17/16.
 */
public class TMOProc extends UnicastRemoteObject implements TMOInterface, Runnable {
    private int procID;
    private int time;
    private LinkedList<Message> Buffer;

    public TMOProc(int procID) throws RemoteException // TODO this is not ideal better to implement try catch
    {
        this.procID = procID;
        this.time = 0;
        this.Buffer= new LinkedList<Message>();
    }

    public void run() {
        while(true) {
            System.out.println("ProcID:" + procID);
            try {
                readMessage();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (procID == 4) {
                Message Msg= new Message((int)System.currentTimeMillis(),procID,"BroadcastMessage");
                try {
                    for(int i=0;i<10;i++){
                        sendMessage(i, Msg);
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        //return;
    }

    public void printTest() {
        System.out.println("THIS IS THE TEST FOR PROC: " + procID);
    }

    public void sendMessage(int procID, Message message) {
        try {
            TMOInterface Rcv = (TMOInterface) Naming.lookup("rmi://localhost/TMOProc" + procID);
            Rcv.receiveMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void receiveMessage(Message message) {
        this.Buffer.addLast(message);
        Collections.sort(Buffer,new Message());
    }
    public void readMessage()
    {
        if(Buffer.isEmpty()){
            return;
        }
        else {
            Message temp= Buffer.remove();
            System.out.println("Procces " + this.procID + "Read message " + temp.Msg );
        }
    }
}
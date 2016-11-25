import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by vincent on 11/17/16.
 */
enum messageType {ACK,Message}
public class Message implements Serializable,Comparator<Message>
{
    int time;
    int senderID;
    messageType Type;
    String Msg;
    Message(){}
    public Message(int time, int senderID,String Msg)
    {
        this.time = time;
        this.senderID = senderID;
        this.Msg = Msg;
    }

    public String toString()
    {
        return "Message time: " + time + ", SenderID: " + senderID;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Message))
            return false;
        return ((this.time == ((Message)(obj)).time) && (this.senderID == ((Message)(obj)).senderID));
    }

    public int compare(Message m1, Message m2)
    {
        if(m1.time == m2.time)
        {
            return m1.senderID - m2.senderID;
        }
        else
        {
            return m1.time-m2.time;
        }
    }
}

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by vincent on 11/17/16.
 */
enum messageType {ACK, Message, ERR}
public class Message implements Serializable, Comparator<Message>
{
    int time;
    int senderID;
    messageType Type;
    String msg;

    public Message()
    {
        this.time = 0;
        this.senderID = -1;
        this.Type = messageType.ERR;
        this.msg = "";
    }
    public Message(int time, int senderID, messageType Type, String msg)
    {
        this.time = time;
        this.senderID = senderID;
        this.Type = Type;
        this.msg = msg;
    }

    public String toString()
    {
        return "Message time: " + time + ", SenderID: " + senderID + ", Message Type: " + Type + ", Message: " + msg;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Message))
            return false;
        return ((this.time == ((Message)(obj)).time) && (this.senderID == ((Message)(obj)).senderID) && (this.Type == ((Message)(obj)).Type) && (this.msg.equals(((Message)(obj)).msg)));
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

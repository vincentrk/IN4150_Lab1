import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by vincent on 11/17/16.
 */
enum messageType {ACK, Message, ERR}
public class Message implements Serializable, Comparator<Message>
{
    private Timestamp timestamp;
    private messageType type;
    private String msg;

    public Message()
    {
        this.timestamp = new Timestamp(-1, -1);
        this.type = messageType.ERR;
        this.msg = "";
    }
    public Message(int time, int senderID, messageType type, String msg)
    {
        this.timestamp = new Timestamp(time, senderID);
        this.type = type;
        this.msg = msg;
    }

    public Message(Timestamp timestamp, messageType type, String msg)
    {
        this.timestamp = timestamp;
        this.type = type;
        this.msg = msg;
    }

    public String toString()
    {
        return "Message timestamp: " + timestamp + ", Message Type: " + type + ", Message: " + msg;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Message))
            return false;
        return ((this.timestamp.equals(((Message)(obj)).timestamp)) && (this.type == ((Message)(obj)).type) && (this.msg.equals(((Message)(obj)).msg)));
    }

    public int compare(Message m1, Message m2)
    {
        return this.timestamp.compare(m1.timestamp, m2.timestamp);
    }

    public messageType getType() {return type;}

    public String getMessage() {return msg;}

    public Timestamp getTimestamp() {return timestamp;}
}

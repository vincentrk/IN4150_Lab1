import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by vincent on 11/27/16.
 */
public class Timestamp implements Serializable, Comparator<Timestamp>
{
    private int time;
    private int id;

    public Timestamp(int time, int id)
    {
        this.time = time;
        this.id = id;
    }

    public Timestamp(Timestamp timestamp)
    {
        this.time = timestamp.time;
        this.id = timestamp.time;
    }

    public int getTime() {return time;}

    public int getId() {return id;}

    public String toString()
    {
        return "Time " + time + ", ID: " + id;
    }

    public boolean equals(Object obj)
    {
        if(!(obj instanceof Timestamp))
            return false;
        return ((this.time == ((Timestamp)(obj)).time) && (this.id == ((Timestamp)(obj)).id));
    }

    public int compare(Timestamp t1, Timestamp t2)
    {
        if(t1.time == t2.time)
        {
            return t1.id - t2.id;
        }
        else
        {
            return t1.time-t2.time;
        }
    }


}

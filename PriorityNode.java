public class PriorityNode<E> implements Comparable
{
    private E data;
    private Integer priority;

    public PriorityNode(E data, Integer priority)
    {
        this.data 		= data;
        this.priority 	= priority;
    }

    public E getData()
    { return data; }

    public void setData(E data)
    { this.data = data; }

    public Integer getPriority()
    { return priority; }

    public void setPriority(Integer priority)
    {  this.priority 	= priority; }

    public int compareTo(Object o)
    {
        PriorityNode other = (PriorityNode) o;
        return priority.compareTo(other.getPriority());
    }


    public String toString()
    {
        return "("+data+", "+priority+")";
    }
}

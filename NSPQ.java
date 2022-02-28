public class NSPQ<E> extends PriorityQueue<PriorityNode> {


    public NSPQ() {
        super();
    }

@Override
    public PriorityNode poll() {

        if(list.size() == 0)
        {
            System.out.println("empty?????");
            return null;
        }
PriorityNode y = list.remove(0);
    for(int a = 0; a < list.size(); a++)
    {
        list.get(a).setPriority(list.get(a).getPriority()+1);
    }
            return y;

    }




}
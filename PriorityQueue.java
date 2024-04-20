import java.util.ArrayList;

public class PriorityQueue<E extends Comparable> implements PriorityQueueInterface<E>  {
    public ArrayList<E> list;
    public PriorityQueue()
    {
        list = new ArrayList<>();
    }



    public String toString() {
        return list.toString();
    }



 

    public boolean offer(E o) {
        list.add(o);
        int index = list.size() - 1;
        while(index > 0)
        {
            if(list.get(index).compareTo(list.get(index-1)) > 0)
            {list.set(index,list.get(index-1));
                list.set(index-1,o);
            }
            index--;
        }

        return true;
    }


    public E poll() {
        if(list.isEmpty())
            return null;
        else
        {
            return list.remove(0);
        }
    }




    public int size() {
        return list.size();
    }


    public boolean empty() {
        return list.isEmpty();
    }


    public void clear() {
        list.clear();
    }


    public E get(int x) {
        return list.get(x);
    }

    public E remove(int index)
    {
        if(index > size())
            return null;
        else
        return list.remove(index);

    }




    public E element() {
        if(list.isEmpty())
            return null;
        else
            return list.get(0);
    }
}

import java.util.Random;
import java.util.ArrayList;
public class Bag
{
    Random rand;
    private ArrayList<Integer> bag;
    public Bag()
    {
        rand = new Random();
        bag = new ArrayList<Integer>();
        generate();
    }
    public void generate()
    { 
        for (int i = 0; i < 7;)
        {
            int t = rand.nextInt(7);
            if (!bag.contains(t))
            {
                bag.add(t);
                i++;
            }         
        }
    }
    public int take()
    {
        if (bag.isEmpty())
        {
            generate();
        }
        return bag.remove(0);
    }
    public void display()
    {
        for (int i = 0; i < 7; i++)
        {
            System.out.println(take());
        }
    }
    public int peak()
    {
        if (bag.isEmpty())
        {
            generate();
        }
        return bag.get(0);
    }
}

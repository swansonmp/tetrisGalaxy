public class OBlock
{
    

    public static boolean advance(Block lead, int dir)
    {
        boolean good = true;
        if (!(lead.getBase()).gravityValid(dir))
            good = false;
        if (!(lead.getOne()).gravityValid(dir))
            good = false;
        if (!(lead.getTwo()).gravityValid(dir))
            good = false;
        if (!(lead.getThree()).gravityValid(dir))
            good = false;

         if (good == true)
         {
            moveAll(lead, dir); 
         }
         return good;
            
    }
    public static void init(Block lead)
    {
        switch (lead.getGravity())
        {
            //from above
            case 0:
                lead.getBase().setBoth(24, 4);
                break;
            //from right
            case 1:
                lead.getBase().setBoth(44, 24);
                break;
            //from below
            case 2:
                lead.getBase().setBoth(24, 44);
                break;
            //from left
            case 3:
                lead.getBase().setBoth(4, 24);
                break;
        }
        int ex = lead.getBase().getX();
        int yi = lead.getBase().getY();
        lead.getOne().setBoth(ex + 1, yi);
        lead.getTwo().setBoth(ex, yi + 1);
        lead.getThree().setBoth(ex + 1, yi + 1);
        
    }
    public static void spinRight(Block lead)
    {
    }
    public static void spinLeft(Block lead)
    {
    }
    public static void moveAll(Block lead, int direction)
    {
        (lead.getBase()).augment(direction);
        (lead.getOne()).augment(direction);
        (lead.getTwo()).augment(direction);
        (lead.getThree()).augment(direction);
    }
}

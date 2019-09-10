public class IBlock
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
        lead.getTwo().setBoth(ex + 2, yi);
        lead.getThree().setBoth(ex + 3, yi);

    }
    public static void spinRight(Block lead)
    {
        switch(lead.getState())
        {
            case 0:
                if (lead.getBase().augmentCheck(2,-1)
                    && lead.getBase().augmentCheck(2,1)
                    && lead.getBase().augmentCheck(2,2))
                {
                    lead.getBase().augment(2,-1);
                    lead.getOne().augment(1,0);
                    lead.getTwo().augment(0,1);
                    lead.getThree().augment(-1,2);
                    lead.incrementRot();
                }
                break;
            case 1:
                if (lead.getBase().augmentCheck(1,2)
                    && lead.getBase().augmentCheck(-1,2)
                    && lead.getBase().augmentCheck(-2,2))
                {
                    lead.getBase().augment(1,2);
                    lead.getOne().augment(0,1);
                    lead.getTwo().augment(-1,0);
                    lead.getThree().augment(-2,-1);
                    lead.incrementRot();
                }
                break;
            case 2:
                if (lead.getBase().augmentCheck(-2,1)
                    && lead.getBase().augmentCheck(-2,-1)
                    && lead.getBase().augmentCheck(-2,-2))
                {
                    lead.getBase().augment(-2,1);
                    lead.getOne().augment(-1,0);
                    lead.getTwo().augment(0,-1);
                    lead.getThree().augment(1,-2);
                    lead.incrementRot();
                }
                break;
            case 3:
                if (lead.getBase().augmentCheck(-1,-2)
                    && lead.getBase().augmentCheck(1,-2)
                    && lead.getBase().augmentCheck(2,-2))
                {
                    lead.getBase().augment(-1,-2);
                    lead.getOne().augment(0,-1);
                    lead.getTwo().augment(1,0);
                    lead.getThree().augment(2,1);
                    lead.incrementRot();
                }
                break;
        }
    }
    public static void spinLeft(Block lead)
    {
        switch(lead.getState())
        {
            case 0:
                if (lead.getBase().augmentCheck(1,2)
                    && lead.getBase().augmentCheck(1,1)
                    && lead.getBase().augmentCheck(1,-1))
                {
                    lead.getBase().augment(1,2);
                    lead.getOne().augment(0,1);
                    lead.getTwo().augment(-1,0);
                    lead.getThree().augment(-2,-1);
                    lead.decrementRot();
                }
                break;
            case 1:
                if (lead.getBase().augmentCheck(-2,1)
                    && lead.getBase().augmentCheck(-1,1)
                    && lead.getBase().augmentCheck(1,1))
                {
                    lead.getBase().augment(-2,1);
                    lead.getOne().augment(-1,0);
                    lead.getTwo().augment(0,-1);
                    lead.getThree().augment(1,-2);
                    lead.decrementRot();
                }
                break;
            case 2:
                if (lead.getBase().augmentCheck(-1,-2)
                    && lead.getBase().augmentCheck(-1,-1)
                    && lead.getBase().augmentCheck(-1,1))
                {
                    lead.getBase().augment(-1,-2);
                    lead.getOne().augment(0,-1);
                    lead.getTwo().augment(1,0);
                    lead.getThree().augment(2,1);
                    lead.decrementRot();
                }
                break;
            case 3:
                if (lead.getBase().augmentCheck(2,-1)
                    && lead.getBase().augmentCheck(1,-1)
                    && lead.getBase().augmentCheck(-1,-1))
                {
                    lead.getBase().augment(2,-1);
                    lead.getOne().augment(1,0);
                    lead.getTwo().augment(0,1);
                    lead.getThree().augment(-1,2);
                    lead.decrementRot();
                }
                break;
        }
    }
    public static void moveAll(Block lead, int direction)
    {
        (lead.getBase()).augment(direction);
        (lead.getOne()).augment(direction);
        (lead.getTwo()).augment(direction);
        (lead.getThree()).augment(direction);
    }
}

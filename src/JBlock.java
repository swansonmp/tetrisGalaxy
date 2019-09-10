public class JBlock
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
        // from above
            case 0:
                lead.getBase().setBoth(24, 4);
                break;
            // from right
            case 1:
                lead.getBase().setBoth(44, 24);
                break;
            // from below
            case 2:
                lead.getBase().setBoth(24, 44);
                break;
            // from left
            case 3:
                lead.getBase().setBoth(4, 24);
                break;
        }
        int ex = lead.getBase().getX();
        int yi = lead.getBase().getY();
        lead.getOne().setBoth(ex - 1, yi - 1);
        lead.getTwo().setBoth(ex - 1, yi);
        lead.getThree().setBoth(ex + 1, yi);

    }

    public static void spinRight(Block lead)
    {
        Cell hold = lead.getBase();

        switch (lead.getState())
        {
            case 0:
                if (hold.augmentCheck(0, -1)
                    && hold.augmentCheck(1, -1) &&
                    hold.augmentCheck(0, 1))
                {
                    // these set the cells position based off the position the
                    // block is in.
                    lead.getOne().augment(2, 0);
                    lead.getTwo().augment(1, -1);
                    lead.getThree().augment(-1, 1);
                    // increments state that its in in memory
                    lead.incrementRot();
                }
                break;
            case 1:
                if (hold.augmentCheck(-1, 0) &&
                    hold.augmentCheck(1, 0) &&
                    hold.augmentCheck(1, 1))
                {
                    lead.getOne().augment(0, 2);
                    lead.getTwo().augment(1, 1);
                    lead.getThree().augment(-1, -1);
                    lead.incrementRot();
                }
                break;
            case 2:
                if (hold.augmentCheck(0, -1) && hold.augmentCheck(0, 1) &&
                    hold.augmentCheck(-1, 1))
                {
                    lead.getOne().augment(-2, 0);
                    lead.getTwo().augment(-1, 1);
                    lead.getThree().augment(1, -1);
                    lead.incrementRot();
                }
                break;
            case 3:
                if (hold.augmentCheck(-1, 0) && hold.augmentCheck(-1, -1) &&
                    hold.augmentCheck(1, 0))
                {
                    lead.getOne().augment(0, -2);
                    lead.getTwo().augment(-1, -1);
                    lead.getThree().augment(1, 1);
                    lead.incrementRot();
                }
                break;
        }
    }

    public static void spinLeft(Block lead)
    {

        Cell hold = lead.getBase();
        switch (lead.getState())
        {
            case 0:
                if (hold.augmentCheck(0, -1) && hold.augmentCheck(0, 1) &&
                    hold.augmentCheck(-1, 1))
                {
                    lead.getOne().augment(0, 2);
                    lead.getTwo().augment(1, 1);
                    lead.getThree().augment(-1, -1);
                    lead.decrementRot();
                }
                break;
            case 1:
                if (hold.augmentCheck(-1, 0) && hold.augmentCheck(-1, -1) &&
                    hold.augmentCheck(1, 0))
                {
                    lead.getOne().augment(-2, 0);
                    lead.getTwo().augment(-1, 1);
                    lead.getThree().augment(1, -1);
                    lead.decrementRot();
                }
                break;
            case 2:
                if (hold.augmentCheck(0, 1) &&
                    hold.augmentCheck(0, -1) &&
                    hold.augmentCheck(1, -1))
                {
                    lead.getOne().augment(0, -2);
                    lead.getTwo().augment(-1, -1);
                    lead.getThree().augment(1, 1);
                    lead.decrementRot();
                }
                break;
            case 3:
                if (hold.augmentCheck(-1, 0) &&
                    hold.augmentCheck(1, 0) &&
                    hold.augmentCheck(1, 1))
                {
                    lead.getOne().augment(2, 0);
                    lead.getTwo().augment(1, -1);
                    lead.getThree().augment(-1, 1);
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

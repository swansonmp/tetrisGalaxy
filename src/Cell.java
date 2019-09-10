
public class Cell
{
    private int x;
    private int y;
    Game game;
    public Cell(Block block)
    {
        game = block.getGame();
    }
    public Cell(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public boolean gravityValid(int grav)
    {  
        switch (grav) {
            case 0:
                return game.board[x][y+1] == 0;
            case 1:
                return game.board[x-1][y] == 0;
            case 2:
                return game.board[x][y-1] == 0;
            case 3:
                return game.board[x+1][y] == 0;
            default:
                return game.board[x][y] == 0;
        }
        
    }
    public int colDistance(int grav)
    {
    	switch (grav)
    	{
    	case 0:
    		for (int i = 1; safe(i, grav); i++)
    		{
    			if (!augmentCheck(0, i))
    			{
    				return i;
    			}
    		}
    		break;
    	case 1:
    		for (int i = 1; safe(i, grav); i++)
    		{
    			if (!augmentCheck(-i, 0))
    			{
    				return i;
    			}
    		}
    		break;
    	case 2:
    		for (int i = 1; safe(i, grav); i++)
    		{
    			if (!augmentCheck(0, -i))
    			{
    				return i;
    			}
    		}
    		break;
    	case 3:
    		for (int i = 1; safe(i, grav); i++)
    		{
    			if (!augmentCheck(i, 0))
    			{
    				return i;
    			}
    		}
    	}
    	return -1;
    }
    public boolean safe(int distance, int grav)
    {
    	switch (grav)
    	{
    	case 0:
    		if (y + distance > 48)
    			return false;
    		break;
    	case 1:
    		if (x - distance < 1)
    			return false;
    		break;
    	case 2:
    		if (y - distance < 1)
    			return false;
    		break;
    	case 3:
    		if (x + distance > 48)
    			return false;
    		break;
    	}
    	return true;
    }
    public void augment(int direction)
    {
        /**
         * 0 - Down
         * 1 - Right
         * 2 - Left
         * 3 - Up
         */
        switch (direction)
        {
            case 0:
                setY(y+1);
                break;
            case 1:
                setX(x-1);
                break;
            case 2:
                setY(y-1);
                break;
            case 3:
                setX(x+1);
                break;
        }
    }
    public void augment(int x, int y)
    {
        setX(this.x + x);
        setY(this.y + y);
    }
    public boolean augmentCheck(int x, int y)
    {
        return (game.board[this.x + x][this.y + y] == 0);
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public void setBoth(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public void clear()
    {
    	x = 0;
    	y = 0;
    }

}

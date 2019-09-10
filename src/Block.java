

public class Block
{
    private Game game;
    private boolean active;
    private int type;
    private int rotation;
    private int gravity;
    private Cell base;
    private Cell one;
    private Cell two;
    private Cell three;
    

    public Block(int type, Game game)
    {
        this.game = game;
        active = true;
        this.type = type;
        this.rotation = 0;
        
        //temporary... falling blocks falling blocks...
        this.gravity = game.gravity;
        init();
    }
    
    public void advance() {
        switch (type) {
            case 0:
                active = IBlock.advance(this, game.gravity);
                break;
            case 1:
                active = JBlock.advance(this, game.gravity);
                break;
            case 2:
                active = LBlock.advance(this, game.gravity);
                break;
            case 3:
                active = OBlock.advance(this, game.gravity);
                break;
            case 4:
                active = SBlock.advance(this, game.gravity);
                break;
            case 5:
                active = TBlock.advance(this, game.gravity);
                break;
            case 6:
                active = ZBlock.advance(this, game.gravity);
                break;
        }
    }
    public void incrementRot()
    {
        switch (rotation)
        {
            case 0:
                rotation = 1;
                break;
            case 1:
                rotation = 2;
                break;
            case 2:
                rotation = 3;
                break;
            case 3:
                rotation = 0;
                break;
        }
    }
    
    public void decrementRot()
    {
        switch (rotation)
        {
            case 0:
                rotation = 3;
                break;
            case 1:
                rotation = 0;
                break;
            case 2:
                rotation = 1;
                break;
            case 3:
                rotation = 2;
                break;
        }
    }
    public void rotateRight()
    {
        switch (type) {
            case 0:
                IBlock.spinRight(this);
                break;
            case 1:
                JBlock.spinRight(this);
                break;
            case 2:
                LBlock.spinRight(this);
                break;
            case 3:
                OBlock.spinRight(this);
                break;
            case 4:
                SBlock.spinRight(this);
                break;
            case 5:
                TBlock.spinRight(this);
                break;
            case 6:
                ZBlock.spinRight(this);
                break;
        }
    }
    
    public void rotateLeft()
    {
        switch (type) {
            case 0:
                IBlock.spinLeft(this);
                break;
            case 1:
                JBlock.spinLeft(this);
                break;
            case 2:
                LBlock.spinLeft(this);
                break;
            case 3:
                OBlock.spinLeft(this);
                break;
            case 4:
                SBlock.spinLeft(this);
                break;
            case 5:
                TBlock.spinLeft(this);
                break;
            case 6:
                ZBlock.spinLeft(this);
                break;
        }
    }
    public void dump()
    {
        int[] act = activeSet();
        game.board[act[0]][act[1]] = act[8];
        game.board[act[2]][act[3]] = act[8];
        game.board[act[4]][act[5]] = act[8];
        game.board[act[6]][act[7]] = act[8];
    }
    public int[] activeSet()
    {
    	int x;
    	int y;
        int[] act = {base.getX(), base.getY(), one.getX(), one.getY(), two.getX(), two.getY(), three.getX(), three.getY(), type + 2, landingZone(), gravity};
        return act;
    }
    public void clear()
    {
    	base = new Cell(this);
    	one = new Cell(this);
    	two = new Cell(this);
    	three = new Cell(this);
    }
    public int landingZone()
    {
    	int num = 100;
    	int b = base.colDistance(gravity);
    	int o = one.colDistance(gravity);
    	int t = two.colDistance(gravity);
    	int r = three.colDistance(gravity);
    	if (b == -1 && o == -1 && t == -1 && r == -1)
    	{
    		return -1;
    	}
    	if (b < num && b != -1)
    		num = b;
    	if (o < num && o != -1)
    		num = o;
    	if (t < num && t != -1)
    		num = t;
    	if (r < num && r != -1)
    		num = r;
    	return num;
    }
    public void hardDrop()
    {
    	int zone = landingZone();
    	if (zone != -1)
    	{
    		for (int i = 0; i < zone - 1; i++)
    		{
    			advance();
    		}
    	}
    }
    //Initializes cell locations
    public void init()
    {
    	switch (game.gravity)
    	{
    	case 0:
    		if (game.board[24][4] != 0)
    			game.g.end();
    		break;
    		//from right
    	case 1:
    		if (game.board[44][24] != 0)
    			game.g.end();
    		break;
    		//from below
    	case 2:
    		if (game.board[24][44] != 0)
    			game.g.end();
    		break;
    		//from left
    	case 3:
    		if (game.board[4][24] != 0)
    			game.g.end();
    		break;
    	}
    
        base = new Cell(this);
        one = new Cell(this);
        two = new Cell(this);
        three = new Cell(this);
        switch (type) {
            case 0:
                IBlock.init(this);
                break;
            case 1:
                JBlock.init(this);
                break;
            case 2:
                LBlock.init(this);
                break;
            case 3:
                OBlock.init(this);
                break;
            case 4:
                SBlock.init(this);
                break;
            case 5:
                TBlock.init(this);
                break;
            case 6:
                ZBlock.init(this);
                break;
        }
    }
    
    public void move(int dir) {
        switch (type) {
            case 0:
                IBlock.advance(this, dir);
                break;
            case 1:
                JBlock.advance(this, dir);
                break;
            case 2:
                LBlock.advance(this, dir);
                break;
            case 3:
                OBlock.advance(this, dir);
                break;
            case 4:
                SBlock.advance(this, dir);
                break;
            case 5:
                TBlock.advance(this, dir);
                break;
            case 6:
                ZBlock.advance(this, dir);
                break;
        }
    }
    public boolean isActive()
    {
        return active;
    }
    public Cell getBase()
    {
        return base;
    }
    public Cell getOne()
    {
        return one;
    }
    public Cell getTwo()
    {
        return two;
    }
    public Cell getThree()
    {
        return three;
    }
    public int getState()
    {
        return rotation;
    }
    public int getGravity()
    {
        return gravity;
    }
    public Game getGame()
    {
        return game;
    }
}

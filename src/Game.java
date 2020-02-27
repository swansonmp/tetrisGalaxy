import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
// Tetris Galaxy 1.0.4.1 -- config added
// Ethan Fritz, Nic Landaverde, William Sease, Matthew Swanson 2019
public class Game
{
    public static final int augment = 24;

    public int[][] board;
    public int gravity;
    public Block active;
    public Random randgen;
    public GraphicsDriver g;
    
    private int[] activeSet;
    public Bag bag;
    public int[] scoring;
    // coords
    private boolean[][] killZone;
    // center should be at [24][24]
    private boolean[][] centerPiece;
    

    public Game()
    {
    	g = new GraphicsDriver();
		Thread visual = new Thread(g);
		visual.start();
		bag = new Bag();
		//frame = new JFrame();
        g.frame.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) { //maybe input buffer here man
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    //System.out.println("Up key pressed");  
                	if (gravity % 2 == 1)
                        active.move(2);
                    if (gravity == 2)
                    	active.advance();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    //System.out.println("Down key pressed");
                    if (gravity % 2 == 1)
                        active.move(0);
                    if (gravity == 0)
                    	active.advance();
                }
                else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    //System.out.println("Left key pressed");
                    if (gravity % 2 == 0)
                        active.move(1);
                    if (gravity == 1)
                    	active.advance();
                }
                else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (gravity % 2 == 0)
                        active.move(3);
                    if (gravity == 3)
                    	active.advance();
                    //System.out.println("Right key pressed");
                }
                else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    active.rotateLeft();
                    //System.out.println("Z key pressed");
                }
                else if (e.getKeyCode() == KeyEvent.VK_X) {
                    active.rotateRight();
                    //System.out.println("X key pressed");
                }
                else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                	active.hardDrop();
                }
                g.passIn(board, getActiveSet(), scoring, bag);
            }
            
            public void keyReleased(KeyEvent e) { 
            }  
            public void keyTyped(KeyEvent e) { }
        });
        activeSet = new int[10];
        activeSet[9] = -1;
        randgen = new Random();
        board = new int[49][49];
        scoring = new int[2];
        board[24][24] = 1;
        gravity = 0;
        while (!g.startIt()) { wait(150); }
        g.passIn(board, activeSet, scoring, bag);
        try { gameLoop();}
        catch (Exception e) { g.end();}
    }

    // public
    public void gameLoop()
    {
        //Step 1: Spawn new piece
        //Step 2: Piece is active loop
        //Step 3: Active loop breaks, check everything
        boolean gamover = false;
        do
        {
            //randgen.nextInt(7)
            active = new Block(bag.take(), this);
            g.passIn(board, getActiveSet(), scoring, bag);
           
            while (active.isActive())
            {
                //need something to space out game ticks
            	wait(frames());
                active.advance();
                g.passIn(board, getActiveSet(), scoring, bag);
                
                
                //player input somewhere here idk
            }
            active.dump();
            g.passIn(board, getActiveSet(), scoring, bag);
            //check for line clears
            insert(getActiveSet());
            incrementGrav();
            //gamover = true;
        } while (!gamover);
    }
    public double frames()
    {
    	double level = scoring[1] / 10;
    	 if (level < 9)
    		 return (800.0 - level * 83.3);
    	 if (level == 9)
    		 return 100;
    	 if (level < 13)
    		 return 83.3;
    	 if (level < 16)
    		 return 66.6;
    	 if (level < 19)
    		 return 50;
    	 if (level < 29)
    		 return 33.3;
    	 return 16.6;
    }
    public void addScore(int[] arr)
    {
        int[] nu = new int[4];
        int count = 0;
        for (int i = 0; i < 4; i++)
        {
            nu[i] = arr[i];
        }
        int rows = 0;
        for (int i = 0; i < 4; i++)
        {
           if (nu[i] != 0)
               count++;
           {
               for (int k = i + 1; k < 4; k++)
               {
                   if (nu[k] == nu[i]){
                       nu[k] = 0;
                   }
               }
           }
        }
        scoring[1] += count;
        for (int i = 0; i < 4; i++)
        {
            int sc = 0;
            if (arr[i] < 0)
                sc = arr[i] * -1;
            else
                sc = arr[i];
            int val;
            switch (count)
            {
            case 1:
            	val = 4;
            	break;
            case 2:
            	val = 5;
            	break;
            case 3:
            	val = 10;
            	break;
            case 4:
            	val = 30;
            	break;
            default:
            	val = 0;
            }
            scoring[0] += (sc * 8) * val; 
        }
    }
    //runs all coordinates
    public void insert(int[] act)
    {
        int[] breaking = new int[4];
        active.clear();
        for (int i = 0; i < 8; i += 2)
        {
            if (snake(findTopLeft(act[i], act[i + 1])))
            {
                breaking[i / 2] = findTopLeft(act[i], act[i + 1]);
                snakeEat(breaking[i / 2]);
                addScore(breaking);
            }
        }        
        collapseAll(breaking);
        //run matts shit with array
    }
    //matt method for clearing lines
    public void collapseAll(int[] arr)
    {
        //TODO make everything negative
        for (int arri = 0; arri < 4; arri++) {
            arr[arri] = Math.abs(arr[arri]) * -1;
        }
        Arrays.sort(arr);
        for (int arri = 0; arri < 4; arri++) {
            if (arr[arri] != 0) {
                collapse(arr[arri]-1); //should be collapse
            }
        }
    }
    public void collapse(int d) { //d always negative
        boolean blocksPresent = false; //tracks whether we moved any blocks
        for (int i = d+1; i <= (-1*d)-1; i++) { //moves top pieces down
            if (board[i+24][d+24] > 0) blocksPresent = true;
            board[i+24][d+1+24] = board[i+24][d+24]; //copies current block towards center
            board[i+24][d+24] = 0; //sets said block to air
        }
        for (int i = d+1; i <= (-1*d)-1; i++) { //moves bottom pieces up
            if (board[i+24][-1*d+24] > 0) blocksPresent = true;
            board[i+24][-1*d-1+24] = board[i+24][-1*d+24]; //copies current block towards center
            board[i+24][-1*d+24] = 0; //sets said block to air
        }
        for (int j = d+2; j <= (-1*d)-2; j++) { //moves left pieces right
            if (board[d+24][j+24] > 0) blocksPresent = true;
            board[d+1+24][j+24] = board[d+24][j+24]; //copies current block towards center
            board[d+24][j+24] = 0; //sets said block to air
        }
        for (int j = d+2; j <= (-1*d)-2; j++) { //moves top pieces down
            if (board[-1*d-1+24][d+24] > 0) blocksPresent = true;
            board[-1*d+24][j+24] = board[-1*d+24][j+24]; //copies current block towards center
            board[-1*d+24][j+24] = 0; //sets said block to air
        }
        if (blocksPresent) {
            collapse(d-1);
        }
    }
    
    //matt method; helper for clearing lines. finds quadrant
    //1 up, 2 down, 3 left, 4 right
    public int findTopLeft(int x, int y) {  
        x -= augment;
        y -= augment;
        if (x<=-y && -x<=-y) {
            return y; //up
        }
        else if (x<=-y)
            return x; //left
        else if (-x<=-y)
            return -x; //right
        else
            return -y; //down
    }
    public void snakeEat(int x)
    {
        int abs = x;
        if (x < 0)
        {
            abs =  x * -1;
        }
        int snekking = 1 + 2 * abs;
        for (int i = 0; i < 4; i++)
        {
            for (int k = 0; k < snekking; k++)
            {
            	//g.passIn(board, getActiveSet(), scoring, bag);
            	//wait(80);
                switch (i)
                {
                    case 0:
                       board[(-abs + k) + 24][-abs + 24] = 0;
                    case 1:
                        board[abs + 24][(-abs + k) +24] = 0;
                    case 2:
                        board[(abs - k) + 24][abs + 24] = 0;
                    case 3:
                        board[-abs + 24 ][(abs - k) + 24] = 0;
                }
            }
        }
    }
    public boolean snake(int x)
    {
        int abs = x;
        if (x < 0)
        {
            abs =  x * -1;
        }
        int snekking = 1 + 2 * abs;
        for (int i = 0; i < 4; i++)
        {
            for (int k = 0; k < snekking; k++)
            {
                if (!full(i, k, abs))
                {
                    return false;
                }
            }
        }
        return true;      
    }
    public boolean full(int i, int k, int abs)
    {
        switch (i)
        {
            case 0:
               return board[(-abs + k) + 24][-abs + 24] != 0;
            case 1:
                return board[abs + 24][(-abs + k) +24] != 0;
            case 2:
                return board[(abs - k) + 24][abs + 24] != 0;
            case 3:
                return board[-abs + 24 ][(abs - k) + 24] != 0;
                
              
        }
        return false;
    }
    public void update()
    {
        
    }
    public static void wait(int duration) {
        long oldTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < oldTime + ((long) duration)) {
            //lol
        }
    }
    
    public static void wait(double duration) {
        long oldTime = System.currentTimeMillis();
        while (System.currentTimeMillis() < oldTime + ((long) duration)) {
            //lol
        }
    }

    public static void main(String[] args)
    {
        Game darkSouls = new Game();
    }
    
    //Returns coordinates of current tetrimino
    //last entry is color of the blocks
    public int[] getActiveSet()
    {
        return active.activeSet();
    }
    
    public void incrementGrav()
    {
        if (board[24][4] != 0 && board[44][24] != 0 && board[24][44] != 0 && board[4][24] != 0)
			g.end();
        switch (gravity)
        {
            case 0:
                gravity = 1;
                if (board[44][24] != 0)
                	incrementGrav();         	
                break;
            case 1:
                gravity = 2;
                if (board[24][44] != 0)
                	incrementGrav(); 
                break;
            case 2:
                gravity = 3;
                if (board[4][24] != 0)
                	incrementGrav(); 
                break;
            case 3:
                gravity = 0;
                if (board[24][4] != 0)
                	incrementGrav(); 
                break;
        }
    }
}

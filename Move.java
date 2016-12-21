/*
	  represents a move on the board 
*/
public class Move{
	private int x;
	private int y;
   
        public getX(){
	    return x;
	}

	public getY(){
	    return y;	
	}
	
	public Move(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class RollKey extends Object{
	public int[] values;
	public int die;
	public int turn;
	 
	public RollKey(int[] values, int die, int turn){
		this.values = values;
		this.die = die;
		this.turn = turn;
	}
	
	public int hashCode(){
		return turn*931 + die*117 + values[0]*98 + values[1]*12 + values[2]*233 + values[3]*3131 + values[4]*22171 + values[5]*219;
	}
	
	public boolean equals(Object o){
		RollKey r = (RollKey)o;
	    if(r==null)
			return false;
		return turn ==r.turn && die==r.die && values[0]==r.values[0]&& values[1]==r.values[1]&& values[2]==r.values[2]&& values[3]==r.values[3]&& values[4]==r.values[4];
	}

}
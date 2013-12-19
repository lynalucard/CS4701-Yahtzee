public class ExpectationStep{
	public int die;
	public int[] values;
	
	public ExpectationStep(int[] dieRoll, boolean[] move){
		die = 0;values = GameEngine.bucketDie(dieRoll);
		for(int i = 0; i < move.length; i++)
			if(!(move[i])){
				die++;
				values[dieRoll[i]-1]--;
			}
	}
	public ExpectationStep(int die, int[] values){
		this.die = die;
		this.values = values;
	}
	
}
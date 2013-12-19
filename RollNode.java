public class RollNode(){
	
	public int[] die;
	public double[] expectations;
	public int turn;
	public boolean[][] moves;
	
	public RollNode(int[] Die, int turn){
		die = Die;
		expectations = new double[32];
		moves = new boolean[32][5];
		for(int i = 0; i <32; i++)
			for(int j = 0; j <5 j++)
				moves[i][j] = (i & (1<<j))==0;
	}
	
	public Move getExpectation(){
		int[] values = GameEngine.bucketDie(die);
		if(turn == 3){
			int category = YahtzeeGame.maximizeScore(values);
			return new Move((double)YahzteeGame.getScore(values, category), null, category);
		}
		if(turn==2){
			double max = 0.0;
			boolean[] move;
			for(int i = 0; i <32; i++){
				double moveExpectation = expectation(ExpectationStep(die, moves[i]));
				if(expectation(moveExpectation >= max){
					move = moves[i];
					max = moveExpectation;
				}
			}
			return Move(max, move, -1);
		 }
		 
		 
	public double expectation(ExpectationStep exStep){
		if(die == 0){
			int category = YahtzeeGame.maximizeScore(exStep.values);
			return new Move(YahzteeGame.getScore(die, category), null, category);
		}
		double exp;
		for(int i =0; i <6; i++){
			values[i]++;
			exp += = expectation(ExpectationStep(die-1, values));
			values[i]++;
		}
		exp = exp / 6.0;
		return exp;
	}
			
			
			
	
	
import java.util.HashMap;

public class GreedyPlayer implements Agent{

	public int score; //current game score (doesn't reall matter)
	public boolean[] categories;//categories[i] is true is category with index i has been used
	public boolean[][] moves;//two dimensional array where moves[i] is an array of length 5 of 
	//the binary representation of ie i moves[3] = [0,0,0,1,1]
	public HashMap<RollKey, Double> map;
	
	
	public GreedyPlayer(){
		this(new boolean[13]);
	}
	
	public GreedyPlayer(boolean[] categories){
		this.score = 0;
		this.categories = categories;
		this.map = new HashMap<RollKey, Double>();
		this.moves = new boolean[32][5];
		for(int i = 0; i <32; i++)
			for(int j = 0; j <5; j++)
				moves[i][j] = (i & (1<<j))==0;
	}

	public boolean[] firstRoll(int[] die){
		map = new HashMap<RollKey, Double>();
		return bestMove(die, 1).move;
	}
	
	public boolean[] secondRoll(int[]die){
		return bestMove(die, 2).move;
	}
	
	public int decideCategory(int[] die){
		int category = bestMove(die, 3).category;
		System.out.println(category);
		categories[category] = true;
		return category;
	}
	
	public Move bestMove(int[] die, int turn){
		if(turn==3){
		    int[] values = GameEngine.bucketDie(die);
			return YahtzeeGame.maximizeScore(values, categories);
			}
		int index = 0;
		double max = 0.0;
		if(turn==2){
			for(int i = 0; i < 32; i++) {//find the hold move with the highest expectation
				double moveExpectation = getHoldExpectation(new ExpectationStep(die, moves[i]), 2);
				if(moveExpectation >= max){
					index = i;
					max = moveExpectation;
				}
				//System.out.println("MOVE: " + moves[i][0] + " " + moves[i][1] + " " 
//+ moves[i][2] + " " 	+ moves[i][3] + " " 	+ moves[i][4]);
//System.out.println(moveExpectation);
			}
		}
		if(turn==1){
			for(int i = 0; i < 32; i++) {//find the hold move with the highest expectation
				double moveExpectation = getHoldExpectation(new ExpectationStep(die, moves[i]), 1);
				//System.out.println(moveExpectation + "---------------" + i);
				if(moveExpectation >= max){
					index = i;
					max = moveExpectation;
				}		
			}
		}
		return new Move(max, moves[index], -1);
	}
	//get the expectation of a move one level down
	public double getHoldExpectation(ExpectationStep exStep, int turn){
		RollKey key = new RollKey(exStep.values, exStep.die, turn);
		//System.out.println(map.size());
		if(map.containsKey(key)){
			return map.get(key);
		}else{
			if(exStep.die==0){
				if(turn ==2)return YahtzeeGame.maximizeScore(exStep.values, categories).score;
				else return generateAll(exStep.values);
			}
			double exp = 0;
			for(int i =0; i <6; i++){
				exStep.values[i]++;
				exp += getHoldExpectation(new ExpectationStep(exStep.die-1, exStep.values), turn);
				exStep.values[i]--;
			}
			exp = exp/6.0;
			map.put(key, exp);
			return exp;
		}
	}
	
	public double generateAll(int[] values){
		int[] die = GameEngine.debucketDie(values);
		double max = 0;
		double current;
		for(int i =0; i < 32; i++){
			current = getHoldExpectation(new ExpectationStep(die, moves[i]), 2);
			if(current>max)
				max = current;
		}
		return max;
	}
	
	
	
	public void receiveScore(int value){
		this.score += value;
	}


}
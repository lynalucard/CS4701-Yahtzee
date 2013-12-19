public class YahtzeeGame(){
	private boolean[] categories;
	private int score;
	
	public YahzteeGame(){
		categories = new boolean[13];
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean[] getCategories(){
		return categories;
	}
	
	public int recordRound(int[] die, int category){
		int turnScore = 0;
		if(categories[category])
			throw Exception("Duplicate Category");
		categories[category] = true;
		switch (category)
			case 0: turnScore += scoreYahtzee(die);break;
			case 7: turnScore += scoreOfAKind(die, 3);break;
			case 8: turnScore += scoreOfAKind(die, 4);break;
			case 9: turnScore += scoreFullHouse(die);break;
			case 10: turnScore += scoreStraight(die, 4);break;
			case 11: turnScore += scoreStraight(die, 5);break;
			case 12: turnScore += scoreChance(die);break;
			default break;
		score += turnScore;
		return turnScore;
	
	}
	
	public int scoreConstant(int [] die, int constant){
		int score = 0;
		for(int i = 0; i <= die.length; i++)
			if(die[i]==constant)
				score += constant;
		return score;
	}
	
	public int scoreYahtzee(int[] die){
		int value = die[0];
		for(int i = 1; i <= die.length; i++)
			if(die[i] != value)
				return 0;
		return 50;
	}
	
	public int scoreChance(int[] die){
		int score = 0;
		for(int i: die)
			score += i;
		return score;
	}
	public int[] bucketDie(int[] die){
		int[] values = new int[6];
		for(int i:die)
			values[i-1]++;
		return values
	}
	public int scoreFullHouse(int[] die){
		int[] values = bucketDie(die);
		int score = 0;
		for(int i = 0; i <= values.length; i++)
			if(!(values[i]==0 || values[i] ==2 ||values[i]==3))
				return 0;
		return 25;
	}
	
	public scoreOfAKind(int[]die, int constant){
		int[] values = bucketDie(die);
		int sum = 0;
		boolean sat = false;
		for(int i = 0; i <=values.length; i++){
			sum += values[i] *(i+1);
			if(values[i] >= constant)
				sat = true;
		}
		return sat? sum : 0;
	}
	
	public scoreStraight(int[] die, int length){
		int[] values = bucketDie(die);
		int len = 0;
		for(int i: values)
			if(values[i] >0)
				len++;
			else
				len = 0;
		if(len >= length)
			return 10*(length-1);
		return 0;
		
	}
	
	
	
}
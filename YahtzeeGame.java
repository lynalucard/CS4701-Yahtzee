import java.lang.Exception;
public class YahtzeeGame{
	private boolean[] categories;
	private int score;
	
	public YahtzeeGame(){
		categories = new boolean[13];
	}
	
	public YahtzeeGame(boolean[] categories){
		this.categories = categories;
	}
	
	public int getScore(){
		return score;
	}
	
	public boolean[] getCategories(){
		return categories;
	}
	
	public int recordRound(int[] values, int category){
		if(categories[category])
			 return -1000;
		categories[category] = true;
		int value = getScore(values, category);
		score += value;
		return value;
	}
	
	public static int getScore(int[] values, int category){
		int turnScore = 0;
		
		switch (category){
			case 0: turnScore += scoreYahtzee(values);break;
			case 1: turnScore += scoreConstant(values, 1);break;
			case 2: turnScore += scoreConstant(values, 2);break;
			case 3: turnScore += scoreConstant(values, 3);break;
			case 4: turnScore += scoreConstant(values, 4);break;
			case 5: turnScore += scoreConstant(values, 5);break;
			case 6: turnScore += scoreConstant(values, 6);break;
			case 7: turnScore += scoreOfAKind(values, 3);break;
			case 8: turnScore += scoreOfAKind(values, 4);break;
			case 9: turnScore += scoreFullHouse(values);break;
			case 10: turnScore += scoreStraight(values, 4);break;
			case 11: turnScore += scoreStraight(values, 5);break;
			case 12: turnScore += scoreChance(values);break;
		}
		return turnScore;
	
	}
	
	public static int scoreConstant(int [] values, int constant){
		return values[constant-1] *constant;
	}
	
	public static int scoreYahtzee(int[] values){
		for(int i:values)
			if(i==5)
				return 50;
		return 0;
	}
	
	public static int scoreChance(int[] values){
		int sum = 0;
		for(int i =0; i < values.length; i++)
			sum += values[i]*(i+1);
		return sum;
	}
	
	public static int scoreFullHouse(int[] values){
		int score = 0;
		for(int i = 0; i < values.length; i++)
			if(!(values[i]==0 || values[i] ==2 ||values[i]==3))
				return 0;
		return 25;
	}
	
	public static int scoreOfAKind(int[] values, int constant){
		int sum = 0;
		boolean sat = false;
		for(int i = 0; i <values.length; i++){
			sum += values[i] *(i+1);
			if(values[i] >= constant)
				sat = true;
		}
		return sat? sum : 0;
	}
	
	public static int scoreStraight(int[] values, int length){
		int len = 0;
		for(int i: values){
			if(len >= length)
				return 10*(length-1);
			if(i > 0)
				len++;
			else
				len = 0;
		}
		if(len >= length)
				return 10*(length-1);
		return 0;
		
	}
	public static Move maximizeScore(int[] values, boolean[] categories){
		int max = 0;
		int current = 0;
		int index = -1;
		for(int i = 0; i < 13; i++){
			if(!categories[i]){
				current = getScore(values, i);
				if(max <= current){
					index = i;
					max = current;
				}
				if(index== -1)
					index = i;
			}
		}
		return new Move((double)max, new boolean[5], index);
	}
	
	public static Move maximizeScore(int[] values, boolean[] categories, double[] heuristic){
		double max = 0;
		double current = 0;
		int index = -1;
		for(int i = 0; i < 13; i++){
			if(!categories[i]){
				current = getScore(values, i) + heuristic[index(categories, i)];
				if(max <= current){
					index = i;
					max = current;
				}
				if(index== -1)
					index = i;
			}
		}
		return new Move(max, new boolean[5], index);
	}
	
	public static int index(boolean[] categories, int category){
		return index(categories) + (1<<category);
	}
	
	public static int index(boolean[] categories){
		int index = 0;
		for(int i = 0; i < 13; i++)
			if(categories[i])
				index += 1<<i;
		return index;
	}
	
	public static boolean[] categories(int index){
		boolean[] c = new boolean[13];
		for(int i=0; i < 13; i++){
			c[i] = ((index>>i)&1)==1;
		}	
		return c;
	}
	
}
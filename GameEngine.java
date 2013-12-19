import java.util.Random;

public class GameEngine{
    public Random rand;
	private YahtzeeGame game;
	
	public GameEngine(){
		rand = new Random();
		game = new YahtzeeGame();
	}
	
	public GameEngine(boolean[] categories){
		rand = new Random();
		game = new YahtzeeGame(categories);
	}
	
	public int[] roll(int[] die, boolean[] hold){
		for(int i = 0; i < die.length; i++){
			if(!hold[i]){
				die[i] = rand.nextInt(6) + 1;
			}
		}
		return die;
	}
	
	public int[] roll(){
		int[] die = new int[5];
		for(int i = 0; i <die.length; i++){
			die[i] = rand.nextInt(6) + 1;
		}
		return die;
	}
	
	public static int[] bucketDie(int[] die){
		int[] values = new int[6];
		for(int i:die)
			values[i-1]++;
		return values;
	}
	
	public static int[] debucketDie(int[] values){
		int[] die = new int[5];
		int ind = 0; 
		int c= 0;
		for(int i = 0; i < values.length; i++){
			while(values[i] >c){
				die[ind] = i + 1;
				ind++;
				c++;
			}
			c = 0;
		}
		return die;
	}
				
	
	public int recordRound(int[] die, int category) throws Exception{
		return game.recordRound(bucketDie(die),category);
	}
	
	public int getScore(){
		return game.getScore();
	}
	
	public void newGame(){
		game = new YahtzeeGame();
	}
	
}
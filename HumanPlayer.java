import java.util.Scanner;
public class HumanPlayer implements Agent {
	int score;
	boolean[] categories;
	Scanner scanner;
	
	public HumanPlayer(){
		score = 0;
		categories = new boolean[13];
		scanner = new Scanner(System.in);
	}
	
	public boolean[] firstRoll(int[] die){
		boolean used = false;
		System.out.println("Categories Availible : ");
		for(int i = 0; i <categories.length; i++)
			if(!categories[i])
				System.out.println(i + " " + categoryName(i));
			else
				used = true;
		if(used){
			System.out.println("Categories Used : ");
			for(int i = 0; i <categories.length; i++)
				if(categories[i])
					System.out.println(categoryName(i));
		}
		printDie(die);
		return inputHold();
	}
	public boolean[] secondRoll(int[] die){
		printDie(die);
		return inputHold();
	}
	
	public int decideCategory(int[] die){
		printDie(die);
		int category =  inputCategory();
		categories[category] = true;
		return category;
	}
	
	public void printDie(int[] die){
		System.out.print("Roll : ");
		for(int d: die)
			System.out.print(d + "   ");
		System.out.print("\n");
	}
	
	public boolean[] inputHold(){
		boolean[] out = new boolean[5];
		System.out.print("Hold die: ");
		String code = scanner.nextLine();
		String[] holdArray = code.split("");
		for(int i = 1; i <6; i++)
			if(Integer.parseInt(holdArray[i])!= 0)
				out[i-1] = true;
		return out;
	}
	
	public int inputCategory(){
		System.out.print("Category : ");
		String cat = scanner.nextLine();
		return Integer.parseInt(cat);
	}
	
	public void receiveScore(int value){
		score += value;
		System.out.println("Scored " + value + " points!");
		System.out.println("Total points = " + score + "\n--------------------------");
	}
		
		
	public String categoryName(int i){
		switch(i){
			case 0: return "Yahtzee!";
			case 1: return "Ones";
			case 2: return "Twos";
			case 3: return "Threes";
			case 4: return "Fours";
			case 5: return "Fives";
			case 6: return "Sixes";
			case 7: return "Three-of-a-kind";
			case 8: return "Four-of-a-kind";
			case 9: return "Full House";
			case 10: return "Small Straight";
			case 11: return "Large Straight";
			case 12: return "Chance";
		}
		return "Hey fuck tard get a real number";
	}
	

}
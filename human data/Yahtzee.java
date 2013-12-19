import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.*;

public class Yahtzee{
	public static void main(String[] args) throws Exception{
		System.out.println("Pete sucks");
		
		/*
		int round = 5;
		int comp = 0;
		PrintWriter writer = new PrintWriter("round" + round +"-" + comp + ".csv");
		GameEngine g;
		Agent a;
		double[] h = new double[8192];
		//fill heuristic
		Scanner s =null;
		
		BufferedReader reader = new BufferedReader(new FileReader("heuristic.csv"));
		String line;
		int count = 0;
		while((line = reader.readLine()) !=null){
			s = new Scanner(line);
			s.useDelimiter(",");
			int index = Integer.parseInt(s.next());
			double score = Double.parseDouble(s.next());
			h[index] = score;		
		}
		//get set
		BufferedReader reader1 = new BufferedReader(new FileReader("Index order.csv"));
		String line1;
		line1 = reader1.readLine();
		
		
		for(int p = 1; p<round; p++)
			line1 = reader1.readLine();
			
		System.out.println(line1);
		String[] inds = line1.split(",");
		for(int z = 0; z <inds.length; z++){
			count++;
			if(count < 100*comp || count > (comp+1)*100)
				continue;
			if(inds[z]=="")
				break;
			int index = Integer.parseInt(inds[z]);
				double sum = 0;
				writer.write(index + ",");
				boolean[] categories;
				for(int j = 0; j < 1000; j++){*/
					PrintWriter writer = new PrintWriter("HumanData.csv");
					try{
					GameEngine g;
					Agent a;
				for(int k = 0; k < 4; k++){
					int turn = 0;
					g = new GameEngine();
					a = new HumanPlayer();
				
					while(turn <13){
						int[] die = g.roll();
						//System.out.println(die[0] + " " + die[1] + " " + die[2] + " " + die[3] + " " + die[4]); 
						boolean[] hold = a.firstRoll(die);
						//System.out.println(hold[0] + " " + hold[1] + " " + hold[2] + " " + hold[3] + " " + hold[4]);
						die = g.roll(die,hold);
						//System.out.println(die[0] + " " + die[1] + " " + die[2] + " " + die[3] + " " + die[4]); 
						hold = a.secondRoll(die);
						//System.out.println(hold[0] + " " + hold[1] + " " + hold[2] + " " + hold[3] + " " + hold[4]);
						die = g.roll(die, hold);
						//System.out.println(die[0] + " " + die[1] + " " + die[2] + " " + die[3] + " " + die[4]); 
						int category = a.decideCategory(die);
						//System.out.println(category);
						int score = g.recordRound(die, category);
						//System.out.println(score);
						//writer.write(category +",");
						a.receiveScore(score);
						writer.write(category + ",");
						writer.write(score + ",");
						turn++;
				}
				writer.write(g.getScore() + "\n");
			}
				//System.out.println("Final score : " + g.getScore());
				//writer.write(g.getScore() + "\n");
				/*sum += g.getScore();
				}
				sum = sum/1000;
				writer.write(sum +"\n");
				System.out.println(count);
		}*/
		}catch(Exception e){}
		writer.close();
	}
}
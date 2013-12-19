import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.util.Scanner;
import java.io.BufferedReader;
import java.util.*;

public class Yahtzee{
	public static void main(String[] args) throws Exception{
		System.out.println("Pete sucks");
		int core = 0;
		/*
		PrintWriter writer = new PrintWriter("GreedyData" + core +".csv");
				for(int k = 0; k < 5000; k++){
					int turn = 0;
					g = new GameEngine();
					a = new GreedyPlayer();
				
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
				System.out.println(k);
			}
		writer.close();
	*/
	Scanner s = new Scanner(new File("hDATA.csv"));
	int count = 0;
	double[] d = new double[13];
	while(s.hasNext()){
		int i = 1;
		Scanner n = new Scanner(s.nextLine());
		n.useDelimiter(",");
		if(count==100000) break;
		while(n.hasNext()){
		int k = Integer.parseInt(n.next());
		d[k] += Integer.parseInt(n.next());
		i++;
		}
		if(count %1000==0) System.out.println(count);
		count++;
	}
	
	for(int i = 0; i < 13; i++){
		d[i] = d[i]/100000;
	}
	
	for(int j = 0; j < 13; j++){
		System.out.println(d[j]);
		}
	
	}
}
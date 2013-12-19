interface Agent {

	boolean[] firstRoll(int[] die);
	
	boolean[] secondRoll(int[]die);
	
	int decideCategory(int[] die);
	
	void receiveScore(int value);
}
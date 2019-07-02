package appraisal;

import java.util.ArrayList;

/*
 * This class keeps track of the individual scores and average for each food item,
 * and has methods to manage and display this information.
 */

public class FoodItem {
	// Default empty constructor
	public FoodItem() {}
	
	// Overloaded constructor - takes name attribute as argument
	public FoodItem(String name) {
		this.name = name;
	}
	
	// Attributes
	// Arraylist to track individual scores
	private ArrayList<Integer> scores = new ArrayList<Integer>();
	private String name;
	/*
	 * Lowest score and highest score are both initialized to
	 * values outside of the allowable input range, to allow
	 * the first entry to be used for both values.
	 */
	private int lowScore = 101;
	private int highScore = 0;
	private int average = 0;
	
	// Getters/Setters for all attributes
	public ArrayList<Integer> getScores() {
		return scores;
	}
	public void setScores(ArrayList<Integer> scores) {
		this.scores = scores;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLowScore() {
		return lowScore;
	}
	public void setLowScore(int lowScore) {
		this.lowScore = lowScore;
	}
	public int getHighScore() {
		return highScore;
	}
	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	public int getAverage() {
		return average;
	}
	public void setAverage(int average) {
		this.average = average;
	}
	
	/*
	 * This method adds a new score to the list, and sets a new highest or lowest
	 * score, if needed.  Also, the current average is updated.
	 */
	
	public void addNewScore(int score) {
		// Adding new score to the list
		scores.add(score);
		
		/*
		 * Checking to see if new score is lower than current low score, 
		 * or higher than current high score, and setting one if needed.
		 */
		if(score < getLowScore()) {
			setLowScore(score);
		}
		if (score > getHighScore()) {
			setHighScore(score);
		}
		
		// Total variable to count up total of all scores
		double total = 0;
		// Loop to figure up average
		for (int num : scores) {
			total += num; // Aggregating total of all scores
		}
		// Updating average
		setAverage((int)Math.round(total / this.scores.size()));
		// Testing ONLY
	}
	
	// Overriden toString() method; returns string of all important
	// information in a formatted manner
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// Checking to see if item has been rated.
		// If so, print all values; otherwise, tell 
		// user item has not been rated.
		if (this.scores.size() > 0) {
			sb.append("Ratings for: " + getName());
			sb.append("\n" + getScores().size() + " total score(s) entered");
			sb.append("\n" + getAverage() + " average score");
			sb.append("\nHighest Score: " + getHighScore() + " Lowest Score: " + getLowScore());
		} else {
			sb.append("There are no ratings for this item yet!");
		}
		
		return sb.toString();
	}
	
}

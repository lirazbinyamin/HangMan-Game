import java.util.HashSet;
import java.util.Set;

public class GameLogic {
	
	private Set<String> guessedLetters = new HashSet<>(); // HashSet to store guessed letters by user in current game
    private final String word; // String to hold current game word
    private int mistakes = 0; // Integer to hold current game mistakes
	private int maxMistakes; // Integer to hold Max mistakes
    
	// Constructor
	public GameLogic(String randomWord,int maxMistakes) {
		
		this.word = randomWord;
		this.maxMistakes = maxMistakes;
		this.guessedLetters = new HashSet<>();
	}
	
	//Method to check if game word contains selected letter, returns true if so. else, returns false
	public boolean checkGuessedLetter(String selectedLetter) {
			
		if (word.contains(selectedLetter)) 
 	        return true;
		 else {
			 mistakes++;
			 return false;
		 }
	}
	
	// Method to check if selected letter wasn't selected before, returns true if so. else, returns false
	public boolean notSelectedAlready(String selectedLetter) {
		
		if (!guessedLetters.contains(selectedLetter)) {
	        guessedLetters.add(selectedLetter);
	        return true; // Letter was not selected before
	    }
	    
	    return false; // Letter was already guessed
	}
	
	// Method to add letter to guessedLetters HashSet
	public void addLetter(String selectedLetter) {
		
			guessedLetters.add(selectedLetter);
		}
	
	// Method to return number of mistakes
	public int getMistakes() {
		return mistakes;
	}

	// Method to check if game is over, if so returns true. else, returns false
	public boolean isGameOver() {
		return mistakes == maxMistakes || isGameWon(); // Check if got to maximum mistakes or game won (users guessed the correct word)
	}
	
	// Method to check if game won, is so returns true. else, returns false
    public boolean isGameWon() {
        
        Set<String> uniqueLettersInWord = new HashSet<>(); // HashSet to store all unique letters in the random word (avoiding duplicates)
        
        // Extract all unique letters from the random word and store in the HashSet
        for (char c : word.toCharArray()) {
            uniqueLettersInWord.add(String.valueOf(c));
        }
        return guessedLetters.containsAll(uniqueLettersInWord); // Check if all letters in the word have been guessed
    }
    

}

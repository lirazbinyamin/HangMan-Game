import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class HangManController {

	private Display display;
	private GameLogic game;
	private Data data;
	
	private final int MAX_MISTAKES = 6; // Final integer to hold maximum mistakes for the game (assuming mistakes as 6 drawing stages for man)
	private String randomWord; // String to hold the random word
	private List<String> letterList = new ArrayList<>();  // ArrayList to hold the letters
	
	@FXML
	private Canvas canv;
	@FXML
    private ComboBox<String> letterComboBox; // Combo box to choose one of the ABC letters in game
    @FXML
    private Button newGameBtn; // Button to start a new game
    @FXML
    private Label selectedLabel; // Label to show all letters selected by user

   
    // Method to initialize game
    public void initialize() {
    	try {
    		initComboBox(); // Build Combo Box with ABC letters
    		data = new Data ("WordBank.txt"); // Get data from file
    		display = new Display(canv); 
    		startNewGame();
    	}catch (IOException e) {
    		System.out.println("ERROR: " + e.getMessage());
    		}
    }
    
    // Method to start new game
    private void startNewGame() {
    	
    	randomWord = data.getRandomWord(); // Get a random word for game from data
    	//System.out.println(randomWord); // Print random word, remove // to monitor word
    	
    	game = new GameLogic(randomWord,MAX_MISTAKES); // New game with current random word
    	display.clearDisplay();
    	display.drawHanger();
    	display.drawWordLines(randomWord.length());
    	selectedLabel.setText("Selected: ");
    }
    
    @FXML
    void newGamePressed(ActionEvent event) {
    	startNewGame();
    }

    @FXML
    void comboBoxPressed(ActionEvent event) {
    	// Get the selected letter from the ComboBox
    	String selectedLetter = letterComboBox.getValue();
    		
    	if (game.notSelectedAlready(selectedLetter)) {
    	    //System.out.println("User selected: " + selectedLetter); // Print user selection, remove // to monitor selections 
    	    
    		display.updateSelectedLabel(selectedLabel,selectedLetter); // Add the selected letter to screen
    	    // Check if the selected letter is in the random word (if not, added to mistakes)
    	    boolean correct = game.checkGuessedLetter(selectedLetter); 
    	    if (correct) {
    	        display.drawLetter(selectedLetter,randomWord); // Draw letter in her spot in the word
    	        game.addLetter(selectedLetter); // add letter to user's guesses 
    	    } 
    	    else {
        	    display.drawMan(game.getMistakes()); // Draw the next part of the hang man
        	    
    	    }
    	    if (game.isGameOver()) {
    	     // Draw Game result to display
    	    	display.drawResult(game.isGameWon(),randomWord);
    	    }
    	}
    
    }
    
    // Method to initialize Combo Box
	private void initComboBox() {
	
        //build Combo Box with ABC letters
        final int LETTERS = 26;
        for (int i = 0; i < LETTERS; i++) {
            char letter = (char) ('A' + i);
            letterList.add(String.valueOf(letter));
        }
        letterComboBox.getItems().addAll(letterList);// Add the letters to the ComboBox
	}
	
}


import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class Display {
	
	@FXML
	private Canvas canvas;  
	@FXML
	private GraphicsContext gc;
	
	private final int LINE_X = 500; // Define X coordinate for the letters lines
	private final int LINE_Y = 300; // Define Y coordinate for the letters lines
	private final int LINE_LENGTH = 25; // Define length for each letter line
	private final int GAP_BETWEEN_LINES = 40; // Define gap between each letter
	private final int LETTER_Y = 290; // Define Y coordinate for the letters above their lines
	
	// Constructor
    public Display(Canvas canvas) {
    	
    	this.canvas = canvas;
    	this.gc = canvas.getGraphicsContext2D();
	}
    
    // Method to clear display
    public void clearDisplay() {
    	
    	gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
    
    // Method to draw the hanger of the man 
    public void drawHanger() {
    	
    	gc.setStroke(Color.BLACK);
        gc.setLineWidth(6);
        gc.strokeLine(LINE_X-200 , 50, LINE_X-200, 350);  
        gc.strokeLine(LINE_X-200, 50, LINE_X-100, 50);  
        gc.strokeLine(LINE_X-100, 50, LINE_X-100, 100);
    }
    
    // Method to draw small line for each letter of the random word
    public void drawWordLines(int letters) {
    	
    	int x = LINE_X;
        for (int i = 0; i < letters; i++) {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x, LINE_Y, x + LINE_LENGTH, LINE_Y);
            x += GAP_BETWEEN_LINES; // Move to the next position
        }
    }
    
    // Method to draw the letter in it's location in the word
    public void drawLetter(String selectedLetter, String word) {
    	
    	gc.setFont(javafx.scene.text.Font.font("Arial", 30));
    	int gap = LINE_X;
    	// Find all occurrences of the selected letter
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == selectedLetter.charAt(0)) {
                int position = gap + i * GAP_BETWEEN_LINES; // Calculate the position
                gc.setFill(Color.BLACK);
                gc.fillText(selectedLetter, position + LINE_LENGTH / 5, LETTER_Y); // Center the letter over the line
            }
        }
    }
    
    //Method to draw man based on given mistakes
	public void drawMan(int mistakes) {
    	//System.out.println("Wrong Letter, mistakes: " + mistakes); // Print number of mistakes, remove // to monitor mistakes
    	
		gc.setStroke(Color.BROWN);
    	gc.setLineWidth(6);
    	switch (mistakes) {
	    	case 1:
	    		 gc.strokeOval(LINE_X-110, 100, 20, 20); //Draw head
	    		 break;
	    	case 2:
	    		 gc.strokeLine(LINE_X-100, 120, LINE_X-100, 200); //Draw body
	    		 break;
	    	case 3:
	    		gc.strokeLine(LINE_X-100, 130, LINE_X-120, 180); //Draw left arm
	    		break;
	    	case 4:
	    		gc.strokeLine(LINE_X-100, 130, LINE_X-80, 180); //Draw right arm
	    		break;
	    	case 5:
	    		gc.strokeLine(LINE_X-100, 200, LINE_X-120, 250); //Draw left leg
	    		break;
	    	case 6:
	    		gc.strokeLine(LINE_X-100, 200, LINE_X-80, 250); //Draw right Leg
	            break;
    	}
	}
	
	//A method to draw game finish title
    public void drawResult(boolean gameWon,String word) {
    	
    	gc.setFont(javafx.scene.text.Font.font("COMIC SANS MS", 40));
    	
    	//Add result message to the display 
    	if (gameWon) {
    	    gc.setFill(Color.GREEN);
    	    gc.fillText("YOU WON :)",LINE_X, LINE_Y-200);
    	} else {
    	    gc.setFill(Color.RED);
    	    gc.fillText("YOU LOST :(", LINE_X, LINE_Y-200);
    	}
    	gc.fillText(word,LINE_X+50, LINE_Y-150);
    }
    
    // Method to update selected letter ins selected Label
    public void updateSelectedLabel(Label selectedLabel, String selectedLetter) {
    	
    	String current = selectedLabel.getText();
    	String update = current + selectedLetter +  ", ";
    	selectedLabel.setText(update);
    }
}
	
	

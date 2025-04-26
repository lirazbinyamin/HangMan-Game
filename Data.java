import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Data {
	
	private final List<String> wordBank = new ArrayList<>();
	
	// Constructor
	public Data(String filePath) throws IOException {
		
		loadWords(filePath);	
	}
	
	// Method to load Words from file to word bank List
	private void loadWords(String filePath) throws IOException {
		
		File file = new File(filePath);
		try (Scanner s= new Scanner(file)) {
			while (s.hasNext()) {
				wordBank.add(s.next());
			}
		}catch (IOException e) {
    		System.out.println("ERROR: "+ e.getMessage());
    	}
		//printWordBank(); // Print word bank, remove // and enable method to monitor  
	}
	
	// Method to choose random word out of Word Bank File located in the package
	public String getRandomWord() {
		
		Random rand = new Random();
		return wordBank.get(rand.nextInt(wordBank.size()));

	}
    

    /**Method to print the words in the word bank file
    private void printWordBank() {
    	for(int i=0;i<wordBank.size();i++) {
    		System.out.print(wordBank.get(i) + " ");
    	}
    }**/
}

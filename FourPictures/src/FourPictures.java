
public class FourPictures {
	String letters;
	String[] fileNames;
	String word;
	String[] revealedWord;
	int correctLetters;

	public FourPictures(String word, String letters, String[] fileNames) {
		this.letters = letters;
		this.fileNames = fileNames;
		this.word = word;
		correctLetters = 0;
		//Makes a revealedWord Array with word.length
		//Number of "_"
		revealedWord = new String[word.length()];
		for (int i = 0; i < this.word.length(); i++) {
			revealedWord[i] = "_ ";
		}
	}

	public String getLetters() {
		return letters;
	}

	public String[] getWord() {
		String[] wordArray = new String[word.length()];
		for (int i = 0; i < word.length(); i++) {
			wordArray[i] = word.substring(i, i + 1);
		}
		return wordArray;
	}

	public String[] getFileNames() {
		return fileNames;
	}

	public String[] getRWord() {
		return revealedWord;
	}

	public String updateRWord(String input) {
		for (int i = 0; i < revealedWord.length; i++) {
			if (input.equals(word.substring(i, i + 1)) && revealedWord[i].equals("_ ")) {
					correctLetters++;
				System.out.println("Number of correct letters: "+correctLetters+" with input "+input+" matched with "+word.substring(i, i+1));
				revealedWord[i] = input;
			}
			
		}
		String rWord = "";
		for (String s : revealedWord) {
			rWord += s + " ";
		}
		return rWord;
	}
	/**
	 * 
	 * @return boolean indicating if all letters have been guessed
	 */

	public boolean checkAllCorrect() {
		if (correctLetters == word.length()) {
			System.out.println("All letters correct: "+correctLetters);
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Returns one letter that is remaining for the hint button
	 */
	public String[] remainingLetter() {
		String[] remainingLetter = new String[1];

		for (int i = 0; i < revealedWord.length; i++) {
			//Gets rid of whitespaces in the revealedWord Array
			String temp = revealedWord[i].replaceAll("\\s+", "");
			//
			if (remainingLetter[0] == null){
				if (temp.equals("_")) {
					remainingLetter[0] = word.substring(i, i + 1);
				}
			}

		}
		return remainingLetter;

	}

}


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

	// public String getRWord(){
	// return revealedWord;
	// }

	public String updateRWord(String input) {
		for (int i = 0; i < revealedWord.length; i++) {
			if (input.equals(word.substring(i, i + 1))) {
				correctLetters++;
				revealedWord[i] = input;
			}
		}
		String rWord = "";
		for (String s : revealedWord) {
			rWord += s + " ";
		}
		return rWord;
	}

	public boolean checkAllCorrect() {
		if (correctLetters == word.length()) {
			return true;
		} else {
			return false;
		}
	}

	public String[] remainingLetter() {
		String[] remainingLetter = new String[1];

		for (int i = 0; i < revealedWord.length; i++) {

			String temp = revealedWord[i].replaceAll("\\s+", "");
			System.out.println("[" + temp + "]");
			// System.out.print(temp.equals("_"));
			if (remainingLetter[0] != null) {

			} else {
				if (temp.equals("_")) {
					remainingLetter[0] = word.substring(i, i + 1);
				}
			}

		}
		return remainingLetter;

	}

}

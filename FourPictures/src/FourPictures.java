

public class FourPictures {
	String letters;
	String[] fileNames;
	String word;
	String revealedWord;
	
	public FourPictures(String word,String letters,String[] fileNames){
		this.letters = letters;
		this.fileNames = fileNames;
		this.word = word;
		revealedWord = "";
		for(int i = 0;i < this.word.length();i++){
			revealedWord += "_ ";
		}
	}
	
	public String[] getLetters() {
		String[] letterArray = new String[letters.length()];
		for(int i = 0; i < letterArray.length;i++){
			letterArray[i] = letters.substring(i, i+1);
		}
		return letterArray;
	}
	
	public String[] getWord() {
		String[] wordArray = new String[word.length()];
		for(int i = 0; i < word.length();i++){
			wordArray[i] = word.substring(i, i+1);
		}
		return wordArray;
	}
	
	public String[] getFileNames() {
		return fileNames;
	}
	
	public String[] getRWord(){
		String[] revealedArray = new String[revealedWord.length()];
		for(int i = 0; i < revealedWord.length();i++){
			System.out.println(revealedWord.substring(i, i+1));
			revealedArray[i] = revealedWord.substring(i, i+1);
		}
		return revealedArray;
	}

}

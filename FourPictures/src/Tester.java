

public class Tester {

	public static void main(String[] args) {
		String filenames[] = {"Paris1.jpg","Paris2.jpg","Paris3.jpg","Paris4.jpg"};
		FourPictures game1 = new FourPictures("paris","pysitrla",filenames);
		//FourPictures game2 = new FourPictures("paris","eipfrasncywv",filenames);
		GameWindow test1 = new GameWindow(game1);
		//GameWindow test2 = new GameWindow(game2);

	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
//		//Below is for disk verision
		//Will not work now as GameWindow uses different constructor
//		String filenames[] = { "Paris1.jpg", "Paris2.jpg", "Paris3.jpg",
//				"Paris4.jpg" };
//
//		FourPictures game1 = new FourPictures("paris", "pysitrla", filenames);
//		new GameWindow(game1);
//
//		FourPictures game2 = new FourPictures("paris", "eipfrasncywv",
//				filenames);
//		new GameWindow(game2);

		GameWindow testing = new GameWindow(
				pullData("http://www.inf.kcl.ac.uk/staff/andrew/fourpictures/levels.txt"));

	}

	/**
	 * Reads data from website to create FourPicture Objects
	 * 
	 * @param website
	 * @return ArrayList of Fourpictures Objects
	 * @throws MalformedURLException
	 * @throws IOException
	 */

	public static ArrayList<FourPictures> pullData(String website)
			throws MalformedURLException, IOException {
		ArrayList<FourPictures> level4Pic = new ArrayList<FourPictures>();
		URL levels = new URL(website);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				levels.openStream()));

		String line = null;
		while ((line = in.readLine()) != null) {
			String word = line;
			String letters = in.readLine();
			String s1 = in.readLine();

			String s2 = in.readLine();

			String s3 = in.readLine();

			String s4 = in.readLine();

			String filename[] = { s1, s2, s3, s4 };
			FourPictures temp = new FourPictures(word, letters, filename);
			level4Pic.add(temp);
		}

		return level4Pic;
	}

}

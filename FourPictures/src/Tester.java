import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;



public class Tester {

	public static void main(String[] args) throws Exception {
//		String filenames[] = {"Paris1.jpg","Paris2.jpg","Paris3.jpg","Paris4.jpg"};
//		FourPictures game1 = new FourPictures("paris","pysitrla",filenames);
//		//FourPictures game2 = new FourPictures("paris","eipfrasncywv",filenames);
//		GameWindow test1 = new GameWindow(game1);
//		//GameWindow test2 = new GameWindow(game2);
		
		ArrayList<FourPictures> level4Pic = new ArrayList<FourPictures>();
		URL levels = new URL("http://www.inf.kcl.ac.uk/staff/andrew/fourpictures/levels.php");
		BufferedReader in = new BufferedReader(new InputStreamReader(levels.openStream()));
		
		String line = null;
		while((line = in.readLine())!= null){
			String word = line;
			String letters = in.readLine();
			String s1 = in.readLine();

			String s2 = in.readLine();

			String s3 = in.readLine();

			String s4 = in.readLine();

			String filename[] = {s1,s2,s3,s4};
			FourPictures temp = new FourPictures(word,letters,filename);
			level4Pic.add(temp);
		}
		

		GameWindow testing = new GameWindow(level4Pic);

	}

}

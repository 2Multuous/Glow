import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScoreManager {
	
	public static String createFile() {
		File highScore = new File("highscore.txt");
		try {
			FileWriter f = new FileWriter("highscore.txt");
			f.write('0');
			return "highscore.txt";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getScore() {
		try {
			FileReader f = new FileReader(createFile());
			String s = "";
			int o = f.read();
			while(o != -1) {
				s += o;
			}
			return Integer.parseInt(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public static void writeScore(int score) {
		try {
			FileWriter f = new FileWriter(createFile());
			String s = "" + score;
			for(char c : s.toCharArray()) {
				f.write(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

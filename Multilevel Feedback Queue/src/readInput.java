import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class readInput {

public static void run(String inputFile) {
			
		
		try {


			FileInputStream fstream = new FileInputStream("assignment1.txt");
			Scanner inputScanner = new Scanner(fstream);
			ArrayList<Process> processes = new ArrayList<>();
			// assuming input file is in correct format
			while (inputScanner.hasNextInt())
			{
				int id = inputScanner.nextInt();
				int arrivalTime = inputScanner.nextInt();
				int burstLength = inputScanner.nextInt();
				Process process = new Process(id, arrivalTime, burstLength);
				Process.run(process);
			}

		}catch (FileNotFoundException e)
		{
			System.out.println("assignment1.txt file not found!");
		}
	}
}

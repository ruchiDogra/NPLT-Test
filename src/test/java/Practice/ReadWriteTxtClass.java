package Practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReadWriteTxtClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//read - write txt file
		
		File file1 = new File ("./src/test/resources/logs/practiceFile.txt");
		file1.createNewFile();
		
		FileWriter fw = new FileWriter(file1);
		BufferedWriter fWrite = new BufferedWriter(fw);
		fWrite.write("This is first line\n");
		fWrite.write("This is last line");
		fWrite.flush();
		
		FileReader fr = new FileReader(file1);
		BufferedReader fRead = new BufferedReader(fr);
		System.out.println(fRead.readLine());

		do
		{
			System.out.println(fRead.readLine());
		}while(fRead.readLine() != null);
		

	}

}

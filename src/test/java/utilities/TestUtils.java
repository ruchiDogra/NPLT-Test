package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestUtils {
		
	
		//its not a working code, try again afterwards.
	    public static void zipUtil(String[] args) throws IOException {
	        List<String> srcFiles = Arrays.asList("test1.txt", "test2.txt");
	        FileOutputStream fos = new FileOutputStream("CompressedFolder.zip");
	        ZipOutputStream zipOut = new ZipOutputStream(fos);
	        for (String srcFile : srcFiles) 
	        {
	            File fileToZip = new File(srcFile);
	            FileInputStream fis = new FileInputStream(fileToZip);
	            ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
	            zipOut.putNextEntry(zipEntry);

	            byte[] bytes = new byte[1024];
	            int length;
	            while((length = fis.read(bytes)) >= 0) {
	                zipOut.write(bytes, 0, length);
	            }
	            fis.close();
	        }
	        zipOut.close();
	        fos.close();
	    }
	

}

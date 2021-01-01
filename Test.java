

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.simple.*;
import org.json.simple.parser.*;
import java.util.*;
import java.io.FileReader;
import java.lang.Object;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Test
{
    public static void main(String args[])
    {

      Scanner sc= new Scanner(System.in);
      System.out.println("1. Press 1 if you want to enter location for you file key value datastore. \n2. Press 2 if you want to make file key value datastore in a default location.");
      int path=sc.nextInt();
      String fileAbsolutePath;
      if(path==1)
      //get path for datastore from the user
          fileAbsolutePath=sc.nextLine();
      else
      //set defualt path for datstore
          fileAbsolutePath="C:\\Users\\COM\\Documents\\store.txt";

      //create a datastorefile
      File file = new File(fileAbsolutePath);

      //check if file exists

		  if (!file.exists())
      {
			     try
           {
				        file.createNewFile();
                System.out.println("Successfully created!");
			     }
            catch (IOException e)
            {
				        e.printStackTrace();
			      }
		  }
      else
          System.out.println("File with this name exists!");


      int ch;
      //check if user wants to create a key value ore read the keys.
      do {

        System.out.println("1. Create Key \n2.Read Key \n3. Exit");
        ch=sc.nextInt();
        switch(ch)
        {
          case 1: createKeyPair(fileAbsolutePath); //call to create a key value pair
         break;

          case 2: readKey(fileAbsolutePath); //read the key value datastore
          break;

          case 3: System.exit(0);
          break;

          default: System.out.println("Please enter a valid choice:");
          break;
        }

      } while (ch!=4);
    }

    //function to create key value pair

    public static void createKeyPair(String fileAbsolutePath)
    {
        Scanner sc2= new Scanner(System.in);
        FileWriter writeFile;
        int max=32;
        System.out.println("Enter the key: ");
        String key=sc2.nextLine();
        if (key.length() > max)
        key = key.substring(0, max);
        System.out.println("Enter the value to the key: ");
        String value=sc2.nextLine();

      JSONObject obj = new JSONObject();
        obj.put(key,value);
        try {
            writeFile=new FileWriter(fileAbsolutePath,true);
            writeFile.write(obj.toJSONString());
            System.out.println("Key added successfully");
         writeFile.flush();
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //fucntion to read the key value datastore
    public static void readKey(String fileAbsolutePath)
    {
      try {
        String json = readFileAsString(fileAbsolutePath);
        System.out.println(json);

      } catch(Exception e) {
        e.printStackTrace();
      }
    }
    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}

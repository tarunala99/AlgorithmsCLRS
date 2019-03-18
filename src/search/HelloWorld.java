package search;

import java.io.BufferedReader;
import java.io.FileReader;

public class HelloWorld {

	public static void main(String[] args)
	{
		String a ="tarun alaasd";
		char b[] = a.toCharArray();
		char temp= 0;
		for(int i=0; i<b.length/2;i++)
		{
			temp=b[b.length-i-1];
			b[b.length-i-1]=b[i];
			b[i] = temp;
		}
		System.out.println(new String(b));
		multTables(12);
		readFile("sample.txt");
	}
	
	public static void multTables ( int max )
    {
        for ( int i = 1; i <= max; i++ ) {
            for ( int j = 1; j <= max; j++ ) {
                System.out.print ( String.format ( "%4d", j * i ));
            }
            System.out.println();
        }
    }
	public static void readFile(String name)
	{
		try 
		{
			BufferedReader bufferedReader = new BufferedReader(new FileReader("sample.txt"));
			String input = bufferedReader.readLine();
			while(input!=null)
			{
				Integer i= Integer.parseInt(input);
				System.out.println(i);
				input = bufferedReader.readLine();
			}
			bufferedReader.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}

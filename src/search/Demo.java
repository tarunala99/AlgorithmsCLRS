package search;

import java.util.HashMap;
import java.util.Map;

public class Demo {
	
	public static void main(String args[])
	{
		String inputString = "abcde";
		char a ='9';
		int p= Character.getNumericValue(a);
		System.out.println(p);
	}

	public static boolean isUnique(String str)
	{
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		for(int i=0; i< str.length();i++)
		{
			char a = str.charAt(i);
			int count =0;
			if(map.containsKey(a))
			{
				return false;
			}
			count = count+1;
			map.put(a,count);
			
		}
		return true;
	}
}


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Main {
	public static double diceCoefficientOptimized(String s, String t)
	{
		if (s == null || t == null)
			return 0;
		if (s == t)
			return 1;
	        if (s.length() < 2 || t.length() < 2)
	            return 0;

		final int n = s.length()-1;
		final int[] sPairs = new int[n];
		for (int i = 0; i <= n; i++)
			if (i == 0)
				sPairs[i] = s.charAt(i) << 16;
			else if (i == n)
				sPairs[i-1] |= s.charAt(i);
			else
				sPairs[i] = (sPairs[i-1] |= s.charAt(i)) << 16;

		final int m = t.length()-1;
		final int[] tPairs = new int[m];
		for (int i = 0; i <= m; i++)
			if (i == 0)
				tPairs[i] = t.charAt(i) << 16;
			else if (i == m)
				tPairs[i-1] |= t.charAt(i);
			else
				tPairs[i] = (tPairs[i-1] |= t.charAt(i)) << 16;

		Arrays.sort(sPairs);
		Arrays.sort(tPairs);

		int matches = 0, i = 0, j = 0;
		while (i < n && j < m)
		{
			if (sPairs[i] == tPairs[j])
			{
				matches += 2;
				i++;
				j++;
			}
			else if (sPairs[i] < tPairs[j])
				i++;
			else
				j++;
		}
		return (double)matches/(n+m);
	}
	
	private static double jaccard_coeffecient(String s1, String s2) {

        double j_coeffecient;
        ArrayList<String> j1 = new ArrayList<String>();
        ArrayList<String> j2 = new ArrayList<String>();
        HashSet<String> set1 = new HashSet<String>();
        HashSet<String> set2 = new HashSet<String>();
        
            s1="$"+s1+"$";
            s2="$"+s2+"$";
            int j=0;
            int i=3;
        
            while(i<=s1.length())
            {
                j1.add(s1.substring(j, i));
                    j++;
                    i++;
            }    
            j=0;
            i=3;
            while(i<=s2.length())
            {
                j2.add(s2.substring(j, i));
                    j++;
                    i++;
            }    
            
            Iterator<String> itr1 = j1.iterator();
            while (itr1.hasNext()) {
                  String element = itr1.next();
                }
                Iterator<String> itr2 = j2.iterator();
                while (itr2.hasNext()) {
                  String element = itr2.next();
                }
            
                set2.addAll(j2);
                set2.addAll(j1);
                set1.addAll(j1);
                set1.retainAll(j2);
                j_coeffecient=((double)set1.size())/((double)set2.size());
                return j_coeffecient;
    }
	
	public static void main(String[] args) throws IOException {
		String line1 = "";
	    String line2 = "rama";
	    
	    JaroWinkler jr = new JaroWinkler();
	    long time = System.nanoTime();
	    double sm = jr.similarity(line1, line2);
	    
	    System.out.println("Jaro Winkler: similarity = " + sm + " Total time (ms): " + (System.nanoTime() - time)/1e6);
	    
	    time = System.nanoTime();
	    double Score = diceCoefficientOptimized(line1, line2);
	    System.out.println("Dice Coe: similarity = " + Score + " Total time (ms): " + (System.nanoTime() - time)/1e6);
	    
	    time = System.nanoTime();
	    double Jarccard = jaccard_coeffecient(line1, line2);
	    System.out.println("Jaccard Coe: similarity = " + Jarccard + " Total time (ms): " + (System.nanoTime() - time)/1e6);
	}
}
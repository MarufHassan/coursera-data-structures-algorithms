import java.io.*;
import java.util.*;

class Node
{
	public static final int Letters =  4;
	public static final int NA      = -1;
	public int next [];
	public boolean patternEnd;

	Node ()
	{
		next = new int [Letters];
		Arrays.fill (next, NA);
		patternEnd = false;
	}
}

public class TrieMatchingExtended implements Runnable {
	int letterToIndex (char letter)
	{
		switch (letter)
		{
			case 'A': return 0;
			case 'C': return 1;
			case 'G': return 2;
			case 'T': return 3;
			default: assert (false); return Node.NA;
		}
	}

	List<Node> buildTrie(List<String> patterns) {
		List<Node> trie = new ArrayList<>();
		trie.add(new Node());

		for (String pattern : patterns) {
			int curr = 0;
			for (int i = 0; i < pattern.length(); i++) {
				char symbol = pattern.charAt(i);
				Node n = trie.get(curr);
				int idx = letterToIndex(symbol);

				if (n.next[idx] != Node.NA) {
					curr = n.next[idx];
				} else {
					trie.add(new Node());
					n.next[idx] = trie.size() - 1;
					curr = trie.size() - 1;
				}
			}
			trie.get(curr).patternEnd = true;
		}
		return trie;
	}

	int prefixTrieMatching(String text, int start, List<Node> trie) {
		for (int i = start, v = 0; i < text.length(); i++) {
			char symbol = text.charAt(i);
			int idx = letterToIndex(symbol);
			v = trie.get(v).next[idx];

			if (v == Node.NA)
				return -1;
			else if (trie.get(v).patternEnd)
				return start;
		}

		return -1;
	}

	List <Integer> solve (String text, int n, List <String> patterns) {
		List <Integer> result = new ArrayList <Integer> ();
		List<Node> trie = buildTrie(patterns);
		
		for (int i = 0; i < text.length(); i++) {
			int pos = prefixTrieMatching(text, i, trie);

			if (pos >= 0)
				result.add(pos);
		}

		return result;
	}

	public void run () {
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader (System.in));
			String text = in.readLine ();
		 	int n = Integer.parseInt (in.readLine ());
		 	List <String> patterns = new ArrayList <String> ();
			for (int i = 0; i < n; i++) {
				patterns.add (in.readLine ());
			}

			List <Integer> ans = solve (text, n, patterns);

			for (int j = 0; j < ans.size (); j++) {
				System.out.print ("" + ans.get (j));
				System.out.print (j + 1 < ans.size () ? " " : "\n");
			}
		}
		catch (Throwable e) {
			e.printStackTrace ();
			System.exit (1);
		}
	}

	public static void main (String [] args) {
		new Thread (new TrieMatchingExtended ()).start ();
	}
}

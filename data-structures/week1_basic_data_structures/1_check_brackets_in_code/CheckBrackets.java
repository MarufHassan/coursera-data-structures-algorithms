import java.io.*;
import java.util.*;

class CheckBrackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        String text = reader.readLine();
        
        Stack<Bracket> s = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                s.push(new Bracket(next, position));
            }

            if (next == ')' || next == ']' || next == '}') {
            	if (s.empty()) {
            		System.out.println(position + 1);
                    return;
            	}
                Bracket b = s.pop();
                if (!b.match(next)) {
                    System.out.println(position + 1);
                    return;
                }
            }
        }

        if (s.empty())
            System.out.println("Success");
        else
            System.out.println(s.pop().position + 1);
    }
}

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}
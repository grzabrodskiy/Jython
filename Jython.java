import java.util.Queue;
import java.util.Scanner;
import java.text.*;

public class Jython {

	public static void main(String[] args) {
		
		Jython j = new Jython();
		j.run();
	}
		
		
	public void run(){
		BST <String, Double> ST = new BST<String, Double>();
		Scanner scanner = new Scanner (System.in);
		String first, expression = "";
		Double answer;		
		DecimalFormat f = new DecimalFormat("###.######");
		
		System.out.println("Welcome to Jyron.\nType help for more info.");
		
		while(true){
			
			System.out.println(">");
			//read a line
			
			
			scanner.useDelimiter("[=\\s]");
			
			first = scanner.next().trim();
			
			//System.out.println("*first: " + first);
			//if (scanner.hasNext())
				expression = scanner.nextLine().trim();
			
			//System.out.println("*evaluate: " + expression + "|");
			
			
			if (first.equalsIgnoreCase("quit") || first.equalsIgnoreCase("exit"))
				break;

			if (first.equalsIgnoreCase("help")){
				System.out.println("Thank you for using Jython.\nUsage: ");
				System.out.println("print {expression} - evaluate expression  ");
				System.out.println("{var} = {expression} - store expression result in the variable");
				System.out.println("\tvariable name should start with a letter, valid characters include letters, numbers, underscore, and dollar sign.");
				System.out.println("help - this screen");
				System.out.println("quit - exit Jython");
				
				continue;
			}
			
			if (first.equalsIgnoreCase("print")){
				
				answer = new Evaluate((new InToPost(expression)).translate()).evaluate(ST);
				
				if (answer != null)
					
					System.out.println(f.format(answer));
				
				continue;
				
			}
			
			
			if(isValidVariableName(first) && expression.length()> 0 &&
					expression.charAt(0)== '='){
				
				expression = expression.substring(1).trim(); // remove =
				
				answer = new Evaluate((new InToPost(expression)).translate()).evaluate(ST);
				
				if (answer != null)
					ST.put(first, answer);
				
				continue;
				
			}
				
			System.out.println("Cannot evaluate expression. Type ? for help.");
			
			
			
		}
		System.out.println("Thank you for using Jython. Have a nice day...");
		
		scanner.close();
	}
	
	private boolean isValidVariableName(String name){
		
		if (name == null || name.length() == 0)
			return false;
		
		if (!name.matches("^[a-zA-Z][a-zA-Z0-9_$]*$"))
			return false;
		
		if (name.equalsIgnoreCase("help") && 
				name.equalsIgnoreCase("quit") && 
				name.equalsIgnoreCase("print"))
			return false;
		
		return true;
		
	}
	
	

}

import java.util.Queue;
//import java.util.ArrayDeque;
import java.util.Stack;


public class Evaluate {
	
	private Queue<String> thePost; //postfix operations
	
	public Evaluate(Queue<String> given){
		
		thePost = given;
		
		//for (String s: thePost)
		//	System.out.println("**" + s);
		
	}
	
	public Double evaluate(BST<String, Double> ST){
	
		Stack<Double> stack = new Stack<Double>();
		Double first, second, temp = null;
		
		for(String s: thePost){
			
			s = s.trim();
			
			if (isOp(s)){
				if (stack.size() < 2){
					System.out.println("Invalid or incomplete expression (two few arguments)");
					return null;
				}
				second = stack.pop();
				first = stack.pop();
				
				temp =  evaluate(first, second, s);
				
				if (temp == null){
					System.out.println("Unable to evaluate expression");
					return null;
				}
				
				
			}
			else {
			
				temp = getValue(s, ST);
				
				//System.out.println("* searching for " + s + " found: " + temp);
			
				if (temp == null){
					System.out.println("Invalid argument " + s);
					return null;
				}
				
			}
			stack.push(temp);
		}
		
		if (stack.isEmpty()){
			System.out.println("Not enough arguments");
			return null;
		}
		temp = stack.pop();
			
		if (!stack.isEmpty()){
			System.out.println("Too many arguments");
			return null;
		}
		
		return temp;
		
	}
	
	
	private boolean isOp(String s){
		return ("+-*/%^".indexOf(s) > -1 && s.length() == 1);
	}
	
	private Double getValue(String s, BST<String, Double> map){
		
		Double d = null;
		
		try{
			d = Double.valueOf(s.trim());
		}
		catch (Exception e){
			//return null;
		}
		
		if (d==null && map.contains(s)){
			d = map.get(s);
		}
		return d;
	}
	
	
	private Double evaluate (Double first, Double second, String op){
		
		try{
			if (op.equals("+"))
				return first + second;
			
			if (op.equals("-"))
				return first - second;
			
			if (op.equals("*"))
				return first * second;
			if (op.equals("/")){
				
				if (second == 0){
					System.out.println("Division by 0");
					return null;
				}		
				return first / second;
			}
			if (op.equals("%")){
				if (second == 0){
					System.out.println("Division by 0");
					return null;
				}
					
				return first % second;
			}
			
			if (op.equals("^"))
				return Math.pow(first, second);
			
		}
		catch (Exception e){
			System.out.println("Unable to evaluate " + first + op + second );
			return null;
		}
		
		System.out.println("Unknown operator: " + op);
		
		return null;
		
	}
	
	
	

}


import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Stack;

public class InToPost {

	private Queue<String> thePost ;
	private String theExpr;
	public InToPost(String given){
		thePost = new ArrayDeque<String> ();
		theExpr = given;
	}
	
	
	public static void main(String[] args){
		
		
		InToPost i = new InToPost("(1+2) + 3");
		
		i.translate();
		
		for (String s: i.thePost){
			System.out.println(s);
		}
		
	}
	
	
	
	public Queue<String> translate(){
		
		Stack<Character> stack = new Stack<Character> ();
		String buffer  = new String();
		//boolean isOp = false; // not an operator
		
		// 1+2*3 => 1|2|3*+
		// (1+2)*3 => 1|2+3*
		boolean isUnary = true; // next can be unary +- operator
		
		
		for (Character ch : theExpr.toCharArray()){
			
			if (ch == ' ')
				continue;
			
			
			if ("+-".indexOf(ch)> -1 && isUnary){ //unary
				buffer = new String(ch.toString());
				isUnary = false;
			}
			
			
			else if ("+-*/%^".indexOf(ch) > -1 ){
				
				if(buffer.trim().length() != 0){
					thePost.add(buffer);
					buffer = new String();
				}
				
				
				while (!stack.isEmpty()){
					
					Character up = stack.pop();
					if (up == '('){
						stack.push(up);
						break;
					}
					else if (precedence(up) > precedence(ch)){
						stack.push(up);
						break;
					}
					else{
						if (up != ' ')
							thePost.add(up.toString());
					}
						
					
				}
				stack.push(ch);
				isUnary = false;
			}
			else if (ch == '('){
				if(buffer.trim().length() != 0){
					thePost.add(buffer);
					buffer = new String();
				}
				
				isUnary = true;
				stack.push(ch);
			}
			else if (ch == ')'){
				
				if(buffer.trim().length() != 0){
					thePost.add(buffer.trim());
					buffer = new String();
				}
				
				while (!stack.isEmpty()) {
			         Character up = stack.pop();
			         if (up == '(') 
			        	 break; 
			         else{ 
			        	 thePost.add(up.toString()); 
			         }	
			    }
				isUnary = false;
				
			}
			else{
			
				buffer += ch;
				isUnary = false;
			}
				
		}
		
		if(buffer.length() != 0){
			thePost.add(buffer);
			buffer = new String();
		}
		
		while (!stack.isEmpty()){
			
			String s = stack.pop().toString().trim();
			if (s.length() > 0)
				thePost.add(s);
				
		}
		
		//System.out.println(buffer);
		
		
		return thePost;
		
	}
	
	
	public int precedence(char op){
		
		if ("+-".indexOf(op) > -1)
			return 30;
		
		if ("*/%".indexOf(op) > -1)
			return 20;

		if ("^".indexOf(op) > -1)
			return 10;
		
		return 100;
		
	}
	
	
	
	
	
	
}

// Theodora Tataru C00231174
// Ana Griga C00231441	
// Year 2
// Calculator Project

package Calculator;

import java.util.EmptyStackException;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ArithmeticOperations
{
	//////////////////////////////////////
	// METHOD from POSTFIX to RESULT (final result)
	public static String result(String postfixArray[], int postfixArrayCount) // returning the final result
		{
			Stack <String> resultStack =  new Stack<String>(); // the stack for the operands
			
			for(int i = 0; i < postfixArrayCount; i++) // looping through the postFix array
				{
					if(postfixArray[i] != null)
						{	
							// if the element is a NUMBER, push it into the stack
							if(isNumber(postfixArray[i])) 
								resultStack.push(postfixArray[i]);
							
							// if the element is an OPERATOR
							else if(isOperator(postfixArray[i]))
								{
									System.out.println("PEEK FIRST: " + resultStack.peek()); // TEST - CONSOLE READING
									double first = Double.parseDouble(resultStack.pop()); 
									System.out.println("PEEK SECOND: " + resultStack.peek()); // TEST - CONSOLE READING
									double second = Double.parseDouble(resultStack.pop()); 
									System.out.println("OPERATOR: " + postfixArray[i]); // TEST - CONSOLE READING
									String operator = postfixArray[i];
									double  calculation = 0;
									
									switch(operator)
										{
											case"+": calculation = second + first; break;
											case"-": calculation = second - first; break;
											case"*": calculation = second * first; break;
											case"÷": calculation = second / first; break;
										}
									// we push the result back to the stack (other operations may be done)
									resultStack.push(calculation+""); // adding the "" because calculation is a double and we need to cast to STRING (as the array)
								}
						}
				}
			System.out.println("TOTAL: " + resultStack.peek()); // TEST - CONSOLE READING
			// if there are no more operators, we pop the number as the result
			return resultStack.pop();
		} // end

	//////////////////////////////////
	// check if the STRING is a OPERAND
	public static boolean isNumber(String checkNumber)
	{	
		try
			{
				Double.parseDouble(checkNumber);
				return true;
			}
		catch(NumberFormatException | NullPointerException nfe)
			{
				return false;
			}
	} // end
	
	///////////////////////////////////
	//check if the STRING IS A OPERATOR
	public static boolean isOperator(String checkOperator)
	{
		switch (checkOperator)
			{
				 case"+": return true;
				 case"-": return true;
				 case"*": return true;
				 case"÷": return true;
				 default: return false;
			}
	} // end
	
	///////////////////////////////////
	// OPERATORS PRIORITY
	public static int thePriority(String operator)
		{
			int priority = 0; // initiate
			
			if(operator.equals("(") || operator.equals(")"))
				priority = 1; // last priority
			if(operator.equals("+") || operator.equals("-"))
				priority = 2; // middle priority
			if(operator.equals("*") || operator.equals("÷"))
				priority = 3; // first priority
			
			return priority;
		} // end
	
	//////////////////////////////////////
	// POSTFIX algorithm - from INFIX to POSTFIX
	public static int postfixStack(String postfixArray[], String infixArray[], int infixArrayCount)
		{ 
			Stack <String> postfix =  new Stack<String>(); // for arithmetic symbols - operators
			int countPostFix = 0; // postfixArray count initialized 
				
			for(int i = 0; i <= infixArrayCount; i++) // looping through infix array
				{
					if(infixArray[i]==null || infixArray[i].equals(""))
						continue;
					// if ( is found push it into the OPERATORS STACK
					if(infixArray[i].equals("(")) 
						{
							postfix.push("(");
						}
					// if ) is found pop all the elements and add them into POSTFIX ARRAY
					else if(infixArray[i].equals(")"))  	
						{	
							try // if there are matching brackets
								{// pop elements until we found the (
									while(!postfix.peek().equals("("))
										{
											postfixArray[countPostFix] = postfix.pop();
											countPostFix++;
										}
									// when the ( is found, will be popped from the stack, without using it
									if(postfix.peek().equals("(")) 
										postfix.pop(); 
								}
							
							catch(EmptyStackException noMatchingBraket) // if there are no matching brackets - ERROR
								{
									Calculator.history.setText(Calculator.textField.getText() +  "Open bracket missing!" + "\n\n"+ Calculator.history.getText());
									Calculator.textField.setText("Matching bracket missing");
									JOptionPane.showMessageDialog(new JFrame(), "Open bracket missing!", "Error", JOptionPane.ERROR_MESSAGE);
									// clear the text field when the error pops up
									ButtonFunction.clear();
								}
						}
					// if is an operator
					else if(isOperator(infixArray[i]))
						{
							// if the current OPERATOR has higher priority than the TOP STACK element, push the element into POSTFIX ARRAY
							if(postfix.isEmpty() || thePriority(infixArray[i]) > thePriority(postfix.peek()))
								{
									countPostFix++;
									postfix.push(infixArray[i]);
								}
							// if the current OPERATOR has less priority than the top element from the STACK, pop elements from the STACK
							// until the stack is empty OR the element has a higher priority
							else 
								{
									while(!postfix.isEmpty() && thePriority(infixArray[i]) <= thePriority(postfix.peek()))
										{
											postfixArray[countPostFix] = postfix.pop();
											countPostFix++;	
										}
									// then add the element to the STACK
									postfix.push(infixArray[i]);
								}
							}
						// if the element is a number push it into POSTFIX array
						else if(isNumber(infixArray[i]))  
							{												
								postfixArray[countPostFix] = infixArray[i];
								countPostFix++;
							}
					}
				// the rest of the operators will be popped, if there are no more elements into the INFIX array
				while(!postfix.isEmpty())
					{
						postfixArray[countPostFix] = postfix.pop();
						countPostFix++;
					}
				
				return countPostFix;
			} // end
	
	///////////////////////////////////
	// check if the number is an INTEGER or a DOUBLE
	public static boolean theNumberIsADouble(String result)
	{
		// by default we accept that the number is a double
		boolean answer = true;
		
		double theResult = Double.parseDouble(result);
		if(theResult%1==0) // if the remainder is 0, the number is an integer
			answer = false;
		
		return answer;
	}// end
	
	///////////////////////////////////
	//check if the number is a BIGDECIMAL
	public static boolean isBigDecimal(String result)
		{	
			return result.contains("E"); 
		}
		
}

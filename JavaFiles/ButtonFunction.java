// Theodora Tataru C00231174
// Ana Griga C00231441
// Year 2
// Calculator Project


package Calculator;

import java.util.EmptyStackException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// THIS CLASS IS DEDICATED FOR BUTTON'S FUNCTIONS
public class ButtonFunction 
{
	// CLEAR BUTTON
	public static void clear()
		{
			// reset the arrays : infix and postfix arrays
			for(int i = 0; i < Calculator.postfixArray.length; i++)
				Calculator.postfixArray[i] = null;
			for(int j = 0; j < Calculator.infixArray.length; j++)
				Calculator.infixArray[j]=null;
			
			// reset the textfield and all the elements
			// the user can start from zero with no errors
			Calculator.textField.setText("");
			Calculator.elements = "";
			Calculator.buttonLabel = "";
			Calculator.infixArrayCount = 0;
			Calculator.postfixArrayCount = 0;
		}// end CLEAR
	
	// BACKSPACE BUTTON
	public static void backspace()
		{
		try	// if there are characters to be deleted it will delete the last one
			{
				// erasing the last character from the textfield 
				// and not displaying the backspace symbol
				String theText = Calculator.textField.getText();
				Calculator.textField.setText(theText.substring(0, theText.length()-2));
				// erasing the last character from the infix array
				Calculator.infixArray[Calculator.infixArrayCount] = 
				Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);
				// replacing the string in elements with the last character deleted
				Calculator.elements = Calculator.infixArray[Calculator.infixArrayCount];
			}
		
		// if there are no more characters to be deleted and we get 
		// StringIndexOutOfBoundsException error, the calculator will move to previous index of the infixArray
		catch(StringIndexOutOfBoundsException errorString )
			{
				try
					{	// moving to the previous index of the infix array
						Calculator.infixArrayCount--;
						// erasing the last character from the infix array
						Calculator.infixArray[Calculator.infixArrayCount] = 
						Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);
						// replacing the string in elements with the last character deleted
						Calculator.elements = Calculator.infixArray[Calculator.infixArrayCount];
					}
				
				// if the index 0 of the array has no more characters to be deleted, reset the calculator
				catch(ArrayIndexOutOfBoundsException errorArray)
					{
						clear();
					}
			}
		// if we need to delete an arithmetic sign
		catch(NullPointerException signError)
			{	// moving to the previous index of the infix array
				Calculator.infixArrayCount--;
				// erasing the last character from the infix array
				Calculator.infixArray[Calculator.infixArrayCount] = 
				Calculator.infixArray[Calculator.infixArrayCount].substring(0, Calculator.infixArray[Calculator.infixArrayCount].length()-1);
			}
		} // end BACKSPACE 
	
	// NUMBER BUTTON + DECIMAL POINT
	public static void operandButton()
		{
			Calculator.elements += Calculator.buttonLabel;								
			Calculator.infixArray[Calculator.infixArrayCount] = Calculator.elements;	
																						
			// the pressed DIGIT is added at the end (append) to the ELEMENTS string
			// the new String is added into an array
			// we do not increase the infixArrayCount, as on this position we might append another digit
		}// end OPERAND BUTTON 
	
	// NEGATIVE button
	public static void negativeSign()
		{
			if(Calculator.elements.equals("")) 	// if the ELEMENTS is empty because the NEGATIVE
				{								// sign can be added just at the beginning of a number
					Calculator.elements += "-"; 
					Calculator.infixArray[Calculator.infixArrayCount] = Calculator.elements;
				}
			else // if the NEGATIVE sign is added in the middle of the number, ERROR
				{
					// alert, because we cannot use the negative sign as a minus!
					JOptionPane.showMessageDialog(new JFrame(), "You can not use the NEGATIVE sign for substraction!", "Calculation Error", JOptionPane.ERROR_MESSAGE);
					// clear the text field if the error pops up
					clear();
				}
		}// end NEGATIVE SIGN

	// OPERATORS BUTTONS
	public static void operatorButton()
		{ 
			Calculator.infixArrayCount++;	// Until an OPERATOR is pressed, the number(s) string is added
											// to the previous index.
			Calculator.infixArray[Calculator.infixArrayCount] = Calculator.buttonLabel;		
			// we add at that index the pressed OPERAND 
			Calculator.elements = "";
			// we clear the elements, to add new number or the new operand and not be mixed
			// with previous digits or operands
			Calculator.infixArrayCount++;		// we increase the position of the next available index
		}// end OPERATORS BUTTONS METHOD
	
	// THE MEMORY STORE
	public static void memoryStore()
		{	// Deleting from the TEXTFIELD the label of the button MS
			String theText = Calculator.textField.getText();
			Calculator.textField.setText(theText.substring(0, theText.length() - 2));
			
			//System.out.println(theText.substring(0, theText.length() - 2));// test only - CONSOLE READING
			if(Calculator.infixArrayCount==0 && Calculator.MS.length()==0 && Calculator.infixArray[Calculator.infixArrayCount]!=null) 
				{	// if the number is valid to be added into the memory (not followed by an operand or a null index)
					// if there is no element saved into MS, the element can be saved into it
					Calculator.MS = Calculator.infixArray[Calculator.infixArrayCount];
					// add the operation into history text area
					System.out.println("The " + Calculator.MS + " was added into memory!"); // test - CONSOLE READING|
					Calculator.history.setText(Calculator.MS + " was added into memory!" + "\n\n"+ Calculator.history.getText());
				}
			
			else if(Calculator.infixArrayCount==0 && Calculator.MS.length()>0)
				{	// if there is already an element saved into MEMORY, give the user an error
					JOptionPane.showMessageDialog(new JFrame(), "Clear the memory first!", "Error", JOptionPane.ERROR_MESSAGE);
					// display error in history
					Calculator.history.setText(Calculator.MS + " is already in the memory!" + "\n\n"+ Calculator.history.getText());
				}
			else
				{   // if the element is not valid for the memory (if it's not a number)
					JOptionPane.showMessageDialog(new JFrame(), "Please insert a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
					// display error in history
					Calculator.history.setText(Calculator.textField.getText() + " is not a valid number to be added in memory!" + "\n\n"+ Calculator.history.getText());
				}	
		}// end MEMORY STORE
	
	// MEMORY CLEAR BUTTON
	public static void memoryClear()
		{
			// do not allow the label of the button to be printed to the text field
			String theText = Calculator.textField.getText();
			Calculator.textField.setText(theText.substring(0, theText.length() - 2));
			
			// if the memory is used
			if(Calculator.MS.length()>0)
				{	// if there is an element saved into MS, delete it, and print the message on history panel
					Calculator.history.setText(Calculator.MS + " was deleted from the memory!" + "\n\n"+ Calculator.history.getText());
					// clear the MS
					Calculator.MS = "";
				}
			else if(Calculator.MS.length()==0)
				{	// if there is no element saved into MS, print it to the history
					Calculator.history.setText(Calculator.MS + "The memory is clear! " + "\n\n"+ Calculator.history.getText());
					// clear the text field when the error pops up
					clear();
				} // end MEMORY CLEAR
		}
	
	// MEMORY RECALL BUTTON
	public static void memoryRecall()
		{	
			// theText takes the string from the textfield 
			String theText = Calculator.textField.getText();
			
				// if the memory is empty, display the message to the history area
				if(Calculator.MS.equals(""))
					{
						// do not display the button label to the text field
						Calculator.textField.setText(theText.substring(0, theText.length() - 2));
						// message in history area
						Calculator.history.setText(Calculator.MS + "The memory is clear! " + "\n\n"+ Calculator.history.getText());
					}
				else // if there is a value stored in MS
					{
						// do not allow the label of the button to be printed to the text field
						Calculator.textField.setText(theText.substring(0, theText.length() - 2));
						// add the number from MS to the infixArray
						Calculator.infixArray[Calculator.infixArrayCount] = Calculator.MS;
						// add the number from MS to the text field
						Calculator.textField.setText(Calculator.textField.getText() + Calculator.MS);
					}
		} // end MEMORY RECALL
	
	// EQUAL BUTTON
	public static void equal()
		{
			if(Calculator.infixArray[0]==null)
				{ 		// if the user inserts a digit, will overwrite the 0
						Calculator.infixArray[0]="0";
						// if the user inserts an operator, according to the  operatorButton() method
						// the operator will be placed on the next index, leaving the 0 on the
						// previous index.
						Calculator.textField.setText("0" + Calculator.textField.getText());
				}
		
			// getting the postfixArray
			Calculator.postfixArrayCount = ArithmeticOperations.postfixStack(Calculator.postfixArray, Calculator.infixArray, Calculator.infixArrayCount);
			try 
				{	// TOTAL is the result of the calculation
					String total = ArithmeticOperations.result(Calculator.postfixArray, Calculator.postfixArrayCount);
					
					// checking if the numbers is a DOUBLE
					if(ArithmeticOperations.theNumberIsADouble(total))
						{	// if the TOTAL has the decimal digits > then 0, we display the TOTAL with the decimal points.
						
							// adding the whole calculation to the HISTORY TEXT AREA
							Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
							System.out.println("DECIMAL POINT " +  total); // TEST - CONSOLE READING
							// display the result on the text field 
							Calculator.textField.setText(total);
							// reset infix array -> ready for next calculation
							Calculator.infixArrayCount = 0;
							// the result will be ready to be used in another calculation on [0]
							Calculator.infixArray[Calculator.infixArrayCount] = total;
							
							// the division by 0
							try { // if the result is INFINITY or NAN, handle the division by 0 in the exception class
									if(Double.parseDouble(Calculator.infixArray[0])==Double.POSITIVE_INFINITY || Double.parseDouble(Calculator.infixArray[0])==Double.NEGATIVE_INFINITY
											||Double.isNaN(Double.parseDouble(Calculator.infixArray[0])) )	
										throw new CalculatorExceptions();
								}
							
							catch(CalculatorExceptions InfinityResult)
								{
									System.out.println("Division by 0, handeled"); // TEST - CONSOLE READING
								}
						}
					// BIG DECIMAL
					else if(ArithmeticOperations.isBigDecimal(total))// adding the whole calculation to the HISTORY TEXT AREA
						{	// if the TOTAL is very big and is written in scientific notation, display to the screen as a bigDecimal
							Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
							System.out.println("DECIMAL POINT " +  total); // TEST - CONSOLE READING
							Calculator.textField.setText(total);
							Calculator.infixArrayCount = 0;
							Calculator.infixArray[Calculator.infixArrayCount] = total;
						}
					// INTEGER
					else 
						{	// if the TOTAL has a decimal point, and the decimals are all 0s, we display it as a whole number
							// getting the number before the decimal point
							total = total.substring(0, total.indexOf('.'));
							Calculator.history.setText(Calculator.textField.getText() +  total + "\n\n"+ Calculator.history.getText());
							System.out.println("NO DECIMAL POINT " +  total); // TEST - CONSOLE READING
							Calculator.textField.setText(total);
							Calculator.infixArrayCount = 0;
							Calculator.infixArray[Calculator.infixArrayCount] = total;
						}
				}
			
			// INVALID INPUT
			catch(EmptyStackException tooManySymbols)
				{	// the postfixt method can not handle the input
					Calculator.history.setText(Calculator.textField.getText() +  "Invalid Input" + "\n\n"+ Calculator.history.getText());
					Calculator.textField.setText("Invalid input");
					JOptionPane.showMessageDialog(new JFrame(), "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
					// clear the text field if the error pops up
					ButtonFunction.clear();
				}
			// pressing equal with no operands or operators inserted
			catch(StringIndexOutOfBoundsException pressingEqualWithNoOP)
				{
					Calculator.history.setText(Calculator.textField.getText() +  "No Operators/Operands found!" + "\n\n"+ Calculator.history.getText());
					Calculator.textField.setText("Invalid input");
					JOptionPane.showMessageDialog(new JFrame(), "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
					// clear the text field if the error pops up
					ButtonFunction.clear();
				}
		}
	
	// MULTIPLE DECIMAL POINTS
	public static void checkDecimalPoint()
		{	
			if(Calculator.infixArray[Calculator.infixArrayCount]!=null) // if the DECIMAL POINT IS NOT THE FIRST CHARACTER 
				{
					if(Calculator.infixArray[Calculator.infixArrayCount].contains("."))
						{	// if there is already a decimal point in the number, ignore it
							String theText = Calculator.textField.getText();
							Calculator.textField.setText(theText.substring(0, theText.length()-1));
							System.out.println("Decimal point ignored"); // TEST - CONSOLE READING
						}
					else  // if there is no decimal point in the number, add it to the number
						operandButton();
				}
			else // if the DECIMAL point starts the number, we ignore it
				{
					String theText = Calculator.textField.getText();
					Calculator.textField.setText(theText.substring(0, theText.length()-1));
					System.out.println("Decimal point ignored"); // TEST - CONSOLE READING
				}
		}
}
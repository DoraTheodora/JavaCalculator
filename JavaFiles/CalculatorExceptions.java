// Theodora Tataru C00231174
// Ana Griga C00231441	
// Year 2
// Calculator Project

package Calculator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CalculatorExceptions extends Exception
{
	public CalculatorExceptions() 
	{
		super();
		// clear everything!
		ButtonFunction.clear();
		// display the error to the history area
		Calculator.history.setText(Calculator.textField.getText() +  "Cannot Divide by 0!" + "\n\n"+ Calculator.history.getText());
		// set the result as null
		Calculator.infixArray[Calculator.infixArrayCount]=null;
		// display the error to the user
		JOptionPane.showMessageDialog(new JFrame(), "The denominator cannot be 0!", "Error", JOptionPane.ERROR_MESSAGE);
	}
}


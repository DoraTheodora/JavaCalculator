// Theodora Tataru C00231174
// Ana Griga C00231441	
// Year 2
// Calculator Project

package Calculator;

import javax.swing.JFrame;

public class CalculatorDriver 
{
	public static void main(String[] args) 
	{
		Calculator myWindow = new Calculator();
		myWindow.setLocation(400, 200);
		myWindow.setVisible(true);
		myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

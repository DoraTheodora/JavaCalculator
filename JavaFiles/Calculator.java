// Theodora Tataru C00231174
// Ana Griga C00231441
// Year 2
// Calculator Project

package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

// THIS CLASS IS DEDICATED FOR GUI AND LISTENERS
public class Calculator extends JFrame
{	
	// VARIABLES
	// text field
	protected static JTextField textField; // where the user will see his input
	
	// text area
	protected static JTextArea history;	// history output
	
	// buttons
	private JButton button;
	
	//label
	private JLabel historyLabel; // history area label
	
	// panels;
	private JPanel forText;			// for the text field
	private JPanel mainButtons;		// for all the buttons
	private JPanel memoryButtons; 	// for the memory buttons
	private JPanel allButtons;		// add all buttons together 
	private JPanel bigPanel;		// the big panel where all the panels goes into
	private JPanel space;			// panel used for spaces 
	private JPanel historyPanel;	// the panel for the text area and history label
	
	// listeners
	protected static String buttonLabel = "";	// labels for the buttons
	protected static String elements = "";		// where we form the numbers
	
	protected static int max = 100; // maximum number of elements input from the user.
	
	protected static String infixArray[] = new String[max];	// users input
	protected static int infixArrayCount = 0; 		// the index of the next available spot for the user input
	
	protected static String postfixArray[] = new String[max]; // POSTFIX RESULT
	protected static int postfixArrayCount;			// the count of the postfixStackArray

	// button listener
	private ButtonListener readLabel = new ButtonListener();
	
	// memoryButtons variables
	protected static String MS = ""; // where we store in memory
	
	// constructor GUI
	public Calculator()
		{
			super("Calculator");

			// text field
			forText = new JPanel();					// the panel for the text field
			textField = new JTextField("", 25); 	// the text field with no predefined text, and a limit of 25 characters
			
			textField.setHorizontalAlignment(JTextField.RIGHT);	// the position of the input (right)
			textField.setEditable(false);						// no input from the keyboard - input just from the buttons
			textField.setBackground(Color.white); 				// set the background white (default is grey from not being editable)
			textField.setFont(new Font("Arial", Font.BOLD, 12)); // change the font and size of the textfield
			
			
			forText.setLayout(new GridLayout(1,1));
			forText.add(textField);	// adding the text field to the panel
			forText.setPreferredSize(new Dimension(300,100)); // setting the panel size	
		
			// all buttons
			mainButtons = new JPanel();	// the panel for all the buttons
			mainButtons.setLayout(new GridLayout(6,4,1,1)); // 6 rows, 4 columns, 1 space between buttons
			
			// FIRST ROW
			space = new JPanel();	// added spaces, so we can add the 
			mainButtons.add(space);	// the backspace button, to the
			space = new JPanel();	// last position on the right
			mainButtons.add(space);	// on the first row
			space = new JPanel();
			mainButtons.add(space);
			// delete button -> /u232b is the unicode for backspace symbol
			button = new JButton("\u232b");		// adding the button after the 3 empty spaces 
			button.addActionListener(readLabel);
			mainButtons.add(button);		
			button.setToolTipText("Backspace");	
			button.setFont(button.getFont().deriveFont(15f));
			// END FIRST ROW			
			
			// adding the main buttons
			// clear button
			button = new JButton("C");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Clear");
			button.setFont(button.getFont().deriveFont(20f));
			// parentheses button
			button = new JButton("(");
			button.addActionListener(readLabel);
			button.setToolTipText("Open Parenthese");
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// modular button
			button = new JButton(")");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Close Parenthese");
			button.setFont(button.getFont().deriveFont(20f));
			// division button
			button = new JButton("÷");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Division");
			button.setFont(button.getFont().deriveFont(20f));
			// 7 button
			button = new JButton("7");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 8 button
			button = new JButton("8");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 9 button
			button = new JButton("9");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// multiplication button
			button = new JButton("*");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Multiplication");
			button.setFont(button.getFont().deriveFont(20f));
			// 4 button
			button = new JButton("4");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 5 button
			button = new JButton("5");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 6 button
			button = new JButton("6");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// subtraction button
			button = new JButton("-");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Subtraction");
			button.setFont(button.getFont().deriveFont(20f));
			// 1 button
			button = new JButton("1");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 2 button
			button = new JButton("2");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// 3 button
			button = new JButton("3");
			button.addActionListener(readLabel);
			button.setBackground(Color.white);
			mainButtons.add(button);
			button.setFont(button.getFont().deriveFont(20f));
			// + button
			button = new JButton("+");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Addition");
			button.setFont(button.getFont().deriveFont(20f));
			// negative button
			button = new JButton("\u2212");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Negative Number");
			button.setFont(button.getFont().deriveFont(20f));
			// 0 button
			button = new JButton("0");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setBackground(Color.white);
			button.setFont(button.getFont().deriveFont(20f));
			// decimal point button
			button = new JButton(".");
			button.addActionListener(readLabel);
			mainButtons.add(button);
			button.setToolTipText("Decimal Point");
			button.setFont(button.getFont().deriveFont(20f));
			// equal button
			button = new JButton("=");
			button.addActionListener(readLabel);
			button.setBackground(Color.CYAN);
			mainButtons.add(button);
			button.setToolTipText("Equal");
			button.setFont(button.getFont().deriveFont(20f));
			// set the dimensions to the button's panel
			mainButtons.setPreferredSize(new Dimension(300,300));
			
			// MEMORY buttons
			memoryButtons = new JPanel();	// the panel for the memory buttons
			memoryButtons.setLayout(new GridLayout(1,3,2,2)); // 1 row and 3 colums
			
			button = new JButton("MS"); // memory 1
			button.addActionListener(readLabel);
			button.setToolTipText("Store in memory");
			button.setFont(button.getFont().deriveFont(15f));
			memoryButtons.add(button); // adding the button to the panel
			
			button = new JButton("MR"); // memory 2
			button.addActionListener(readLabel);
			memoryButtons.add(button); // adding the button to the panel
			button.setToolTipText("Memory Recall");
			button.setFont(button.getFont().deriveFont(15f));
			
			button = new JButton("MC"); // memory 1
			button.addActionListener(readLabel);
			button.setToolTipText("Clear Memory");
			button.setFont(button.getFont().deriveFont(15f));
			memoryButtons.add(button); // adding the button to the panel
			

			// the panel for all panels with buttons
			allButtons = new JPanel();
			allButtons.setLayout(new BorderLayout());
			allButtons.add(mainButtons, BorderLayout.NORTH);
			allButtons.add(memoryButtons, BorderLayout.SOUTH);
			
			//history screen
			history = new JTextArea();
			history.setPreferredSize(new Dimension(320,410));
			history.setEditable(false); // no input from the user, just display the history
			history.setFont(new Font("Arial", Font.BOLD, 12));
			historyLabel= new JLabel("HISTORY:");
			
			// panel for the history text area and history label
			historyPanel = new JPanel();
			historyPanel.setLayout(new BorderLayout());
			historyPanel.add(historyLabel, BorderLayout.NORTH);
			historyPanel.add(history, BorderLayout.SOUTH);
	
			// adding all panels to the main panel
			bigPanel = new JPanel();
			bigPanel.setLayout(new BorderLayout());
			bigPanel.add(forText, BorderLayout.NORTH);
			bigPanel.add(allButtons, BorderLayout.SOUTH);
			
			// add the big panel to the frame;
			setLayout(new FlowLayout(FlowLayout.CENTER));
			add(bigPanel);
			add(historyPanel);
			pack();
		} // end GUI
	
	// LISTENERS
	// listeners for number buttons
	class ButtonListener implements ActionListener
		{
			@Override
			public void actionPerformed(ActionEvent readLabel) 
				{
					// getting the labels symbols from the buttons and print
					// them to the text field
					buttonLabel = readLabel.getActionCommand();
					textField.setText(textField.getText() + buttonLabel);
					
					// CLEAR button
					if(buttonLabel.equals("C"))
						ButtonFunction.clear();
					
					// BACKSPACE button
					if(buttonLabel.equals("\u232b"))
						ButtonFunction.backspace();
					
					// NUMBER buttons
					if(buttonLabel.equals("0"))		// if the condition is true: the user presses a DIGIT button
						ButtonFunction.operandButton();
															
					else if(buttonLabel.equals("1"))
						ButtonFunction.operandButton();
						
					else if(buttonLabel.equals("2"))
						ButtonFunction.operandButton();
						
					else if(buttonLabel.equals("3"))
						ButtonFunction.operandButton();
						
					else if(buttonLabel.equals("4"))
						ButtonFunction.operandButton();
			
					else if(buttonLabel.equals("5"))
						ButtonFunction.operandButton();
						
					else if(buttonLabel.equals("6"))
						ButtonFunction.operandButton();

					else if(buttonLabel.equals("7"))
						ButtonFunction.operandButton();

					else if(buttonLabel.equals("8"))
						ButtonFunction.operandButton();
							
					else if(buttonLabel.equals("9"))
						ButtonFunction.operandButton();
					// DECIMAL POINT
					else if(buttonLabel.equals("."))
						ButtonFunction.checkDecimalPoint();
					// NEGATIVE SIGN
					else if(buttonLabel.equals("\u2212"))	
						ButtonFunction.negativeSign();
					// end NUMBERS
					
					// OPERATORS
					// DIVISION button
					if(buttonLabel.equals("÷"))
						ButtonFunction.operatorButton();
									
					// MULTIPLICATION button
					else if(buttonLabel.equals("*"))
						ButtonFunction.operatorButton();

					// MINUS button
					else if(buttonLabel.equals("-"))
						ButtonFunction.operatorButton();

					// PLUS button
					else if(buttonLabel.equals("+"))
						ButtonFunction.operatorButton();

					// OPEN parentheses button
					else if(buttonLabel.equals("("))
						ButtonFunction.operatorButton();

					// CLOSE parentheses button
					else if(buttonLabel.equals(")"))
						ButtonFunction.operatorButton();
					
					// EQUAL button
					else if(buttonLabel.equals("="))
						ButtonFunction.equal();

					// MEMORY buttons
					// MEMORY STORE BUTTON
					if(buttonLabel.equals("MS"))
						ButtonFunction.memoryStore();
					
					// MEMORY CLEAR BUTTON
					if(buttonLabel.equals("MC"))
						ButtonFunction.memoryClear();
					
					// RECALL MEMORY BUTTON
					if(buttonLabel.equals("MR"))
						ButtonFunction.memoryRecall();

					
					// FOR PROGRAMMERS USE: 
					// visualize the elements into the array - CONSOLE READING
					System.out.println("*************************************************");
					for(int i = 0; i < infixArrayCount+1; i++)
						{
							if(infixArray[i] != null)
								System.out.println(infixArray[i] + " INFIX "  + i + "\t");
						}
					
					System.out.println("=================================================");
					for(int i = 0; i < postfixArrayCount; i++)
						{
							if(postfixArray[i] != null)
								System.out.println(postfixArray[i] + " POSTFIX " + i + "\t");
						}	
				}	
		} // end LISTENTERS
}



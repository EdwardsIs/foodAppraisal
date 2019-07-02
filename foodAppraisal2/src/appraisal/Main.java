package appraisal;

import javax.swing.*;
import java.awt.event.*;


public class Main {
	public static void main(String args[]) {
		// List of FoodItem objects, initialized with names
		FoodItem foodItems[] = {new FoodItem("Cheeseburger Deluxe"),
				new FoodItem("Cheeseburger"), new FoodItem("Hamburger"),
				new FoodItem("Fries"), new FoodItem("Soda")};
		
		// Frame to hold all other GUI components
		JFrame frame = new JFrame("Contestant 512");
		
		// Labels
		JLabel lblHeader, lblPromptFood, lblPromptRate; // Creating label objects
		
		lblHeader = new JLabel("Food Appraisal");
		lblHeader.setBounds(100, 10, 150, 20);
		frame.add(lblHeader);
		
		lblPromptFood = new JLabel("Which Item Would You Like to Rate/View?");
		lblPromptFood.setBounds(10, 40, 300, 20);
		frame.add(lblPromptFood);
		
		lblPromptRate = new JLabel();
		lblPromptRate.setBounds(10, 120, 390, 20);
		lblPromptRate.setVisible(false);
		frame.add(lblPromptRate);
		
		// Text Area
		JTextArea tAreaOutput = new JTextArea();
		tAreaOutput.setEditable(false); // Setting text area as disabled, as text area is only for output
		tAreaOutput.setBounds(10, 250, 300, 100);
		frame.add(tAreaOutput);
		
		// Text field
		JTextField txtRate = new JTextField();
		txtRate.setBounds(10, 145, 45, 20);
		txtRate.setVisible(false);
		frame.add(txtRate);
		
		// Combo box
		String selections[] = {"(none)", "Cheeseburger Deluxe",
				"Cheeseburger", "Hamburger", "Fries", "Soda"};
		JComboBox cboFoodItem = new JComboBox(selections);
		cboFoodItem.setBounds(10, 70, 150, 20);
		frame.add(cboFoodItem);
		
		// Adding action handler to combo box
		cboFoodItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Clearing rate text box
				txtRate.setText("");
				
				if (cboFoodItem.getSelectedIndex() == 0) {
					lblPromptRate.setVisible(false);
					txtRate.setVisible(false);
					tAreaOutput.setText("");
				} else {
					lblPromptRate.setText("How would you rate our " + foodItems[cboFoodItem.getSelectedIndex() - 1].getName()
							+ "? (1-100)");
					lblPromptRate.setVisible(true);
					txtRate.setVisible(true);
					tAreaOutput.setText(foodItems[cboFoodItem.getSelectedIndex() - 1].toString());
				}
			}
		});
		
		// Buttons
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(10, 390, 100, 35);
		frame.add(btnCalculate);
		
		// Adding action handler to calculate button
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Checking to see if user has selected an item yet
				if (cboFoodItem.getSelectedIndex() == 0) {
					// No item selected - show user message
					JOptionPane.showMessageDialog(null, "Please select a food item from the drop-down");
				} else { // Item selected
					// If the user has entered a rate
					if(!(txtRate.getText().isEmpty())) {
						int score;
						try {
							// This line will throw an error if input is non-numeric
							double dscore = Double.parseDouble(txtRate.getText());
							score = (int)Math.round(dscore);
							// Checking to see if score is in proper range
							if (score >= 1 && score <=100) {
								// Score is in range of 1-100, adding new score
								foodItems[cboFoodItem.getSelectedIndex() - 1].addNewScore(score);
							} else {
								// Score is out of desired range, showing error message to user
								JOptionPane.showMessageDialog(null, "Enter a numeric value between 1 & 100 for the rating");
							}
						} catch (Exception exc) {
							// Value was non-numeric; displaying error to user
							JOptionPane.showMessageDialog(null, "Enter a numeric value between 1 & 100 for the rating");
						}
					}
					tAreaOutput.setText(foodItems[cboFoodItem.getSelectedIndex() - 1].toString());
					txtRate.setText("");
				}
			}
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(140, 390, 100, 35);
		frame.add(btnClear);
		
		// Adding action handler to clear button
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Hiding rating widgets
				txtRate.setVisible(false);
				lblPromptRate.setVisible(false);
				
				// Clearing text field/area
				tAreaOutput.setText("");
				txtRate.setText("");
				
				// Resetting combo box
				cboFoodItem.setSelectedIndex(0);
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(270, 390, 100, 35);
		frame.add(btnExit);
		
		// Adding action handler to exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Exiting the program
				System.exit(0);
			}
		});
		
		
		// Configuring frame size and layout
		frame.setSize(400, 475); // Setting dimensions
		frame.setLayout(null); // Using no layout manager
		frame.setVisible(true);
	}

}

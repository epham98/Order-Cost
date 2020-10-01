package application;

//makes a window with a user interface to place an order, price, quantity, tax, and shipping cost and calculates total cost

import java.text.NumberFormat;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Orinoco extends Application {
	
	//create grid pane and ui elements
	GridPane gridPane = new GridPane();
	private TextField tfItem = new TextField();
	private TextField tfPrice = new TextField();
	private TextField tfQuantity = new TextField();
	private CheckBox cbTax= new CheckBox("Taxable?");
	private RadioButton rbNextDay = new RadioButton("$20");
	private RadioButton rbThisWeek = new RadioButton("$12");
	private RadioButton rbSomeDay = new RadioButton("$5");
	ToggleGroup group = new ToggleGroup();
	private Label lbTotalDue = new Label("$0.00");
	private Button btProcess = new Button("Process");
	private Button btReset = new Button("Reset");
	
	@Override // Override the start method in the Application class
	 public void start(Stage primaryStage) {
		//add ui elements to be displayed in program
		gridPane.setAlignment(Pos.CENTER);
		//gridPane.setAlignment(Pos.CENTER);
		gridPane.setHgap(5); gridPane.setVgap(5);
		 gridPane.add(new Label("Item:"), 0, 0);
		 	gridPane.add(tfItem, 1, 0);
		 gridPane.add(new Label("Price:"), 0, 1);
		 	gridPane.add(tfPrice, 1, 1);
		 gridPane.add(new Label("Quantity:"), 0, 2);
		 	gridPane.add(tfQuantity, 1, 2);
		 gridPane.add(cbTax, 1, 3);
		 gridPane.add(new Label("Shipping"), 0, 4);
		 gridPane.add(new Label("Next Day"), 0, 5);
		 gridPane.add(rbNextDay, 1, 5);
		 gridPane.add(new Label("This Week"), 0, 6);
		 gridPane.add(rbThisWeek, 1, 6);
		 gridPane.add(new Label("Some Day"), 0, 7);
		 gridPane.add(rbSomeDay, 1, 7);
		 gridPane.add(new Label("Total Due"), 0, 8);
		 gridPane.add(lbTotalDue, 1, 8);
		 gridPane.add(btProcess, 0, 9);
		 gridPane.add(btReset, 1, 9);
		 rbNextDay.setToggleGroup(group);
		 rbThisWeek.setToggleGroup(group);
		 rbSomeDay.setToggleGroup(group);
		 btProcess.setOnAction(e -> process());
		 btReset.setOnAction(e -> reset());
		// Create a scene and place it in the stage
	    Scene scene = new Scene(gridPane, 500, 500);
	    primaryStage.setTitle("orinoco.com"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	
	private void process() {
		//private variables to be used in calculating total cost of item
		double price = Double.parseDouble(tfPrice.getText());
		int quantity = Integer.parseInt(tfQuantity.getText());
		final double TAXRATE = 0.07;
		double tax;
		double shipping = 0;
		
		double subTotal = price * quantity;
		
		//applies tax to the total if selected
		if (cbTax.isSelected()) {
			 tax = subTotal * TAXRATE;
		}
		else {
			 tax = 0;
		}
		
		//applies shipping charges depending on selection
		if (rbNextDay.isSelected()) {
			shipping = 20;
		}
		else if (rbThisWeek.isSelected()) {
			shipping = 12;
		}
		else if (rbSomeDay.isSelected()) {
			shipping = 5;
		}
		
		//formats total into currency
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
		double total = subTotal + tax + shipping;
		lbTotalDue.setText(fmt.format(total));
	}
	
	private void reset() {
		//clears all fields to allow user to place a new order
		tfItem.setText("");
		tfPrice.setText("");
		tfQuantity.setText("");
		cbTax.setSelected(false);
		rbNextDay.setSelected(false);
		rbThisWeek.setSelected(false);
		rbSomeDay.setSelected(false);
		lbTotalDue.setText("$0.00");
	}
	 
	/**
	* The main method is only needed for the IDE with limited
	* JavaFX support. Not needed for running from the command line.
	*/
	public static void main(String[] args) {
		launch(args);
	}
	 
	
	
}

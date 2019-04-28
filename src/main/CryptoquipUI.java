package main;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Facilitates user interaction of the Cryptoquip Solver
 *
 * @author Justin Roderman
 * @since: April 28, 2019
 */

public class CryptoquipUI extends Application
{
	private final int MAX_LETTERS = 26;

	@FXML private GridPane grdStrings;

	@FXML private TextField txtA;
	@FXML private TextField txtB;
	@FXML private TextField txtC;
	@FXML private TextField txtD;
	@FXML private TextField txtE;
	@FXML private TextField txtF;
	@FXML private TextField txtG;
	@FXML private TextField txtH;
	@FXML private TextField txtI;
	@FXML private TextField txtJ;
	@FXML private TextField txtK;
	@FXML private TextField txtL;
	@FXML private TextField txtM;
	@FXML private TextField txtN;
	@FXML private TextField txtO;
	@FXML private TextField txtP;
	@FXML private TextField txtQ;
	@FXML private TextField txtR;
	@FXML private TextField txtS;
	@FXML private TextField txtT;
	@FXML private TextField txtU;
	@FXML private TextField txtV;
	@FXML private TextField txtW;
	@FXML private TextField txtX;
	@FXML private TextField txtY;
	@FXML private TextField txtZ;

	@FXML private Button btn;

	private TextField[] textFields;
	private char[] charTranslations;

	/**
	 * Where the application launches from
	 * @param args What is passed in (don't worry about this)
	 */
	public static void main(String[] args)
	{
		launch(args);
	}

	/**
	 * Where the application launches from
	 * @throws IOException If an input or output exception occurred
	 */
	@Override
	public void start(Stage arg0) throws Exception
	{
		FXMLLoader load = new FXMLLoader(getClass().getResource("CryptoquipUI.fxml")); // You may have to change the path in order to access Cryptoquip.fxml
		load.setController(this); // Makes it so that you can control the UI using this class

		Parent root = (Parent) load.load();
		Scene scene = new Scene(root);

		// Start the application
		Stage stage = new Stage();
		stage.setTitle("Cryptoquip Solver");
		stage.setScene(scene);
		initUI();
		setPuzzle("This is a test");

		stage.show();
	}

	/**
	 * Initializes all aspects of the UI
	 */
	private void initUI()
	{
		initVariables();
		initListeners();
	}

	/**
	 * Initializes all variables
	 */
	private void initVariables()
	{
		textFields = new TextField[MAX_LETTERS];
		textFields[0] = txtA;
		textFields[1] = txtB;
		textFields[2] = txtC;
		textFields[3] = txtD;
		textFields[4] = txtE;
		textFields[5] = txtF;
		textFields[6] = txtG;
		textFields[7] = txtH;
		textFields[8] = txtI;
		textFields[9] = txtJ;
		textFields[10] = txtK;
		textFields[11] = txtL;
		textFields[12] = txtM;
		textFields[13] = txtN;
		textFields[14] = txtO;
		textFields[15] = txtP;
		textFields[16] = txtQ;
		textFields[17] = txtR;
		textFields[18] = txtS;
		textFields[19] = txtT;
		textFields[20] = txtU;
		textFields[21] = txtV;
		textFields[22] = txtW;
		textFields[23] = txtX;
		textFields[24] = txtY;
		textFields[25] = txtZ;

		charTranslations = new char[MAX_LETTERS];
		for(int i = 0; i < MAX_LETTERS; i++)
		{
			charTranslations[i] = '\0';
		}
	}

	/**
	 * Sets up listeners for all nodes
	 */
	private void initListeners()
	{
		for(int i = 0; i < MAX_LETTERS; i++)
		{
			initTextFieldListener(textFields[i], i);
		}

		btn.setOnAction(al -> {
			System.out.println(charTranslations[3]);
		});
	}

	/**
	 * Sets up the listener for all text fields
	 * @param field The field to add the listener to
	 * @param index Index of the listener
	 */
	private void initTextFieldListener(TextField field, int index)
	{
		field.focusedProperty().addListener((al, oldVal, newVal) -> {
			if (!newVal)
			{
				char letter = '\0';
				if(!field.getText().trim().isEmpty())
				{
					letter = field.getText().trim().charAt(0);
					if(letter >= 'a' && letter <= 'z')
						letter -= ('a' - 'A');
					if(letter < 'A' || letter > 'Z')
						letter = '\0';
					else {} // TODO Check if value already chosen
					field.setText("" + letter);
				}
				charTranslations[index] = letter;
			}
		});
	}
}
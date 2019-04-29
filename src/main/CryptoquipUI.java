package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Facilitates user interaction of the Cryptoquip Solver
 *
 * @author Justin Roderman
 * @since: April 28, 2019
 */

public class CryptoquipUI extends Application
{
	private final int MAX_LETTERS = 26; // Amount of letters in the alphabet
	public final int MAX_LABEL_LENGTH = 20; // Max amount of letters that can be in one line before wrapping
	public final int MAX_LABEL_COUNT = 6; // Max amount of lines that can be shown before it overlaps title

	@FXML private SplitPane splitPane;
	@FXML private GridPane grdTranslations;
	@FXML private GridPane grdStrings;

	@FXML private Label[] codeLabels;
	@FXML private Label[] translatedLabels;

	@FXML private Button btnNewGame;

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
	@FXML private TextField[] textFields;

	private String solutionStr;
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
		stage.setResizable(false);
		stage.setScene(scene);
		initUI();

		stage.show();

		grdTranslations.setDisable(true);
		btnNewGame.fire();
	}

	/**
	 * Exit the new game window
	 * @param input The input to start the game with.
	 * 	If empty, do not start new game
	 */
	public void exitNewGame(String input)
	{
		if(!input.isEmpty())
		{
			setPuzzle(input);
			grdTranslations.setDisable(false);
		}
		splitPane.setDisable(false);
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
		// Initializes the text field listeners
		for(int i = 0; i < MAX_LETTERS; i++)
		{
			initTextFieldListener(textFields[i], i);
		}

		// Creates a new game
		btnNewGame.setOnAction(al -> {
			splitPane.setDisable(true);
			try {
				new NewGameUI(this);
			}
			catch (IOException e) {
				System.out.println("Error loading NewGameUI!");
				e.printStackTrace();
			}
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
					if(letter >= 'a' && letter <= 'z') // Switch from lowercase to uppercase
						letter -= ('a' - 'A');
					if(letter < 'A' || letter > 'Z') // If the input is not a letter, ignore it
						letter = '\0';
					else // If it is a letter, check for duplicates
					{
						for(int i = 0; i < MAX_LETTERS; i++)
						{
							if(charTranslations[i] == letter) // If a duplicate is found, set that letter to null
							{
								charTranslations[i] = '\0';
								textFields[i].setText("");
								updateStrings((char)('A' + i), '\0');
								break;
							}
						}
					}
					field.setText("" + letter);
				}
				charTranslations[index] = letter;
				updateStrings((char)('A' + index), letter);
			}
		});
	}

	/**
	 * Sets the puzzle code to solve
	 * @param code The encrypted code to solve
	 */
	private void setPuzzle(String code)
	{
		// Set the solution
		if(code.isEmpty())
			throw new RuntimeException("Code needs to be a word!");
		solutionStr = code.toUpperCase();
		String[] lblStrings = splitString(solutionStr);

		// Reset strings
		grdStrings.getChildren().clear();

		// Set up the labels
		translatedLabels = new Label[lblStrings.length];
		codeLabels = new Label[lblStrings.length];

		// Add all labels
		for(int i = 0; i < lblStrings.length; i++)
		{
			String lblCodeStr = "";
			String lblTranslatedStr = "";
			for(char c : lblStrings[i].toCharArray())
			{
				lblCodeStr += "" + c + " ";
				lblTranslatedStr += (c >= 'A' && c <= 'Z' ? "_" : "" + c) + " ";
			}
			lblCodeStr = lblCodeStr.substring(0, lblCodeStr.length() - 1); // Get rid of final space
			lblTranslatedStr = lblTranslatedStr.substring(0, lblTranslatedStr.length() - 1); // Get rid of final space

			// Do twice, if ii is 0, refer to code string, otherwise refer to translated string
			for(int ii = 0; ii < 2; ii++)
			{
				Label lbl = new Label(ii == 0 ? lblCodeStr : lblTranslatedStr);
				lbl.setFont(Font.font("Ubuntu Mono", 30));
				if(ii == 0) // Code string
					lbl.setStyle("-fx-text-fill: gray");
				grdStrings.addRow((i * 3) + ii, lbl);
				GridPane.setHalignment(lbl, HPos.CENTER);

				if(ii == 0) // Code string
					codeLabels[i] = lbl;
				else // Translated string
					translatedLabels[i] = lbl;
			}
			grdStrings.addRow((i * 3) + 2, new Label());
		}
	}

	/**
	 * Splits the string to wrap text
	 * @param strOrig The original string to split
	 * @return Array of strings, similar to wrapped text
	 */
	private String[] splitString(String strOrig)
	{
		ArrayList<String> res = new ArrayList<String>();

		// Set up the string lines
		String[] splitStrs = strOrig.split(" ");
		if(splitStrs[0].length() > MAX_LABEL_LENGTH)
			throw new RuntimeException("0" + splitStrs[0]);
		res.add(splitStrs[0]);

		// Loop through all strings
		for(int i = 1; i < splitStrs.length; i++)
		{
			String str = splitStrs[i];
			if(str.length() > MAX_LABEL_LENGTH)
				throw new RuntimeException("0" + splitStrs[i]);
			String currStr = res.get(res.size()-1);
			if(currStr.length() + 1 + str.length() <= MAX_LABEL_LENGTH) // Check if adding the word will go over the limit (add 1 for ' ')
				res.set(res.size()-1, currStr + " " + str);
			else // If too long, add new line
				res.add(str);
		}
		if(res.size() > MAX_LABEL_COUNT)
			throw new RuntimeException("1");
		return Arrays.copyOf(res.toArray(), res.size(), String[].class);
	}

	/**
	 * Updates the string with a new character
	 * @param oldChar The old char to switch with the new char
	 * @param newChar The new character
	 */
	private void updateStrings(char oldChar, char newChar)
	{
		for(int i = 0; i < codeLabels.length; i++)
		{
			// Replace all instances of the old char with the new char
			String codeStr = codeLabels[i].getText();
			char[] translatedChars = translatedLabels[i].getText().toCharArray();
			int index = codeStr.indexOf(oldChar);
			while(index != -1)
			{
				translatedChars[index] = (newChar == '\0' ? '_' : newChar);
				index = codeStr.indexOf(oldChar, index + 1);
			}
			translatedLabels[i].setText(String.valueOf(translatedChars));
		}
	}
}




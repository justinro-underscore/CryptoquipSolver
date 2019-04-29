package main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Creates a new game for the Cryptoquip Solver
 *
 * @author Justin Roderman
 * @since: April 28, 2019
 */

public class NewGameUI
{
	@FXML TextArea txtInput;
	@FXML Button btnCancel;
	@FXML Button btnSubmit;

	private CryptoquipUI base; // Reference to the instance that called this

	/**
	 * Creates a NewGame window to set up a new game
	 * @param base Reference to the object that opened this window
	 * @throws IOException
	 */
	public NewGameUI(CryptoquipUI base) throws IOException
	{
		FXMLLoader load = new FXMLLoader(getClass().getResource("NewGameUI.fxml"));
		load.setController(this); // Makes it so that you can control the UI using this class

		Parent root = (Parent) load.load();
		Scene scene = new Scene(root);

		this.base = base;

		// Start the application
		Stage stage = new Stage();
		stage.setTitle("New Game");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		initializeListeners(stage);
		stage.setOnCloseRequest(e -> // In case the user exits without pressing cancel
		{
			base.exitNewGame("");
		});
	}

	/**
	 * Sets the functions that run when the user clicks on certain objects
	 */
	private void initializeListeners(Stage stage)
	{
		// Submits the code to start a new game
		btnSubmit.setOnAction(al -> {
			if(!txtInput.getText().trim().isEmpty())
			{
				String input = txtInput.getText();
				input = input.replace('\n', ' ').replace('\r', ' ').replaceAll("[ ]+", " "); // Apply replacements
				try {
					base.exitNewGame(input);
					stage.close();
				}
				catch(RuntimeException e) {
					showDialogBox("Input Error", "Word Too Long", "Your word \n\"" + e.getMessage() + "\"\n is too long. Words must be at most " + base.MAX_LABEL_LENGTH + " letters long.", AlertType.ERROR);
				}
			}
			else
				showDialogBox("Input Error", "Empty Input!", "You must insert a code to run the program.", AlertType.ERROR);
		});

		// Cancels the user creation
		btnCancel.setOnAction(al -> {
			base.exitNewGame("");
			stage.close();
		});
	}

	/**
	 * Show a dialog box
	 * @param title String representing title of dialog box
	 * @param header String representing head of dialog box
	 * @param content Content of dialog box
	 * @param type Type of dialog box
	 */
	public void showDialogBox(String title, String header, String content, AlertType type)
	{
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(header);
		Label label = new Label(content);
		label.setWrapText(true);
		label.setMaxWidth(400);
		alert.getDialogPane().setContent(label);

		alert.showAndWait();
	}
}

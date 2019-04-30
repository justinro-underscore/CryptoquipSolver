package main;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Keeps track of the notes for the Cryptoquip Solver.
 * This allows the user to mark down what letters could go
 * 	with each letter.
 *
 * @author Justin Roderman
 * @since: April 30, 2019
 */

public class NotesUI
{
	private final String SELECT_LETTER_STR = "Select a Letter"; // Used for default state

	@FXML TextArea txtNotes;
	@FXML Label lblLetter;

	private int currLetter; // Keeps track of the current letter we're looking at
	private String[] letterNotes; // Keeps track of the notes for each letter

	/**
	 * Creates a new instance of the Notes window for players
	 * to keep track of their guesses
	 * @param base The window that opens the notes
	 * @throws IOException
	 */
	public NotesUI(CryptoquipUI base) throws IOException
	{
		FXMLLoader load = new FXMLLoader(getClass().getResource("NotesUI.fxml"));
		load.setController(this); // Makes it so that you can control the UI using this class

		Parent root = (Parent) load.load();
		Scene scene = new Scene(root);

		// Start the application
		Stage stage = new Stage();
		stage.setTitle("Notes");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
		initializeVars();
		initializeListeners();
		stage.setOnCloseRequest(e -> // In case the user exits without pressing cancel
		{
			base.exitNotes();
		});
	}

	/**
	 * Sets the new letter of the notes window
	 * @param index The new current letter
	 */
	public void setLetter(int index)
	{
		// If we defocus, set back to default
		if(index < 0)
		{
			lblLetter.setText(SELECT_LETTER_STR);
			txtNotes.setText("");
			txtNotes.setDisable(true);
		}
		else // If we are focused on something, set the new currLetter
		{
			lblLetter.setText("" + (char)(index + 'A'));
			txtNotes.setDisable(false);
			System.out.println(currLetter + " " + index);
			txtNotes.setText(letterNotes[index]);
			currLetter = index;
		}
	}

	/**
	 * Initializes letterNotes array
	 */
	private void initializeVars()
	{
		letterNotes = new String[CryptoquipUI.MAX_LETTERS];
		for(int i = 0; i < letterNotes.length; i++)
		{
			letterNotes[i] = "";
		}
	}

	/**
	 * Initializes the listeners for txtNotes
	 */
	private void initializeListeners()
	{
		// When we hit a key, update the letterNotes entry for this letter
		txtNotes.textProperty().addListener((al, oldVal, newVal) -> {
			if(txtNotes.isFocused()) // To avoid overwriting when we set a new letter
				letterNotes[currLetter] = newVal;
		});
	}
}

package com.haw.projecthorse.level.util.overlay.popup;

import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.haw.projecthorse.gamemanager.GameManagerFactory;
import com.haw.projecthorse.gamemanager.settings.Settings;
import com.haw.projecthorse.level.util.uielements.ButtonLarge;

/**
 * Dieses Popup ist speziell für die Verwendung in Mini Spielen gedacht. Es
 * bietet dem Nutzer zugriff auf die Funktionen: Musik : An/Aus Spiel Verlassen
 * Weiter Spielen
 * 
 * @author Philipp
 * @version 1.0
 */
public class GamePausePopup extends Popup implements Observer {

	private Settings setting = GameManagerFactory.getInstance().getSettings();
	private ImageTextButton musicButton;
	private ImageTextButton soundButton;

	/**
	 * Konstruktor.
	 */
	public GamePausePopup() {

		musicButton = createButton("", new ChangeListener() {

			@Override
			public void changed(final ChangeEvent event, final Actor actor) {

				setting.setMusicState(!setting.getMusicState());
				event.cancel();
			}

		});

		soundButton = createButton("", new ChangeListener() {

			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				setting.setSoundState(!setting.getSoundState());
				event.cancel();
			}

		});

		createButton("Spiel verlassen", new ChangeListener() {

			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				GamePausePopup.this.getOverlay().disposePopup();
				GameManagerFactory.getInstance().navigateBack();
				event.cancel();
			}

		});

		createButton("Weiter Spielen", new ChangeListener() {

			@Override
			public void changed(final ChangeEvent event, final Actor actor) {
				GamePausePopup.this.getOverlay().disposePopup();
				event.cancel();
			}

		});

		setTextes();
		setting.addObserver(this);

	}

	/**
	 * Updatet die Texte der Buttons.
	 */
	private void setTextes() {
		if (setting.getMusicState()) {
			musicButton.setText("Musik ist AN");
		} else {
			musicButton.setText("Musik ist AUS");
		}

		if (setting.getSoundState()) {
			soundButton.setText("Sound ist AN");
		} else {
			soundButton.setText("Sound ist AUS");
		}
	}

	/**
	 * Diese Methode erzeugt einen Button.
	 * 
	 * @param label
	 *            Button Text
	 * @param inputListener
	 *            {@link ChangeListener} um auf den Button Klick zu reagieren
	 * @return button
	 */
	private ImageTextButton createButton(final String label, final ChangeListener inputListener) {
		ImageTextButton btn = new ButtonLarge(label, inputListener);
		btn.addListener(inputListener);
		this.addActor(btn);
		return btn;
	}

	@Override
	public final void update(final Observable observable, final Object data) {
		setTextes();
	}

}

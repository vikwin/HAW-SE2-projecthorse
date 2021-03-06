package com.haw.projecthorse.level.util.overlay.navbar;

import com.badlogic.gdx.Input.Orientation;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.haw.projecthorse.gamemanager.GameManagerFactory;
import com.haw.projecthorse.level.util.overlay.Overlay;
import com.haw.projecthorse.level.util.overlay.OverlayWidgetGroup;
import com.haw.projecthorse.level.util.overlay.navbar.button.NavbarButton;

/**
 * Eine Navbar ist eine kleine Leiste mit Menü Elementen(Pause Button) die
 * entweder am oberen oder am unteren Bildschirmrand angezeigt wird. Jeder
 * Navbar können mehrere Menü Elemente (NavBarButton) über die Methode addButton
 * hinzugefügt werden.
 * 
 * @author Philipp
 * @version 1.0
 */
public class NavBar extends OverlayWidgetGroup {

	private HorizontalGroup horizontalGroup;
	private final int NAVBAR_HIGH;
	private final int NAVBAR_WITH;

	/**
	 * Konstruktor.
	 */
	public NavBar() {
		if (GameManagerFactory.getInstance().getPlatform().getOrientation() == Orientation.Landscape) {
			NAVBAR_HIGH = (int) (this.height * 0.25);
			NAVBAR_WITH = (int) (this.width);
		} else {
			NAVBAR_HIGH = (int) (this.height * 0.14);
			NAVBAR_WITH = (int) (this.width);
		}

		horizontalGroup = new HorizontalGroup();

		horizontalGroup.reverse();
		horizontalGroup.space((float) (this.width * 0.005));
		horizontalGroup.setHeight(NAVBAR_HIGH);
		horizontalGroup.setWidth(NAVBAR_WITH);
		setToTop();
		this.addActor(horizontalGroup);
		this.pack();
	}

	/**
	 * Leifert das Parent Overlay Object zurück. Wenn die NavigationBar nicht
	 * auf einem Overlay liegt wird null zurück gegeben. Daher sollte die Navbar
	 * immer auf einem Overlay liegen.
	 * 
	 * @return Overlay
	 */
	public final Overlay getOverlay() {
		if (this.getParent() == null) {
			return null;
		}
		if (!(this.getStage() instanceof Overlay)) {
			return null;
		}
		return (Overlay) this.getStage();
	}

	/**
	 * Setzt die Navigationsleiste nach oben.
	 */
	public final void setToTop() {
		this.horizontalGroup.setY(this.height - NAVBAR_HIGH);
	};

	/**
	 * Setzt die Navigationsleiste nach unten.
	 */
	public final void setToButton() {
		this.horizontalGroup.setY(horizontalGroup.getHeight());
	}

	/**
	 * Fügt ein neuen NavbarButton zur Navbar hinzu.
	 * 
	 * @param btn
	 *            fügt einen {@link NavbarButton} hinzu
	 */
	public final void addButton(final NavbarButton btn) {

		this.horizontalGroup.addActor(btn);
		this.horizontalGroup.layout();

	}

	/**
	 * Fügt ein neuen NavbarButton zur Navbar hinzu.
	 * 
	 * @param btn
	 *            {@link NavbarButton}
	 * @param index
	 *            {@link Integer} index andem der Button hinzugefügt werden
	 *            soll.
	 */
	public final void addButtonAt(final NavbarButton btn, final int index) {

		this.horizontalGroup.addActorAt(index, btn);
		this.horizontalGroup.layout();

	}
}

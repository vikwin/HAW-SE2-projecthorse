package com.haw.projecthorse.lootmanager.popups;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.haw.projecthorse.assetmanager.AssetManager;
import com.haw.projecthorse.level.util.overlay.popup.Popup;
import com.haw.projecthorse.level.util.swipehandler.SwipeListener;
import com.haw.projecthorse.lootmanager.Loot;

public class LootPopup extends Popup {

	public LootPopup(ArrayList<Loot> loots) {
		super();

		Label label = createLabel("Deine gesammelten Gegenstaende");
//		label.setWidth(popupWidth - 80);
//		label.setWrap(true);
//		label.layout();
		addActor(label);

		LootDisplay button = new LootDisplay(loots, (int)(popupHeigh / 2.5),
				popupWidth - 80);
		addActor(button);
		
		Button ok = createButton("OK");
		ok.addListener(new ChangeListener() {
			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				LootPopup.this.getOverlay().disposePopup();
				event.cancel();
			}
		});
		addActor(ok);
	}

	private class LootDisplay extends Group {
		private ArrayList<Loot> lootList;
		private int i, max;
		private float imageX, imageY, imageWidth, imageHeight;
		private Drawable currentImage = null;
		private boolean refreshed; // zeigt an, ob sich der Index vom Image
									// ge�ndert hat und somit die Daten daf�r
									// neu berechnet werden m�ssen

		public LootDisplay(ArrayList<Loot> loots, int lootHeight, int lootWidth) {
			// Instanzvariable instazieren
			lootList = new ArrayList<Loot>(loots);
			i = 0;
			max = lootList.size();
			refreshed = false;

			setBounds(getX(), getY(), lootWidth, lootHeight);
			createArrowButtons();
			addSwipeListener();
		}

		private void addSwipeListener() {
			addListener(new SwipeListener() {
				@Override
				protected boolean handleSwiped(SwipeEvent event, Actor actor) {
					swiped(event, actor);
					return true;
				}

				@Override
				public void swiped(SwipeEvent event, Actor actor) {
					switch (event.getDirection()) {
					case LEFT:
						nextLoot();
						break;
					case RIGHT:
						prevLoot();
						break;
					default:
						break;
					}

					// Da wir das Popup sind, sollten wir das Event canceln, um
					// ein Weiterreichen an die unterliegendes Stages zu
					// vermeiden
					event.cancel(); // TODO: Funktioniert so leider noch nicht!
				}
			});
		}

		private void createArrowButtons() {
			ImageButton left = new ImageButton(new TextureRegionDrawable(
					AssetManager.getTextureRegion("loot", "arrow_left")));
			left.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					prevLoot();
				}
			});
			left.setHeight(getHeight() / 4);
			left.setWidth(getHeight() / 4);

			left.setPosition(0, (getHeight() - left.getHeight()) / 2);
			addActor(left);

			ImageButton right = new ImageButton(new TextureRegionDrawable(
					AssetManager.getTextureRegion("loot", "arrow_right")));
			right.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					nextLoot();
				}
			});
			right.setHeight(getHeight() / 4);
			right.setWidth(getHeight() / 4);

			right.setPosition(getWidth() - right.getWidth(),
					(getHeight() - right.getHeight()) / 2);
			addActor(right);
		}

		private void nextLoot() {
			i = (i + 1) % max;
			refreshed = false;
		}

		private void prevLoot() {
			i = (i == 0) ? max - 1 : i - 1;
			refreshed = false;
		}

		private void refreshImage() {
			currentImage = lootList.get(i).getImage();
			
			imageWidth = (currentImage.getMinWidth() > getWidth()) ? getWidth() : currentImage.getMinWidth();
			imageHeight = (currentImage.getMinHeight() > getHeight()) ? getHeight() : currentImage.getMinHeight();
			
			imageX = getX() + ((getWidth() - imageWidth) / 2);
			imageY = getY() + ((getHeight() - imageHeight) / 2);

			refreshed = true;
		}

		@Override
		public void draw(Batch batch, float parentAlpha) {
			if (!refreshed) {
				refreshImage();
			}

			currentImage.draw(batch, imageX, imageY,
					imageWidth, imageHeight);
			
			super.draw(batch, parentAlpha);
		}
	}
}
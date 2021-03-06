package com.haw.projecthorse.level.game.applerun;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.haw.projecthorse.assetmanager.AssetManager;
import com.haw.projecthorse.level.game.Game;

/**
 * AppleRun Game.
 * 
 * @author Lars
 * @version 1.0
 */

public class AppleRun extends Game {
	private Gamestate gamestate;
	private Music music;

	/**
	 * Constructor.
	 */
	public AppleRun() {
		AssetManager.loadMusic("AppleRun");
		AssetManager.loadSounds("AppleRun");
		music = audioManager.getMusic("AppleRun", "musicloop.mp3");
		music.setLooping(true);
		gamestate = new Gamestate(this.getViewport(), this.getSpriteBatch(), width, height, this.chest, this.overlay);
		music.play();
	}

	@Override
	protected void doRender(final float delta) {
		Gdx.gl.glClearColor(0.8f, 0.9f, 0.99f, 1); // Hintergrund malen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gamestate.update(delta);
	}

	@Override
	protected void doDispose() {
		music.stop();
		gamestate.dispose();
	}

	@Override
	protected void doResize(final int width, final int height) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doShow() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doHide() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doPause() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doResume() {
		// TODO Auto-generated method stub

	}

}

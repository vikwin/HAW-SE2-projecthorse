package com.haw.projecthorse.audiomanager;

import com.badlogic.gdx.audio.Music;
import com.haw.projecthorse.assetmanager.AssetManager;

public class ManagedMusic implements Music{

	private Music internal;
	private float desiredVolume;
	boolean muted;
	private AudioManagerImpl manager; 
	
	public ManagedMusic(String levelId, String name, boolean muted, AudioManagerImpl manager) {
		internal = AssetManager.getMusic(levelId, name);
		this.muted = muted;
		desiredVolume = 1f;
		this.manager = manager;
		setMuted(muted);
	}
	
	void setMuted(boolean state) {
		muted = state;
		if (muted)
			internal.setVolume(0f);
		else
			internal.setVolume(desiredVolume);
		
	}

	@Override
	public void play() {
		internal.play();
		
	}
	
	@Override
	public void stop() {
		internal.stop();

	}

	@Override
	public void pause() {
		internal.pause();

	}


	@Override
	public boolean isPlaying() {
		return internal.isPlaying();
	}

	@Override
	public void setLooping(boolean isLooping) {
		internal.setLooping(isLooping);
		
	}

	@Override
	public boolean isLooping() {
		return internal.isLooping();
	}

	@Override
	public void setVolume(float volume) {
		desiredVolume = volume;
		if (!muted)
			internal.setVolume(volume);
		
	}

	@Override
	public float getVolume() {
		return desiredVolume;
	}

	@Override
	public void setPan(float pan, float volume) {
		desiredVolume = volume;
		if (muted)
			internal.setPan(pan, 0f);
		else
			internal.setPan(pan, volume);
	}

	@Override
	public float getPosition() {
		return internal.getPosition();
	}

	@Override
	public void dispose() {
		manager.remove(this);
		internal.dispose();
		
	}

	@Override
	public void setOnCompletionListener(OnCompletionListener listener) {
		internal.setOnCompletionListener(listener);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((internal == null) ? 0 : internal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagedMusic other = (ManagedMusic) obj;
		if (internal == null) {
			if (other.internal != null)
				return false;
		} else if (!internal.equals(other.internal))
			return false;
		return true;
	}
	
	

}
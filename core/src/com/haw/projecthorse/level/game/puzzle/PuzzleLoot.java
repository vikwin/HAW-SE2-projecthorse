package com.haw.projecthorse.level.game.puzzle;

import com.haw.projecthorse.lootmanager.Loot;
import com.haw.projecthorse.lootmanager.LootImage;

/**
 * 
 * @author Masha
 * @version 1.0
 */
public class PuzzleLoot extends Loot {

	private LootImage image;
	
/**
 * 
 * @param name
 * @param description
 * @param imageName
 */
	public PuzzleLoot(final String name, final String description, final String imageName) {
		super(name, description);
		this.image = new LootImage("puzzle", imageName);
	}

	@Override
	protected LootImage getLootImage() {
		return image;
	}

	@Override
	protected int doHashCode() {
		return ((this.image == null) ? 0 : this.image.hashCode());
	}

	@Override
	protected boolean doEquals(final Object other) {
		// PuzzleLoot o = (PuzzleLoot)other;
		// return imageName.equals(o.imageName);
		if (this.image == null) {
			if (((PuzzleLoot) other).image != null) {
				return false;
			}
		} else if (!this.image.equals(((PuzzleLoot) other).image)) {
			return false;
		}
		return true;

	}

	@Override
	public String getCategory() {
		return "Puzzle";
	}

}

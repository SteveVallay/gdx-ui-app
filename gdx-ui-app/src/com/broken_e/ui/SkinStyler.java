package com.broken_e.ui;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * just an interface to implement for a class that includes code to add styles to your skin programmatically. optional.
 * 
 * @author trey miller
 * 
 */
public interface SkinStyler {

	/** called in UiApp#create to add styles to your skin programmatically. */
	public void styleSkin(Skin skin, TextureAtlas atlas);
}

package com.broken_e.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ColoredTextureRegionDrawable extends TextureRegionDrawable {
	public final Color color = new Color(Color.WHITE);

	public ColoredTextureRegionDrawable(TextureRegion region, Color color) {
		super(region);
		this.color.set(color);
	}

	@Override
	public void draw(SpriteBatch batch, float x, float y, float width, float height) {
		float a = batch.getColor().a;
		batch.setColor(color.r, color.g, color.b, color.a * a);
		super.draw(batch, x, y, width, height);
	}
}
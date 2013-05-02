package com.broken_e.test.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.broken_e.test.ui.TestApp;

public class Mob extends Actor {

	private TextureRegion region;
	private Sprite sprite;

	public Mob(TestApp app) {
		sprite = new Sprite(app.skin.getAtlas().findRegion("white-pixel"));
		setBounds(0f, 0f, 1f, 1f);
		setColor(Color.WHITE);
		this.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Mob.this.fire(Pools.obtain(MobTouchedEvent.class));
				return false;
			}
		});
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		Color color = getColor();
		sprite.setColor(color.r, color.g, color.b, color.a * parentAlpha);
		sprite.setScale(getScaleX(), getScaleY());
		sprite.setRotation(getRotation());
		sprite.setOrigin(getOriginX(), getOriginY());
		sprite.setBounds(getX(), getY(), getWidth(), getHeight());
		sprite.draw(batch);
	}

	public static class MobTouchedEvent extends Event {
	}
}

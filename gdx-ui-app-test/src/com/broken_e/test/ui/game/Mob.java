package com.broken_e.test.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.broken_e.test.ui.TestApp;

public class Mob extends Actor {

	private Sprite sprite = new Sprite();
	private float speed;
	float accum;

	/**
	 * so that it can have a no-arg constructor and be poolable. To be optimized still...
	 */
	public Mob(TextureRegion region, float speed) {
		this.speed = speed;
		accum = 0;
		clearActions();
		sprite.setRegion(region);

		setBounds(MathUtils.random(16f), MathUtils.random(12f), 1.2f, 1.2f);
		setColor(Color.WHITE);
		this.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Mob.this.fire(mobTouchedEventPool.obtain());
				return false;
			}
		});
	}
	

	@Override
	public void act(float delta) {
		if (getActions().size == 0) {
			addAction(Actions.color(Color.RED, speed));
			newMoveTo();
		}
		accum += delta;
		if (accum > speed)
			fire(mobExplodeEventPool.obtain());
		super.act(delta);
	}

	private void newMoveTo() {
		Actor p = getParent();
		float px = p.getX();
		float py = p.getY();
		float x = MathUtils.random(px, p.getWidth() - px - getWidth());
		float y = MathUtils.random(py, p.getHeight() - py - getHeight());
		addAction(Actions.sequence(Actions.moveTo(x, y, 2f), Actions.run(moveToRunnable)));
	}

	private Runnable moveToRunnable = new Runnable() {
		@Override
		public void run() {
			newMoveTo();
		}
	};

	private final float buf = .1f;

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;
		return x >= -buf && x < getWidth() + buf && y >= -buf && y < getHeight() + buf ? this : null;
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

	/** dummy class for specifying the type of event being a mob touched */
	public static class MobTouchedEvent extends Event {
	}
	private Pool<MobTouchedEvent> mobTouchedEventPool = new Pool<MobTouchedEvent>(){
		@Override
		protected MobTouchedEvent newObject() {
			return new MobTouchedEvent();
		}
	};
	
	public static class MobExplodeEvent extends Event {
	}
	private Pool<MobExplodeEvent> mobExplodeEventPool = new Pool<MobExplodeEvent>(){
		@Override
		protected MobExplodeEvent newObject() {
			return new MobExplodeEvent();
		}
	};
}

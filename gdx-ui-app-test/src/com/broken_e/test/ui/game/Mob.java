package com.broken_e.test.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;

/**
 * the main (only) game object
 * 
 * @author trey miller
 * 
 */
public class Mob extends Actor {

	private Sprite sprite = new Sprite();
	private float speed;
	float accum;

	public Mob() {
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Mob.this.fire(mobTouchedEventPool.obtain());
				return false;
			}
		});
	}

	/** resets the mob instead of the constructor, for poolability */
	public Mob init(TextureRegion region, float speed) {
		this.speed = speed;
		accum = 0;
		sprite.setRegion(region);
		setBounds(MathUtils.random(16f), MathUtils.random(12f), 1.2f, 1.2f);
		setColor(Color.WHITE);
		clearActions();
		return this;
	}

	@Override
	public void act(float delta) {
		/** would do this in init except there's no parent at that point for newMoveTo() */
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
		if (x > getX() && !sprite.isFlipX())
			sprite.flip(true, false);
		else if (x < getX() && sprite.isFlipX())
			sprite.flip(true, false);
	}

	private Runnable moveToRunnable = new Runnable() {
		@Override
		public void run() {
			newMoveTo();
		}
	};

	private final float buf = .1f;

	/** gives the mobs a bit of padding for click so if you click slightly outside the bounds it still works */
	@Override
	public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled)
			return null;
		return x >= -buf && x < getWidth() + buf && y >= -buf && y < getHeight() + buf ? this : null;
	}

	/** just sets the sprite to this actor's stuff and then draws it */
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

	/** pool for the MobTouchedEvent */
	private static Pool<MobTouchedEvent> mobTouchedEventPool = new Pool<MobTouchedEvent>() {
		@Override
		protected MobTouchedEvent newObject() {
			return new MobTouchedEvent();
		}
	};

	/** dummy class for specifying the type of event being a mob exploded */
	public static class MobExplodeEvent extends Event {
	}

	/** pool for the MobExplodeEvent */
	private static Pool<MobExplodeEvent> mobExplodeEventPool = new Pool<MobExplodeEvent>() {
		@Override
		protected MobExplodeEvent newObject() {
			return new MobExplodeEvent();
		}
	};
}

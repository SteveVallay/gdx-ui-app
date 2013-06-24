package com.broken_e.test.ui.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pools;
import com.broken_e.test.ui.game.MobRemoveEvent.MobExplodeEvent;
import com.broken_e.test.ui.game.MobRemoveEvent.MobTouchedEvent;

/**
 * the main (only) game object
 * 
 * @author trey miller
 * 
 */
public class Mob extends Actor {

	private final Sprite sprite = new Sprite();
	private float speed;
	private float accum;
	private boolean isRemoving = false;

	public Mob() {
		addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (!isRemoving) {
					Mob.this.fire(Pools.obtain(MobTouchedEvent.class));
					isRemoving = true;
				}
				return false;
			}
		});
	}

	/** resets the mob instead of the constructor, for poolability */
	public Mob init(TextureRegion region, float speed) {
		this.speed = speed;
		accum = 0;
		isRemoving = false;
		sprite.setRegion(region);
		setBounds(MathUtils.random(16f), MathUtils.random(12f), 1.2f, 1.2f);
		setScale(1f);
		setRotation(0f);
		setOrigin(getWidth() * .5f, getHeight() * .5f);
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
		if (accum > speed && !isRemoving) {
			fire(Pools.obtain(MobExplodeEvent.class));
			isRemoving = true;
		}
		super.act(delta);
	}

	private void newMoveTo() {
		Actor p = getParent();
		Action moveAction;
		if (p == null) {
			moveAction = Actions.delay(.5f);
		} else {
			float px = p.getX();
			float py = p.getY();
			float x = MathUtils.random(px, p.getWidth() - px - getWidth());
			float y = MathUtils.random(py, p.getHeight() - py - getHeight());
			moveAction = Actions.moveTo(x, y, 2f);
			if (x > getX() && !sprite.isFlipX())
				sprite.flip(true, false);
			else if (x < getX() && sprite.isFlipX())
				sprite.flip(true, false);
		}
		addAction(Actions.sequence(moveAction, Actions.run(moveToRunnable)));
	}

	private Runnable moveToRunnable = new Runnable() {
		@Override
		public void run() {
			newMoveTo();
		}
	};

	private static final float buf = .1f;

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
}

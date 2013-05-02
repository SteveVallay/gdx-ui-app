package com.broken_e.test.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.broken_e.test.ui.TestApp;
import com.broken_e.test.ui.game.Mob.MobTouchedEvent;

public class GameRoot extends Group {

	private TestApp app;

	private OrthographicCamera cam = new OrthographicCamera();
	private float screenW = Gdx.graphics.getWidth();
	private float screenH = Gdx.graphics.getHeight();
	private Vector3 v3 = new Vector3();
	private Matrix4 tmpMatrix4 = new Matrix4();

	public GameRoot(TestApp app) {
		this.app = app;
	}

	/** used because actors usually need to run the full constructor before adding things to them */
	public GameRoot init() {
		this.setSize(16, (screenH / screenW) * 16);
		cam.setToOrtho(false, getWidth(), getHeight());
		cam.update();
		this.addActor(new Mob(app));
		this.addListener(new EventListener() {
			@Override
			public boolean handle(Event event) {
				if (event instanceof MobTouchedEvent) {
					Actor actor = event.getTarget();
					if (actor instanceof Mob) {
						event.stop();
						actor.setColor(Color.BLUE);
					}
				}
				return false;
			}
		});
		return this;
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		v3.set(x, screenH - y, 0f);
		cam.unproject(v3);
		return super.hit(v3.x, v3.y, touchable);
	}

	@Override
	public void act(float delta) {

		super.act(delta);
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		tmpMatrix4.set(batch.getProjectionMatrix());
		batch.setProjectionMatrix(cam.combined);
		super.draw(batch, parentAlpha);
		batch.setProjectionMatrix(tmpMatrix4);
	}
}

package com.broken_e.test.ui.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;

public class GameRoot extends Group {

	private OrthographicCamera cam = new OrthographicCamera();
	Matrix4 tmpMatrix4 = new Matrix4();

	public GameRoot() {
		cam.setToOrtho(false, 16, (Gdx.graphics.getHeight() / Gdx.graphics.getWidth()) * 16);
	}

	/** used because actors usually need to run the full constructor before adding to them */
	public GameRoot init() {
		
		return this;
	}

	public void draw(SpriteBatch batch, float parentAlpha) {
		tmpMatrix4.set(batch.getProjectionMatrix());
		batch.setProjectionMatrix(cam.combined);
		super.draw(batch, parentAlpha);
		batch.setProjectionMatrix(tmpMatrix4);
	}

}

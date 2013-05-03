package com.broken_e.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public abstract class BaseScreen extends Group {

	protected final UiApp app;
	protected final Table mainTable = new Table();
	public static float defaultPad;

	public BaseScreen(UiApp app) {
		this.app = app;
		defaultPad = Math.round(Math.max(app.h, app.w) * .02f);
		mainTable.defaults().pad(defaultPad);
		mainTable.size(app.w, app.h);
		this.addActor(mainTable);
	}

	public BaseScreen show() {
		return this;
	}

	/** override for custom screen transitions, otherwise current screen just slides to the left */
	protected void screenOut() {
		float xPos = -app.w;
		MoveToAction action = Actions.moveTo(xPos, 0f, app.dur);
		addAction(action);
	}

	public abstract void onBackPress();

	public static int getAdPxW() {
		return (int) (320f * Gdx.graphics.getDensity());
	}

	public static int getAdPxH() {
		return (int) (50f * Gdx.graphics.getDensity());
	}

	public void hide() {
	}
}

package com.broken_e.test.ui;

import com.badlogic.gdx.Gdx;
import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.UiApp;

public class MainScreen extends BaseScreen {

	public MainScreen(UiApp app) {
		super(app);
	}

	@Override
	public void onBackPress() {
		Gdx.app.exit();
	}

}

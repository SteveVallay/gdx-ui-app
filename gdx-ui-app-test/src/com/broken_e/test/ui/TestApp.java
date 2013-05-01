package com.broken_e.test.ui;

import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.SkinStyler;
import com.broken_e.ui.UiApp;

public class TestApp extends UiApp {
	
	private GameScreen gameScreen;

	@Override
	protected String atlasPath() {
		return "data/tex.atlas";
	}

	@Override
	protected String skinPath() {
		return null;
	}

	@Override
	protected SkinStyler getSkinStyler() {
		return new Styles();
	}

	@Override
	protected BaseScreen getFirstScreen() {
		return new MainScreen(this);
	}

	public GameScreen getGameScreen(){
		if (gameScreen == null)
			gameScreen = new GameScreen(this);
		return gameScreen;
	}

}

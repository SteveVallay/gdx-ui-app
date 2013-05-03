package com.broken_e.test.ui;

import com.broken_e.test.ui.game.Stats;
import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.UiApp;

public class GameOverScreen extends BaseScreen{

	public GameOverScreen(UiApp app, Stats stats) {
		super(app);
		mainTable.setSkin(app.skin);
		mainTable.add("Game Over!");
		mainTable.row();
		mainTable.add("Points: " + stats.getPoints());
	}

	@Override
	public void onBackPress() {
		app.switchScreens(new MainScreen(app));
	}

}

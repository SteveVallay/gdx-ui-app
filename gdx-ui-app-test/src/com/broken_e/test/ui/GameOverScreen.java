package com.broken_e.test.ui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.broken_e.test.ui.game.Stats;
import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.UiApp;

public class GameOverScreen extends BaseScreen{

	public GameOverScreen(final UiApp app, Stats stats) {
		super(app);
		mainTable.setSkin(app.skin);
		mainTable.add("Game Over!");
		mainTable.row();
		mainTable.add("Points: " + stats.getPoints());
		mainTable.addListener(new ClickListener(){
			public void clicked (InputEvent event, float x, float y) {
				app.switchScreens(new MainScreen(app));
			}
		});
	}

	@Override
	public void onBackPress() {
		app.switchScreens(new MainScreen(app));
	}

}

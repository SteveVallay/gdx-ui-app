package com.broken_e.test.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.UiApp;

public class MainScreen extends BaseScreen {

	public MainScreen(final UiApp app) {
		super(app);
		
		Label label = new Label("Test App", app.skin);
		final TextButton button = new TextButton("Start", app.skin);
		
		button.addListener(new ClickListener(){
			public void clicked (InputEvent event, float x, float y) {
				app.switchScreens(((TestApp) app).getGameScreen());
				button.setChecked(false);
			}
		});
		
		this.mainTable.add(label);
		this.mainTable.row();
		this.mainTable.add(button);
	}

	@Override
	public void onBackPress() {
		Gdx.app.exit();
	}

}

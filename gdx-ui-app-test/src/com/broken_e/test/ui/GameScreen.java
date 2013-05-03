package com.broken_e.test.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;
import com.broken_e.test.ui.game.GameRoot;
import com.broken_e.test.ui.game.Stats;
import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.UiApp;

public class GameScreen extends BaseScreen {

	private Label lblPoints;
	private Label lblStrikes;
	
	public GameScreen(UiApp app) {
		super(app);
		
		mainTable.addActor(new GameRoot(this, app.atlas).init());
		
		Label lblPointText = new Label("Points: ", app.skin);
		lblPointText.setTouchable(Touchable.disabled);
		lblPoints = new Label("0", app.skin);
		lblPoints.setTouchable(Touchable.disabled);
		
		lblStrikes = new Label("", app.skin);
		
		mainTable.row().left().top();
		mainTable.add(lblPointText);
		mainTable.add(lblPoints).expandX().fill();
		mainTable.add(lblStrikes);
		mainTable.row();
		mainTable.add().expand().fill().colspan(3);
//		mainTable.debug();
	}

	@Override
	public void onBackPress() {
		app.switchScreens(new MainScreen(app));
	}

	public void pointsChanged(StringBuilder points) {
		lblPoints.setText(points);
	}

	public void mobExploded(StringBuilder totalStrikes) {
		lblStrikes.setText(totalStrikes);
	}

	public void gameOver(Stats stats) {
		app.switchScreens(new GameOverScreen(app, stats));
		
	}

}

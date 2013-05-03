package com.broken_e.ui;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public abstract class UiApp implements ApplicationListener {
	public Stage stage;
	protected TextureAtlas atlas;
	public Skin skin;
	public static InputMultiplexer inputs = new InputMultiplexer();

	private BaseScreen currentScreen, nextScreen;

	public float w, h;

	private float durAccum = -420f;
	protected float dur = .333f;

	private final Color clearColor = new Color(Color.BLACK);

	@Override
	public void create() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		stage = new Stage();
		atlas = new TextureAtlas(atlasPath());
		
		skin = new Skin(atlas);
		String skinPath = skinPath();
		if (skinPath != null)
			skin.load(Gdx.files.internal(skinPath));
		SkinStyler skinStyler = getSkinStyler();
		if (skinStyler != null)
			skinStyler.styleSkin(skin, atlas);
		
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		stage.addListener(new InputListener() {
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.BACK) {
					currentScreen.onBackPress();
				}
				return false;
			}
		});
		currentScreen = getFirstScreen().show();
		stage.addActor(currentScreen);
		Gdx.input.setInputProcessor(inputs);
		inputs.addProcessor(stage);
	}

	/** provide the path to the atlas */
	protected abstract String atlasPath();
	
	/** provide the path to the skin file (optional).  If no path is provided, an empty skin is created. */
	protected abstract String skinPath();

	/** add the skin styles here (optional, can be null) */
	protected abstract SkinStyler getSkinStyler();

	/** specify the screen to be loaded at the beginning */
	protected abstract BaseScreen getFirstScreen();

	

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		if (durAccum > 0f) {
			durAccum -= delta;
			if (durAccum <= 0f) {
				currentScreen.hide();
				currentScreen.remove();
//				Gdx.app.log("currentScreen", String.valueOf(currentScreen.getClass()) + "\nparent: " + currentScreen.getParent());
				currentScreen = nextScreen;
				currentScreen.setTouchable(Touchable.enabled);
				currentScreen.setPosition(0f, 0f);
				nextScreen = null;
			}
		}

		Gdx.gl.glClearColor(clearColor.r, clearColor.g, clearColor.b, clearColor.a);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		stage.draw();
	}

	public void setClearColor(Color color) {
		clearColor.set(color);
	}

	public void switchScreens(BaseScreen screen) {
		durAccum = dur;
		nextScreen = screen;
		nextScreen.setTouchable(Touchable.disabled);
		nextScreen.show();
		stage.addActor(nextScreen);
		if (currentScreen != null) {
			currentScreen.screenOut();
			currentScreen.setTouchable(Touchable.disabled);
			stage.addActor(currentScreen);
		}
	}

	@Override
	public void resize(int width, int height) {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		stage.setViewport(w, h, false);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
	}


}

package com.broken_e.test.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.broken_e.ui.ColoredTextureRegionDrawable;
import com.broken_e.ui.SkinStyler;

public class Styles implements SkinStyler {

	@Override
	public void styleSkin(Skin skin, TextureAtlas atlas) {
//		float s = Math.min(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//		int smallFontSize = (int) (s / 16f);
//		int bigFontSize = (int) (s / 11f);
//
//		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("data/MAIAN.TTF"));
//		BitmapFont fontDefault = gen.generateFont(smallFontSize);
//		BitmapFont fontBig = gen.generateFont(bigFontSize);
//		gen.dispose();
		BitmapFont font = new BitmapFont(Gdx.files.internal("data/lucida-console-21.fnt"), false);
		skin.add("default", font);
//		skin.add("big", fontBig);

		Color darkBlue = new Color(0f, 0f, .3f, 1f);

		NinePatchDrawable btn1up = new NinePatchDrawable(atlas.createPatch("patchThick"));
		NinePatchDrawable btn1down = new NinePatchDrawable(atlas.createPatch("patchThickDown"));
		NinePatch window1patch = atlas.createPatch("window1");
		skin.add("btn1up", btn1up);
		skin.add("btn1down", btn1down);
		skin.add("window1", window1patch);
		skin.add("white-pixel", atlas.findRegion("white-pixel"), TextureRegion.class);

		LabelStyle lbs = new LabelStyle();
		lbs.font = font;
		lbs.fontColor = Color.WHITE;
		skin.add("default", lbs);

		TextButtonStyle tbs = new TextButtonStyle(btn1up, btn1down, btn1down, font);
		tbs.fontColor = darkBlue;
		tbs.pressedOffsetX = Math.round(1f * Gdx.graphics.getDensity());
		tbs.pressedOffsetY = tbs.pressedOffsetX * -1f;
		skin.add("default", tbs);

	}

}

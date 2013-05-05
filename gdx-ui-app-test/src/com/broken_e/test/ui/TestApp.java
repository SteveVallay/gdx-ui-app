package com.broken_e.test.ui;

import com.broken_e.ui.BaseScreen;
import com.broken_e.ui.SkinStyler;
import com.broken_e.ui.UiApp;

/**
 * an example game using the gdx-ui-app stuff.
 * 
 * @author trey miller
 * 
 */
public class TestApp extends UiApp {
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
}

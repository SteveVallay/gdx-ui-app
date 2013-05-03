package com.broken_e.ui;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.broken_e.test.ui.TestApp;

public class DesktopStarter {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "broken-e gdx-ui-app test";
		cfg.useGL20 = false;
		{
			/** set resolution here */
			int i = 1; 
			boolean isLandscape = true;

			int[][] res = new int[8][];
			res[0] = new int[] { 320, 240 };
			res[1] = new int[] { 480, 320 };
			res[2] = new int[] { 720, 480 };
			res[3] = new int[] { 800, 480 };
			res[4] = new int[] { 854, 480 };
			res[5] = new int[] { 1024, 600 };
			res[6] = new int[] { 1280, 768 };
			res[7] = new int[] { 1280, 800 };

			cfg.width = res[i][isLandscape ? 0 : 1];
			cfg.height = res[i][isLandscape ? 1 : 0];
		}
		new LwjglApplication(new TestApp(), cfg);
	}
}

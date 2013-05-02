package com.broken_e.test.ui.game;

import com.broken_e.utils.KeyValue;
import com.badlogic.gdx.utils.StringBuilder;

public class Stats {

	public KeyValue<String> keyValue = new KeyValue<String>();

	static public final String strPOINTS = "Points";
	
	private final StringBuilder tmpSB = new StringBuilder();

	public Stats() {
		if (!keyValue.containsKey(strPOINTS, false))
			keyValue.put(strPOINTS, 0);
	}

	public void pointUp() {
		keyValue.addToValue(strPOINTS, 1);
	}
	
	/** not safe to continue using the returned StringBuilder */
	public StringBuilder getPoints(){
		tmpSB.setLength(0);
		tmpSB.append(keyValue.getValue(strPOINTS));
		return tmpSB;
	}

}

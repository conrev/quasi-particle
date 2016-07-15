package com.quasiparticle.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.quasiparticle.game.quasiparticle;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "Quasi - Particle";
		config.vSyncEnabled = false;
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new quasiparticle(), config);
	}
}

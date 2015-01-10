package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
	    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "Super Smash Bros. K";
	    config.width = 1200;
	    config.height = 720;
	    new LwjglApplication(new Smash(), config);
	}
}

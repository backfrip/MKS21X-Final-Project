package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
	    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	    config.title = "Super Smash Bros. K";
	    config.width = 1200;
	    config.height = 720;
	    config.x=-1;
	    config.y=-1;
	    config.foregroundFPS=60;
	    config.backgroundFPS=0;
	    config.addIcon("resource/16px-icon.png", Internal);//I DON'T KNOW IF THIS WORKS
	    new LwjglApplication(new Smash(), config);
	}
}

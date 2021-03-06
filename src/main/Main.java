package main;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.Files;

/**
 * Desktop launcher for Smash game.
 */
public class Main {
    /**
     * Defines application configuration and starts new LwjglApplication.
     * 
     * @param args
     *            Not used
     */
    public static void main(String[] args) {
	LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
	config.title = "Super Smash Bros. K";
	config.width = 1280;
	config.height = 720;
	config.resizable = false;
	config.addIcon("resource/ico/128px-icon.png", Files.FileType.Internal); // Mac
	config.addIcon("resource/ico/32px-icon.png", Files.FileType.Internal); // Linux
	config.addIcon("resource/ico/16px-icon.png", Files.FileType.Internal); // Windows

	new LwjglApplication(new Smash(), config);
    }
}

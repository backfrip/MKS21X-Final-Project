package main;

import screen.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

/**
 * Smash game which utilizes the various screens.
 *
 */
public class Smash extends Game {
    public static Music theme0, theme1;
    public static Menu menu;
    public static InputProcessor inputProcessor;

    /**
     * Creates new Smash game. Menu themes are loaded as static Music.
     */
    public void create() {
	setScreen(new Splash(this));
	theme0 = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/music/menu-theme0.wav"));
	theme0.setLooping(true);

	theme1 = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/music/menu-theme1.wav"));
	theme1.setLooping(true);

	menu = new Menu(this);
	
	inputProcessor = Gdx.input.getInputProcessor();
    }

    @Override
    public void dispose() {
	theme0.dispose();
	theme1.dispose();
	super.dispose();
    }
    
    public static void swapTheme() {
	if (Smash.theme0.isPlaying()) {
	    Smash.theme0.stop();
	    Smash.theme1.play();
	} else {
	    Smash.theme1.stop();
	    Smash.theme0.play();
	}
    }
}

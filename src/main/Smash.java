package main;

import screen.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

/**
 * Smash game which utilizes the various screens.
 *
 */
public class Smash extends Game {
    public static Music theme0, theme1;

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
    }

    @Override
    public void dispose() {
	theme0.dispose();
	theme1.dispose();
	super.dispose();
    }
}

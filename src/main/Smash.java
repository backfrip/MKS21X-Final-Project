package main;

import screen.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

public class Smash extends Game {
    public static Music theme;
    
    public void create() {
	setScreen(new Splash(this));
	theme = Gdx.audio.newMusic(new FileHandle("resource/sound/music/menu-theme.wav"));
	theme.setLooping(true);
    }
    
    @Override
    public void dispose() {
	theme.dispose();
	super.dispose();
    }
}

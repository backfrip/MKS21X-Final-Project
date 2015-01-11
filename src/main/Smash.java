package main;

import screen.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import java.util.Random;

public class Smash extends Game {
    public static Music theme;
    public static Random rand;
    public void create() {
	Random rand=new Random();
	setScreen(new Splash(this));
	theme = Gdx.audio.newMusic(new FileHandle("resource/sound/music/menu-theme"+rand.nextInt(2)+".wav"));
	theme.setLooping(true);
    }
    
    @Override
    public void dispose() {
	theme.dispose();
	super.dispose();
    }
}

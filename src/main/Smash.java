package main;

import screen.*;

import com.badlogic.gdx.Game;

public class Smash extends Game {
    public void create() {
	setScreen(new Splash(this));
    }
}

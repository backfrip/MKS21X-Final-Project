package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Char implements Screen {
    private Smash game;
    
    public Char(Smash gameRef) {
	game = gameRef;
    }

    @Override
    public void render(float arg0) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void hide() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void pause() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void resize(int arg0, int arg1) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void resume() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void show() {
	// TODO Auto-generated method stub
	
    }

}

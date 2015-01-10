package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen {
    private SpriteBatch batch;
    private Texture splash;
    private Smash game;

    public Splash(Smash gameRef) {
	game = gameRef;
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.begin();
	batch.draw(splash, 0, 0);
	batch.end();

	if (Gdx.input.justTouched())
	    game.setScreen(new Splash(game));
    }

    @Override
    public void show() {
	batch = new SpriteBatch();
	splash = new Texture(new FileHandle("resource/test_splash.jpg"));
    }

    @Override
    public void dispose() {
	batch.dispose();
	splash.dispose();
	game.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int arg0, int arg1) {
    }

    @Override
    public void resume() {
    }
}

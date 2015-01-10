package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Splash implements Screen {
    private SpriteBatch batch;
    private Texture splash;
    private Smash game;
    private OrthographicCamera camera;

    public Splash(Smash gameRef) {
	game = gameRef;
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 32, 18);
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	batch.setProjectionMatrix(camera.combined);
	batch.begin();
	batch.draw(splash, 1, 1, 30, 16);
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

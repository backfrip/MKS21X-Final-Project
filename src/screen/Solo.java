package screen;

import main.Smash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

/**
 * Manages solo mode and is displayed between solo matches
 */
public class Solo implements Screen {
    private Smash game;
    private OrthographicCamera camera;
    private Fighter player;
    private int matchNum;

    public Title(Smash gameRef) {
	game = gameRef;
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 32, 18);
	batch = new SpriteBatch();
	batch.setProjectionMatrix(camera.combined);
	player.setDamage(0);
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }
}

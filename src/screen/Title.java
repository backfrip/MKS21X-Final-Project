package screen;

import main.Smash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Displays title screen.
 */
public class Title implements Screen {
    private SpriteBatch batch;
    private Texture title,text;
    private Smash game;
    private OrthographicCamera camera;
    private Music advance;
    private boolean go;
    private int count;

    public Title(Smash gameRef) {
	game = gameRef;
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 32, 18);
batch = new SpriteBatch();
	batch.setProjectionMatrix(camera.combined);
	title = new Texture(new FileHandle("resource/title.jpg"));
	text = new Texture(new FileHandle("resource/title-text.png"));
	advance = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/menu-advance.ogg"));
	go = false;
	count = 0;
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	batch.begin();
	batch.draw(title, 0, 0, 32, 18);
	if (!go) {
	    drawText(50, 100);
	    if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
		go = true;
		advance.play();
	    }
	} else {
	    count++;
	    drawText(5, 10);
	    if (!advance.isPlaying())
		game.setScreen(Smash.menu);
	}
	batch.end();
	count++;
    }
    
    private void drawText(int on, int reset) {
	if (count < on)
	    batch.draw(text, 10, 2, 12, 1);
	else if (count > reset)
	    count = 0;
    }

    @Override
    public void show() {
    }

    @Override
    public void dispose() {
	batch.dispose();
	title.dispose();
	game.dispose();
	advance.dispose();
	text.dispose();
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

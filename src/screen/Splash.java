package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.Tween;

/**
 * Opening splash for game. Should play theme and display various attributions.
 */
public class Splash implements Screen {
    private SpriteBatch batch;
    private Sprite splash;
    private Smash game;
    private TweenManager tweenManager;
    private Music theme;
    private float d;

    /**
     * Creates a new Splash screen. Sets up various resources.
     * 
     * @param gameRef
     *            Game linked to.
     */
    public Splash(Smash gameRef) {
	game = gameRef;

	batch = new SpriteBatch();
	d = 0;

	if (MathUtils.random(200) == 0) {
	    splash = new Sprite(new Texture(new FileHandle(
		    "resource/splash/splash-secret.png")));
	    theme = Gdx.audio.newMusic(new FileHandle(
		    "resource/sound/music/splash-secret.wav"));
	} else {
	    splash = new Sprite(new Texture(new FileHandle(
		    "resource/splash/splash.png")));
	    theme = Gdx.audio.newMusic(new FileHandle(
		    "resource/sound/music/splash-secret.wav"));// placeholder
							       // until we get a
							       // real theme
	}

	splash.setScale((float) 1);
	splash.setPosition((float) 0, (float) 0);

	tweenManager = new TweenManager();
    }

    /**
     * Renders frames of Splash screen. Clears screen, draws SpriteBatch,
     * updates tweenManager, manages screen exit.
     */
    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	batch.begin();
	splash.draw(batch);
	batch.end();

	tweenManager.update(delta);

	if (!theme.isPlaying()) {
	    Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0)
		    .start(tweenManager);
	    d += delta;
	}

	if (d > 3 || Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
	    game.setScreen(new Title(game));
	}
    }

    /**
     * Called when Splash is shown. Plays theme and [currently] manages initial
     * fade in.
     */
    @Override
    public void show() {
	theme.play();
	Tween.registerAccessor(Sprite.class, new SpriteAccessor());
	Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
	Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
    }

    @Override
    public void dispose() {
	batch.dispose();
	theme.dispose();
	game.dispose();
    }

    /**
     * Called when screen is hidden. Stops theme if playing.
     */
    @Override
    public void hide() {
	theme.stop();
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

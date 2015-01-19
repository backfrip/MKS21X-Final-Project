package screen;

import main.*;
import misc.JeremysBox2DTest;
import misc.Test2.Box2DTest2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Main menu of the Smash game.
 */
public class Menu implements Screen {
    private Smash game;
    private ImageButton smash, exit, options, solo;
    private Stage stage;
    private Skin skin;
    private TextureAtlas atlas;

    /**
     * Creates a new Menu screen. Defines and positions various buttons used.
     * 
     * @param gameRef
     *            Game linked to.
     */
    public Menu(Smash gameRef) {
	game = gameRef;

	atlas = new TextureAtlas("resource/main-menu/menu.pack");
	skin = new Skin(atlas);
	smash = new ImageButton(skin.getDrawable("smash"),
		skin.getDrawable("smash-pressed"),
		skin.getDrawable("smash-selected"));
	options = new ImageButton(skin.getDrawable("options"),
		skin.getDrawable("options-pressed"),
		skin.getDrawable("options-selected"));
	solo = new ImageButton(skin.getDrawable("solo"),
		skin.getDrawable("solo-pressed"),
		skin.getDrawable("solo-selected"));
	exit = new ImageButton(skin.getDrawable("quit"),
		skin.getDrawable("quit-pressed"),
		skin.getDrawable("quit-selected"));


	smash.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		smash.toggle();
		game.setScreen(new Box2DTest2(game));
	    }
	});
	options.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		options.toggle();
		game.setScreen(new Settings(game));
	    }
	});
	exit.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	});
	stage = new Stage();
	stage.addActor(exit);
	exit.setX(10 * 4);
	exit.setY(23 * 4);
	stage.addActor(options);
	options.setX(122 * 4);
	options.setY(23 * 4);
	stage.addActor(smash);
	smash.setX(10 * 4);
	smash.setY(58 * 4);
	stage.addActor(solo);
	solo.setX(141 * 4);
	solo.setY(58 * 4);
    }

    /**
     * Renders Menu. Clears screen, processes Stage action, and draws Stage.
     */
    @Override
    public void render(float arg0) {
	float g = (float) 140 / (float) 255;
	Gdx.gl.glClearColor(g, g, g, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	stage.act();
	stage.draw();

	if (Gdx.input.isKeyJustPressed(Keys.T))
	    Smash.swapTheme();
	if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))
	    Gdx.app.exit();
    }

    @Override
    public void dispose() {
	game.dispose();
	stage.dispose();
	atlas.dispose();
	skin.dispose();
    }

    /**
     * Called when Menu screen is showed. Plays one of two themes.
     */
    @Override
    public void show() {
	Gdx.input.setInputProcessor(stage);
	if (!Smash.theme0.isPlaying() && !Smash.theme1.isPlaying()) {
	    if (MathUtils.random(1) == 0)
		Smash.theme0.play();
	    else
		Smash.theme1.play();
	}
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

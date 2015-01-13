package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


/**
 * Main menu of the Smash game.
 */
public class Menu implements Screen {
    private Smash game;
    private ImageButton smash,exit,options,solo;
    private TextButtonStyle style;
    private Stage stage;
    private BitmapFont font;
    private Table table;
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

	font = new BitmapFont();

	style = new TextButtonStyle();
	style.font = font;

	atlas = new TextureAtlas("resource/main-menu/menu.pack");
	skin = new Skin(atlas);
	smash = new ImageButton(skin.getDrawable("smash"),skin.getDrawable("smash-pressed"),skin.getDrawable("smash-selected"));
	options = new ImageButton(skin.getDrawable("options"),skin.getDrawable("options-pressed"),skin.getDrawable("options-selected"));
	solo = new ImageButton(skin.getDrawable("solo"),skin.getDrawable("solo-pressed"),skin.getDrawable("solo-selected"));
	smash.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new Char(game));
	    }
	});

	exit = new ImageButton(skin.getDrawable("quit"),skin.getDrawable("quit-pressed"),skin.getDrawable("quit-selected"));
	exit.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	    });

	table = new Table(skin);
	table.add(smash);
	table.getCell(smash);
	table.add(solo).row();
	table.add(exit);
	table.add(options);
	table.setFillParent(true);
	stage = new Stage();
	stage.addActor(table);
	Gdx.input.setInputProcessor(stage);

    }

    /**
     * Renders Menu. Clears screen, processes Stage action, and draws Stage.
     */
    @Override
    public void render(float arg0) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	stage.act();
	stage.draw();

	if (Gdx.input.isKeyJustPressed(Keys.T))
	    swapTheme();
	
	if (Gdx.input.isKeyJustPressed(Keys.ESCAPE))
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
    public void show() { // At some point, we should switch Menu's
	// initialization to be in Smash, so that one instance
	// can be used consistently.
	if (MathUtils.random(1) == 0)
	    Smash.theme0.play();
	else
	    Smash.theme1.play();
    }

    private void swapTheme() {
	if (Smash.theme0.isPlaying()) {
	    Smash.theme0.stop();
	    Smash.theme1.play();
	} else {
	    Smash.theme1.stop();
	    Smash.theme0.play();
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

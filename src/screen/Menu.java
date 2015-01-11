package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Menu implements Screen {
    private Smash game;
    private Music theme;
    private TextButton test;
    private TextButtonStyle style;
    private Stage stage;
    private BitmapFont font;
    private Table table;

    public Menu(Smash gameRef) {
	game = gameRef;
	theme = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/music/menu-theme.wav"));
	
	
	font = new BitmapFont();
	
	style = new TextButtonStyle();
	style.font = font;
	
	test = new TextButton("Exit Game", style);
	test.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	});
	
	table = new Table();
	table.add(test).row();
	table.setFillParent(true);
	
	stage = new Stage();
	stage.addActor(table);
	
	Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float arg0) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	stage.act();
	stage.draw();
    }

    @Override
    public void dispose() {
	theme.dispose();
	game.dispose();
    }

    @Override
    public void hide() {
	theme.stop();
    }

    @Override
    public void pause() {
	theme.pause();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
	theme.play();
    }

    @Override
    public void show() {
	theme.setLooping(true);
	theme.play();
    }

}

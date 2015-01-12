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
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.files.FileHandle;

public class Menu implements Screen {
    private Smash game;
    private TextButton smash, exit;
    private TextButtonStyle style;
    private Stage stage;
    private BitmapFont font;
    private Table table;
    //private ImageButton smash,solo,exit,options;

    public Menu(Smash gameRef) {
	game = gameRef;
	
	font = new BitmapFont();
	
	style = new TextButtonStyle();
	style.font = font;
	
	smash = new TextButton("Smash", style);
	
	//smash=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(new FileHandle("resource/main-menu/smash.png"))))); doesn't work
	smash.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		game.setScreen(new Char(game));
	    }
	});
	
	exit = new TextButton("Exit Game", style);
	exit.addListener(new ClickListener() {
	    @Override
	    public void clicked(InputEvent event, float x, float y) {
		Gdx.app.exit();
	    }
	});
	
	table = new Table();
	table.add(smash).row();
	table.add(exit).row();
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
	game.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
	Smash.theme0.pause();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
	Smash.theme0.play();
    }

    @Override
    public void show() {
	Smash.theme0.play();
    }

}

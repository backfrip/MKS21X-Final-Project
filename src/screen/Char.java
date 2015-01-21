package screen;

import main.*;
import misc.Test2.Box2DTest2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Char implements Screen {
    private Smash game;
    private Sprite p1cur;
    //private Rectangle p1;
    private SpriteBatch batch;
    private ImageButton p1,p2,adventurer,ready;
    private TextureAtlas atlas;
    private Skin skin;
    private boolean p1good,p2good,done;
    //private Fighter player1,player2;
    private Stage stage;
    private enum Choosing{
	pOne,pTwo,nobody;
    }
    Choosing who;
    
    public Char(Smash gameRef) {
	game = gameRef;/*
	p1cur=new Sprite(new Texture("resource/characterselect/p1.png"));
	p1=new Rectangle(100,100,10,10);
	batch=new SpriteBatch();*/
	atlas = new TextureAtlas("resource/characterselect/chara.pack");
	skin = new Skin(atlas);
	p1 = new ImageButton(skin.getDrawable("p1"));
	p2 = new ImageButton(skin.getDrawable("p2"));
	adventurer = new ImageButton(skin.getDrawable("adventurer"));
	ready = new ImageButton(skin.getDrawable("ready"));
	who = Choosing.nobody;
	done=false;
	p1.addListener(new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
		    who=Choosing.pOne;
		}
	    });
	p2.addListener(new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
		    who=Choosing.pTwo;
		}
	    });
	adventurer.addListener(new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
		    switch(who){
		    case pOne:
			//player1=new Adventurer();
			p1good=true;
		    case pTwo:
			//player2=new Adventurer();
			p2good=true;
		    case nobody:
		    }
		}
	    });
	ready.addListener(new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
		    game.setScreen(new Box2DTest2(game));
		}
	    });
	stage = new Stage();
	stage.addActor(p1);
	p1.setX(5 * 4);
	p1.setY(0);
	stage.addActor(p2);
	p2.setX(20 * 4);
	p2.setY(0 * 4);
	stage.addActor(adventurer);
	adventurer.setX(10 * 4);
	adventurer.setY(110 * 4);

    }

    @Override
    public void render(float arg0) {
	Gdx.gl.glClearColor(140/255f,140/255f, 140/255f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	if(p1good && p2good && !done){
	    stage.addActor(ready);
	    ready.setX(0 * 4);
	    ready.setY(90 * 4);
	    done=true;
	}
	stage.act();
	stage.draw();
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

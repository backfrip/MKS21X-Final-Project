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

public class Title implements Screen {
    private SpriteBatch batch;
    private Texture splash;
    private Smash game;
    private OrthographicCamera camera;
    private Music advance;
    private boolean go;
    private int count;
    private Texture text;
    public Title(Smash gameRef) {
	game = gameRef;
	camera = new OrthographicCamera();
	camera.setToOrtho(false, 32, 18);
    }

    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	count++;
	if (!go) {
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    batch.draw(splash, 0, 0, 32, 18);
	    if(count<60){
		batch.draw(text,10,2,12,1);
		//System.out.println("hi");
	    }else if(count>120){
		count=0;
		//System.out.println("yo");
	    }
	    batch.end();
	    if (Gdx.input.justTouched()
		    || Gdx.input.isKeyJustPressed(Keys.ANY_KEY)) {
		go = true;
		advance.play();
		count=0;
	    }
	} else {
	    if(advance.isPlaying()){
		count++;
		batch.begin();
		batch.draw(splash, 0, 0, 32, 18);
		if(count<5){
		    batch.draw(text,10,2,12,1);
		}else if (count>10){
		    count=0;
		}
		batch.end();
	    }
	    if (!advance.isPlaying())
		game.setScreen(new Menu(game));
	}
    }	

    @Override
    public void show() {
	batch = new SpriteBatch();
	splash = new Texture(new FileHandle("resource/test_splash.jpg"));
	text = new Texture(new FileHandle("resource/splashtext.png"));
	advance = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/menu-advance.ogg"));
	go = false;
	count=0;
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
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }
}

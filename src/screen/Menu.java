package screen;

import main.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;

public class Menu implements Screen {
    private Smash game;
    private Music theme;

    public Menu(Smash gameRef) {
	game = gameRef;
    }

    @Override
    public void dispose() {
	theme.dispose();
	game.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(float arg0) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
	theme = Gdx.audio.newMusic(new FileHandle(
		"resource/sound/music/menu-theme.wav"));
	theme.setLooping(true);
	theme.play();
    }

}

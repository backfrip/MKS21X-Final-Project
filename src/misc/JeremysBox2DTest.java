package misc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class JeremysBox2DTest implements Screen {
    
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private OrthographicCamera camera;
    
    private final float TIMESTEP = 1 / 60f;
    private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
    
	@Override
	public void render(float delta) {
	    Gdx.gl.glClearColor(0, 0, 0, 1);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    
	    debugRenderer.render(world, camera.combined);
	    
	    world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	}
    
    @Override
    public void resize(int width, int height) {
    }
    
    @Override
    public void show() {
	world = new World(new Vector2(0, -9.81f), true);
	debugRenderer = new Box2DDebugRenderer();
	
	camera = new OrthographicCamera(Gdx.graphics.getWidth() / 10, Gdx.graphics.getHeight() / 10);
	
	// body definiton
	BodyDef ballDef = new BodyDef();
	ballDef.type = BodyType.DynamicBody;
	ballDef.position.set(0, 1);
	
	// ball shape
	CircleShape shape = new CircleShape();
	shape.setRadius(.5f);
	
	// fixture definition
	FixtureDef fixtureDef = new FixtureDef();
	fixtureDef.shape = shape;
	fixtureDef.density = 2.5f;
	fixtureDef.friction = .25f;
	fixtureDef.restitution = .75f;
	
	world.createBody(ballDef).createFixture(fixtureDef);
	
	shape.dispose();
    }

    @Override
	public void hide() {
	dispose();
    }
    
    @Override
    public void pause() {
    }
    
    @Override
	public void resume() {
    }
    
    @Override
    public void dispose() {
	world.dispose();
	debugRenderer.dispose();
    }
    
}

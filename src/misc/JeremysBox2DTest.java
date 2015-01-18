package misc;

import main.*;
import screen.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;

public class JeremysBox2DTest implements Screen {
	
	private World world;
	private Box2DDebugRenderer debugRenderer;
	private OrthographicCamera camera;
	
	private final float TIMESTEP = 1 / 60f;
	private final int VELOCITYITERATIONS = 8, POSITIONITERATIONS = 3;
	
    private float speed = 250;
    private Vector2 movement = new Vector2();
    private Body box;
    private Player p1,p2,p3,p4;
    private Boolean fourGuys,threeGuys;
    private Smash gameRef;
    public JeremysBox2DTest(Smash game){
	gameRef=game;
    }
    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
	p1.update();
	p2.update();
	world.step(TIMESTEP, VELOCITYITERATIONS, POSITIONITERATIONS);
	box.applyForceToCenter(movement, true);
	
	camera.update();
	
	debugRenderer.render(world, camera.combined);
    }

	@Override
	public void resize(int width, int height) {
		camera.viewportWidth = width / 25;
		camera.viewportHeight = height / 25;
		camera.update();
	}

	@Override
	public void show() {
	    world = new World(new Vector2(0, -9.81f), true);
	    debugRenderer = new Box2DDebugRenderer();
	    
	    camera = new OrthographicCamera();
	    p1 = new Player(world,0,5,1,1);
	    p2 = new Player(world,0,5,4,2);
	    Gdx.input.setInputProcessor(new InputMultiplexer(new InputAdapter() {
		    
		    @Override
		    public boolean keyDown(int keycode) {
			switch(keycode) {
			case Keys.ESCAPE:
			    gameRef.setScreen(new Menu(gameRef));
			    return true;
			}
			return false;
		    }
		}, p1, p2));
		
		// BALL
		// body definition
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(0, 10);
		
		// ball shape
		CircleShape ballShape = new CircleShape();
		ballShape.setRadius(.5f);
		
		// fixture definition
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = ballShape;
		fixtureDef.density = 2.5f;
		fixtureDef.friction = .25f;
		fixtureDef.restitution = .75f;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
		
		ballShape.dispose();
		
		// GROUND
		// body definition
		bodyDef.type = BodyType.StaticBody;
		bodyDef.position.set(0, 0);
		
		// ground shape
		ChainShape groundShape = new ChainShape();
		groundShape.createChain(new Vector2[] {new Vector2(-50, 0), new Vector2(50, 0)});
		
		// fixture definition
		fixtureDef.shape = groundShape;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = 0;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
		
		groundShape.dispose();
		
		//platform test
		groundShape = new ChainShape();
		groundShape.createChain(new Vector2[] {new Vector2(-5, 10), new Vector2(5, 10)});
		
		// fixture definition
		fixtureDef.shape = groundShape;
		fixtureDef.friction = .5f;
		fixtureDef.restitution = 0;
		
		world.createBody(bodyDef).createFixture(fixtureDef);
		
		groundShape.dispose();
		
		// BOX
		// body definition
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(2.25f, 10);
		
		// box shape
		PolygonShape boxShape = new PolygonShape();
		boxShape.setAsBox(.5f, 1);
		
		// fixture definition
		fixtureDef.shape = boxShape;
		fixtureDef.friction = .75f;
		fixtureDef.restitution = .1f;
		fixtureDef.density = 5;
		
		box = world.createBody(bodyDef);
		box.createFixture(fixtureDef);
		
		boxShape.dispose();
		world.setContactFilter(p1);
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

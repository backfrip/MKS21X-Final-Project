package misc.Test2;

import main.Smash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A Play screen prototype. Handles rendering of Box2D world in which collisions
 * (the basis of all combat) occur. Manages players and their input.
 */
public class Box2DTest2 implements Screen {
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private PlayContactListener cl;
    private OrthographicCamera camera;
    private Smash game;
    private SpriteBatch batch;

    // Final release should have 4 players
    public NewPlayer p1, p2;

    // The bit categories used to classify and manage collisions between
    // different fixtures in the world
    public static final short STAGE = 0x0001;
    public static final short PLAYER = 0x0002;
    public static final short ATTACK_SENSOR = 0x0004;
    public static final short STAGE_BOUNDS = 0x0008;

    public Box2DTest2(Smash gameRef) {
	game = gameRef; // As always

	// Sets up a contact listener to use for detecting collisions between
	// all the different fixtures and deciding what to do when they occur
	cl = new PlayContactListener(this);

	world = new World(new Vector2(0, -9.81f), true); // #creatingtheworld
	world.setContactListener(cl);

	// What we're using to see the objects right now. Ideally, we'd be using
	// sprites, but this is a stand in to observe physics while we work on
	// implementing those
	debugRenderer = new Box2DDebugRenderer();

	// So's as we can sees
	camera = new OrthographicCamera();

	p1 = new NewPlayer(world, 1, 0, -5);
	p2 = new NewPlayer(world, 2, 2, -5);

	// Loads up TestStage's static bodies in the world (and possibly
	// kinematic in the near future (moving platforms FTW))
	new TestStage(world);
	
	batch = new SpriteBatch();
	p2.tintSprite();
    }

    /**
     * Renders the screen.
     */
    @Override
    public void render(float delta) {
	Gdx.gl.glClearColor(0, .5f, .5f, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	getInput(); // What's going w/ the keyboards yo?

	world.step(delta, 8, 3); // Simulate some physics

	// Make physics visible
	debugRenderer.render(world, camera.combined);
	
	batch.setProjectionMatrix(camera.projection);
	
	batch.begin();
	p1.drawSprite(batch);
	p2.drawSprite(batch);
	batch.end();

	camera.update(); // Look at the simulated physics
    }

    /**
     * Gets keyboard input and handles player actions. May be changed into an
     * InputAdapter in the future.
     */
    private void getInput() {
	if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) // Esss-kah-pay!
	    game.setScreen(Smash.menu); // ESC goes to main menu

	// Player 1 Movement
	if (Gdx.input.isKeyJustPressed(Keys.UP))
	    p1.jump();
	if (Gdx.input.isKeyPressed(Keys.RIGHT)
		&& !Gdx.input.isKeyPressed(Keys.LEFT))
	    p1.right();
	if (Gdx.input.isKeyPressed(Keys.LEFT)
		&& !Gdx.input.isKeyPressed(Keys.RIGHT))
	    p1.left();
	if (Gdx.input.isKeyPressed(Keys.DOWN))
	    p1.down();

	// Player 2 Movement
	if (Gdx.input.isKeyJustPressed(Keys.W))
	    p2.jump();
	if (Gdx.input.isKeyPressed(Keys.D) && !Gdx.input.isKeyPressed(Keys.A))
	    p2.right();
	if (Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D))
	    p2.left();
	if (Gdx.input.isKeyPressed(Keys.S))
	    p2.down();

	// Player 1 Attacks
	if (Gdx.input.isKeyJustPressed(Keys.CONTROL_RIGHT)) {
	    if (Gdx.input.isKeyPressed(Keys.UP))
		doAttack(p1, "11");
	    else if (Gdx.input.isKeyPressed(Keys.RIGHT)
		    && !Gdx.input.isKeyPressed(Keys.LEFT))
		doAttack(p1, "12");
	    else if (Gdx.input.isKeyPressed(Keys.LEFT)
		    && !Gdx.input.isKeyPressed(Keys.RIGHT))
		doAttack(p1, "14");
	    else if (Gdx.input.isKeyPressed(Keys.DOWN))
		doAttack(p1, "13");
	    else
		doAttack(p1, "10");
	}

	// Player 2 Attacks
	if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
	    if (Gdx.input.isKeyPressed(Keys.W))
		doAttack(p1, "21");
	    else if (Gdx.input.isKeyPressed(Keys.D)
		    && !Gdx.input.isKeyPressed(Keys.A))
		doAttack(p1, "22");
	    else if (Gdx.input.isKeyPressed(Keys.A)
		    && !Gdx.input.isKeyPressed(Keys.D))
		doAttack(p1, "24");
	    else if (Gdx.input.isKeyPressed(Keys.S))
		doAttack(p1, "23");
	    else
		doAttack(p1, "20");
	}

    }

    /**
     * Simulate an attack. Checks with PlayContactListener to see if there's a
     * play within range, and then "hit()s" them if there is
     * 
     * @param p
     *            the player that's doing the hitting
     * @param attackCode
     *            a generated key for PlayContactListener's Hashtable containing
     *            every attack that could possibly hit
     */
    private void doAttack(NewPlayer p, String attackCode) {
	p.attack(); // Player executes their attack no matter what
	int hit = cl.attackHits(attackCode); // If the attack hits, do hitting
	if (hit == 1)
	    p1.hit(p.pos());
	if (hit == 2)
	    p2.hit(p.pos());

    }

    /**
     * Called when Box2DTest2 is set as Smash's screen.
     */
    @Override
    public void show() {
	Gdx.input.setInputProcessor(Smash.inputProcessor);
    }

    /**
     * Called after show. Gives us an opportunity to change the camera
     * dimensions proportional to the window dimensions.
     */
    @Override
    public void resize(int width, int height) {
	camera.viewportWidth = width / 20;
	camera.viewportHeight = height / 20;
	camera.update();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    /**
     * Take out the trash.
     */
    @Override
    public void dispose() {
	world.dispose();
	debugRenderer.dispose();
	batch.dispose();
    }
}

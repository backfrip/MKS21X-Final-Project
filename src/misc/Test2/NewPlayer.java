package misc.Test2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Template for what should actually be an abstract baseCharacter class that all
 * the different characters derive from. Not yet included would be variables and
 * methods handling various aspects to differentiate characters (dimensions,
 * mass, friction, etc), as well as individualized attack boxes and attacks and
 * whatnot.
 */
public class NewPlayer {
    private Body body;
    private Fixture attack00, attack01, attack02, attack03, attack04;
    private Vector2 trajectory;
    private int aerialState; // 0 = ground; 1 = aerial; 2 = falling; 3 = falling
			     // with no actions

    // @formatter:off
    // (Copied from PlayContactListener)
    // Attack Direction
    // x   1   x
    //  \  |  /
    // 4 - 0 - 2
    //  /  |  \
    // x   3   x
    // @formatter:on

    /**
     * Creates a new NewPlayer.
     * 
     * @param world
     *            World in which the NewPlayer's body is created
     * @param playerNum
     *            Number used for NewPlayer's body's userData
     * @param x
     *            Height of NewPlayer
     * @param y
     *            Width of NewPlayer
     */
    public NewPlayer(World world, int playerNum, float x, float y) {
	// Create the NewPlayer's body
	BodyDef bd = new BodyDef();
	bd.type = BodyDef.BodyType.DynamicBody;
	bd.position.set(x, y);
	bd.fixedRotation = true;

	// The shape we are going to recylce for all of our fixtures
	PolygonShape shape = new PolygonShape();

	// Definition for the fixture that gives our player a physical presence
	// (all the attack boxes are like ghost fixtures)
	FixtureDef fd = new FixtureDef();
	shape.setAsBox(1, 1.5f);
	fd.shape = shape;
	fd.restitution = 0f;
	fd.friction = .8f;
	fd.density = 3;
	// This part is really cool. It adds the player to a category of
	// fixtures and tells it not to interact with any other fixtures of the
	// same type. We'll probably also used this type of logic designing
	// one-way platforms.
	fd.filter.categoryBits = Box2DTest2.PLAYER;
	fd.filter.maskBits = ~Box2DTest2.PLAYER;

	body = world.createBody(bd); // Get the body added to the world
	body.setUserData(playerNum); // Give the body a special number (String)
	body.createFixture(fd); // Generate the collision fixture and attach it
				// to the body

	// BEGIN THE HORRIFYING CHAIN OF ATTACK BOX DEFINITIONS!!!!
	// (Should really be moved to separate functions or something)
	fd = new FixtureDef();
	shape.setAsBox(2, 1, new Vector2(0, 0), 0);
	fd.shape = shape;
	fd.isSensor = true; // This sensorness makes it a ghost box
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER; // This mask bits part makes it
						// a ghost box that only
						// "collides" with players

	// We don't actually need to store the fixture right now, but it might
	// be helpful later for "individualized attacks"
	attack00 = body.createFixture(fd);
	attack00.setUserData("0");

	// Ditto
	// |||
	// VVV

	fd = new FixtureDef();
	shape.setAsBox(1.5f, .5f, new Vector2(0, 1.5f), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack01 = body.createFixture(fd);
	attack01.setUserData("1");

	fd = new FixtureDef();
	shape.setAsBox(.5f, 1.5f, new Vector2(1, 0), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack02 = body.createFixture(fd);
	attack02.setUserData("2");

	fd = new FixtureDef();
	shape.setAsBox(1.5f, .5f, new Vector2(0, -1.5f), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack03 = body.createFixture(fd);
	attack03.setUserData("3");

	fd = new FixtureDef();
	shape.setAsBox(.5f, 1.5f, new Vector2(-1, 0), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack04 = body.createFixture(fd);
	attack04.setUserData("4");

	shape.dispose(); // Thank you shape, goodbye now!

	trajectory = new Vector2(1, 0); // We'll explain this later, just note
					// that its length is 1
    }

    /**
     * The function that regulates a character's jump. Heights should vary.
     */
    public void jump() {
	if (aerialState < 2) { // IF can jump / can double jump
	    // Leave x velocity constant but go directly up at a constant y
	    // (Would probably differ between characters)
	    body.setLinearVelocity(body.getLinearVelocity().x, 15); // Boing!
	    if (aerialState == 1) // IF the player is off the ground, increment
				  // it to 2 (otherwise, PlayContactListener
				  // will handle incrementing from 0 to 1)
		aerialState++;
	}
    }

    /**
     * Moves player right.
     */
    public void right() {
	if (body.getLinearVelocity().x < 10) { // IF not moving at maximum speed
	    body.applyLinearImpulse(100, 0, 0, 0, true); // Apply an impulse
							 // rightwards
	}
    }

    /**
     * Moves player left.
     */
    public void left() {
	// Like right() but backwards
	if (body.getLinearVelocity().x > -10) {
	    body.applyLinearImpulse(-100, 0, 0, 0, true);
	}
    }

    /**
     * Accelerates the player downwards.
     */
    public void down() {
	body.applyLinearImpulse(0, -20, 0, 0, true); // Accelerate downwards!
						     // Not an intended feature,
						     // but can be used to
						     // reduce slide on platform
    }

    /**
     * Doesn't do anything yet. When damage is implemented, will likely return a
     * randomized value, depending on which attack was used.
     */
    public void attack() {
	// System.out.println("Player " + num + " says: Take that!");
    }

    /**
     * Takes an enemy's location to calculate in what direction knockback should
     * be applied. In the future, will probably accept a damage value as well.
     * 
     * @param enemyLocation
     *            a vector location of the enemy's body
     */
    public void hit(Vector2 enemyLocation) {
	enemyLocation.x -= body.getPosition().x; // Set x to the distance
						 // between the bodies
	enemyLocation.y -= body.getPosition().y; // Extrapolate

	/*
	 * Taking the heading of the vector that measures distance between
	 * bodies, reverse it and apply it to the trajectory. Since trajectory
	 * is of length 1, this will always yield a proportional base we can
	 * scale up for the components of velocity to use.
	 */
	trajectory.setAngle(enemyLocation.angle() + 180);
	body.setLinearVelocity(body.getLinearVelocity().x + trajectory.x * 10,
		trajectory.y * 10 + 2); // As of right now, we always give it a
					// boost in the y-direction. This should
					// probably be attack varied in the
					// future.
	aerialState = 1; // (Was 3 in release. Should be 1, allowing for more
			 // actions, but there should be a stun period in which
			 // the character is unable to move (so that one can
			 // chain))
    }

    /**
     * Returns the NewPlayer's body's position.
     * 
     * @return the body's position (as Vector2)
     */
    public Vector2 pos() {
	return body.getPosition();
    }

    /**
     * Allows other objects to set the player's aerial state. Possibly unsafe,
     * code should be refactored to accomodate a different option in the future.
     * 
     * @param value
     *            the new value for aerialState
     */
    public void setAerialState(int value) {
	aerialState = value;
    }
}

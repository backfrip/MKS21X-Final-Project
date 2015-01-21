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
	BodyDef bd = new BodyDef();
	bd.type = BodyDef.BodyType.DynamicBody;
	bd.position.set(x, y);
	bd.fixedRotation = true;

	PolygonShape shape = new PolygonShape();

	FixtureDef fd = new FixtureDef();
	shape.setAsBox(1, 1.5f);
	fd.shape = shape;
	fd.restitution = 0f;
	fd.friction = .8f;
	fd.density = 3;
	fd.filter.categoryBits = Box2DTest2.PLAYER;
	fd.filter.maskBits = ~Box2DTest2.PLAYER;

	body = world.createBody(bd);
	body.setUserData(playerNum);
	body.createFixture(fd);

	fd = new FixtureDef();
	shape.setAsBox(2, 1, new Vector2(0, 0), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack00 = body.createFixture(fd);
	attack00.setUserData("0");

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
	shape.setAsBox(.5f, 1.5f, new Vector2(0, -1), 0);
	fd.shape = shape;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.ATTACK_SENSOR;
	fd.filter.maskBits = Box2DTest2.PLAYER;

	attack04 = body.createFixture(fd);
	attack04.setUserData("4");

	shape.dispose();

	trajectory = new Vector2(1, 0);
    }

    public void jump() {
	if (aerialState < 2) {
	    body.setLinearVelocity(body.getLinearVelocity().x, 15);
	    if (aerialState == 1)
		aerialState++;
	}
    }

    public void right() {
	if (body.getLinearVelocity().x < 10) {
	    // body.setLinearVelocity(10, body.getLinearVelocity().y);
	    body.applyLinearImpulse(100, 0, 0, 0, true);
	}
    }

    public void left() {
	if (body.getLinearVelocity().x > -10) {
	    // body.setLinearVelocity(-10, body.getLinearVelocity().y);
	    body.applyLinearImpulse(-100, 0, 0, 0, true);
	}
    }

    public void down() {
	body.applyLinearImpulse(0, -20, 0, 0, true);
    }

    public void attack() {
	// System.out.println("Player " + num + " says: Take that!");
    }

    public void hit(Vector2 enemyLocation) {
	// System.out.println("Player " + num + " says: I'm hit!");
	// System.out.println("Enemy @ " + (enemyLocation.x -
	// body.getPosition().x) + " x and " + (enemyLocation.y -
	// body.getPosition().y) + " y");
	enemyLocation.x -= body.getPosition().x;
	enemyLocation.y -= body.getPosition().y;
	// System.out.println("Enemy at " + enemyLocation.angle() + " degrees");
	trajectory.setAngle(enemyLocation.angle() + 180);
	// System.out.println(trajectory.x + ", " + trajectory.y);
	body.setLinearVelocity(body.getLinearVelocity().x + trajectory.x * 10,
	/* body.getLinearVelocity().y + */trajectory.y * 10 + 2);
	aerialState = 3;
    }

    public Vector2 pos() {
	return body.getPosition();
    }

    public void setAerialState(int value) {
	aerialState = value;
    }
}

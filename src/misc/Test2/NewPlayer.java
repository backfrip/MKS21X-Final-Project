package misc.Test2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class NewPlayer {
    private Body body;
    private Fixture testAttackBox;
    private int num;
    private Vector2 trajectory;
    private int aerialState; // 0 = ground; 1 = aerial; 2 = falling; 3 = falling
			     // no actions

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

	testAttackBox = body.createFixture(fd);
	testAttackBox.setUserData("0");

	shape.dispose();

	num = playerNum;
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
	System.out.println("Player " + num + " says: Take that!");
    }

    public void hit(Vector2 enemyLocation) {
	System.out.println("Player " + num + " says: I'm hit!");
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
	if (aerialState > 1)
	    aerialState = 1;
    }

    public Vector2 pos() {
	return body.getPosition();
    }

    public void setAerialState(int value) {
	aerialState = value;
    }
}

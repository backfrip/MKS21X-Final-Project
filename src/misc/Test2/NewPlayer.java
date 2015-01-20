package misc.Test2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class NewPlayer {
    private Body body;
    private Fixture fixture, testAttackBox;
    private int num;

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

	body = world.createBody(bd);
	body.setUserData(playerNum);
	fixture = body.createFixture(fd);

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
    }

    public void jump() {
	body.setLinearVelocity(body.getLinearVelocity().x, 10);
    }

    public void right() {
	if (body.getLinearVelocity().x < 10) {
	    body.setLinearVelocity(10, body.getLinearVelocity().y);
	}
    }

    public void left() {
	if (body.getLinearVelocity().x > -10) {
	    body.setLinearVelocity(-10, body.getLinearVelocity().y);
	}
    }

    public void down() {
	body.applyLinearImpulse(0, -20, 0, 0, true);
    }

    public void attack() {
	System.out.println("Player " + num + " says: Take that!");
    }
    
    public void hit() {
	System.out.println("Player " + num + " says: I'm hit!");
    }
}

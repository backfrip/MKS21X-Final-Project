package player;

import misc.Constants;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Adventurer extends Player {

    public Adventurer(World world, int num, float width, float height) {
	super(world, num, width, height, 1);
    }

    @Override
    protected void createFixtures(Body body) {
	FixtureDef fd = new FixtureDef();
	PolygonShape shape = new PolygonShape();
	
	// Player Body Fixture
	shape.setAsBox(1, 1);
	fd.shape = shape;
	fd.restitution = 0;
	fd.friction = .8f;
	fd.density = 3;
	fd.filter.categoryBits = Constants.PLAYER;
	fd.filter.maskBits = ~Constants.PLAYER;
	
	body.createFixture(fd);
	
	// Attack Sensor Fixtures
	fd = new FixtureDef();
	fd.isSensor = true;
	fd.filter.categoryBits = Constants.ATTACK_SENSOR;
	fd.filter.maskBits = Constants.PLAYER;
	
	shape.setAsBox(2, 1, new Vector2(1.5f, 0), 0);
	fd.shape = shape;
	body.createFixture(fd).setUserData("00");;
	
	shape.setAsBox(2, 1, new Vector2(1.5f, 0), 0);
	fd.shape = shape;
	body.createFixture(fd).setUserData("02");;
    }

}

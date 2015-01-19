package misc.Test2;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class NewPlayer {
    private Body body;
    private Fixture fixture;

    public NewPlayer(World world, float x, float y) {
	BodyDef bd = new BodyDef();
	bd.type = BodyDef.BodyType.DynamicBody;
	bd.position.set(x, y);
	bd.fixedRotation = true;

	PolygonShape shape = new PolygonShape();
	shape.setAsBox(1, 1.5f);

	FixtureDef fd = new FixtureDef();
	fd.shape = shape;
	fd.restitution = .1f;
	fd.friction = .8f;
	fd.density = 3;

	body = world.createBody(bd);
	fixture = body.createFixture(fd);
	
	shape.dispose();
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
}

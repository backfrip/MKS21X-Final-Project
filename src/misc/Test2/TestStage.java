package misc.Test2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class TestStage {
    private Body body;
    private Fixture fixture;

    public TestStage(World world) {
	BodyDef bd = new BodyDef();
	bd.type = BodyDef.BodyType.StaticBody;
	bd.position.set(0, 0);

	ChainShape shape = new ChainShape();
	shape.createChain(new Vector2[] { new Vector2(-20, -10),
		new Vector2(20, -10) });

	FixtureDef fd = new FixtureDef();
	fd.shape = shape;
	fd.friction = .5f;
	fd.restitution = 0;

	body = world.createBody(bd);
	body.createFixture(fd);
	
	PolygonShape shapeP = new PolygonShape();
	shapeP.setAsBox(35, 25);
	
	fd = new FixtureDef();
	fd.shape = shapeP;
	fd.isSensor = true;
	fd.filter.categoryBits = Box2DTest2.STAGE_BOUNDS;
	fd.filter.maskBits = Box2DTest2.PLAYER;
	
	body.createFixture(fd);

	shape.dispose();
	shapeP.dispose();
    }
}

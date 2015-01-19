package misc.Test2;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class TestStage {
    private Body body;
    private Fixture fixture;
    
    public TestStage(World world) {
	BodyDef bd = new BodyDef();
	bd.type = BodyDef.BodyType.StaticBody;
	bd.position.set(0, 0);

	ChainShape shape = new ChainShape();
	shape.createChain(new Vector2[] { new Vector2(-20, 0),
		new Vector2(20, 0) });
	
	FixtureDef fd = new FixtureDef();
	fd.shape = shape;
	fd.friction = .5f;
	fd.restitution = 0;
	
	world.createBody(bd).createFixture(fd);
	
	shape.dispose();
    }
}

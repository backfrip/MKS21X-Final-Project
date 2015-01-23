package player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Player {
    private Body body;

    // @formatter:off
    // Attack Fixture Direction (prefix 0 = ground, 1 = aerial)
    //  x  1  x
    //   \ | /
    // 4 - 0 - 2
    //   / | \
    //  x  3  x
    // @formatter:on

    private Vector2 trajectory;
    private int aerialState;
    // 0-ground, 1-jumped, 2-falling, 3-helpless
    private int direction = 1;

    public Player(World world, int num, float x, float y, float gravityScale) {
	BodyDef bd = new BodyDef();
	bd.fixedRotation = true;
	bd.gravityScale = gravityScale;
	bd.position.set(x, y);
	bd.type = BodyDef.BodyType.DynamicBody;

	body = world.createBody(bd);
	body.setUserData(num);
	createFixtures(body);
    }

    abstract protected void createFixtures(Body body);
}
package misc.Test2;

import java.util.Hashtable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class PlayContactListener implements ContactListener {
    private Fixture fa;
    private Fixture fb;
    private Hashtable<String, String> attacks = new Hashtable<String, String>(); // Hashtable
										 // containing
										 // possbile
										 // attacks

    // K, V | "xx", "x" | "<attackingPlayer><attackDirection>",
    // "<defendingPlayer>"

    // Attack Direction
    // x 1 x
    // \ | /
    // 4 - 0 - 2
    // / | \
    // x 3 x

    // NOTE: Plan for aerial attack distinction later (probably add another
    // String character)

    @Override
    public void beginContact(Contact c) {
	fa = c.getFixtureA();
	fb = c.getFixtureB();

	if (fa.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.put(formatAttack(fa), fb.getBody().getUserData().toString());
	if (fb.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.put(formatAttack(fb), fa.getBody().getUserData().toString());
    }

    @Override
    public void endContact(Contact c) {
	fa = c.getFixtureA();
	fb = c.getFixtureB();

	if (fa.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.remove(formatAttack(fa));
	if (fb.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.remove(formatAttack(fb));
	if (fa.getFilterData().categoryBits == Box2DTest2.STAGE_BOUNDS)
	    System.out.println("Player "
		    + fb.getBody().getUserData().toString() + " just died!");
	if (fb.getFilterData().categoryBits == Box2DTest2.STAGE_BOUNDS)
	    System.out.println("Player "
		    + fa.getBody().getUserData().toString() + " just died!");
    }

    private String formatAttack(Fixture f) {
	return f.getBody().getUserData().toString()
		+ f.getUserData().toString();
    }


    public int attackHits(String attackCode) {
	if (attacks.containsKey(attackCode))
	    return Integer.parseInt(attacks.get(attackCode));
	return 0;
    }

    @Override
    public void postSolve(Contact c, ContactImpulse cI) {
    }

    @Override
    public void preSolve(Contact c, Manifold m) {
    }

}

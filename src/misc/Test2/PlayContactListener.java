package misc.Test2;

import java.util.Hashtable;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

/**
 * A ContactListener for Box2DTest2 [and Play]. Detects collisions between
 * fixtures and takes action in special cases. Whenever I look at this class, I
 * start thinking of Bourne Ultimatum, because my parents were watching it the
 * whole time I was writing this. -E
 */
public class PlayContactListener implements ContactListener {
    private Fixture fa;
    private Fixture fb;
    private Hashtable<String, String> attacks = new Hashtable<String, String>(); // Hashtable
										 // containing
										 // possbile
										 // attacks
    private Box2DTest2 screen;

    /**
     * Makes a new PlayContactListener.
     * 
     * @param playScreen
     *            The Box2DTest2 screen managing the world collisions are
     *            detected for.
     */
    public PlayContactListener(Box2DTest2 playScreen) {
	super();
	screen = playScreen;
    }

    // K, V | "xx", "x" | "<attackingPlayer><attackDirection>",
    // "<defendingPlayer>"

    // @formatter:off
    // Attack Direction
    // x   1   x
    //  \  |  /
    // 4 - 0 - 2
    //  /  |  \
    // x   3   x
    // @formatter:on

    // NOTE: Plan for aerial attack distinction later (probably add another
    // String character)

    /**
     * Detects contact between two fixtures.
     */
    @Override
    public void beginContact(Contact c) {
	fa = c.getFixtureA();
	fb = c.getFixtureB();

	// If one of the fixtures is an attack sensor, the other must be a
	// player within range of an attack. This attack possibility is stored.
	if (fa.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.put(formatAttack(fa), fb.getBody().getUserData().toString());
	if (fb.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.put(formatAttack(fb), fa.getBody().getUserData().toString());

	// If the a player comes into contact with the stage, it's aerialStatus
	// should be set to 0 to reenable double-jumping
	if (fa.getFilterData().categoryBits == Box2DTest2.PLAYER
		&& fb.getFilterData().categoryBits == Box2DTest2.STAGE)
	    setAerial(Integer.parseInt(fa.getBody().getUserData().toString()),
		    0);
	if (fb.getFilterData().categoryBits == Box2DTest2.PLAYER
		&& fa.getFilterData().categoryBits == Box2DTest2.STAGE)
	    setAerial(Integer.parseInt(fb.getBody().getUserData().toString()),
		    0);

    }

    /**
     * Detects the end of contact between two fixtures.
     */
    @Override
    public void endContact(Contact c) {
	fa = c.getFixtureA();
	fb = c.getFixtureB();

	// A player is no longer within range of an attack.
	if (fa.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.remove(formatAttack(fa));
	if (fb.getFilterData().categoryBits == Box2DTest2.ATTACK_SENSOR)
	    attacks.remove(formatAttack(fb));

	// A player has moved outside of stage bounds and "died"
	if (fa.getFilterData().categoryBits == Box2DTest2.STAGE_BOUNDS)
	    System.out.println("Player "
		    + fb.getBody().getUserData().toString() + " just died!");
	if (fb.getFilterData().categoryBits == Box2DTest2.STAGE_BOUNDS)
	    System.out.println("Player "
		    + fa.getBody().getUserData().toString() + " just died!");

	// A player has jumped/fallen/been knocked off the stage and is now
	// aerial (can no longer use its first jump)
	if (fa.getFilterData().categoryBits == Box2DTest2.PLAYER
		&& fb.getFilterData().categoryBits == Box2DTest2.STAGE)
	    setAerial(Integer.parseInt(fa.getBody().getUserData().toString()),
		    1);
	if (fb.getFilterData().categoryBits == Box2DTest2.PLAYER
		&& fa.getFilterData().categoryBits == Box2DTest2.STAGE)
	    setAerial(Integer.parseInt(fb.getBody().getUserData().toString()),
		    1);
    }

    /**
     * Returns a String to represent an attack possibility. Output is used as a
     * key in the attacks Hashtable.
     * 
     * @param f
     *            the attack sensor
     * @return an attackCode
     */
    private String formatAttack(Fixture f) {
	return f.getBody().getUserData().toString()
		+ f.getUserData().toString();
    }

    /**
     * Checks an attackCode against stored attack possibilities.
     * 
     * @param attackCode
     *            the key for a stored attack
     * @return an int representing the player the attack will hit successfully,
     *         0 if there is no hit
     */
    // Just realized we don't account for multiple characters in an attack's
    // range...
    public int attackHits(String attackCode) {
	if (attacks.containsKey(attackCode))
	    return Integer.parseInt(attacks.get(attackCode));
	return 0;
    }

    /**
     * Adjusts the aerialStatus of a player
     * 
     * @param num
     *            which player to adjust
     * @param value
     *            the new value for aerialStatus
     */
    private void setAerial(int num, int value) {
	if (num == 1)
	    screen.p1.setAerialState(value);
	if (num == 2)
	    screen.p2.setAerialState(value);
    }

    // Beats me...s
    // |
    // V
    @Override
    public void postSolve(Contact c, ContactImpulse cI) {
    }

    @Override
    public void preSolve(Contact c, Manifold m) {
    }

}

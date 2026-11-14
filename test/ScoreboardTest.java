import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.scoreboard.Scoreboard;

/**
 * Test cases for any {@code Scoreboard} implementation.
 *
 * @author Atang Fontem
 */
public abstract class ScoreboardTest {

    /**
     * A small score, used for low-scoring test cases.
     */
    private static final int SMALL = 3;

    /**
     * A medium score, used for typical test cases.
     */
    private static final int MEDIUM = 7;

    /**
     * A large score, used for high-scoring test cases.
     */
    private static final int LARGE = 12;

    /**
     * Invokes the appropriate {@code Scoreboard} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new Scoreboard
     * @ensures constructorTest = (0, 0)
     */
    protected abstract Scoreboard constructorTest();

    /**
     * Invokes the appropriate {@code Scoreboard} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new Scoreboard
     * @ensures constructorRef = (0, 0)
     */
    protected abstract Scoreboard constructorRef();

    /**
     * Creates and returns a {@code Scoreboard} of the implementation under
     * test type with the given scores.
     *
     * @param home
     *            score of the Home team
     * @param guest
     *            score of the Guest team
     * @return the constructed scoreboard
     * @requires home >= 0 and guest >= 0
     * @ensures createFromArgsTest = (home, guest)
     */
    private Scoreboard createFromArgsTest(int home, int guest) {
        Scoreboard s = this.constructorTest();
        s.addHome(home);
        s.addGuest(guest);
        return s;
    }

    /**
     * Creates and returns a {@code Scoreboard} of the reference
     * implementation type with the given scores.
     *
     * @param home
     *            score of the Home team
     * @param guest
     *            score of the Guest team
     * @return the constructed scoreboard
     * @requires home >= 0 and guest >= 0
     * @ensures createFromArgsRef = (home, guest)
     */
    private Scoreboard createFromArgsRef(int home, int guest) {
        Scoreboard s = this.constructorRef();
        s.addHome(home);
        s.addGuest(guest);
        return s;
    }

    /*
     * Constructor tests -------------------------------------------------
     */

    /**
     * Tests that the constructor creates a scoreboard with both scores at 0.
     */
    @Test
    public final void testConstructor() {
        Scoreboard s = this.constructorTest();
        assertEquals(0, s.homeScore());
        assertEquals(0, s.guestScore());
    }

    /**
     * Tests that the constructor under test produces the same value as the
     * reference constructor.
     */
    @Test
    public final void testConstructorSame() {
        Scoreboard test = this.constructorTest();
        Scoreboard ref = this.constructorRef();
        assertEquals(ref, test);
    }

    /*
     * addHome tests -----------------------------------------------------
     */

    /**
     * Tests adding one point to the home team from the initial state.
     */
    @Test
    public final void testAddHomeOne() {
        Scoreboard test = this.constructorTest();
        test.addHome(1);
        assertEquals(1, test.homeScore());
        assertEquals(0, test.guestScore());
    }

    /**
     * Tests adding points to a home team that already has points.
     */
    @Test
    public final void testAddHomeIncrease() {
        Scoreboard s = this.createFromArgsTest(SMALL, MEDIUM);
        Scoreboard sExpected = this.createFromArgsRef(SMALL + 2, MEDIUM);
        s.addHome(2);
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding zero points to the home team.
     */
    @Test
    public final void testAddHomeZeroPoints() {
        Scoreboard s = this.createFromArgsTest(MEDIUM, LARGE);
        Scoreboard sExpected = this.createFromArgsRef(MEDIUM, LARGE);
        s.addHome(0);
        assertEquals(sExpected, s);
    }

    /*
     * addGuest tests ----------------------------------------------------
     */

    /**
     * Tests adding one point to the guest team from the initial state.
     */
    @Test
    public final void testAddGuestOne() {
        Scoreboard test = this.constructorTest();
        test.addGuest(1);
        assertEquals(0, test.homeScore());
        assertEquals(1, test.guestScore());
    }

    /**
     * Tests adding points to a guest team that already has points.
     */
    @Test
    public final void testAddGuestIncrease() {
        Scoreboard s = this.createFromArgsTest(LARGE, SMALL);
        Scoreboard sExpected = this.createFromArgsRef(LARGE, SMALL + MEDIUM);
        s.addGuest(MEDIUM);
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding zero points to the guest team.
     */
    @Test
    public final void testAddGuestZeroPoints() {
        Scoreboard s = this.createFromArgsTest(LARGE, MEDIUM);
        Scoreboard sExpected = this.createFromArgsRef(LARGE, MEDIUM);
        s.addGuest(0);
        assertEquals(sExpected, s);
    }

    /*
     * Standard methods tests: clear, newInstance, transferFrom ----------
     */

    /**
     * Tests clearing a scoreboard that contains scores.
     */
    @Test
    public final void testClearNonEmpty() {
        Scoreboard s = this.createFromArgsTest(SMALL, MEDIUM);
        Scoreboard sExpected = this.constructorRef();
        s.clear();
        assertEquals(sExpected, s);
    }

    /**
     * Tests clearing a scoreboard that is already empty.
     */
    @Test
    public final void testClearEmpty() {
        Scoreboard s = this.constructorTest();
        Scoreboard sExpected = this.constructorRef();
        s.clear();
        assertEquals(sExpected, s);
    }

    /**
     * Tests that newInstance returns an initial scoreboard and leaves the
     * original unchanged.
     */
    @Test
    public final void testNewInstance() {
        Scoreboard s1 = this.createFromArgsTest(LARGE, MEDIUM);
        Scoreboard s2 = s1.newInstance();
        assertEquals(this.constructorRef(), s2);
        assertEquals(this.createFromArgsRef(LARGE, MEDIUM), s1);
    }

    /**
     * Tests that a scoreboard from newInstance is separate from the original.
     */
    @Test
    public final void testNewInstanceIndependent() {
        Scoreboard s1 = this.createFromArgsTest(LARGE, MEDIUM);
        Scoreboard s2 = s1.newInstance();
        s2.addHome(SMALL);
        assertEquals(this.createFromArgsRef(LARGE, MEDIUM), s1);
        assertEquals(this.createFromArgsRef(SMALL, 0), s2);
    }

    /**
     * Tests transferring scores from one scoreboard to another, and that the
     * source is reset afterward.
     */
    @Test
    public final void testTransferFrom() {
        Scoreboard source = this.createFromArgsTest(SMALL, MEDIUM);
        Scoreboard target = this.createFromArgsTest(1, 1);
        target.transferFrom(source);
        assertEquals(this.createFromArgsRef(SMALL, MEDIUM), target);
        assertEquals(this.constructorRef(), source);
    }

    /*
     * totalPoints tests -------------------------------------------------
     */

    /**
     * Tests the total points when both teams have zero points.
     */
    @Test
    public final void testTotalPointsZero() {
        Scoreboard test = this.constructorTest();
        assertEquals(0, test.totalPoints());
    }

    /**
     * Tests calculating the combined points of both teams.
     */
    @Test
    public final void testTotalPointsBasic() {
        Scoreboard test = this.createFromArgsTest(MEDIUM, SMALL);
        assertEquals(MEDIUM + SMALL, test.totalPoints());
    }

    /*
     * margin tests ------------------------------------------------------
     */

    /**
     * Tests the margin when the scoreboard is in its initial state.
     */
    @Test
    public final void testMarginInitial() {
        Scoreboard test = this.constructorTest();
        assertEquals(0, test.margin());
    }

    /**
     * Tests calculating the margin when the home team is winning.
     */
    @Test
    public final void testMarginHomeWinning() {
        Scoreboard test = this.createFromArgsTest(LARGE, MEDIUM);
        assertEquals(LARGE - MEDIUM, test.margin());
    }

    /**
     * Tests calculating the margin when the guest team is winning.
     */
    @Test
    public final void testMarginGuestWinning() {
        Scoreboard test = this.createFromArgsTest(MEDIUM, LARGE);
        assertEquals(LARGE - MEDIUM, test.margin());
    }

    /*
     * isTied tests ------------------------------------------------------
     */

    /**
     * Tests that a new scoreboard is tied.
     */
    @Test
    public final void testIsTiedInitial() {
        Scoreboard test = this.constructorTest();
        assertTrue(test.isTied());
    }

    /**
     * Tests that a scoreboard with equal nonzero scores is tied.
     */
    @Test
    public final void testIsTied() {
        Scoreboard test = this.createFromArgsTest(MEDIUM, MEDIUM);
        assertTrue(test.isTied());
    }

    /**
     * Tests that a scoreboard with different scores is not tied.
     */
    @Test
    public final void testNotIsTied() {
        Scoreboard test = this.createFromArgsTest(MEDIUM, SMALL);
        assertFalse(test.isTied());
    }

    /*
     * leader tests ------------------------------------------------------
     */

    /**
     * Tests that leader identifies the home team as the leader.
     */
    @Test
    public final void testLeaderHome() {
        Scoreboard test = this.createFromArgsTest(LARGE, SMALL);
        assertEquals("home", test.leader());
    }

    /**
     * Tests that leader identifies the guest team as the leader.
     */
    @Test
    public final void testLeaderGuest() {
        Scoreboard test = this.createFromArgsTest(SMALL, LARGE);
        assertEquals("guest", test.leader());
    }

    /**
     * Tests that leader identifies a tied scoreboard.
     */
    @Test
    public final void testLeaderTied() {
        Scoreboard test = this.createFromArgsTest(MEDIUM, MEDIUM);
        assertEquals("tied", test.leader());
    }

    /*
     * setHome tests -----------------------------------------------------
     */

    /**
     * Tests setting the home score higher than its current score.
     */
    @Test
    public final void testSetHomeHigher() {
        Scoreboard s = this.createFromArgsTest(1, MEDIUM);
        Scoreboard sExpected = this.createFromArgsRef(LARGE, MEDIUM);
        s.setHome(LARGE);
        assertEquals(sExpected, s);
    }

    /**
     * Tests setting the home score lower than its current score.
     */
    @Test
    public final void testSetHomeLower() {
        Scoreboard s = this.createFromArgsTest(LARGE, MEDIUM);
        Scoreboard sExpected = this.createFromArgsRef(SMALL, MEDIUM);
        s.setHome(SMALL);
        assertEquals(sExpected, s);
    }

    /**
     * Tests setting the home score to zero.
     */
    @Test
    public final void testSetHomeToZero() {
        Scoreboard s = this.createFromArgsTest(LARGE, MEDIUM);
        Scoreboard sExpected = this.createFromArgsRef(0, MEDIUM);
        s.setHome(0);
        assertEquals(sExpected, s);
    }

    /*
     * setGuest tests ----------------------------------------------------
     */

    /**
     * Tests setting the guest score higher than its current score.
     */
    @Test
    public final void testSetGuestHigher() {
        Scoreboard s = this.createFromArgsTest(MEDIUM, 1);
        Scoreboard sExpected = this.createFromArgsRef(MEDIUM, LARGE);
        s.setGuest(LARGE);
        assertEquals(sExpected, s);
    }

    /**
     * Tests setting the guest score lower than its current score.
     */
    @Test
    public final void testSetGuestLower() {
        Scoreboard s = this.createFromArgsTest(MEDIUM, LARGE);
        Scoreboard sExpected = this.createFromArgsRef(MEDIUM, SMALL);
        s.setGuest(SMALL);
        assertEquals(sExpected, s);
    }

    /**
     * Tests setting the guest score to zero.
     */
    @Test
    public final void testSetGuestToZero() {
        Scoreboard s = this.createFromArgsTest(MEDIUM, LARGE);
        Scoreboard sExpected = this.createFromArgsRef(MEDIUM, 0);
        s.setGuest(0);
        assertEquals(sExpected, s);
    }
}

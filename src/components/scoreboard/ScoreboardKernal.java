package components.scoreboard;

import components.standard.Standard;

/**
 * Kernel interface for a two-team Scoreboard component. Contains only the
 * mandatory methods needed to build, change, and observe the scoreboard.
 * Everything else is defined in {@code Scoreboard} and implemented using
 * only these methods.
 *
 * @mathdefinitions ScoreboardKernal is modeled by (home: integer, guest: integer)
 * @@requires home >= 0 and guest >= 0
 * @ensures <pre>
 * ():
 *  initally this = (0, 0)
 * </pre>
 *
 * @author  Atang Fontem
 */
public interface ScoreboardKernal extends Standard<Scoreboard> {

    /**
     * Adds a specific number of points to the home team's score.
     *
     * @param points
     *            the number of points to add
     * @updates this
     * @requires points >= 0
     * @ensures this = (#this.home + points, #this.guest)
     */
    void addHome(int points);

    /**
     * Adds a specific number of points to the guest team's score.
     *
     * @param points
     *            the number of points to add
     * @updates this
     * @requires points >= 0
     * @ensures this = (#this.home, #this.guest + points)
     */
    void addGuest(int points);

    /**
     * Reports the home team's score.
     *
     * @return the home score
     * @ensures homeScore = this.home
     */
    int homeScore();

    /**
     * Reports the guest team's score.
     *
     * @return the guest score
     * @ensures guestScore = this.guest
     */
    int guestScore();
}


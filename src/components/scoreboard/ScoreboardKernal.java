package components.scoreboard;

import components.standard.Standard;

/**
 * Kernel interface for a two-team Scoreboard component. Contains only the
 * mandatory methods needed to build, change, and observe the scoreboard.
 * Everything else is defined in {@code Scoreboard} and implemented using
 * only these methods.
 *
 * @mathmodel type ScoreboardKernal is modeled by (home: integer, guest: integer)
 * @constraint home >= 0 and guest >= 0
 * @initially <pre>
 * ():
 *  ensures this = (0, 0)
 * </pre>
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
    void addToHome(int points);

    /**
     * Adds a specific number of points to the guest team's score.
     *
     * @param points
     *            the number of points to add
     * @updates this
     * @requires points >= 0
     * @ensures this = (#this.home, #this.guest + points)
     */
    void addToGuest(int points);

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


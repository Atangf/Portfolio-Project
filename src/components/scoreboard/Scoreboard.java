package components.scoreboard;

/**
 * Scoreboard component for a two-team game. Extends {@code ScoreboardKernal}
 * with secondary methods that are implemented using only kernel methods.
 *
 * @mathmodel type Scoreboard is modeled by (home: integer, guest: integer)
 * @constraint home >= 0 and guest >= 0
 * @initially <pre>
 * ():
 *  ensures this = (0, 0)
 * </pre>
 */
public interface Scoreboard extends ScoreboardKernal {

    /**
     * Reports the combined score of both teams.
     *
     * @return the sum of the home and guest scores
     * @ensures totalPoints = this.homeScore() + this.guestScore()
     */
    int totalPoints();

    /**
     * Reports the difference between the two team scores.
     *
     * @return the absolute difference between the home and guest scores
     * @ensures margin = |this.homeScore() - this.guestScore()|
     */
    int margin();

    /**
     * Reports whether the two teams have the same score.
     *
     * @return true if the home and guest scores are equal, false otherwise
     * @ensures isTied = (this.homeScore() = this.guestScore())
     */
    boolean isTied();

    /**
     * Reports which team is currently ahead.
     *
     * @return "home" if home is ahead, "guest" if guest is ahead, or "tied"
     *         if the scores are equal
     * @ensures leader = "home" if this.homeScore() > this.guestScore(),
     *          "guest" if this.homeScore() < this.guestScore(),
     *          "tied" otherwise
     */
    String leader();

    /**
     * Sets the home score.
     *
     * @param points
     *            the new home score
     * @updates this
     * @requires points >= this.homeScore()
     * @ensures this.homeScore() = points and this.guestScore() = #this.guestScore()
     */
    void setHome(int points);

    /**
     * Sets the guest score.
     *
     * @param points
     *            the new guest score
     * @updates this
     * @requires points >= this.guestScore()
     * @ensures this.guestScore() = points and this.homeScore() = #this.homeScore()
     */
    void setGuest(int points);
}

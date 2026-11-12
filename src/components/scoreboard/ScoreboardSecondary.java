package components.scoreboard;

import java.util.Objects;

/**
 * Layered implementations of secondary methods for {@code Scoreboard}.
 *
 * <p>
 * Implements {@code totalPoints}, {@code margin}, {@code isTied},
 * {@code leader}, {@code setHome}, {@code setGuest}, {@code toString},
 * {@code equals}, and {@code hashCode} using only {@code ScoreboardKernal}
 * methods and the Standard method {@code clear}. No method here accesses the
 * representation directly, so they work with any kernel implementation.
 *
 * @author Atang Fontem
 */
public abstract class ScoreboardSecondary implements Scoreboard {
    @Override
    public final int totalPoints() {
        return this.homeScore() + this.guestScore();
    }

    @Override
    public final int margin() {
        return Math.abs(this.homeScore() - this.guestScore());
    }

    @Override
    public final boolean isTied() {
        return this.homeScore() == this.guestScore();
    }

    @Override
    public final String leader() {
        String result = "tied";

        if (this.homeScore() > this.guestScore()) {
            result = "home";
        } else if (this.guestScore() > this.homeScore()) {
            result = "guest";
        }
        return result;
    }

    @Override
    public final void setHome(int points) {
        int guest = this.guestScore();
        this.clear();
        this.addHome(points);
        this.addGuest(guest);
    }

    @Override
    public final void setGuest(int points) {
        int home = this.homeScore();
        this.clear();
        this.addGuest(points);
        this.addHome(home);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final String toString() {
        String s = "(" + this.homeScore() + ", " + this.guestScore() + ")";
        return s;
    }

    @Override
    public final boolean equals(Object obj) {
        boolean result = false;
        if (obj == this) {
            result = true;
        } else if (obj instanceof Scoreboard) {
            Scoreboard other = (Scoreboard) obj;
            result = this.homeScore() == other.homeScore()
                    && this.guestScore() == other.guestScore();
        }
        return result;

    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.homeScore(), this.guestScore());
    }

}

package components.scoreboard;

/**
 * {@code Scoreboard} represented with two integer fields, with implementations
 * of primary methods.
 *
 * @convention home >= 0 and guest >= 0
 * @correspondence this = (home, guest)
 *
 * @author Atang Fontem
 */
public class Scoreboard1 extends ScoreboardSecondary {

    /**
     * Home team's score.
     */
    private int home;

    /**
     * Guest team's score.
     */
    private int guest;

    /**
     * Creator of the initial representation.
     */
    private void createNewRep() {
        this.home = 0;
        this.guest = 0;
    }

    /**
     * No-argument constructor.
     */
    public Scoreboard1() {
        this.createNewRep();
    }

    @Override
    public final void addHome(int points) {
        assert points >= 0 : "Violation of: points >= 0";
        this.home = this.home + points;
    }

    @Override
    public final void addGuest(int points) {
        assert points >= 0 : "Violation of: points >= 0";
        this.guest = this.guest + points;
    }

    @Override
    public final int homeScore() {
        return this.home;
    }

    @Override
    public final int guestScore() {
        return this.guest;
    }

    /*
     * Standard methods.
     */
    @Override
    public final Scoreboard newInstance() {
        return new Scoreboard1();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Scoreboard source) {
        assert source instanceof Scoreboard1 : ""
                + "Violation of: source is of dynamic type Scoreboard1L";
        Scoreboard1 s = (Scoreboard1) source;
        this.home = s.home;
        this.guest = s.guest;
        s.createNewRep();
    }

}

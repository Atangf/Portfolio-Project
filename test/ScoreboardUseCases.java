import components.scoreboard.Scoreboard;
import components.scoreboard.Scoreboard1;

/**
 * Example client programs showing how {@code Scoreboard} can be used.
 *
 * @author Atang Fontem
 */
public final class ScoreboardUseCases {

    /**
     * The number 3, used for three-point plays.
     */
    private static final int THREE = 3;

    /**
     * The number 10, used as the target score.
     */
    private static final int TEN = 10;

    /**
     * The number 14, used as a football score.
     */
    private static final int FOURTEEN = 14;

    /**
     * The number 20, used as a football score.
     */
    private static final int TWENTY = 20;

    /**
     * The number 21, used as a football score.
     */
    private static final int TWENTY_ONE = 21;

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ScoreboardUseCases() {
    }

    /**
     * Use case 1: scoring a basketball game.
     */
    private static void basketballGame() {
        Scoreboard game = new Scoreboard1();

        game.addHome(2); // home hits a layup
        game.addGuest(THREE); // guest hits a three
        game.addHome(THREE);
        game.addHome(2);
        game.addGuest(2);

        System.out.println("Use case 1:");
        System.out.println("Score: " + game); // (7, 5)
        System.out.println("Leader: " + game.leader()); // home
        System.out.println("Margin: " + game.margin()); // 2
        System.out.println("Total points: " + game.totalPoints()); // 12
        System.out.println("");
    }

    /**
     * Use case 2: correcting a referee touchdown mistake.
     */
    private static void scoreCorrection() {
        Scoreboard game = new Scoreboard1();
        game.addHome(FOURTEEN);
        game.addGuest(TWENTY);

        // guest player was found to be out of bounds for touchdown
        game.setGuest(FOURTEEN);

        System.out.println("Use case 2:");
        System.out.println("After correction: " + game); // (14, 14)
        System.out.println("Tied? " + game.isTied()); // true
        System.out.println("");
    }

    /**
     * Use case 3: first team to reach a target wins.
     *
     * @param target
     *            the score a team must reach to win
     */
    private static void raceToTarget(int target) {
        Scoreboard game = new Scoreboard1();
        int[] homePlays = { 2, THREE, 2, 2, THREE, 2 };
        int[] guestPlays = { THREE, 2, 2, THREE, 2, 2 };

        int i = 0;
        while (game.homeScore() < target && game.guestScore() < target
                && i < homePlays.length) {
            game.addHome(homePlays[i]);
            game.addGuest(guestPlays[i]);
            i++;
        }

        System.out.println("Use case 3:");
        System.out.println("Final: " + game + ", winner: " + game.leader());
        System.out.println("");
    }

    /**
     * Use case 4: resetting and moving scoreboards around.
     */
    private static void resetAndTransfer() {
        Scoreboard first = new Scoreboard1();
        first.addHome(TWENTY_ONE);
        first.addGuest(FOURTEEN);

        Scoreboard second = first.newInstance(); // new empty scoreboard
        second.transferFrom(first); // move the score over

        System.out.println("Use case 4:");
        System.out.println("first:  " + first); // (0, 0)
        System.out.println("second: " + second); // (21, 14)

        second.clear();
        System.out.println("cleared: " + second); // (0, 0)
        System.out.println("");
    }

    /**
     * Runs all of the use cases.
     *
     * @param args
     *            the command line arguments; unused
     */
    public static void main(String[] args) {
        basketballGame();
        scoreCorrection();
        raceToTarget(TEN);
        resetAndTransfer();
    }
}

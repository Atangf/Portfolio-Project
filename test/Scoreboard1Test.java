import components.scoreboard.Scoreboard;
import components.scoreboard.Scoreboard1;

/**
 * Customized JUnit test fixture for {@code Scoreboard1}.
 *
 * @author Atang Fontem
 */
public class Scoreboard1Test extends ScoreboardTest {

    @Override
    protected final Scoreboard constructorTest() {
        return new Scoreboard1();
    }

    @Override
    protected final Scoreboard constructorRef() {
        return new Scoreboard1();
    }
}

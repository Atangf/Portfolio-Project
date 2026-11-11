package components.scoreboard;

public abstract class ScoreboardSecondary implements Scoreboard {
    @Override
    public final int totalPoints() {
        return 0;
    }

    @Override
    public final int margin() {
        return 0;
    }

    @Override
    public final boolean isTied() {
        return true;
    }

    @Override
    public final String leader() {
        return "";
    }

    @Override
    public final void setHome(int points){

    }

    @Override
    public final void setGuest(int points){

    }

    @Override
    public String toString() {

    }

    @Override
    public boolean equals(Object o) {

    }

    @Override
    public int hashCode(){
        
    }


}

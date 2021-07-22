package cricscore.model.people;

import cricscore.exception.InvalidMatch;
import cricscore.model.match.Ball;
import cricscore.model.match.ScoreCard;

public class Scorer extends Person {
    public Scorer(String name) {
        super(name);
    }

    public void setScore(Ball ball, String match, int innings) throws InvalidMatch {
        ScoreCard scoreCard = ScoreCard.INSTANCE(match, innings);
        scoreCard.setScoreCardForBall(ball);
    }
}

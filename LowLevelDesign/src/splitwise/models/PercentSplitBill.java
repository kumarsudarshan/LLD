package splitwise.models;

public class PercentSplitBill extends SplitBill {
    double percent;

    public PercentSplitBill(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
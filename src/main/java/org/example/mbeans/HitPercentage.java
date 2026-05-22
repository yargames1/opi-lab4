package org.example.mbeans;

public class HitPercentage implements HitPercentageMBean {

    private final PointStats stats;

    public HitPercentage(PointStats stats) {
        this.stats = stats;
    }

    @Override
    public double getMissPercentage() {

        if (stats.getTotalPoints() == 0) {
            return 0;
        }

        return ((double) stats.getMissPoints()
                / stats.getTotalPoints()) * 100.0;
    }
}
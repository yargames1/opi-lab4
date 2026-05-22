package org.example.mbeans;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class PointStats extends NotificationBroadcasterSupport
        implements PointStatsMBean{

    private int totalPoints = 0;
    private int missPoints = 0;

    private long sequenceNumber = 1;

    public synchronized void addPoint(boolean hit) {
        totalPoints++;

        if (!hit) {
            missPoints++;
        }

        if (totalPoints % 15 == 0) {
            Notification notification = new Notification(
                    "points.multipleOf15",
                    this,
                    sequenceNumber++,
                    "Количество точек кратно 15: " + totalPoints
            );

            sendNotification(notification);
        }
    }

    @Override
    public int getTotalPoints() {
        return totalPoints;
    }

    @Override
    public int getMissPoints() {
        return missPoints;
    }
}

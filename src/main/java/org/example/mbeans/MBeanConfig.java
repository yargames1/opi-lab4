package org.example.mbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

@ApplicationScoped
public class MBeanConfig {

    private PointStats pointStats;

    @PostConstruct
    public void init() {

        try {

            MBeanServer server =
                    ManagementFactory.getPlatformMBeanServer();

            pointStats = new PointStats();

            HitPercentage hitPercentage =
                    new HitPercentage(pointStats);

            ObjectName statsName =
                    new ObjectName("org.example:type=PointStats");

            ObjectName percentName =
                    new ObjectName("org.example:type=HitPercentage");

            server.registerMBean(pointStats, statsName);

            server.registerMBean(hitPercentage, percentName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PointStats getPointStats() {
        return pointStats;
    }
}
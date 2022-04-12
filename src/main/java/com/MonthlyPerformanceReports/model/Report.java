package com.MonthlyPerformanceReports.model;

public class Report {
    private long topPerformersThreshold;
    private boolean useExprienceMultiplier;
    private long periodLimit;

    public Report(long topPerformersThreshold, boolean useExprienceMultiplier, long periodLimit) {
        this.topPerformersThreshold = topPerformersThreshold;
        this.useExprienceMultiplier = useExprienceMultiplier;
        this.periodLimit = periodLimit;
    }

    public long getTopPerformersThreshold() {
        return this.topPerformersThreshold;
    }

    public boolean isUseExprienceMultiplier() {
        return this.useExprienceMultiplier;
    }

    public long getPeriodLimit() {
        return this.periodLimit;
    }
}
package com.MonthlyPerformanceReports.model;

public class Employee {
    private String name;
    private long totalSales;
    private long salesPeriod;
    private double experienceMultiplier;
    private double score;

    public Employee(String name, long totalSales, long salesPeriod, double experienceMultiplier) {
        this.name = name;
        this.totalSales = totalSales;
        this.salesPeriod = salesPeriod;
        this.experienceMultiplier = experienceMultiplier;
        this.score = 0.0;
    }

    public String getName() {
        return this.name;
    }

    public long getTotalSales() {
        return this.totalSales;
    }

    public long getSalesPeriod() {
        return this.salesPeriod;
    }

    public double getExperienceMultiplier() {
        return this.experienceMultiplier;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double score) {
        this.score = score;
    }


}
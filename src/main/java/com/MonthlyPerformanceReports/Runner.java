package com.MonthlyPerformanceReports;

import com.MonthlyPerformanceReports.controller.ReportControllerImpl;
import com.MonthlyPerformanceReports.io.ConsoleReader;
import com.MonthlyPerformanceReports.io.ConsoleWriter;

import java.io.IOException;

public class Runner implements Runnable {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;
    private final ReportControllerImpl controller;

    public Runner() {
        this.controller = new ReportControllerImpl();
        this.reader = new ConsoleReader();
        this.writer = new ConsoleWriter();
    }

    public void run() {
        try {
            executeApp();
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            this.writer.writeLine(e.getMessage());
        }
    }

    private void executeApp() throws IOException {

        String employeeData, reportData;

        System.out.println("Please, enter path to JSON employees data file in the format \"D:/temp/File1.json\" :");

        employeeData = this.reader.readLine();
        this.controller.readJsonEmployeesData(employeeData.trim());


        System.out.println("Please, enter path to JSON report definition file in the format \"D:/temp/File2.json\" :");
        reportData = this.reader.readLine();
        this.controller.readJsonReportDefinition(reportData.trim());


    }
}
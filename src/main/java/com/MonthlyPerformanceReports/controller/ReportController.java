package com.MonthlyPerformanceReports.controller;

import com.MonthlyPerformanceReports.model.Employee;
import com.MonthlyPerformanceReports.model.Report;

import java.util.ArrayList;

public interface ReportController {

    void readJsonEmployeesData(String path);

    void readJsonReportDefinition(String path);

    void createReportFile(ArrayList<Employee> employees, Report report);
}
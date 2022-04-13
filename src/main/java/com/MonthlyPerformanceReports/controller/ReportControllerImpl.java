package com.MonthlyPerformanceReports.controller;

import com.MonthlyPerformanceReports.io.ConsoleWriter;
import com.MonthlyPerformanceReports.model.Employee;
import com.MonthlyPerformanceReports.model.Report;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ReportControllerImpl implements ReportController{
    private final ConsoleWriter writer = new ConsoleWriter();
    private FileReader fileReader;
    private final JSONParser jsonParser = new JSONParser();
    private final ArrayList<Employee> employees = new ArrayList<>();

    @Override
    public void readJsonEmployeesData(String data) {
        try {
            fileReader = new FileReader(data.replace('/', '\\'));
            Object object = jsonParser.parse(fileReader);
            // We need to parse the data to JSONArray because we have array of objects at this JSON file
            JSONArray jsonData = (JSONArray) object;

            for (Object obj : jsonData) {
                if (obj instanceof JSONObject) {
                    JSONObject jsonObject = ((JSONObject) obj);

                    String name = (String) jsonObject.get("name");
                    long totalSales = (long) jsonObject.get("totalSales");
                    long salesPeriod = (long) jsonObject.get("salesPeriod");
                    double experienceMultiplier = (double) jsonObject.get("experienceMultiplier");

                    Employee employee = new Employee(name, totalSales, salesPeriod, experienceMultiplier);

                    employees.add(employee);
                }
            }
        } catch (FileNotFoundException e) {
            this.writer.writeLine("File not found!");
        } catch (ParseException e) {
            this.writer.writeLine("Unable to parse file!");
        } catch (IOException e) {
            this.writer.writeLine("Failed I/O operation!");
        }
    }

    @Override
    public void readJsonReportDefinition(String data) {
        try {

            fileReader = new FileReader(data.replace('/', '\\'));
            Object object = jsonParser.parse(fileReader);
            JSONObject jsonObject = ((JSONObject) object);

            long topPerformersThreshold = (long) jsonObject.get("topPerformersThreshold");
            boolean useExprienceMultiplier = (boolean) jsonObject.get("useExprienceMultiplier");
            long periodLimit = (long) jsonObject.get("periodLimit");

            Report report = new Report(topPerformersThreshold, useExprienceMultiplier, periodLimit);
            for (Employee employee : employees) {

                double score;
                if(report.isUseExprienceMultiplier()){
                    score = employee.getTotalSales() * 1.0 / employee.getSalesPeriod() * employee.getExperienceMultiplier();
                } else{
                    score = employee.getTotalSales() * 1.0 / employee.getSalesPeriod();
                }

                employee.setScore(score);
            }

            createReportFile(employees, report);

        } catch (FileNotFoundException e) {
            this.writer.writeLine("File not found!");
        } catch (ParseException e) {
            this.writer.writeLine("Unable to parse file!");
        } catch (IOException e) {
            this.writer.writeLine("Failed I/O operation!");
        }
    }
    @Override
    public void createReportFile(ArrayList<Employee> employees, Report report) {

        double totalScores = employees.stream().mapToDouble(Employee::getScore).sum();

        for (Employee employee : employees) {

            if (employee.getSalesPeriod() <= report.getPeriodLimit()) {

                if (employee.getScore() >= totalScores * (report.getTopPerformersThreshold() * 1.0 / 100)) {
                    try {
                        PrintWriter writer = new PrintWriter("result.csv");

                        writer.println("Name, Score");
                        employees
                                .forEach(emp -> writer.println(emp.getName() + ", " + emp.getScore()));

                        writer.close();
                    } catch (IOException e) {
                        this.writer.writeLine("Failed I/O operation!");
                    }
                }
            }
        }
    }
}
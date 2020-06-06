package com.test.controller.worker;


import com.test.controller.admin.salaryEntry.ShareData;
import com.test.entity.Worker;
import com.test.utils.Conversion;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {

    private static Map<String,Double> salary = new HashMap<>();

    public static void setWorker(Worker worker)
    {
        salary = Conversion.stringToMap(worker.getTimeAndSalary());
    }

    public static Double getSalary(int year, int month)
    {
        return salary.get(String.valueOf(year)+month);
    }

    public static void setSalary(int year, int month, double n)
    {
        salary.put(String.valueOf(year)+month,n);
    }

    public static double getAverageSalary()
    {
        AtomicReference<Double> sum = new AtomicReference<>(0.0);
        salary.forEach((k, v)-> sum.updateAndGet(v1 -> v1 + v));
        return sum.get();
    }

    public static String getTimeAndSalary()
    {
        return Conversion.mapToString(salary);
    }

    public static Integer[] getAllYears()
    {
        Set<Integer> years = new HashSet<>();
        salary.forEach((k,v)->years.add(Integer.valueOf(k.substring(0,4))));
        return years.toArray(Integer[]::new);
    }

    public static Integer[] getAllMonths()
    {
        List<Integer> months = new ArrayList<>();
        salary.forEach((k,v)->months.add(Integer.valueOf(k.substring(4,k.length()>=6 ? 6 : 5))));
        return months.toArray(Integer[]::new);
    }

    public static Double[] getAllSalaries()
    {
        List<Double> salaries = new ArrayList<>();
        salary.forEach((k,v)->salaries.add(Double.valueOf(v)));
        return salaries.toArray(Double[]::new);
    }

    public static String combinepositionAndDepartment(String position, String department) {
        return position + "+" + department;
    }

    public static String combineNameAndCellphone(String name, String cellphone) {
        return name + "(" + cellphone + ")";
    }

    public static Worker getWorkerByCombineValue(String combineValue) {
        return ShareData.workers.stream().filter(
                t -> t.getCellphone().equals(combineValue.substring(combineValue.indexOf("(") + 1, combineValue.indexOf(")"))))
                .findFirst().orElse(null);
    }

    public static Map<Integer,Double> getMonthsAndSalariesByYears(Integer year)
    {
        Map<Integer,Double> map = new HashMap<>();
        salary.forEach((k,v)->
        {
            if(k.contains(year.toString()))
            {
                map.put(Integer.parseInt(k.substring(4,k.length() >= 6 ? 6:5)),v);
            }
        });
        return map;
    }
}

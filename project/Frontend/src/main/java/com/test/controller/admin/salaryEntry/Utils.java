package com.test.controller.admin.salaryEntry;


import com.test.entity.Worker;
import com.test.utils.Check;
import com.test.utils.Conversion;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class Utils {

    private static Map<String,Double> salary = new HashMap<>();

    public static void setWorker(Worker worker)
    {
        salary = Check.isEmpty(worker.getTimeAndSalary()) ? new HashMap<>() :
            Conversion.stringToMap(worker.getTimeAndSalary());
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
        return sum.get() / salary.size();
    }

    public static String getTimeAndSalary()
    {
        return Conversion.mapToString(salary);
    }

    public static String combinePositionAndDepartment(String position, String department) {
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

}

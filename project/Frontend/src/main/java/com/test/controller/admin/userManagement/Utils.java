package com.test.controller.admin.userManagement;


import com.test.entity.Worker;

public class Utils {
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
}

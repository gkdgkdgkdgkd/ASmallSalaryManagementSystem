package com.test.constant.path;

/**
 * FXML文件路径
 */
public final class FXMLPath {

    private static final String FXML_PREFIX = "/fxml/";
    private static final String FXML_SUFFIX = ".fxml";

    private static final String START_PATH = "start/";
    public static final String MAIN = FXML_PREFIX + START_PATH + "Main" + FXML_SUFFIX;
    public static final String SIGN_IN_UP = FXML_PREFIX + START_PATH + "SignInUp" + FXML_SUFFIX;
    public static final String RETRIEVE_PASSWORD = FXML_PREFIX + START_PATH + "RetrievePassword" + FXML_SUFFIX;

    private static final String ADMIN_PATH = "admin/";
    public static final String ADMIN = FXML_PREFIX + ADMIN_PATH + "Admin" + FXML_SUFFIX;
    public static final String USER_MANAGEMENT = FXML_PREFIX + ADMIN_PATH + "UserManagement" + FXML_SUFFIX;
    public static final String SALARY_ENTRY = FXML_PREFIX + ADMIN_PATH + "SalaryEntry" + FXML_SUFFIX;

    private static final String WORKER_PATH = "worker/";
    public static final String WORKER = FXML_PREFIX + WORKER_PATH + "Worker" + FXML_SUFFIX;
    public static final String SALARY_QUERY = FXML_PREFIX + WORKER_PATH + "SalaryQuery" + FXML_SUFFIX;
    public static final String USER_INFORMATION = FXML_PREFIX + WORKER_PATH + "UserInformation" + FXML_SUFFIX;

    public static final String MESSAGE_BOX = FXML_PREFIX + "MessageBox" + FXML_SUFFIX;
}

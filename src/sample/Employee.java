package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class Employee extends Person {
    private static int SSN = 0;
    private String firstName = null;
    private String surName = null;
    private int employmentDate = 0;
    private static int gamesSold = 0;
    private static double income = 0;
    private String email = null;
    private EmployeeAccount employeeAccount = null;
    private Boss supervisor = null;


    public Employee() {
    }

    public Employee(int SSN, String firstName, String surName, String email, String userName) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        EmployeeAccount employeeAccount = new EmployeeAccount ( userName );
        this.employeeAccount = employeeAccount;
    }
    public Employee(String firstName, String surName, int employmentDate, int gamesSold, double income, String userName) {
        setFirstName ( firstName );
        setSurname ( surName );
        setEmploymentDate ( employmentDate );
        setGamesSold ( gamesSold );
        setIncome ( income );
        EmployeeAccount employeeAccount = new EmployeeAccount ( userName );
        setEmployeeAccount ( employeeAccount );
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public EmployeeAccount getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(EmployeeAccount employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public int getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(int employmentDate) {
        this.employmentDate = employmentDate;
    }

    public static int getGamesSold() {
        return gamesSold;
    }

    public static void setGamesSold(int gamesSold) {
        Employee.gamesSold = gamesSold;
    }

    public static double getIncome() {
        return income;
    }

    public static void setIncome(double income) {
        Employee.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boss getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Boss supervisor) {
        this.supervisor = supervisor;
    }
}




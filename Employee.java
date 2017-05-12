package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class Employee extends Person {
    private int employmentDate = 0;
    private int gamesSold = 0;
    private double income = 0;
    private String email = null;
    private EmployeeAccount employeeAccount = null;


    public Employee() {
    }

    public Employee(int SSN, String firstName, String surname, String email, String userName) {
        setSSN(SSN);
        setFirstName(firstName);
        setSurname ( surname );
        setEmail(email);
        EmployeeAccount employeeAccount = new EmployeeAccount ( userName );
        setEmployeeAccount(employeeAccount);
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

    public int getGamesSold() {
        return gamesSold;
    }

    public void setGamesSold(int gamesSold) {
        this.gamesSold = gamesSold;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}




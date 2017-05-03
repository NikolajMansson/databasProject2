package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class Boss extends Employee {
    private static int SSN = 0;
    private String firstName = null;
    private String surName = null;
    private int employmentDate = 0;
    private static int gamesSold = 0;
    private static double income = 0;
    private String email = null;
    private BossAccount bossAccount = null;


    public Boss() {
    }

    public Boss(int SSN, String firstName, String surName, String email, String userName) {
        setSSN ( SSN );
        setFirstName ( firstName );
        setSurName ( surName );
        setEmail ( email );
        BossAccount bossAccount = new BossAccount ( userName );
        setBossAccount ( bossAccount );
    }

    public Boss(String firstName, String surName, int employmentDate, int gamesSold, double income, String userName) {
        setFirstName ( firstName );
        setSurname ( surName );
        setEmploymentDate ( employmentDate );
        setGamesSold ( gamesSold );
        setIncome ( income );
        BossAccount bossAccount = new BossAccount ( userName );
        setBossAccount ( bossAccount );
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
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
        Boss.gamesSold = gamesSold;
    }

    public static double getIncome() {
        return income;
    }

    public static void setIncome(double income) {
        Boss.income = income;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BossAccount getBossAccount() {
        return bossAccount;
    }

    public void setBossAccount(BossAccount bossAccount) {
        this.bossAccount = bossAccount;
    }
}

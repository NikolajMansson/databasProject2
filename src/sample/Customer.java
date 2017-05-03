package sample;

/**
 * Created by Nikolaj on 2017-04-29.
 */
public class Customer extends Person {
    private int SSN = 0;
    private String firstName = null;
    private String surName = null;
    private int registrationDate = 0;
    private String userName = null;
    private String password = null;
    private String email = null;
    private CustomerAccount customerAccount = null;

    Customer() {

    }

    public Customer(int SSN, String firstName, String surName, String email, String userName) {
        this.SSN = SSN;
        this.firstName = firstName;
        this.surName = surName;
        this.email = email;
        CustomerAccount customerAccount = new CustomerAccount ( userName );
        this.customerAccount = customerAccount;
    }

    @Override
    public int getSSN() {
        return SSN;
    }

    @Override
    public void setSSN(int SSN) {
        this.SSN = SSN;
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

    public int getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(int registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerAccount getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(CustomerAccount customerAccount) {
        this.customerAccount = customerAccount;
    }
}

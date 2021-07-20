package ir.maktab.hw7.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class User {
    private int id;
    private String userName;
    private String nationalCode;
    private LocalDate birthday;
    private String password;
    private boolean status;
    private BigDecimal balance;

    public User() {
    }

    public User(int id, String userName, String nationalCode, LocalDate birthday) {
        this.id = id;
        this.userName = userName;
        this.nationalCode = nationalCode;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}

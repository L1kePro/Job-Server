package net.thumbtack.school.hiring.request;

import com.google.gson.Gson;
import net.thumbtack.school.hiring.errors.HiringErrorCode;
import net.thumbtack.school.hiring.errors.HiringException;

public class RegisterEmployerDtoRequest {
    Gson gson = new Gson();
    private String firstName, lastName, patronymic, email, login, password, company, address;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) throws HiringException {
        if (firstName == null || firstName.length() < 3) throw new HiringException(HiringErrorCode.WRONG_FIRSTNAME);
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSubName() {
        return patronymic;
    }
    public void setSubName(String patronymic) {
        this.patronymic = patronymic;
    }
    public String getMail() {
        return email;
    }
    public void setMail(String email) {
        this.email = email;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) throws HiringException {
        if (login == null || login.length() < 3 ) throw new HiringException(HiringErrorCode.WRONG_LOGIN);
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws HiringException {
        if (password == null || password.length() < 3) throw new HiringException(HiringErrorCode.WRONG_PASSWORD);
        this.password = password;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getCompany() {
        return company;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public RegisterEmployerDtoRequest(String jsonString) throws HiringException {
        RegisterEmployerDtoRequest redr = gson.fromJson(jsonString, RegisterEmployerDtoRequest.class);
        try {
            setFirstName(redr.getFirstName());
        } catch (HiringException e) {
            throw new HiringException(HiringErrorCode.WRONG_FIRSTNAME);
        }

        try {
            setLogin(redr.getLogin());
        } catch (HiringException e) {
            throw new HiringException(HiringErrorCode.WRONG_LOGIN);
        }

        try {
            setPassword(redr.getPassword());
        } catch (HiringException e) {
            throw new HiringException(HiringErrorCode.WRONG_PASSWORD);
        }
        lastName = redr.getLastName();
        patronymic = redr.getSubName();
        email = redr.getMail();
        company = redr.getCompany();
        address = redr.getAddress();
    }
}

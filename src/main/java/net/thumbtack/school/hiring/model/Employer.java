package net.thumbtack.school.hiring.model;

import net.thumbtack.school.hiring.errors.HiringException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Employer {
    private int id;
    private String firstName, lastName, patronymic, email, login, password, company, address, uuid;
    List<Vacancy> vacancy;

    public Employer() {
        super();
    }

    public Employer(int id, String firstName, String lastName, String patronymic, String email,
                    String login, String password, String company, String address, List<Vacancy> vacancy) throws ClassNotFoundException, HiringException {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.login = login;
        this.password = password;
        this.company = company;
        this.address = address;
        this.uuid = String.valueOf(UUID.randomUUID());
        this.vacancy = vacancy;
    }

    public Employer(String firstName, String lastName, String patronymic, String email,
                    String login, String password, String company, String address, List<Vacancy> vacancy) throws ClassNotFoundException, HiringException {
        this(0, firstName, lastName, patronymic, email, login, password, company, address, vacancy);
    }

    public Employer(String firstName, String lastName, String patronymic, String email,
                    String login, String password, String company, String address) throws ClassNotFoundException, HiringException {
        this(0, firstName, lastName, patronymic, email, login, password, company, address, new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Vacancy> getVacancy() {
        return vacancy;
    }

    public void setVacancy(List<Vacancy> vacancy) {
        this.vacancy = vacancy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employer)) return false;
        Employer employer = (Employer) o;
        return getId() == employer.getId() &&
                Objects.equals(getFirstName(), employer.getFirstName()) &&
                Objects.equals(getLastName(), employer.getLastName()) &&
                Objects.equals(getPatronymic(), employer.getPatronymic()) &&
                Objects.equals(getEmail(), employer.getEmail()) &&
                Objects.equals(getLogin(), employer.getLogin()) &&
                Objects.equals(getPassword(), employer.getPassword()) &&
                Objects.equals(getCompany(), employer.getCompany()) &&
                Objects.equals(getAddress(), employer.getAddress()) &&
                Objects.equals(getUuid(), employer.getUuid()) &&
                Objects.equals(getVacancy(), employer.getVacancy());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getFirstName(), getLastName(), getPatronymic(), getEmail(), getLogin(), getPassword(), getCompany(), getAddress(), getUuid(), getVacancy());
    }
}

package net.thumbtack.school.hiring.model;

import com.google.gson.Gson;

import net.thumbtack.school.hiring.errors.HiringException;

import java.util.*;

public class Employee {
    private int id;
    private String firstName, lastName, patronymic, email, login, password, uuid;
    private Boolean active;
    private List<Skill> skill;

    public Employee() {
        super();
    }

    public Employee(int id, String firstName, String lastName, String patronymic,
                    String email, String login, String password, List<Skill> skill) throws ClassNotFoundException, HiringException {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.login = login;
        this.password = password;
        this.uuid = String.valueOf(UUID.randomUUID());
        this.active = true;
        this.skill = skill;
    }

    public Employee (String firstName, String lastName, String patronymic, String email, String login, String password, List<Skill> skill) throws ClassNotFoundException, HiringException {
        this(0, firstName, lastName, patronymic, email, login, password, skill);
    }

    public Employee (String firstName, String lastName, String patronymic, String email, String login, String password) throws ClassNotFoundException, HiringException {
        this(0, firstName, lastName, patronymic, email, login, password, new ArrayList<>());
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
    public void setLogin(String login){
           this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
    public List<Skill> getSkill() {
        return skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getId() == employee.getId() &&
                Objects.equals(getFirstName(), employee.getFirstName()) &&
                Objects.equals(getLastName(), employee.getLastName()) &&
                Objects.equals(getPatronymic(), employee.getPatronymic()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getLogin(), employee.getLogin()) &&
                Objects.equals(getPassword(), employee.getPassword()) &&
                Objects.equals(getUuid(), employee.getUuid()) &&
                Objects.equals(getActive(), employee.getActive()) &&
                Objects.equals(getSkill(), employee.getSkill());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getFirstName(), getLastName(), getPatronymic(), getEmail(), getLogin(), getPassword(), getUuid(), getActive(), getSkill());
    }
}

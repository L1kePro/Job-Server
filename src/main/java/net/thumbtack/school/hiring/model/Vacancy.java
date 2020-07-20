package net.thumbtack.school.hiring.model;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Vacancy{
   private int id, id_employer, salary;
   private String name;
   Boolean active;
   private List<Skill> skill;

    public Vacancy() { super(); }

    public Vacancy(int id, int id_employer, int salary, String name, List<Skill> skill) {
        super();
        this.id = id;
        this.id_employer = id_employer;
        this.salary = salary;
        this.name = name;
        this.skill = skill;
        this.active = true;
    }

    public Vacancy(int id_employer, int salary, String name, List<Skill> skill) {
     this(0, id_employer, salary, name, skill);
    }

    public Vacancy(int salary, String name, List<Skill> skill) {
        this(0, 0, salary, name, skill);
    }

    public Vacancy(int salary, String name) {
        this(0, 0, salary, name, new ArrayList<>());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_employer() {
        return id_employer;
    }

    public void setId_employer(int id_employer) {
        this.id_employer = id_employer;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy = (Vacancy) o;
        return getId() == vacancy.getId() &&
                getId_employer() == vacancy.getId_employer() &&
                getSalary() == vacancy.getSalary() &&
                Objects.equals(getName(), vacancy.getName()) &&
                Objects.equals(getActive(), vacancy.getActive()) &&
                Objects.equals(getSkill(), vacancy.getSkill());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getId_employer(), getSalary(), getName(), getActive(), getSkill());
    }
}

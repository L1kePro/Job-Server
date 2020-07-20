package net.thumbtack.school.hiring.model;

import java.util.Objects;

public class Skill {
    private int id, lvl;
    private String name;


    public Skill() {super();}

    public Skill(int id, String name, int lvl) {
        super();
        this.id = id;
        this.name = name;
        this.lvl = lvl;
    }

    public Skill(String name, int lvl) {
        this(0, name, lvl);
        }

    public Skill(String name) {
        this.id = 0;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Skill)) return false;
        Skill skill = (Skill) o;
        return getId() == skill.getId() &&
                getLvl() == skill.getLvl() &&
                Objects.equals(getName(), skill.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getLvl(), getName());
    }
}

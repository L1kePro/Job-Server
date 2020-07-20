package net.thumbtack.school.hiring.mybatis.dao;

import net.thumbtack.school.hiring.model.Skill;

import java.util.List;

public interface SkillsDao {

    // вставляет Skill в базу данных. Если name не null.
    Skill insert(Skill skill);

    //Возвращает Skill по его ID
    Skill getById(int id);

    //Возвращает все Skill
    List<Skill> getAll();

    // удаляет Skill из базы данных
    void delete(Skill skill);

    // удаляет все Skill из базы данных
    void deleteAll();

}

package net.thumbtack.school.hiring.mybatis.mappers;

import net.thumbtack.school.hiring.model.Skill;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SkillMapper {
    @Insert("INSERT INTO skills (name, lvl) VALUES ( #{skills.name}, #{skills.lvl})")
    @Options(useGeneratedKeys = true, keyProperty = "skills.id")
    Integer insert(@Param("skills")Skill skill);
//Возвращает ID по имени и уровню навыка
@Select("SELECT id FROM skills WHERE name = #{name} and lvl = #{lvl}")
Integer getIdByNameLvl(Skill skill);

    @Select("SELECT id, name, lvl FROM skills WHERE id = #{id}")
    Skill getById(int id);
//Возвращает навыки по ID Работника
    @Select("SELECT skills.id, skills.name, skills.lvl FROM employee_skills, skills where id_employee = #{id_employee}")
    List<Skill> getByIdEmployee(int id_employee);
    //Возвращает навыки вакансий по ID вакансии
    @Select("SELECT skills.id, skills.name, skills.lvl FROM vacancy_skills, skills where id_vacancy = #{id_vacancy}")
    List<Skill> getByIdVacancy(int id_vacancy);

    @Select("SELECT id, name FROM skills")
    @Results({ @Result(property = "id", column = "id") })
    List<Skill> getAll();

    @Delete("DELETE FROM skills WHERE id = #{skills.id}")
    int delete(@Param("skills") Skill skill);

    @Delete("DELETE FROM skills")
    void deleteAll();
}

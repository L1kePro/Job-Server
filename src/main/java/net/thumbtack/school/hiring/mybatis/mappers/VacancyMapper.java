package net.thumbtack.school.hiring.mybatis.mappers;

import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Vacancy;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface VacancyMapper {
    @Insert("INSERT INTO vacancies (id_employer, name, salary) VALUES ( #{vacancies.id_employer}, #{vacancies.name}, #{vacancies.salary})")
    @Options(useGeneratedKeys = true, keyProperty = "vacancies.id")
    Integer insert(@Param("vacancies")Vacancy vacancy);

    @Select("SELECT id, id_employer, name, salary, isactive as active FROM vacancies where id_employer = #{id_employer}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "skill", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.SkillMapper.getByIdEmployee", fetchType = FetchType.LAZY))
    })
    List<Vacancy> getByIdEmployer(int id_employer);

}

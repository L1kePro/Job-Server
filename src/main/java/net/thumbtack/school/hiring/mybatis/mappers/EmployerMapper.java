package net.thumbtack.school.hiring.mybatis.mappers;

import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Vacancy;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface EmployerMapper {
    @Insert("INSERT INTO employer (firstName, lastName, patronymic, email, login, pass, uuid, company, address)" +
            " VALUES ( #{firstName}, #{lastName}, #{patronymic}, #{email}, #{login}, #{password}, #{uuid}, #{company}, #{address});")
    @Options(useGeneratedKeys = true)
    Integer insert(Employer employer);

    @Insert("INSERT INTO vacancies (id_employer, name, salary) VALUES (#{employer.id}, #{vacancy.name}, #{vacancy.salary})")
    @Options(useGeneratedKeys = true)
    void addVacancy(@Param("employer")Employer employer,@Param("vacancy") Vacancy vacancy);

    @Insert("INSERT INTO logins (login) VALUES ( #{login});")
    @Options(useGeneratedKeys = true)
    Integer insertLogin(String login);

    @Insert("INSERT INTO uuids (uuid) VALUES ( #{uuid});")
    @Options(useGeneratedKeys = true)
    Integer insertUuid(String uuid);

    //Получает работодателя с листом вакансий по его ID
    @Select("SELECT id, firstName, lastName, patronymic, email, login, pass as password, uuid, company, address FROM employer WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vacancy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.VacancyMapper.getByIdEmployer", fetchType = FetchType.LAZY))
    })
    Employer getById(int id);

    @Select("SELECT id, firstName, lastName, patronymic, email, login, pass as password, uuid, company, address FROM employer")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "vacancy", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.VacancyMapper.getByIdEmployer", fetchType = FetchType.LAZY))
    })
    List<Employer> getAll();

    @Update("UPDATE employer SET firstName = #{firstName}, lastName = #{lastName}, patronymic = #{patronymic}," +
            "email = #{email}, pass = #{password}, company = #{company}, address = #{address}  WHERE id = #{id} ")
    void update(Employer employer);

    @Delete("DELETE FROM employer WHERE id = #{employer.id}")
    int delete(@Param("employer") Employer employer);

    @Insert("DELETE FROM logins WHERE login = #{login};")
    @Options(useGeneratedKeys = true)
    Integer deleteLogin(String login);

    @Insert("DELETE FROM uuids WHERE uuid = #{uuid};")
    @Options(useGeneratedKeys = true)
    Integer deleteUuid(String uuid);

    @Delete("DELETE FROM logins")
    void deleteAllLogins();

    @Delete("DELETE FROM uuids")
    void deleteAllUuids();

    @Delete("DELETE FROM employer")
    void deleteAll();

}

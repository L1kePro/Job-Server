package net.thumbtack.school.hiring.mybatis.mappers;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Skill;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface EmployeeMapper {
    @Insert("INSERT INTO employee (firstName, lastName, patronymic, email, login, pass, active, uuid)" +
            " VALUES ( #{firstName}, #{lastName}, #{patronymic}, #{email}, #{login}, #{password}, #{active}, #{uuid});")
    @Options(useGeneratedKeys = true)
    Integer insert(Employee employee);

    @Insert("INSERT INTO logins (login) VALUES ( #{login});")
    @Options(useGeneratedKeys = true)
    Integer insertLogin(String login);

    @Insert("INSERT INTO uuids (uuid) VALUES ( #{uuid});")
    @Options(useGeneratedKeys = true)
    Integer insertUuid(String uuid);

    @Insert("INSERT INTO employee_skills (id_employee, id_skill) VALUES (#{employee.id}, #{skill.id})")
    @Options(useGeneratedKeys = true)
    void addSkill(@Param("employee")Employee employee,@Param("skill") Skill skill);
//Получает работника с листом навыков по его ID
    @Select("SELECT id, firstName, lastName, patronymic, email, login, pass as password, active, uuid FROM employee WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "skill", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.SkillMapper.getByIdEmployee", fetchType = FetchType.LAZY))
    })
    Employee getById(int id);

    @Select("SELECT id, firstName, lastName, patronymic, email, login, pass as password, active, uuid FROM employee")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "skill", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.SkillMapper.getByIdEmployee", fetchType = FetchType.LAZY))
    })
    List<Employee> getAll();

    @Update("UPDATE employee SET firstName = #{firstName}, lastName = #{lastName}, patronymic = #{patronymic}," +
            "email = #{email}, pass = #{password}  WHERE id = #{id}")
    void update(Employee employee);
//Возвращает uuid по login + password
    @Select("SELECT uuid FROM employee WHERE login = #{login} and pass = #{password}")
    String login (@Param("login") String login, @Param("password") String password);
//Возвращает работника по его uuid
    @Select("SELECT id, firstName, lastName, patronymic, email, login, pass as password, active, uuid FROM employee WHERE uuid = #{uuid}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "skill", column = "id", javaType = List.class,
                    many = @Many(select = "net.thumbtack.school.hiring.mybatis.mappers.SkillMapper.getByIdEmployee", fetchType = FetchType.LAZY))
    })
    Employee getByUuid(String uuid);
    //Меня состояние работника на залогинен
    @Update("UPDATE uuids SET is_online = #{1} WHERE uuid = #{uuid}")
    void SetEmployeeOnline(String uuid);

    //Меня состояние работника на разлогинен
    @Update("UPDATE uuids SET is_online = #{0} WHERE uuid = #{uuid}")
    void SetEmployeeOffline(String uuid);

    @Delete("DELETE FROM employee WHERE id = #{employee.id}")
    int delete(@Param("employee") Employee skill);

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

    @Delete("DELETE FROM employee")
    void deleteAll();

}

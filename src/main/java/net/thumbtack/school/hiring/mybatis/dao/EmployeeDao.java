package net.thumbtack.school.hiring.mybatis.dao;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Skill;

import java.util.List;

public interface EmployeeDao {

    // вставляет Employee в базу данных. Если firstName, lastName, email, login, pass, uuid не null.
    Employee insert(Employee employee);

    // получает Employee по его ID. Если такого ID нет, метод возвращает null
    Employee getById(int id);

    // получает список всех Employee. Если БД не содержит ни одного Employee, метод возвращает пустой список
    List<Employee> getAll();

    // изменяет Employee в базе данных.
    Employee update(Employee employee);

//Вставляет работника и вносит данные в таблицы с логинами и uuid
    Employee insertEmployeeTransactional(Employee employee2019);

    //Вставляет работника и вносит данные в таблицы с навыками, логинами и uuid
    Employee insertEmployeeTransactional(Employee school2018, List<Skill> skill);

    // вставляет список из Employee в базу данных.
    void batchInsert(List<Employee> employee);

    // удаляет Employee из базы данных
    void delete(Employee employee);

    // удаляет все Employee из базы данных
    void deleteAll();
//Возвращает uuid если такой работник найден и меняет его статус на активный
    String login(String login, String password);
}

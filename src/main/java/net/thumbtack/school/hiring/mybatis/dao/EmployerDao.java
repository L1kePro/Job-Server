package net.thumbtack.school.hiring.mybatis.dao;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Vacancy;

import java.util.List;

public interface EmployerDao {

    //Вставляет работодателя в БД и вносит данные в таблицы с логинами и uuid
    Employer insert(Employer employer2019);

    //Вставляет работодателя в БД и вносит данные в таблицы с навыками, логинами, вакансий и uuid
    Employer insertEmployerWithVacancy(Employer employer2019, List<Vacancy> vacancy, List<Skill> skill);

    // получает Employer по его ID. Если такого ID нет, метод возвращает null
    Employer getById(int id);

    // получает список всех Employer. Если БД не содержит ни одного Employer, метод возвращает пустой список
    List<Employer> getAll();

    // изменяет Employer в базе данных.
    Employer update(Employer employer);

    // вставляет список из Employer в базу данных.
    void batchInsert(List<Employer> employer);

    // удаляет Employer из базы данных
    void delete(Employer employer);

    // удаляет все Employer из базы данных
    void deleteAll();
}

package net.thumbtack.school.hiring.mybatis.dao;

import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Vacancy;

import java.util.List;

public interface VacanciesDao {

    // Вставляет Vacancy в базу данных. Если Employer не null,
    // Vacancy после вставки принадлежит этому работодателю.
    Vacancy insert(Employer employer, Vacancy vacancy);

    // получает Vacancy по его ID. Если такого ID нет, метод возвращает null
    Vacancy getById(int id);

    // получает список всех Vacancy, независимо от их Employer.
    // Если БД не содержит ни одного Vacancy, метод возвращает пустой список
    List<Vacancy> getAll();

    // изменяет Vacancy в базе данных. Метод не изменяет принадлежности Vacancy к Employer
    Vacancy update(Vacancy vacancy);

}

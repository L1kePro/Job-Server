package net.thumbtack.school.hiring.mybatis;

import net.thumbtack.school.hiring.errors.HiringException;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.mybatis.dao.EmployerDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestEmployerOperations extends TestBase {
    //Вставляется в БД запись о работодателе заполняя таблицы вакансии, логин и uuid, если таких записей там нет
    @Test
    public void testInsertEmployer() throws HiringException, ClassNotFoundException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy = new Vacancy(45000, "JavaDev");
        vacancies.add(vacancy);
        Employer employerIvanov = insertEmployer("Victor", "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA", vacancies);
        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertEquals(employerIvanov, employerIvanovFromDB);
    }

//    //Вставка работодателя с вакансиями и требуемыми навыками к ним
//    @Test
//    public void testInsertEmployerWithSkillTransactional() throws HiringException, ClassNotFoundException {
//        List<Skill> skills = new ArrayList<>();
//        Skill skill = new Skill("Java", 5);
//        skills.add(skill);
//        Employer employerIvanov = insertEmployerTransactional("Victor", "Viktorov", "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1",skills);
//        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
//        assertEquals(employerIvanov, employerIvanovFromDB);
//    }
    //Получение не существоющего работодателя из БД
    @Test
    public void testGetNonexistentEmployerById() {
        assertNull(EmployerDao.getById(1234567));
    }
    //Получение пустого списка работников
    @Test
    public void testGetEmployersFromEmptyEmployersTable() {
        assertEquals(0, EmployerDao.getAll().size());
    }
    //Запись в БД с неверными параметрами
    @Test(expected = RuntimeException.class)
    public void testInsertEmployerWithNullFirstName() throws HiringException, ClassNotFoundException {
        Employer employerIvanov = new Employer(null, "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA");
        EmployerDao.insert(employerIvanov);
    }
    //Изменение данных работодателя(работает если заменять даже часть данных а не все)
    @Test
    public void testUpdateEmployer() throws HiringException, ClassNotFoundException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy = new Vacancy(45000, "JavaDev");
        vacancies.add(vacancy);
        Employer employerIvanov = insertEmployer("Victor", "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA",vacancies);
        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertEquals(employerIvanov, employerIvanovFromDB);
        employerIvanov.setFirstName("Федор");
        employerIvanov.setLastName("Федоров");
        employerIvanov.setPatronymic("Федорович");
        employerIvanov.setEmail("Fedor@mail.ru");
        employerIvanov.setPassword("fedor123");
        EmployerDao.update(employerIvanov);
        employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertEquals(employerIvanov, employerIvanovFromDB);
    }
    //Изменение данных работодателя на некорректные
    @Test(expected = RuntimeException.class)
    public void testUpdateEmployerSetNullLastName() throws HiringException, ClassNotFoundException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy = new Vacancy(45000, "JavaDev");
        vacancies.add(vacancy);
        Employer employerIvanov = insertEmployer("Victor", "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA",vacancies);
        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertEquals(employerIvanov, employerIvanovFromDB);
        employerIvanov.setLastName(null);
        EmployerDao.update(employerIvanov);
    }
    //Удаление работодателя из БД
    @Test
    public void testDeleteEmployer() throws HiringException, ClassNotFoundException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy = new Vacancy(45000, "JavaDev");
        vacancies.add(vacancy);
        Employer employerIvanov = insertEmployer("Victor", "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA",vacancies);
        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertEquals(employerIvanov, employerIvanovFromDB);
        EmployerDao.delete(employerIvanov);
        employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
        assertNull(employerIvanovFromDB);
    }
    //Удаление всех работников из БД
    @Test
    public void testInsertAndDeleteTwoEmployers() throws HiringException, ClassNotFoundException {
        List<Vacancy> vacancies = new ArrayList<>();
        Vacancy vacancy = new Vacancy(45000, "JavaDev");
        vacancies.add(vacancy);
        List<Vacancy> vacancies2 = new ArrayList<>();
        Vacancy vacancy2 = new Vacancy(45000, "JavaDev");
        vacancies2.add(vacancy2);
        Employer employerIvanov = insertEmployer("Victor", "Viktorov",
                "Viktorovic", "Viktor@mail.ru", "Employer1", "pass1", "apple", "USA",vacancies);
        Employer employerPetrov = insertEmployer("Петр", "Петров", "Петрович",
                "Viktor@mail.ru", "Employer2",  "pass2", "Microsoft", "Toronto", vacancies2);
        List<Employer> employers = new ArrayList<>();
        employers.add(employerIvanov);
        employers.add(employerPetrov);
        List<Employer> employersFromDB = EmployerDao.getAll();
        assertEquals(employers, employersFromDB);
        EmployerDao.deleteAll();
        employersFromDB = EmployerDao.getAll();
        assertEquals(0, employersFromDB.size());
    }

//    //Вставляется в БД запись о работнике заполняя таблицы логин и uuid, если таких заисей там нет, после чего удаляет вставленные записи
//    @Test
//    public void testDeleteEmployer2() throws HiringException, ClassNotFoundException {
//        Employer employerIvanov = insertEmployerTransactional("Victor", "Viktorov", "Viktorovic","Viktor@mail.ru", "Employer1", "pass1");
//        Employer employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
//        assertEquals(employerIvanov, employerIvanovFromDB);
//        EmployerDao.delete(employerIvanov);
//        employerIvanovFromDB = EmployerDao.getById(employerIvanov.getId());
//        assertNull(employerIvanovFromDB);
//    }


//    @Test
//    public void testIfCondition() {
//        Employer employerIvanov = insertEmployer("Иван", "Иванов", 5, null);
//        Employer employerPetrovPetr = insertEmployer("Петр", "Петров", 4, null);
//        Employer employerPetrovFedor = insertEmployer("Федор", "Петров", 3, null);
//        Employer employerSidorov = insertEmployer("Петр", "Сидоров", 4, null);
//        Employer employerSidorenko = insertEmployer("Иван", "Сидоренко", 2, null);
//
//        List<Employer> employersFull = new ArrayList<>();
//        employersFull.add(employerIvanov);
//        employersFull.add(employerPetrovPetr);
//        employersFull.add(employerPetrovFedor);
//        employersFull.add(employerSidorov);
//        employersFull.add(employerSidorenko);
//
//        List<Employer> employersIvan = new ArrayList<>();
//        employersIvan.add(employerIvanov);
//        employersIvan.add(employerSidorenko);
//
//        List<Employer> employersSidor = new ArrayList<>();
//        employersSidor.add(employerSidorov);
//        employersSidor.add(employerSidorenko);
//
//        List<Employer> employersPetrovWithRating4 = new ArrayList<>();
//        employersPetrovWithRating4.add(employerPetrovPetr);
//
//        List<Employer> employersFullFromDB = employerDao.getAll();
//        employersFullFromDB.sort(Comparator.comparingInt(Employer::getId));
//        assertEquals(employersFull, employersFullFromDB);
//
//        List<Employer> employersIvanFromDB = employerDao.getAllWithParams("Иван", null, null);
//        employersIvanFromDB.sort(Comparator.comparingInt(Employer::getId));
//        assertEquals(employersIvan, employersIvanFromDB);
//
//        List<Employer> employersSidorFromDB = employerDao.getAllWithParams(null, "Сидор%", null);
//        employersSidorFromDB.sort(Comparator.comparingInt(Employer::getId));
//        assertEquals(employersSidor, employersSidorFromDB);
//
//        List<Employer> employersPetrovWithRating4FromDB = employerDao.getAllWithParams(null, "Петров", 4);
//        employersPetrovWithRating4FromDB.sort(Comparator.comparingInt(Employer::getId));
//        assertEquals(employersPetrovWithRating4, employersPetrovWithRating4FromDB);
//
//    }
//
//    @Test
//    public void testBatchInsertEmployers() {
//        Employer employerIvanov = new Employer("Иван", "Иванов", 5);
//        Employer employerPetrovPetr = new Employer("Петр", "Петров", 4);
//        Employer employerPetrovFedor = new Employer("Федор", "Петров", 3);
//        Employer employerSidorov = new Employer("Петр", "Сидоров", 4);
//        Employer employerSidorenko = new Employer("Иван", "Сидоренко", 2);
//
//        List<Employer> employersFull = new ArrayList<>();
//        employersFull.add(employerIvanov);
//        employersFull.add(employerPetrovPetr);
//        employersFull.add(employerPetrovFedor);
//        employersFull.add(employerSidorov);
//        employersFull.add(employerSidorenko);
//        employerDao.batchInsert(employersFull);
//        List<Employer> employersFullFromDB = employerDao.getAll();
//        employersFullFromDB.sort(Comparator.comparingInt(Employer::getId));
//        assertEquals(employersFull, employersFullFromDB);
//    }

}



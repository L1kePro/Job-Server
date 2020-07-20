package net.thumbtack.school.hiring.mybatis;

import net.thumbtack.school.hiring.errors.HiringException;
import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Skill;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestEmployeeOperations extends TestBase {
//Запись работника в БД
    @Test
    public void testInsertEmployee() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployee("Victor", "Viktorov", "Viktorovic", "Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
    }
    //Получение не существоющего работника из БД
    @Test
    public void testGetNonexistentEmployeeById() {
        assertNull(EmployeeDao.getById(1234567));
    }
//Получение пустого списка работников
    @Test
    public void testGetEmployeesFromEmptyEmployeesTable() {
        assertEquals(0, EmployeeDao.getAll().size());
    }
//Запись в БД с неверными параметрами
    @Test(expected = RuntimeException.class)
    public void testInsertEmployeeWithNullFirstName() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = new Employee(null, "Viktorov","Viktorovic", "Viktor@mail.ru", "Employee1", "pass1");
        EmployeeDao.insert(employeeIvanov);
    }
//Изменение данных работника(работает если заменять даже часть данных а не все)
    @Test
    public void testUpdateEmployee() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployee("Victor", "Viktorov", "Viktorovic","Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
        employeeIvanov.setFirstName("Федор");
        employeeIvanov.setLastName("Федоров");
        employeeIvanov.setPatronymic("Федорович");
        employeeIvanov.setEmail("Fedor@mail.ru");
        employeeIvanov.setPassword("fedor123");
        EmployeeDao.update(employeeIvanov);
        employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
    }
//Изменение данных работника на некорректные
    @Test(expected = RuntimeException.class)
    public void testUpdateEmployeeSetNullLastName() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployee("Victor", "Viktorov", "Viktorovic","Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
        employeeIvanov.setLastName(null);
        EmployeeDao.update(employeeIvanov);
    }
//Удаление работника из БД
    @Test
    public void testDeleteEmployee() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployee("Victor", "Viktorov", "Viktorovic","Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
        EmployeeDao.delete(employeeIvanov);
        employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertNull(employeeIvanovFromDB);
    }
//Удаление всех работников из БД
    @Test
    public void testInsertAndDeleteTwoEmployees() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployee("Victor", "Viktorov","Viktorovic", "Viktor@mail.ru", "Employee1", "pass1");
        Employee employeePetrov = insertEmployee("Петр", "Петров", "Петрович","Viktor@mail.ru", "Employee2",  "pass2");
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeIvanov);
        employees.add(employeePetrov);
        List<Employee> employeesFromDB = EmployeeDao.getAll();
        assertEquals(employees, employeesFromDB);
        EmployeeDao.deleteAll();
        employeesFromDB = EmployeeDao.getAll();
        assertEquals(0, employeesFromDB.size());
    }
    //Вставляется в БД запись о работнике заполняя таблицы логин и uuid, если таких заисей там нет
    @Test
    public void testInsertEmployeeTransactional() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployeeTransactional("Victor", "Viktorov", "Viktorovic", "Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
    }
    //Вставляется в БД запись о работнике заполняя таблицы логин и uuid, если таких заисей там нет, после чего удаляет вставленные записи
    @Test
    public void testDeleteEmployee2() throws HiringException, ClassNotFoundException {
        Employee employeeIvanov = insertEmployeeTransactional("Victor", "Viktorov", "Viktorovic","Viktor@mail.ru", "Employee1", "pass1");
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
        EmployeeDao.delete(employeeIvanov);
        employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        assertNull(employeeIvanovFromDB);
    }

    //Вставка работников с одинаковыми навыками навыками и проверка их изменения
    @Test
    public void testInsertEmployeeWithSkillTransactional() throws HiringException, ClassNotFoundException {
        List<Skill> skills = new ArrayList<>();
        Skill skill = new Skill("Java", 5);
        skills.add(skill);
        List<Skill> skills1 = new ArrayList<>();
        Skill skill1 = new Skill("Java", 5);
        skills1.add(skill1);
        Employee employeeIvanov = insertEmployeeTransactional("Victor", "Viktorov", "Viktorovic", "Viktor@mail.ru", "Employee1", "pass1",skills);
        Employee employeeIvanov1 = insertEmployeeTransactional("Victor2", "Viktorov2", "Viktorovic2", "Viktor2@mail.ru", "Employee2", "pass1",skills1);
        Employee employeeIvanovFromDB = EmployeeDao.getById(employeeIvanov.getId());
        Employee employeeIvanovFromDB1 = EmployeeDao.getById(employeeIvanov1.getId());
        assertEquals(employeeIvanov, employeeIvanovFromDB);
        assertEquals(employeeIvanov1, employeeIvanovFromDB1);
        EmployeeDao.login("Employee1", "pass1");
//        skill = new Skill("PHP", 2);
//        skills = (employeeIvanovFromDB.getSkill());
//        skills.add(skill);
//        employeeIvanovFromDB.setSkill(skills);
//        employeeIvanov = insertEmployeeTransactional(employeeIvanovFromDB.getFirstName(), employeeIvanovFromDB.getLastName(), employeeIvanovFromDB.getPatronymic(),
//                employeeIvanovFromDB.getEmail(), employeeIvanovFromDB.getLogin(), employeeIvanovFromDB.getPassword());
    }


}



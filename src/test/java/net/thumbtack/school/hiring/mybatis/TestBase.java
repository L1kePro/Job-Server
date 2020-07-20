package net.thumbtack.school.hiring.mybatis;

import net.thumbtack.school.hiring.errors.HiringException;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.mybatis.dao.*;
import net.thumbtack.school.hiring.mybatis.daoimpl.*;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Employee;
//import net.thumbtack.school.hiring.model.Subject;
//import net.thumbtack.school.hiring.model.Trainee;
import net.thumbtack.school.hiring.mybatis.utils.MyBatisUtils;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;

import java.util.*;

import static org.junit.Assert.assertNotEquals;

public class TestBase {

    private CommonDao commonDao = new CommonDaoImpl();
    protected SkillsDao SkillsDao = new SkillDaoImpl();
    protected EmployeeDao EmployeeDao = new EmployeeDaoImpl();
    protected EmployerDao EmployerDao = new EmployerDaoImpl();
    protected VacanciesDao VacancyDao = new VacancyDaoImpl();

    private static boolean setUpIsDone = false;

    @BeforeClass()
    public static void setUp() {
        if (!setUpIsDone) {
            Assume.assumeTrue(MyBatisUtils.initSqlSessionFactory());
            setUpIsDone = true;
        }
    }

    @Before()
    public void clearDatabase() {
        commonDao.clear();
    }

//    protected Vacancy insertVacancy(int id_employer, int salary, String name, List<Skill> skill) {
//        Vacancy vacancy = new Vacancy(id_employer, salary, name, skill);
//        VacancyDao.insert(vacancy);
//        assertNotEquals(0, skill.getId());
//        return skill;
//    }

//    protected Trainee insertTrainee(String firstName, String lastName, int rating, Group group) {
//        if (group != null) {
//            Trainee trainee = new Trainee(firstName, lastName, rating, group.getId());
//            traineeDao.insert(group, trainee);
//            assertNotEquals(0, trainee.getId());
//            return trainee;
//        }
//        Trainee trainee = new Trainee(firstName, lastName, rating);
//        traineeDao.insert(group, trainee);
//        assertNotEquals(0, trainee.getId());
//        return trainee;
//
//    }
//
    protected Skill insertSkill(String name, int lvl) {
        Skill skill = new Skill(name, lvl);
        SkillsDao.insert(skill);
        assertNotEquals(0, skill.getId());
        return skill;
    }

    protected Employee insertEmployee(String firstName, String lastName, String patronymic, String email, String login, String password) throws HiringException, ClassNotFoundException {
        Employee employee = new Employee(firstName, lastName, patronymic, email, login, password);
        EmployeeDao.insert(employee);
        assertNotEquals(0, employee.getId());
        return employee;
    }
//Вставляется в БД запись о работнике заполняя таблицы логин и uuid, если таких заисей там нет
    protected Employee insertEmployeeTransactional(String firstName, String lastName, String patronymic, String email, String login, String password) throws HiringException, ClassNotFoundException {
        Employee employee = new Employee(firstName, lastName, patronymic, email, login, password);
        EmployeeDao.insertEmployeeTransactional(employee);
        assertNotEquals(0, employee.getId());
        return employee;
    }
//Вставляется в БД запись о работнике заполняя таблицы навыков, логин и uuid, если таких заисей там нет
    protected Employee insertEmployeeTransactional(String firstName, String lastName, String patronymic, String email, String login, String password, List<Skill> skill) throws HiringException, ClassNotFoundException {
        Employee employee = new Employee(firstName, lastName, patronymic, email, login, password, skill);
        EmployeeDao.insertEmployeeTransactional(employee,skill);
        assertNotEquals(0, employee.getId());
        return employee;
    }
//Вставка работодателя без вакансий
    protected Employer insertEmployer(String firstName, String lastName, String patronymic, String email,
                                      String login, String password, String company, String address, List<Vacancy> vacancy) throws HiringException, ClassNotFoundException {
        Employer employer = new Employer(firstName, lastName, patronymic, email, login, password, company, address, vacancy);
        EmployerDao.insert(employer);
        assertNotEquals(0, employer.getId());
        return employer;
    }
//
//    protected Map<String, Subject> insertSubjects(String... subjectNames) {
//        Map<String, Subject> subjects = new HashMap<>();
//        for (String name : subjectNames) {
//            subjects.put(name, insertSubject(name));
//        }
//        return subjects;
//    }
//
//    protected School insertSchool(String name, int year) {
//        School school = new School(name, year);
//        schoolDao.insert(school);
//        assertNotEquals(0, school.getId());
//        return school;
//    }
//
//
//    protected Group insertGroup(School school, String name, String room, int year) {
//        Group group = new Group(name + year, room);
//        groupDao.insert(school, group);
//        assertNotEquals(0, group.getId());
//        return group;
//    }
//
//
//    protected List<Group> insertSchoolGroups(School school, int year, String[] names, String[] rooms) {
//        Group group0 = insertGroup(school, names[0], rooms[0], year);
//        Group group1 = insertGroup(school, names[1], rooms[1], year);
//        List<Group> groups = new ArrayList<>();
//        groups.add(group0);
//        groups.add(group1);
//        school.setGroups(groups);
//        return groups;
//    }
//
//    protected Group insertGroupWithSubjects(School school, int year, String name, String room,
//                                            Map<String, Subject> subjects, String[] subjectNames) {
//        Group group = insertGroup(school, name, room, year);
//        for (String subjectName : subjectNames) {
//            group.addSubject(subjects.get(subjectName));
//            groupDao.addSubjectToGroup(group, subjects.get(subjectName));
//        }
//        return group;
//    }
//
//
//    protected List<Group> insertSchoolGroupsWithSubjects(School school, int year, Map<String, Subject> subjects) {
//        Group groupFrontEnd = insertGroupWithSubjects(school, year, "FrontEnd", "11", subjects, new String[]{"Linux", "NodeJS"});
//        Group groupBackEnd = insertGroupWithSubjects(school, year, "BackEnd", "12", subjects, new String[]{"MySQL", "NodeJS"});
//        List<Group> groups = new ArrayList<>();
//        groups.add(groupFrontEnd);
//        groups.add(groupBackEnd);
//        school.setGroups(groups);
//        return groups;
//    }
//
//    protected void insertBackendTrainees(Group groupBackEnd) {
//        Trainee traineeSidorov = insertTrainee("Сидор", "Сидоров", 2, groupBackEnd);
//        Trainee traineeSmirnov = insertTrainee("Николай", "Смирнов", 3, groupBackEnd);
//        groupBackEnd.addTrainee(traineeSidorov);
//        groupBackEnd.addTrainee(traineeSmirnov);
//    }
//
//    protected void insertFrontEndTrainees(Group groupFrontEnd) {
//        Trainee traineeIvanov = insertTrainee("Иван", "Иванов", 5, groupFrontEnd);
//        Trainee traineePetrov = insertTrainee("Петр", "Петров", 4, groupFrontEnd);
//        groupFrontEnd.addTrainee(traineeIvanov);
//        groupFrontEnd.addTrainee(traineePetrov);
//    }
}

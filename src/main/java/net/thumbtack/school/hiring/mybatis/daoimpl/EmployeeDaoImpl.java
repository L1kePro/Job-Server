package net.thumbtack.school.hiring.mybatis.daoimpl;

import net.thumbtack.school.hiring.model.Employee;
import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.mybatis.dao.EmployeeDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeDaoImpl extends DaoImplBase implements EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Override
    public Employee insert(Employee employee) {
        LOGGER.debug("DAO insert Employee {}", employee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).insert(employee);
                employee.setId(employee.getId());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Employee {}, {}", employee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return employee;
    }

    @Override
    public Employee getById(int id) {
        LOGGER.debug("DAO get Employee by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getEmployeeMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Employee {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Employee> getAll() {
        LOGGER.debug("DAO get all Employee ");
        try (SqlSession sqlSession = getSession()){
            return  getEmployeeMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all {}", ex);
            throw ex;
        }
    }

    @Override
    public Employee update(Employee employee) {
        LOGGER.debug("DAO update Employee {}", employee);
        try (SqlSession sqlSession = getSession()){
            try {
                getEmployeeMapper(sqlSession).update(employee);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Employee {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        } return employee;
    }

    @Override
    public Employee insertEmployeeTransactional(Employee employee2019) {
        LOGGER.debug("DAO insert insertEmployeeTransactional {}", employee2019);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).insert(employee2019);
                getEmployeeMapper(sqlSession).insertLogin(employee2019.getLogin());
                getEmployeeMapper(sqlSession).insertUuid(employee2019.getUuid());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert insertEmployeeTransactional {}, {}", employee2019, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return employee2019;
    }

    @Override
    public Employee insertEmployeeTransactional(Employee employee2019, List<Skill> skill) {
        LOGGER.debug("DAO insert insertEmployeeTransactional {}", employee2019);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).insert(employee2019);
                getEmployeeMapper(sqlSession).insertLogin(employee2019.getLogin());
                getEmployeeMapper(sqlSession).insertUuid(employee2019.getUuid());
                employee2019.setId(employee2019.getId());
                for (Skill skills: employee2019.getSkill() ) {
                    if (getSkillMapper(sqlSession).getIdByNameLvl(skills) != null) {
                        skills.setId(getSkillMapper(sqlSession).getIdByNameLvl(skills));
                        getEmployeeMapper(sqlSession).addSkill(employee2019, skills);
                        sqlSession.commit();
                        return employee2019;
                    }
                    getSkillMapper(sqlSession).insert(skills);
                    skills.setId(skills.getId());
                    getEmployeeMapper(sqlSession).addSkill(employee2019, skills);
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert insertEmployeeTransactional {}, {}", employee2019, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return employee2019;
    }

    @Override
    public void batchInsert(List<Employee> employee) {

    }

    @Override
    public void delete(Employee employee) {
        LOGGER.debug("DAO delete Employee {} ", employee);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).delete(employee);
                getEmployeeMapper(sqlSession).deleteLogin(employee.getLogin());
                getEmployeeMapper(sqlSession).deleteUuid(employee.getUuid());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Employee {} {}", employee, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Employee {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployeeMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all Employee {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public String login(String login, String password) {
        String uuid;
        LOGGER.debug("DAO login Employee {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                uuid = getEmployeeMapper(sqlSession).login(login, password);
                getEmployeeMapper(sqlSession).SetEmployeeOnline(uuid);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't login Employee {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return uuid;
    }
}

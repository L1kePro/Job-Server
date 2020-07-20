package net.thumbtack.school.hiring.mybatis.daoimpl;

import net.thumbtack.school.hiring.model.Employer;
import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.model.Vacancy;
import net.thumbtack.school.hiring.mybatis.dao.EmployerDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployerDaoImpl extends DaoImplBase implements EmployerDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployerDaoImpl.class);

    @Override
    public Employer insert(Employer employer2019) {
        LOGGER.debug("DAO insert insertEmployer {}", employer2019);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployerMapper(sqlSession).insert(employer2019);
                getEmployerMapper(sqlSession).insertLogin(employer2019.getLogin());
                getEmployerMapper(sqlSession).insertUuid(employer2019.getUuid());
                employer2019.setId(employer2019.getId());
                for (Vacancy vacancies : employer2019.getVacancy()) {
                    vacancies.setId_employer(employer2019.getId());
                    getVacancyMapper(sqlSession).insert(vacancies);
                    vacancies.setId(vacancies.getId());
//                    getEmployerMapper(sqlSession).addVacancy(employer2019, vacancies);
                }
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert insertEmployer {}, {}", employer2019, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }return employer2019;
    }
    @Override
    public Employer insertEmployerWithVacancy(Employer employer2019, List<Vacancy> vacancy, List<Skill> skill) {
        return null;
    }

    @Override
    public Employer getById(int id) {
        LOGGER.debug("DAO get Employer by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getEmployerMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Employer {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Employer> getAll() {
        LOGGER.debug("DAO get all Employer ");
        try (SqlSession sqlSession = getSession()){
            return  getEmployerMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all {}", ex);
            throw ex;
        }
    }

    @Override
    public Employer update(Employer employer) {
        LOGGER.debug("DAO update Employer {}", employer);
        try (SqlSession sqlSession = getSession()){
            try {
                getEmployerMapper(sqlSession).update(employer);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't update Employer {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        } return employer;
    }

    @Override
    public void batchInsert(List<Employer> employer) {

    }

    @Override
    public void delete(Employer employer) {
        LOGGER.debug("DAO delete Employer {} ", employer);
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployerMapper(sqlSession).delete(employer);
                getEmployerMapper(sqlSession).deleteLogin(employer.getLogin());
                getEmployerMapper(sqlSession).deleteUuid(employer.getUuid());
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Employer {} {}", employer, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Employer {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getEmployerMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all Employer {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}

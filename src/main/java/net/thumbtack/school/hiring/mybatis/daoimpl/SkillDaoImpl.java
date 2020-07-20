package net.thumbtack.school.hiring.mybatis.daoimpl;

import net.thumbtack.school.hiring.model.Skill;
import net.thumbtack.school.hiring.mybatis.dao.SkillsDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SkillDaoImpl extends DaoImplBase implements SkillsDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SkillDaoImpl.class);

    @Override
    public Skill insert(Skill skill) {
        LOGGER.debug("DAO insert Skill {}", skill);
        try (SqlSession sqlSession = getSession()) {
            try {
//                System.out.println(getSkillMapper(sqlSession).getIdByNameLvl(skill));
                if (getSkillMapper(sqlSession).getIdByNameLvl(skill) != null) {
                skill.setId(getSkillMapper(sqlSession).getIdByNameLvl(skill));
                return skill;
                }
                getSkillMapper(sqlSession).insert(skill);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't insert Skill {}, {}", skill, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
        return skill;
    }

    @Override
    public Skill getById(int id) {
        LOGGER.debug("DAO get Skill by id {}", id);
        try (SqlSession sqlSession = getSession()) {
            return getSkillMapper(sqlSession).getById(id);
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get Skill {}", ex);
            throw ex;
        }
    }

    @Override
    public List<Skill> getAll() {
        LOGGER.debug("DAO get all skill ");
        try (SqlSession sqlSession = getSession()){
            return  getSkillMapper(sqlSession).getAll();
        } catch (RuntimeException ex) {
            LOGGER.info("Can't get all {}", ex);
            throw ex;
        }
    }

    @Override
    public void delete(Skill skill) {
        LOGGER.debug("DAO delete Skill {} ", skill);
        try (SqlSession sqlSession = getSession()) {
            try {
                getSkillMapper(sqlSession).delete(skill);
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete Skill {} {}", skill, ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }

    @Override
    public void deleteAll() {
        LOGGER.debug("DAO delete all Skills {}");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSkillMapper(sqlSession).deleteAll();
            } catch (RuntimeException ex) {
                LOGGER.info("Can't delete all Skills {}", ex);
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}


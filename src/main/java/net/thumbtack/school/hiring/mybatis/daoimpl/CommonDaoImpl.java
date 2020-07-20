package net.thumbtack.school.hiring.mybatis.daoimpl;

import net.thumbtack.school.hiring.mybatis.dao.CommonDao;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonDaoImpl extends DaoImplBase implements CommonDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDaoImpl.class);

    @Override
    public void clear() {
        LOGGER.debug("Clear Database");
        try (SqlSession sqlSession = getSession()) {
            try {
                getSkillMapper(sqlSession).deleteAll();
                getEmployeeMapper(sqlSession).deleteAll();
                getEmployeeMapper(sqlSession).deleteAllLogins();
                getEmployeeMapper(sqlSession).deleteAllUuids();
                getEmployerMapper(sqlSession).deleteAll();

            } catch (RuntimeException ex) {
                LOGGER.info("Can't clear database");
                sqlSession.rollback();
                throw ex;
            }
            sqlSession.commit();
        }
    }
}

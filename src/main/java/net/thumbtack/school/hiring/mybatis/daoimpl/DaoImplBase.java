package net.thumbtack.school.hiring.mybatis.daoimpl;

import net.thumbtack.school.hiring.mybatis.mappers.EmployeeMapper;
import net.thumbtack.school.hiring.mybatis.mappers.EmployerMapper;
import net.thumbtack.school.hiring.mybatis.mappers.SkillMapper;
import net.thumbtack.school.hiring.mybatis.mappers.VacancyMapper;
import org.apache.ibatis.session.SqlSession;
import net.thumbtack.school.hiring.mybatis.utils.MyBatisUtils;

public class DaoImplBase {

    protected SqlSession getSession() {
        return MyBatisUtils.getSqlSessionFactory().openSession();
    }

    protected SkillMapper getSkillMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(SkillMapper.class);
    }

    protected EmployeeMapper getEmployeeMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(EmployeeMapper.class);
    }

    protected EmployerMapper getEmployerMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(EmployerMapper.class);
    }

    protected VacancyMapper getVacancyMapper(SqlSession sqlSession) {
        return sqlSession.getMapper(VacancyMapper.class);
    }

}
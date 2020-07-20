package net.thumbtack.school.hiring.mybatis;

import net.thumbtack.school.hiring.model.Skill;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestSkillOperations extends TestBase {

    @Test
    public void testInsertSkill() {
        Skill skill = insertSkill("Linux", 4);
        Skill skill1 = insertSkill("Linux", 4);
        Skill skillFromDB = SkillsDao.getById(skill.getId());
        Skill skillFromDB1 = SkillsDao.getById(skill.getId());
        assertEquals(skill, skillFromDB);
        assertEquals(skill1, skillFromDB1);
    }

    @Test
    public void testGetNonexistentSkill() {
        assertNull(SkillsDao.getById(1234567));
    }

    @Test
    public void testGetSkillsFromEmptySkillsTable() {
        assertEquals(0, SkillsDao.getAll().size());
    }

    @Test(expected = RuntimeException.class)
    public void testInsertSkillWithNullName() {
        Skill skill = new Skill(null);
        SkillsDao.insert(skill);
    }

    @Test
    public void testDeleteSkill() {
        Skill skill = insertSkill("Linux", 5);
        Skill skillFromDB = SkillsDao.getById(skill.getId());
        assertEquals(skill, skillFromDB);
        SkillsDao.delete(skill);
        skillFromDB = SkillsDao.getById(skill.getId());
        assertNull(skillFromDB);
    }

//    @Test
//    public void testInsertAndDeleteTwoSkills() {
//        Skill skillLinux = insertSkill("Linux");
//        Skill skillMySQL = insertSkill("MySQL");
//        List<Skill> skills = new ArrayList<>();
//        skills.add(skillLinux);
//        skills.add(skillMySQL);
//        List<Skill> skillsFromDB = SkillsDao.getAll();
//        assertEquals(skills, skillsFromDB);
//        SkillsDao.deleteAll();
//        skillsFromDB = SkillsDao.getAll();
//        assertEquals(0, skillsFromDB.size());
//    }

}

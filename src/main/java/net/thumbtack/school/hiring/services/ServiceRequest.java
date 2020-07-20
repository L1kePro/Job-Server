//package net.thumbtack.school.hiring.Services;
//
//import com.google.gson.Gson;
//import net.thumbtack.school.hiring.Errors.HiringErrorCode;
//import net.thumbtack.school.hiring.Errors.HiringException;
//import net.thumbtack.school.hiring.dataBase.DataBase;
//import net.thumbtack.school.hiring.dataBase.Employee;
//import net.thumbtack.school.hiring.dataBase.Employer;
//import net.thumbtack.school.hiring.dataBase.Vacancy;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.UUID;
//
//public class ServiceRequest {
//
////ОБЩИЕ МЕТОДЫ
//    //Войти на сервер
//    public static String login(String text) throws ClassNotFoundException {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String login = (String) map.get("login");
//        String password = (String) map.get("password");
//        String res = "Input data incorrect";
//        if (DataBase.checkLoginPasswordEmployee(login, password)) {
//            res = "Successful login";
//            DataBase.addToListUUID(DataBase.getUUIDFromLogin(login));
//            return json.toJson(res);
//        }
//        if (DataBase.checkLoginPasswordEmployer(login, password)) {
//            DataBase.addToListUUID(DataBase.getUUIDFromLogin(login));
//            res = "Successful login";
//            return json.toJson(res);
//        }
//        return json.toJson(res);
//        }
//    //Смена пароля
//    public static void changePassword(String text) {
//    Gson json = new Gson();
//    Map map = json.fromJson(text, Map.class);
//    String newPassword = (String) map.get("password");
//    String tempUUID = (String) map.get("UUID");
//    UUID uuid = UUID.fromString(tempUUID);
//    Employee employee = null;
//    Employer employer = null;
//    Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//        employee = (Employee) tempIter.next();
//        if (employee.getUuid().equals(uuid)) {
//            employee.setPassword(newPassword);
//        }
//    }
//            tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setPassword(newPassword);
//            }
//        }
//}
//    //Смена имени
//    public static void changeName(String text) {
//    Gson json = new Gson();
//    Map map = json.fromJson(text, Map.class);
//    String newName = (String) map.get("name");
//    String tempUUID = (String) map.get("UUID");
//    UUID uuid = UUID.fromString(tempUUID);
//    Employee employee = null;
//    Employer employer = null;
//    Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//        employee = (Employee) tempIter.next();
//        if (employee.getUuid().equals(uuid)) {
//            employee.setFirstName(newName);
//        }
//    }
//            tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setFirstName(newName);
//            }
//        }
//    }
//    //Смена фамилии
//    public static void changeLastName(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String newLastName = (String) map.get("lastName");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employee employee = null;
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//            employee = (Employee) tempIter.next();
//            if (employee.getUuid().equals(uuid)) {
//                employee.setLastName(newLastName);
//            }
//        }
//                tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setLastName(newLastName);
//            }
//        }
//    }
//    //Смена отчества
//    public static void changeSubName(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String subName = (String) map.get("subName");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employee employee = null;
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//            employee = (Employee) tempIter.next();
//            if (employee.getUuid().equals(uuid)) {
//                employee.setSubName(subName);
//            }
//        }
//        tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setSubName(subName);
//            }
//        }
//    }
//    //Смена почты
//    public static void changeEMail(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String eMail = (String) map.get("email");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employee employee = null;
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//            employee = (Employee) tempIter.next();
//            if (employee.getUuid().equals(uuid)) {
//                employee.setMail(eMail);
//            }
//        }
//        tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setMail(eMail);
//            }
//        }
//    }
//    //Удаление себя с сервера
//    public static void deleteMyself(UUID uuid) {
//        Employee employee = null;
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//            employee = (Employee) tempIter.next();
//            if (employee.getUuid().equals(uuid)) {
//                DataBase.getListEmployee().remove(employee);
//                DataBase.getListUUID().remove(uuid);
//            }
//        }
//        tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                DataBase.getListEmployer().remove(employer);
//                DataBase.getListUUID().remove(uuid);
//            }
//        }
//
//    }
//    //Выйти с сервера
//    public static void logout(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        DataBase.deleteFromListUUID(uuid);
//    }
//    //МЕТОДЫ РАБОТОДАТЕЛЯ
//    //Добавить навыки в вакансию
//    public static void addSkillInVacancy(String text) {
//        HashMap<String, Integer> list = new HashMap<>();
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        String nameVacancy = (String) map.get("nameVacancy");
//        Boolean necessary = (Boolean) map.get("necessary");
//        String skill = (String) map.get("skill");
//        Double tempInteger = (Double) map.get("lvl");
//        Integer lvl = tempInteger.intValue();
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator iterVacancy = employer.getVacancies().iterator();
//                while (iterVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) iterVacancy.next();
//                    if (vacancy.getNameVacancy().equals(nameVacancy)) {
//                        if (!(vacancy.getNecessarySkills().containsKey(necessary))) {
//                            vacancy.getNecessarySkills().put(necessary, new HashMap<String, Integer>());
//                        }
//                        if (!(vacancy.getNecessarySkills().get(necessary).keySet().contains(skill))) {
//                            vacancy.getNecessarySkills().get(necessary).put(skill, lvl);
//                            DataBase.addToListSkills(skill);
//                        }
//                        vacancy.getNecessarySkills().get(necessary).replace(skill, lvl);
//                    }
//                }
//            }
//        }
//    }
//    // Удаление вакансии
//    public static void deleteVacancy(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String nameVacancy = (String) map.get("nameVacancy");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator iterVacancy = employer.getVacancies().iterator();
//                while (iterVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) iterVacancy.next();
//                    if (vacancy.getNameVacancy().equals(nameVacancy)) {
//                        iterVacancy.remove();
//                    }
//                }
//            }
//        }
//    }
////    Сделать вакансию неактивной
//    public static void deactivateVacancy(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String nameVacancy = (String) map.get("nameVacancy");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator iterVacancy = employer.getVacancies().iterator();
//                while (iterVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) iterVacancy.next();
//                    if (vacancy.getNameVacancy().equals(nameVacancy)) {
//                        vacancy.setActiv(false);
//                    }
//                }
//            }
//        }
//    }
////    Сделать вакансию активной
//  public static void activateVacancy(String text) throws HiringException {
//Gson json = new Gson();
//    Map map = json.fromJson(text, Map.class);
//    String nameVacancy = (String) map.get("nameVacancy");
//    String tempUUID = (String) map.get("UUID");
//    UUID uuid = UUID.fromString(tempUUID);
//    Employer employer = null;
//    Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//        employer = (Employer) tempIter.next();
//        if (employer.getUuid().equals(uuid)) {
//            Iterator iterVacancy = employer.getVacancies().iterator();
//            while (iterVacancy.hasNext()) {
//                Vacancy vacancy = (Vacancy) iterVacancy.next();
//                if (vacancy.getNameVacancy().equals(nameVacancy)) {
//                    vacancy.setActiv(true);
//                }
//            }
//        }
//    }
//    }
//    //Смена названии компании
//    public static void changeNameCompany(String text) {
//    Gson json = new Gson();
//    Map map = json.fromJson(text, Map.class);
//    String newNameCompany = (String) map.get("nameCompany");
//    String tempUUID = (String) map.get("UUID");
//    UUID uuid = UUID.fromString(tempUUID);
//    Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//        employer = (Employer) tempIter.next();
//        if (employer.getUuid().equals(uuid)) {
//            employer.setCompany(newNameCompany);
//        }
//    }
//    }
//    //Смена адреса
//    public static void changeAddress(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String address = (String) map.get("address");
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employer employer = null;
//        Iterator tempIter = DataBase.getListEmployer().iterator();
//        while (tempIter.hasNext()) {
//            employer = (Employer) tempIter.next();
//            if (employer.getUuid().equals(uuid)) {
//                employer.setAddress(address);
//            }
//        }
//    }
////    Нанять работника (делает вакансию и резюме неактивными)
//    public static void hire(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String loginEmployee = (String) map.get("loginEmployee");
//        deactivateVacancy(text); //Принимает UUID работодателя и имя вакансии
//        UUID uuid = DataBase.getUUIDFromLogin(loginEmployee);
//        deactivateResume("{\"UUID\":\"" + uuid + "\"}"); //принимает UUID работника
//    }
//
//    //МЕТОДЫ РАБОТНИКА
//    //Сделать резюме неактивным
//    public static void deactivateResume(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        Employee employee = null;
//        Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//            employee = (Employee) tempIter.next();
//            if (employee.getUuid().equals(uuid)) {
//                employee.setActive(false);
//            }
//        }
//    }
//    //Сделать резюме активным
//    public static void activateResume(String text) {
//    Gson json = new Gson();
//    Map map = json.fromJson(text, Map.class);
//    String tempUUID = (String) map.get("UUID");
//    UUID uuid = UUID.fromString(tempUUID);
//    Employee employee = null;
//    Iterator tempIter = DataBase.getListEmployee().iterator();
//        while (tempIter.hasNext()) {
//        employee = (Employee) tempIter.next();
//        if (employee.getUuid().equals(uuid)) {
//            employee.setActive(true);
//        }
//    }
//    }
//
//}

//package net.thumbtack.school.hiring.Services;
//
//import com.google.gson.Gson;
//import net.thumbtack.school.hiring.dataBase.DataBase;
//import net.thumbtack.school.hiring.dataBase.Employee;
//import net.thumbtack.school.hiring.dataBase.Employer;
//import net.thumbtack.school.hiring.dataBase.Vacancy;
//
//import java.util.*;
//
//public class ServiceResponse {
//    //ОБЩИЕ МЕТОДЫ
////Получить список всех вакансий
//    public static ArrayList<Vacancy> getAllVacancy() {
//       ArrayList<Vacancy> res = new ArrayList();
//       Iterator employerIter = DataBase.getListEmployer().iterator();
//       while (employerIter.hasNext()) {
//          Employer employer = (Employer) employerIter.next();
//          Iterator vacancyIter = employer.getVacancies().iterator();
//           while (vacancyIter.hasNext()) {
//               Vacancy vacancy = (Vacancy) vacancyIter.next();
//               res.add(vacancy);
//           }
//       }
//       return res;
//    }
////Получить список всех активных вакансий
//    public static ArrayList getAllActiveVacancy() {
//        Gson json = new Gson();
//        ArrayList res = new ArrayList();
//       Iterator iterEmployer = DataBase.getListEmployer().iterator();
//       while (iterEmployer.hasNext()) {
//           Vacancy vacancy = (Vacancy) iterEmployer.next();
//           if (vacancy.getActiv()) {
//               res.add(vacancy);
//           }
//       }
//       return res;
//    }
//    //получить список всех умений
//    public static String getAllSkill() {
//        Gson json = new Gson();
//        return json.toJson(DataBase.getListSkills());
//    }
//    //МЕТОДЫ РАБОТОДАТЕЛЯ
//
//    //Получения списка всех умений в вакансии в виде Map (вспомогательный метод)
////    public static Map<String, Integer> getAllVacancySkillsInMap(String text) {
////        Gson json = new Gson();
////        Map map = json.fromJson(text, Map.class);
////        String vacancy = (String) map.get("vacancy");
////        Double salary1 = (Double) map.get("salary");
////        Integer salary = salary1.intValue();
////        HashMap<String, Integer> skill = new HashMap<>();
////        HashMap<String, Integer> fullSkills = new HashMap<>();
////        HashMap<String, Integer> res = new HashMap<>();
////        for (Map.Entry employer: DataBase.getList1().get(ServiceRequest.getTempLogin()).getPersonalVacancies()
////                .get(ServiceRequest.getTempCompany()).get("Активная").get(vacancy).get(salary).entrySet()) {
////            skill = (HashMap<String, Integer>) employer.getValue();
////            for (Map.Entry sk: skill.entrySet()) {
////                fullSkills.put((String)sk.getKey(),(Integer) sk.getValue());
////            }
////        }return  fullSkills;
////    }
//    //Получение всех обязательных навыков вакансии в виде массива (вспомогательный метод)
//    public static ArrayList<String> getAllVacancyNecessarySkillsInArray(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        String nameVacancy = (String) map.get("nameVacancy");
//        ArrayList<String> res = new ArrayList<>();
//        Iterator tempEmployer = DataBase.getListEmployer().iterator();
//        while (tempEmployer.hasNext()) {
//            Employer employer = (Employer) tempEmployer.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator tempVacancy = employer.getVacancies().iterator();
//                while (tempVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) tempVacancy.next();
//                    if (vacancy.getNameVacancy().equalsIgnoreCase(nameVacancy)) {
//                        res.addAll(vacancy.getNecessarySkills().get(true).keySet());
//                    }
//                }
//            }
//        }
//        return res;
//    }
//    //Получение всех обязательных навыков вакансии и их уровня в виде массива (вспомогательный метод)
//    public static HashMap<String, Integer> getAllVacancyNecessarySkillAndLvlInArray(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        String nameVacancy = (String) map.get("nameVacancy");
//        HashMap<String, Integer> res = new HashMap<>();
//        Iterator tempEmployer = DataBase.getListEmployer().iterator();
//        while (tempEmployer.hasNext()) {
//            Employer employer = (Employer) tempEmployer.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator tempVacancy = employer.getVacancies().iterator();
//                while (tempVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) tempVacancy.next();
//                    if (vacancy.getNameVacancy().equalsIgnoreCase(nameVacancy)) {
//                        res.putAll(vacancy.getNecessarySkills().get(true));
//                    }
//                }
//            }
//        }
//        return res;
//    }
//    //Получение всех навыков вакансии и их уровня в виде массива (вспомогательный метод)
//    public static HashMap<String, Integer> getAllVacancySkillAndLvlInArray(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        String nameVacancy = (String) map.get("nameVacancy");
//        HashMap<String, Integer> res = new HashMap<>();
//        Iterator tempEmployer = DataBase.getListEmployer().iterator();
//        while (tempEmployer.hasNext()) {
//            Employer employer = (Employer) tempEmployer.next();
//            if (employer.getUuid().equals(uuid)) {
//                Iterator tempVacancy = employer.getVacancies().iterator();
//                while (tempVacancy.hasNext()) {
//                    Vacancy vacancy = (Vacancy) tempVacancy.next();
//                    if (vacancy.getActiv()) {
//                        if (vacancy.getNameVacancy().equalsIgnoreCase(nameVacancy)) {
//                            res.putAll(vacancy.getNecessarySkills().get(true));
//                            res.putAll(vacancy.getNecessarySkills().get(false));
//                        }
//                    }
//                }
//            }
//        }
//        return res;
//    }
//    //Просмотр всех своих вакансий
////    public static String getOwnVacancies() {
////        Gson json = new Gson();
////        return json.toJson(DataBase.getList1().get(ServiceRequest.getTempLogin()).getPersonalVacancies());
////    }
//    //просмотр всех своих активных вакансий
//    public static String getOwnActiveVacancies(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        ArrayList res = new ArrayList();
//        Iterator iterEmployer = DataBase.getListEmployer().iterator();
//        while (iterEmployer.hasNext()) {
//            Employer employer = (Employer) iterEmployer.next();
//            if (employer.getUuid().equals(uuid)) {
//                Vacancy vacancy = (Vacancy) iterEmployer.next();
//                if (vacancy.getActiv()) {
//                    res.add(vacancy);
//                }
//            }
//        }
//        return json.toJson(res);
//    }
////    //Просмотр всех своих неактивных вакансий
//    public static String getOwmDisableVacancies(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        ArrayList res = new ArrayList();
//        Iterator iterEmployer = DataBase.getListEmployer().iterator();
//        while (iterEmployer.hasNext()) {
//            Employer employer = (Employer) iterEmployer.next();
//            if (employer.getUuid().equals(uuid)) {
//                Vacancy vacancy = (Vacancy) iterEmployer.next();
//                if (!(vacancy.getActiv())) {
//                    res.add(vacancy);
//                }
//            }
//        }
//        return json.toJson(res);
//    }
//    //Вспомогательный метод для проверки есть ли хоть один навык работника в вакансии на необходимом уровне
//    public static Integer passOneOrMoreSkillAndLvl(HashMap<String, Integer> temp, HashMap<String, Integer> arr) {
//        ArrayList tempList = new ArrayList();
//        ArrayList arrayList = new ArrayList();
//        tempList.addAll(temp.keySet());
//        arrayList.addAll(arr.keySet());
//        Boolean res = false;
//        Integer count = 0;
//        for (Object a : arrayList) {
//            if (tempList.contains(a)) {
//                if (temp.get(a) <= arr.get(a)) {
//                    count++;
//                }
//            }
//        }
//        return count;
//    }
//    //Просмотр всех резюме где содержится хотя бы 1 обязательное умение на уровне не ниже указанного
//    public static ArrayList<Employee> getAllResumeWithOneOrMorePassNecessarySkillAndLvl(String text) {
//        HashMap<String, Integer> skillAndLvlEmployer = new HashMap(getAllVacancyNecessarySkillAndLvlInArray(text));
//        HashMap<String, Integer> skillAndLvlEmployee = new HashMap<>();
//        ArrayList<Employee> res = new ArrayList<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        while (iterEmployee.hasNext()) {
//            skillAndLvlEmployee.clear();
//            Employee employee = (Employee) iterEmployee.next();
//            skillAndLvlEmployee.putAll(employee.getSkill());
//            if (ServiceResponse.passOneOrMoreSkillAndLvl(skillAndLvlEmployer, skillAndLvlEmployee ) > 0) {
//                res.add(employee);
//            }
//        }
//        return res;
//    }
//    //Просмотр всех работников имеющих все необходимые умения для вакансии на любом уровне
//    public static ArrayList<Employee> getAllPassResumeWithFullSkillsAnyLvl(String text) {
//        ArrayList<Employee> res = new ArrayList<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        ArrayList<String> skillEmployer = ServiceResponse.getAllVacancyNecessarySkillsInArray(text);
//        ArrayList skill = new ArrayList();
//        while (iterEmployee.hasNext()) {
//            skill.clear();
//            Employee employee = (Employee) iterEmployee.next();
//            skill.addAll(employee.getSkill().keySet());
//            if (ServiceResponse.passAllSkill(skillEmployer, skill)) {
//                res.add(employee);
//            }
//        }
//         return res;
//    }
//
//    //Просмотр всех подходящих резюме строго соответствующих обязательным навыкам и уровню
//    public static ArrayList<Employee> getAllResumeWithNecessarySkillsAndLvl(String text) {
//        ArrayList<Employee> res = new ArrayList<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        HashMap<String, Integer> skillAndLvlEmployer = new HashMap(getAllVacancyNecessarySkillAndLvlInArray(text));
//        HashMap<String, Integer> skillAndLvlEmployee = new HashMap<>();
//        while (iterEmployee.hasNext()) {
//            skillAndLvlEmployee.clear();
//            Employee employee = (Employee) iterEmployee.next();
//            skillAndLvlEmployee.putAll(employee.getSkill());
//            if (ServiceResponse.passAllSkillAndLvl(skillAndLvlEmployer, skillAndLvlEmployee )) {
//                res.add(employee);
//            }
//        }
//        return res;
//    }
////Получение списка всех резюме, имеющих все необходимые для этой вакансии умения на уровне не ниже уровня, указанного в требовании.
//    public static ArrayList<Employee> getResumeWithAllPassSkillsAndLvl(String text) {
//        ArrayList<Employee> res = new ArrayList<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        HashMap<String, Integer> skillAndLvlEmployer = new HashMap(getAllVacancySkillAndLvlInArray(text));
//        HashMap<String, Integer> skillAndLvlEmployee = new HashMap<>();
//        while (iterEmployee.hasNext()) {
//            skillAndLvlEmployee.clear();
//            Employee employee = (Employee) iterEmployee.next();
//            skillAndLvlEmployee.putAll(employee.getSkill());
//            if (ServiceResponse.passAllSkillAndLvl(skillAndLvlEmployer, skillAndLvlEmployee )) {
//                res.add(employee);
//            }
//        }
//        return res;
//    }
////МЕТОДА РАБОТНИКА
////Вспомогательный метод для проверки всех навыков в вакансии + уровень с навыками работника
//public static Boolean passAllSkillAndLvl(HashMap<String, Integer> temp, HashMap<String, Integer> arr) {
//    ArrayList tempList = new ArrayList();
//    ArrayList arrayList = new ArrayList();
//    tempList.addAll(temp.keySet());
//    arrayList.addAll(arr.keySet());//работник
//    Boolean res = false;
//    if (arrayList.containsAll(tempList)) {
//        res = true;
//        for (int i = 0; i < tempList.size(); i++) {
//            if (!(temp.get(tempList.get(i)) <= arr.get(tempList.get(i)))) {
//                res = false;
//            }
//        }
//    }
//    return res;
//}
//    //Вспомогательный метод для сверки всех навыков в вакансии с навыками работника
//    public static Boolean passAllSkill(ArrayList tempList, ArrayList arrayList) {
//        Boolean res = false;
//        if (arrayList.containsAll(tempList)) {
//            res = true;
//        }
//        return res;
//    }
//    //Получение скиллов + уровень владения работника
//    public static HashMap<String, Integer> getEmployeeSkillsInMap(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        HashMap<String, Integer> res = new HashMap<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        while (iterEmployee.hasNext()) {
//            Employee employee = (Employee) iterEmployee.next();
//            if (employee.getUuid().equals(uuid)) {
//                res.putAll(employee.getSkill());
//            }
//        }
//        return res;
//    }
//    //Получение скиллов работника
//    public static ArrayList<String> getEmployeeSkills(String text) {
//        Gson json = new Gson();
//        Map map = json.fromJson(text, Map.class);
//        String tempUUID = (String) map.get("UUID");
//        UUID uuid = UUID.fromString(tempUUID);
//        ArrayList<String> res = new ArrayList<>();
//        Iterator iterEmployee = DataBase.getListEmployee().iterator();
//        while (iterEmployee.hasNext()) {
//            Employee employee = (Employee) iterEmployee.next();
//            if (employee.getUuid().equals(uuid)) {
//                res.addAll(employee.getSkill().keySet());
//            }
//        }
//        return res;
//    }
//    //Получить список всех вакансий где набор всех (Обязательных и необязательных) умений соответствует скиллам и уровню в резюме
//    public static ArrayList<Vacancy> getAllVacancyWithPassSkillAndLvl(String text) {
//        ArrayList<Vacancy> res = new ArrayList<>();
//        ArrayList<Vacancy> vacanciesList = ServiceResponse.getAllVacancy();
//        HashMap<String, Integer> skillAndLvlEmployee = getEmployeeSkillsInMap(text);
//        HashMap<String, Integer> skillAndLvlVacancy = new HashMap<>();
//        Iterator iterVacancies = vacanciesList.iterator();
//        while (iterVacancies.hasNext()) {
//            skillAndLvlVacancy.clear();
//            Vacancy vacancy = (Vacancy) iterVacancies.next();
//            if (vacancy.getNecessarySkills().containsKey(false)) {
//                skillAndLvlVacancy.putAll(vacancy.getNecessarySkills().get(false));
//            }
//            skillAndLvlVacancy.putAll(vacancy.getNecessarySkills().get(true));
//            if (passAllSkillAndLvl(skillAndLvlVacancy, skillAndLvlEmployee)) {
//                if (vacancy.getActiv()) {
//                    res.add(vacancy);
//                }
//            }
//        }
//        return res;
//    }
//    //Получить список всех вакансий где набор обязательных умений соответствует скиллам и уровню в резюме
//    public static ArrayList<Vacancy> getAllVacancyWithPassNecessarySkillAndLvl(String text) {
//        ArrayList<Vacancy> res = new ArrayList<>();
//        ArrayList<Vacancy> vacanciesList = ServiceResponse.getAllVacancy();
//        HashMap<String, Integer> skillAndLvlEmployee = getEmployeeSkillsInMap(text);
//        HashMap<String, Integer> skillAndLvlVacancy = new HashMap<>();
//        Iterator iterVacancies = vacanciesList.iterator();
//        while (iterVacancies.hasNext()) {
//            skillAndLvlVacancy.clear();
//            Vacancy vacancy = (Vacancy) iterVacancies.next();
//            skillAndLvlVacancy.putAll(vacancy.getNecessarySkills().get(true));
//            if (passAllSkillAndLvl(skillAndLvlVacancy, skillAndLvlEmployee)) {
//                if (vacancy.getActiv()) {
//                    res.add(vacancy);
//                }
//            }
//        }
//        return res;
//    }
////Cписок всех вакансий работодателей, для которых его набор умений соответствует требованиям работодателя на любом уровне
//    public static ArrayList<Vacancy> getAllVacanciesWithPassSkillAnyLvl(String text) {
//        ArrayList<Vacancy> res = new ArrayList<>();
//        ArrayList<Vacancy> vacanciesList = ServiceResponse.getAllVacancy();
//        ArrayList<String> skillEmployee = getEmployeeSkills(text);
//        ArrayList<String> skillVacancy = new ArrayList<>();
//        Iterator iterVacancies = vacanciesList.iterator();
//        while (iterVacancies.hasNext()) {
//            skillVacancy.clear();
//            Vacancy vacancy = (Vacancy) iterVacancies.next();
//            if (vacancy.getNecessarySkills().containsKey(false)) {
//                skillVacancy.addAll(vacancy.getNecessarySkills().get(false).keySet());
//            }
//            skillVacancy.addAll(vacancy.getNecessarySkills().get(true).keySet());
//            if (passAllSkill(skillVacancy, skillEmployee)) {
//                if (vacancy.getActiv()) {
//                    res.add(vacancy);
//                }
//            }
//        }
//        return res;
//    }
//    /*список всех вакансий работодателей, для которых работник имеет хотя бы одно умение из списка в требовании работодателя на уровне не ниже уровня, указанного в требовании.
//     отсортированный*/
//    public static ArrayList<Vacancy> getAllVacanciesWithOneOrMorePassSkillAndLvl(String text) {
//        HashMap<Integer, Map.Entry<Vacancy, Integer>> res = new HashMap<>();
//        ArrayList<Vacancy> vacanciesList = ServiceResponse.getAllVacancy();
//        HashMap<String, Integer> skillAndLvlEmployee = getEmployeeSkillsInMap(text);
//        HashMap<String, Integer> skillAndLvlVacancy = new HashMap<>();
//        Iterator iterVacancies = vacanciesList.iterator();
//        TreeMap<Integer, Map.Entry<Vacancy, Integer>> sortedRes = new TreeMap<>(new ValueComparator(res));
//        Integer i = 0;
//        while (iterVacancies.hasNext()) {
//            skillAndLvlVacancy.clear();
//            Vacancy vacancy = (Vacancy) iterVacancies.next();
//            if (vacancy.getNecessarySkills().containsKey(false)) {
//                skillAndLvlVacancy.putAll(vacancy.getNecessarySkills().get(false));
//            }
//            skillAndLvlVacancy.putAll(vacancy.getNecessarySkills().get(true));
//            Integer tempInt = ServiceResponse.passOneOrMoreSkillAndLvl(skillAndLvlVacancy, skillAndLvlEmployee);
//            if (tempInt > 0) {
//                if (vacancy.getActiv()) {
//                    Map.Entry<Vacancy, Integer> tempEntry = new AbstractMap.SimpleEntry<Vacancy, Integer>(vacancy, tempInt);
//                    res.put(i, tempEntry);
//                    sortedRes.put(i, tempEntry);
//                    i++;
//                }
//            }
//        }
//        ArrayList<Vacancy> result = new ArrayList();
//        for (Map.Entry<Vacancy, Integer> s: sortedRes.values()){
//             result.add(s.getKey());
//        }
//        return result;
//    }
//
//    static class ValueComparator implements Comparator<Integer> {
//        Map<Integer, Map.Entry<Vacancy, Integer>> base;
//
//        public ValueComparator(Map<Integer, Map.Entry<Vacancy, Integer>> base) {
//            this.base = base;
//        }
//
//        public int compare(Integer a, Integer b) {
//            Integer vacancyA =  base.get(a).getValue();
//            Integer vacancyB =  base.get(b).getValue();
//            if (vacancyA >= vacancyB) {
//                return -1;
//            } else {
//                return 1;
//            }
//        }
//    }
//
//}

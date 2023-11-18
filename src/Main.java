import module.Student;

import java.util.Arrays;
import java.util.HashMap;

import static util.json.JsonParser.parseJsonArray;
import static dao.StudentDao.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Student s = selectStudent("admin");
        s.setOnline(false);
        updateStudent(s);
    }
}
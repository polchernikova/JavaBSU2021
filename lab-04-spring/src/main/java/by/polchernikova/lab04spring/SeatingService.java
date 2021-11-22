package by.polchernikova.lab04spring;

import by.polchernikova.lab04spring.Model.Classroom;
import by.polchernikova.lab04spring.Model.Student;
import by.polchernikova.lab04spring.Repozitories.ClassroomRepository;
import by.polchernikova.lab04spring.Repozitories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class SeatingService {
    @Autowired
    StudentRepository students;

    @Autowired
    ClassroomRepository classrooms;

    public ArrayList<ArrayList<ArrayList<Student>>> prepareDataForDisplaying() {
        ArrayList<ArrayList<ArrayList<Student>>> data = new ArrayList<>();
        List<Classroom> classes = new ArrayList<>();
        classrooms.findAll().forEach(classroom -> classes.add(classroom));
        for(int i = 0; i < classes.size(); i++) {
            ArrayList<ArrayList<Student>> classroom = new ArrayList<ArrayList<Student>>();
            for(int j = 0; j < 5; j++) {
                ArrayList<Student> row = new ArrayList<>();
                for(int k = 0; k < 6; k++) {
                    row.add(null);
                }
                classroom.add(row);
            }
            data.add(classroom);
        }
        for(int i = 0; i < classes.size(); i++) {
            Classroom classroom = classes.get(i);
            Iterable<Student> studentsInClass = students.findAllByClassroom(classroom);
            for(Student st : studentsInClass) {
                Integer place = st.getPlace();
                Integer row = place / 6;
                Integer placeInRow = place % 6;
                data.get(classroom.getNumber()).get(row).set(placeInRow, st);
            }
        }
        return data;
    }


    public void makeSeating() {
        List<Student> studentsWithoutSeating = new ArrayList<>();
        students.findAll().forEach(student -> studentsWithoutSeating.add(student));
        List<Integer> cabinets = new ArrayList<>();
        classrooms.findAll().forEach(classroom -> cabinets.add(classroom.getNumber()));
        String prevSchool = "";
        Integer nextPlace = 0; // from 0 to 29
        Integer nextCabinetIndex = 0;
        for(Student student : studentsWithoutSeating) {
            String school = student.getSchool();
            if(!Objects.equals(prevSchool, "") && Objects.equals(prevSchool, school)) {
                nextPlace += 1;
                if(nextPlace > 30) {
                    nextPlace = 0;
                    nextCabinetIndex += 1;
                }
            }
            prevSchool = school;
            student.setClassroom(classrooms.findByNumber(cabinets.get(nextCabinetIndex)));
            student.setPlace(nextPlace);
            students.save(student);
            nextPlace += 1;
            if(nextPlace > 30) {
                nextPlace = 0;
                nextCabinetIndex += 1;
            }
        }
    }
}

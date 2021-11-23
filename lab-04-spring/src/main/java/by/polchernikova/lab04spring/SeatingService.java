package by.polchernikova.lab04spring;

import by.polchernikova.lab04spring.Model.Classroom;
import by.polchernikova.lab04spring.Model.Student;
import by.polchernikova.lab04spring.Repozitories.ClassroomRepository;
import by.polchernikova.lab04spring.Repozitories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
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

    public ArrayList<Pair<Integer, ArrayList<ArrayList<ArrayList<Student>>>>> prepareDataForDisplaying() {
        ArrayList<Pair<Integer, ArrayList<ArrayList<ArrayList<Student>>>>> data = new ArrayList<>();
        List<Classroom> classes = new ArrayList<>();
        classrooms.findAll().forEach(classroom -> classes.add(classroom));
        for(int i = 0; i < classes.size(); i++) {
            ArrayList<ArrayList<ArrayList<Student>>> classroom = new ArrayList<>();
            for(int j = 0; j < 5; j++) {
                ArrayList<ArrayList<Student>> row = new ArrayList<>();
                for(int k = 0; k < 3; k++) {
                    ArrayList<Student> desk = new ArrayList<>();
                    for(int l = 0; l < 2; l++) {
                        desk.add(null);
                    }
                    row.add(desk);
                }
                classroom.add(row);
            }
            data.add(Pair.of(classes.get(i).getNumber(), classroom));
        }
        for(int i = 0; i < classes.size(); i++) {
            Classroom classroom = classes.get(i);
            Iterable<Student> studentsInClass = students.findAllByClassroom(classroom);
            for(Student st : studentsInClass) {
                Integer place = st.getPlace();
                Integer row = place / 6;
                Integer placeInRow = place % 6;
                Integer desk = placeInRow / 2;
                Integer placeOnADesk = placeInRow % 2;
                data.get(i).getSecond().get(row).get(desk).set(placeOnADesk, st);
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

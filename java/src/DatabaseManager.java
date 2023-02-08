import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.*;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class DatabaseManager {
    private static final String STUDENTS_FILE = "students.csv";
    private static final String PROFESORS_FILE = "profesors.csv";

    private static final String COURSES_FILE = "courses.csv";

    public void createStudents(int numberOfStudents) throws IOException {
        List<Student> students = generateRandomStudents(numberOfStudents);
        writeStudentsToFile(STUDENTS_FILE, students);
    }

    private List<Student> generateRandomStudents(int numberOfStudents) {
        List<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfStudents; i++) {
            int group = random.nextInt(10) + 1;
            String nume = "Nume" + (i+1);
            String prenume = "Prenume" + (i+1);
            Student student = new Student(nume, prenume, group);
            students.add(student);
        }
        return students;
    }

    private void writeStudentsToFile(String fileName, List<Student> students) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (Student student : students) {
            String line = student.nume + "," + student.prenume + "," + student.grupa;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }

    public void addStudent() throws IOException {
        // Create a scanner for reading input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the student's information
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.print("Grupa: ");
        int grupa = scanner.nextInt();
        scanner.nextLine();

        // Create a new Student object with the user's information
        Student student = new Student(nume, prenume, grupa);

        // Retrieve the existing students from file
        List<Student> students = readStudentsFromFile(STUDENTS_FILE);

        // Add the new student to the list
        students.add(student);

        // Write the updated list of students to the file
        writeStudentsToFile(STUDENTS_FILE, students);
    }

    private List<Student> readStudentsFromFile(String fileName) throws IOException {
        List<Student> students = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] studentInfo = line.split(",");
                String nume = studentInfo[0];
                String prenume = studentInfo[1];
                int grupa = Integer.parseInt(studentInfo[2]);
                students.add(new Student(nume, prenume, grupa));
            }
            br.close();
        }
        return students;
    }

    public void createProfesors(int numberOfProfesors) throws IOException {
        List<Profesor> profesors = generateRandomProfesors(numberOfProfesors);
        writeProfesorsToFile(PROFESORS_FILE, profesors);
    }

    private List<Profesor> generateRandomProfesors(int numberOfProfesors) {
        List<Profesor> profesors = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfProfesors; i++) {
            String nume = "Nume" + (i+1);
            String prenume = "Prenume" + (i+1);
            Profesor profesor = new Profesor(nume, prenume);
            profesors.add(profesor);
        }
        return profesors;
    }

    private void writeProfesorsToFile(String fileName, List<Profesor> profesors) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (Profesor profesor : profesors) {
            String line = profesor.nume + "," + profesor.prenume;
            bw.write(line);
            bw.newLine();
        }
        bw.close();
    }

    public void addProfesor() throws IOException {
        // Create a scanner for reading input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the profesor's information
        System.out.print("Nume: ");
        String nume = scanner.nextLine();
        System.out.print("Prenume: ");
        String prenume = scanner.nextLine();

        // Create a new Profesor object with the user's information
        Profesor profesor = new Profesor(nume, prenume);

        // Retrieve the existing profesors from file
        List<Profesor> profesors = readProfesorsFromFile(PROFESORS_FILE);

        // Add the new profesor to the list
        profesors.add(profesor);

        // Write the updated list of profesors to the file
        writeProfesorsToFile(PROFESORS_FILE, profesors);
    }

    private List<Profesor> readProfesorsFromFile(String fileName) throws IOException {
        List<Profesor> profesors = new ArrayList<>();
        File file = new File(fileName);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] profesorInfo = line.split(",");
                String nume = profesorInfo[0];
                String prenume = profesorInfo[1];
                profesors.add(new Profesor(nume, prenume));
            }
            br.close();
        }
        return profesors;
    }
    public void createCourses(int numberOfCourses) throws IOException {
        List<Student> students = readStudentsFromFile(STUDENTS_FILE);
        List<Profesor> profesors = readProfesorsFromFile(PROFESORS_FILE);
        List<Curs> courses = generateRandomCourses(numberOfCourses, students, profesors);
        writeCoursesToFile(COURSES_FILE, courses);
    }

    private List<Curs> generateRandomCourses(int numberOfCourses, List<Student> students, List<Profesor> profesors) {
        List<Curs> courses = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < numberOfCourses; i++) {
            String nume = "Course" + (i+1);
            String descriere;
            descriere = "Description for Course" + (i+1);
            Profesor profu = profesors.get(random.nextInt(profesors.size()));
            Set<Student> studenti = new HashSet<>();
            int numberOfStudents = random.nextInt(students.size()) + 1;
            for (int j = 0; j < numberOfStudents; j++) {
                studenti.add(students.get(random.nextInt(students.size())));
            }
            Curs curs = new Curs(nume, descriere, profu, studenti);
            courses.add(curs);
        }
        return courses;
    }
    private void writeCoursesToFile(String fileName, List<Curs> courses) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for (Curs curs : courses) {
            StringBuilder line = new StringBuilder();
            line.append(curs.nume).append(",").append(curs.descriere).append(",").append(curs.profu.nume).append(",").append(curs.profu.prenume).append(",");
            for(Student student: curs.studenti) {
                line.append(student.nume).append(",").append(student.prenume).append(";");
            }
            bw.write(line.toString());
            bw.newLine();
        }
        bw.close();
    }

    public void sortStudentsAlphabetically() throws IOException {
        // Citeste fisierul CSV si creeaza o lista de studenti
        List<Student> students = readStudentsFromFile(STUDENTS_FILE);

        // sorteaza lista de studenti
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.nume.compareTo(o2.nume);
            }
        });

        // afiseaza studentii sortati
        for (Student student : students) {
            System.out.println(student.nume + " " + student.prenume + " " + student.grupa);
        }
    }

    public void groupByGroup() throws IOException {
        // Citeste fisierul CSV si creeaza o lista de studenti
        List<Student> students = readStudentsFromFile(STUDENTS_FILE);

        // Creeaza un map care sa asocieze grupa cu lista de studenti din acea grupa
        Map<Integer, List<Student>> groupedStudents = new HashMap<>();
        for (Student student : students) {
            // Daca nu exista deja o lista pentru acea grupa, creeaza una noua
            if (!groupedStudents.containsKey(student.grupa)) {
                groupedStudents.put(student.grupa, new ArrayList<>());
            }
            // Adauga studentul la lista corespunzatoare
            groupedStudents.get(student.grupa).add(student);
        }

        // Parcurge fiecare grupa si afiseaza studentii din acea grupa
        for (Map.Entry<Integer, List<Student>> entry : groupedStudents.entrySet()) {
            System.out.println("Group " + entry.getKey() + ":");
            for (Student student : entry.getValue()) {
                System.out.println("    " + student.nume + " " + student.prenume);
            }
        }
    }
}

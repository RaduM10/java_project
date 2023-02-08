import java.io.IOException;

public class Aplicatie  implements Comenzi {
    DatabaseManager dbmanager;

    public  Aplicatie(DatabaseManager db){
        this.dbmanager = db;
    }
    @Override
    public void execute()  throws IOException{
        Comenzi creareStudenti = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.createStudents(10);
            }
        };
        Comenzi creareProfi = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.createProfesors(1);
            }
        };
        Comenzi creareCurs = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.createCourses(1);
            }
        };
        Comenzi adaugaStudent = new Comenzi(){
            @Override
            public void execute() throws IOException {
               dbmanager.addStudent();
            }
        };
        Comenzi adaugaProf = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.addProfesor();
            }
        };

        Comenzi grupareGrupa = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.groupByGroup();
            }
        };

        Comenzi afisareSort = new Comenzi(){
            @Override
            public void execute() throws IOException {
                dbmanager.sortStudentsAlphabetically();
            }
        };
        MenuItem adaugareProf = new MenuCommand("Adauga Profesor", "Adauga un nou profesor in baza de date", adaugaProf);
        MenuItem adaugaStud = new MenuCommand("Adauga Studentd", "Adauga un nou student in baza de date", adaugaStudent);
        MenuItem generareStudenti = new MenuCommand("Generare Studenti", "Genereaza un fisier cu studenti", creareStudenti);
        MenuItem generareProfi = new MenuCommand("Generare Profesori", "Genereaza un fisier cu profesori", creareProfi);
        MenuItem generareCurs = new MenuCommand("Generare Cursuri", "Genereaza un fisier cu cursuri", creareCurs);
        MenuItem grupareStudenti = new MenuCommand("Grupare Studenti", "Grupeaza studentii dupa grupa", grupareGrupa);
        MenuItem sortareStudenti = new MenuCommand("Sorteaza Studenti", "Sorteaza studentii",afisareSort);

        Menu mainMenu = new Menu("Meniu Admin", new MenuItem[]{generareStudenti,adaugaStud, generareProfi,adaugareProf, generareCurs, grupareStudenti, sortareStudenti});

        mainMenu.display();
        mainMenu.handleInput();
    }

}

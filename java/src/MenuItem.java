abstract  class MenuItem implements  Comenzi{
    String nume;
    String descriere;

    public MenuItem(String nume, String descriere){
        this.nume = nume;
        this.descriere = descriere;
    }

    public String getNume()
    {
        return  nume;
    }

    public String getDescriere(){
        return  descriere;
    }
}

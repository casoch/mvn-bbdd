package es.gimbernat;

public class Visitante {

    enum Tipo{ADULTO, JUNIOR, SENIOR, NIÑO};

    private int id;
    private String nombre;
    private Tipo tipo;
    

    public Visitante(){}

    public Visitante(String nombre, Tipo tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public boolean insert()
    {
        return false;
    }

    public boolean update()
    {
        return false;
    }

    public boolean delete()
    {
        return false;
    }
}

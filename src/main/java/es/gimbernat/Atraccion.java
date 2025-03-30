package es.gimbernat;

public class Atraccion {

    enum Tipo{MONTAÑA_RUSA, ESPECTACULO, TIOVIVO};

    private int id;
    private String nombre;
    private Tipo tipo;
    private int capacidad;

    public Atraccion(){}

    public Atraccion(String nombre, Tipo tipo, int capacidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public boolean persist()
    {
        return false;
    }

    public boolean merge()
    {
        return false;
    }

    public boolean remove()
    {
        return false;
    }
    
    public Atraccion[] find()
    {
    	return null;
    }
}

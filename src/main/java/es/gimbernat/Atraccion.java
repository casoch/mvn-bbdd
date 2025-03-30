package es.gimbernat;

import java.util.List;

public class Atraccion {

    enum Tipo{MONTAÑA_RUSA, ESPECTACULO, TIOVIVO};

    private int id;
    private String nombre;
    private Tipo tipo;
    private int capacidad;
    private EntityManager em;

    public Atraccion(EntityManager em){
    	this.em = em;
    }

    public Atraccion(EntityManager em, int id, String nombre, Tipo tipo, int capacidad) {
        this(em, nombre, tipo, capacidad);
    	this.id = id;
    }
    
	public Atraccion(EntityManager em, String nombre, Tipo tipo, int capacidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.em = em;
    }

	public void askUser()
	{
		this.nombre = (Utils.askText("Indica el nombre de la atracción: "));
		this.tipo = (Utils.askEnum("Indica el tipo de la atracción: ", Tipo.class));
		this.capacidad = (Utils.askInteger("Indica la capacidad de la atracción:"));
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
    
    public List<Atraccion> find()
    {
    	return null;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	@Override
	public String toString() {
		return "Atraccion [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", capacidad=" + capacidad + "]";
	}
	
	
}

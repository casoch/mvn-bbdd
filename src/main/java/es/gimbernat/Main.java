package es.gimbernat;

public class Main {
    public static void main(String[] args) {
    	EntityManager bd = new EntityManager();
        boolean okConexion = bd.init();

        if (okConexion)
        {
        	String[] opciones = {
        			"Alta", "Modificar", "Eliminar", "Ver", "Salir"
        	};
        	int opcion;
        	do
        	{
        		opcion = Utils.menu("Menú CRUD", opciones);
        		switch (opcion) {
				case 1: //Alta
					Atraccion a = new Atraccion(bd);
					a.askUser();
					a.persist();
					break;
				case 2: //Modificar
					
					break;
				case 3: //Eliminar
					
					break;
				case 4: //Ver
					
					break;

				default:
					break;
				}
        	}while(opcion!=0);
        }

    }
}
package es.gimbernat;

public class Main {
    public static void main(String[] args) {
    	BBDD bd = new BBDD();
        boolean okConexion = bd.init();

        System.out.println(okConexion);

    }
}
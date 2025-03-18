package es.gimbernat;

public class Main {
    public static void main(String[] args) {
        boolean okConexion = BBDD.init();

        System.out.println(okConexion);

    }
}
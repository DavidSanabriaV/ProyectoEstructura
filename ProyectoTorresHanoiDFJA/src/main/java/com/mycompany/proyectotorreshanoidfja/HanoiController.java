package com.mycompany.proyectotorreshanoidfja;

//Imports a utilizar
import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class HanoiController {

    //Variable y pila a usar en toda la clase
    private PilaDinamica[] torres;
    private int discos;
    private int movimientosRealizados = 0;

    public HanoiController(int discos) {
        this.discos = discos;

        //Creacion de las 3 pilas
        torres = new PilaDinamica[4];
        for (int i = 1; i < 4; i++) {
            torres[i] = new PilaDinamica();
        }
    }

    public void mostrarTorres() {
        //Metodo para mostrar las torres mediante un for que recorre las torres
        for (int i = 1; i < torres.length; i++) {
            System.out.print("Torre: " + i);
            torres[i].mostrar();
        }
    }

    //Metodo para iniciar el juego
    public void juego() {

        //Metodo para ingresar los discos de mayor a menor 
        System.out.println("Ingresando discos a la torre");
        for (int i = discos; i >= 1; i--) {
            torres[1].push(i);
        }

        //Llamado a los demas metodos a utilizar
        mostrarTorres();
        movimientos();
    }

    //Metodo para calcular los movimientos optimos
    private int contMovimientosOptimos(int n) {
        if (n == 1) {
            return 1;
        }
        return 2 * contMovimientosOptimos(n - 1) + 1;
    }

    //Metodo para hacer los movimientos
    private void movimientos() {
        //Try catch para evitar que el programa no se caiga tan facil
        try {
            //Menu del juego 
            int decision = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero 1 si desea jugar" + "\n"
                    + "Ingrese el numero 2 si desea ver los movimientos optimos" + "\n"
                    + "Ingrese el numero 3 si desea salirse del programa" + "\n"
                    + "Ingrese el numero 4 si desea ver los movimientos óptimos de forma secuencial" + "\n"));
            while (decision < 1 || decision > 4) {
                decision = Integer.parseInt(JOptionPane.showInputDialog("Número incorrecto" + "\n"
                        + "Ingrese el numero 1 si desea jugar" + "\n"
                        + "Ingrese el numero 2 si desea ver los movimientos optimos" + "\n"
                        + "Ingrese el numero 3 si desea salirse del programa" + "\n"
                        + "Ingrese el numero 4 si desea ver los movimientos óptimos de forma secuencial" + "\n"));
            }
            //Metodo para ejecutar el metodo de movimientos optimos
            if (decision == 2) {
                int movsOptimos = contMovimientosOptimos(discos) - movimientosRealizados;
                JOptionPane.showMessageDialog(null, "Movimientos optimos restantes: " + movsOptimos);
                movimientos();
                return;
            }
            //Metodo para salirse del programa
            if (decision == 3) {
                return;
            }
            // Opción 4: Mostrar movimientos óptimos de forma secuencial
            if (decision == 4) {
                mostrarMovimientosOptimos(discos, 1, 3, 2);
                movimientos();
                return;
            }

            //Asignacion de lo puesto por el usuario a las variables 
            int actual = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la torre de la que desea mover el disco"));
            int destino = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de la torre a la cual quiere mover el disco"));

            //Metodo para comprobar que se seleccionen torres que existan y que no esten vacias al querer hacer un movimiento
            if (actual < 1 || actual > 3 || destino < 1 || destino > 3) {
                JOptionPane.showMessageDialog(null, "Movimiento invalido." + "\n" + "Debe elegir entre 1, 2 y 3");
            } else if (torres[actual].isEmpty()) {
                JOptionPane.showMessageDialog(null, "La torre de origen esta vacia. Intente nuevamente.");
            } else {
                int movimiento = torres[actual].pop();
                //Metodo para comprobar si el movimiento se puede hacer
                if (!torres[destino].isEmpty() && torres[destino].peek() < movimiento) {
                    JOptionPane.showMessageDialog(null, "Movimiento invalido");
                    torres[actual].push(movimiento);
                } else {
                    torres[destino].push(movimiento);
                    movimientosRealizados++;
                }
                mostrarTorres();

                if (torres[1].isEmpty() && (torres[2].size() == discos || torres[3].size() == discos)) {
                    JOptionPane.showMessageDialog(null, "Felicidades ganaste");
                    return;
                }
            }

            movimientos();

        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Incorrecto, solo se permiten ingresar numeros.");
            movimientos();
        }
    }

    private void mostrarMovimientosOptimos(int n, int origen, int destino, int auxiliar) {
        if (n == 1) {
            JOptionPane.showMessageDialog(null, "Mover disco 1 de Torre " + origen + " a Torre " + destino);
            return;
        }
        mostrarMovimientosOptimos(n - 1, origen, auxiliar, destino);
        JOptionPane.showMessageDialog(null, "Mover disco " + n + " de Torre " + origen + " a Torre " + destino);
        mostrarMovimientosOptimos(n - 1, auxiliar, destino, origen);
    }
}

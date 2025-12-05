/**
 * Classe con il main, che avvia l'app
 * che rappresenta il flusso dei clienti di un ufficio postale
 * messi in attesa da un totem elettronico che assegna
 * un numero progressivo e stampa il ticket.
 * Clienti gestiti da un solo sportello
 * @author frida
 * @version 1.0
 */
public class SimulatorePoste {
    public static void main(String[] args) {

        ListaClienti listaClienti = new ListaClienti();


        Thread arriviThread1 = new Thread(new GestoreArrivi(listaClienti, "Totem A"));
        Thread arriviThread2 = new Thread(new GestoreArrivi(listaClienti, "Totem B"));


        Thread sportelloThread1 = new Thread(new Sportello(listaClienti, "Marzia"));
        Thread sportelloThread2 = new Thread(new Sportello(listaClienti, "Cinzia"));

        arriviThread1.start();
        arriviThread2.start();

        sportelloThread1.start();
        sportelloThread2.start();
    }
}
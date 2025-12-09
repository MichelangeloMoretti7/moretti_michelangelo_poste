import java.time.Duration;
import java.time.LocalTime;

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

        int limiteClienti = 3;
        int minutiApertura = 2;

        ListaClienti listaClienti = new ListaClienti(limiteClienti);

        LocalTime apertura = LocalTime.now();
        LocalTime chiusura = apertura.plusMinutes(minutiApertura);

        System.out.println(" Apertura ufficio alle: " + apertura);
        System.out.println(" Chiusura prevista alle: " + chiusura);

        Thread arriviThread1 = new Thread(new GestoreArrivi(listaClienti, "Totem A", chiusura));
        Thread arriviThread2 = new Thread(new GestoreArrivi(listaClienti, "Totem B", chiusura));

        Thread sportelloThread1 = new Thread(new Sportello(listaClienti, "Marzia"));
        Thread sportelloThread2 = new Thread(new Sportello(listaClienti, "Cinzia"));

        arriviThread1.start();
        arriviThread2.start();
        sportelloThread1.start();
        sportelloThread2.start();

        while (LocalTime.now().isBefore(chiusura)) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
        }

        System.out.println("\n Orario di chiusura ufficio ");


        arriviThread1.interrupt();
        arriviThread2.interrupt();
        sportelloThread1.interrupt();
        sportelloThread2.interrupt();
    }
}

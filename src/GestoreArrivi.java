import java.time.Duration;
import java.time.LocalTime;

/**
 * Classe che implementa il thread per il totem touch screen che aggiunge
 * i clienti alla lista di attesa e genera il numero di attesa.
 * rappresenta il produttore
 * @author frida
 * @version 1.0
 */
public class GestoreArrivi implements Runnable {

    private ListaClienti listaClienti;
    private final int attesaArrivi = 200;
    private String nomeTotem;
    private LocalTime endTime;

    public GestoreArrivi(ListaClienti listaClienti, String nomeTotem, LocalTime endTime) {
        this.listaClienti = listaClienti;
        this.nomeTotem = nomeTotem;
        this.endTime = endTime;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {

                if (LocalTime.now().isAfter(endTime)) {
                    System.out.println("[" + nomeTotem + "] Tempo di apertura finito. Stop arrivi.");
                    break;
                }

                Thread.sleep(attesaArrivi);

                Integer clienteArrivato = listaClienti.addCliente(nomeTotem);

                if (clienteArrivato == null) {
                    System.out.println("[" + nomeTotem + "] Lista piena, attendo liberazione...");
                    Thread.sleep(300);
                } else {

                    Duration duration = Duration.between(LocalTime.now(), endTime);
                    long secondsBetween = duration.getSeconds();
                    System.out.println("[" + nomeTotem + "] Tempo rimanente: " + secondsBetween + " secondi");
                }
            }
        } catch (InterruptedException e) {
            System.out.println("[" + nomeTotem + "] Interrotto durante lo sleep");
        } finally {
            System.out.println("[" + nomeTotem + "] Posta Chiusa");
        }
    }
}

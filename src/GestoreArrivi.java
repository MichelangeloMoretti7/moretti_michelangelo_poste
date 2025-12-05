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

    public GestoreArrivi(ListaClienti listaClienti, String nomeTotem) {
        this.listaClienti = listaClienti;
        this.nomeTotem = nomeTotem;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Thread.sleep(attesaArrivi);

                Integer clienteArrivato = listaClienti.addCliente();

                if (clienteArrivato == null) {
                    break;
                }

                System.out.println("[" + nomeTotem + "] Arrivo Cliente Numero \t " + clienteArrivato);
            }
        } catch (InterruptedException e) {
            System.out.println("[" + nomeTotem + "] Interrotto durante lo sleep");
        } finally {
            System.out.println("[" + nomeTotem + "] Posta Chiusa");
        }
    }
}
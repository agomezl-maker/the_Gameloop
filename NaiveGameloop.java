public class NaiveGameLoop implements Runnable {

    private boolean running = false;
    private Thread gameThread;

    // Variable de ejemplo: posición de un "personaje"
    private double posicionX = 0;

    public void start() {
        running = true;
        // Se crea un hilo secundario para no congelar la interfaz
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stop() {
        running = false;
    }
    public void run() {
        // Bucle infinito SIN control de tiempo (el problema de la diapositiva 3)
        while (running) {
            input();
            update();
            render();
        }
    }

    private void input() {
        // Aquí normalmente se leería teclado/mando
        // (se deja vacío para el ejemplo)
    }

    private void update() {
        // Lógica del juego: mover el personaje
        posicionX += 1;
    }

    private void render() {
        // Simula "dibujar" mostrando la posición en consola
        System.out.println("Posición X: " + posicionX);
    }

    public static void main(String[] args) {
        NaiveGameLoop juego = new NaiveGameLoop();
        juego.start();
    }
}

public class Main {
    private boolean running = true;

    // Inicialización
    public void init() {
        System.out.println("Load Game.....");
    }

    // Entrada
    public void input() {
        // Aquí se procesaría la entrada del usuario
    }

    // Actualización de la lógica
    public void update() {
        System.out.println("Hello, World!");

        // Finaliza el ciclo después de una iteración
        running = false;
    }

    // Renderizado
    public void render() {
        // Aquí se dibujaría en pantalla
    }

    // Limpieza
    public void cleanup() {
        System.out.println("finished Game.");
    }

    // Game Loop
    public void run() {
        init();

        while (running) {
            input();
            update();
            render();
        }

        cleanup();
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.run();
    }
}

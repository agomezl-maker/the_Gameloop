# Game Loop: El Motor de Vida de un Videojuego

**Link de diapositivas:** https://prezi.com/p/edit/_lv2t-qlmn6m/

## Descripción

Este README resume el concepto de **Game Loop**, explicando por qué los videojuegos necesitan un modelo de ejecución distinto al del software tradicional, cómo se estructura ese bucle y cómo se implementa de forma profesional en Java usando control de tiempo con `System.nanoTime()`.

## Software reactivo vs. videojuego

Un programa tradicional (un procesador de texto, un formulario) es **reactivo**: permanece inactivo hasta que el usuario realiza una acción. Si nadie pulsa una tecla, el programa no procesa nada.

Un videojuego no puede funcionar así. Aunque el jugador no toque ningún botón, el mundo del juego debe seguir corriendo: los enemigos siguen decidiendo su próximo movimiento, los efectos visuales continúan, el tiempo avanza. Esa necesidad de actividad constante e interactividad en tiempo real (un reto que ni el cine, con su ilusión de movimiento por imágenes fijas, enfrenta) es la razón de ser del **Game Loop**: un bucle que nunca se detiene.

## ¿Qué es el Game Loop?

El Game Loop es el **corazón de cualquier videojuego**: conecta y sincroniza todos sus sistemas. Es una secuencia que se repite continuamente, idealmente cada **16.6 ms** (para lograr 60 imágenes por segundo), ejecutando en orden tres fases:

1. **Input** — Entrada: se revisa de inmediato qué está haciendo el jugador (teclado, control, etc.).
2. **Update** — Actualización: se calcula la lógica y la física del juego en memoria RAM (posiciones, colisiones, vida, comportamiento de enemigos, etc.).
3. **Render** — Dibujo: se borra el frame anterior y se pinta en pantalla el nuevo estado del mundo.

Este ciclo **Input → Update → Render** se repite sin parar desde que se abre el juego hasta que se cierra.

## Implementación en Java

El bucle infinito **no puede** ejecutarse en el hilo principal, ya que congelaría toda la ventana del sistema. Por eso es obligatorio crear un **hilo secundario**, usando la clase `Thread` y la interfaz `Runnable`, para que el Game Loop corra de forma independiente.

### El problema del hardware

Un bucle simple, sin ningún control de tiempo, depende directamente de la potencia de la computadora:

- En una máquina rápida → el bucle se ejecuta miles de veces por segundo → movimiento absurdamente rápido e injugable.
- En una máquina lenta → el juego corre extremadamente lento.

En un desarrollo profesional esto es inaceptable: el juego debe comportarse igual sin importar el hardware.

### La solución: separar UPS y FPS

Usando `System.nanoTime()` (precisión en nanosegundos) se separan dos conceptos que antes estaban mezclados:

- **UPS (Updates Per Second):** actualizaciones de lógica por segundo. Deben mantenerse **fijos** (por ejemplo, siempre 60 UPS).
- **FPS (Frames Per Second):** cuadros dibujados por segundo. Pueden **variar** según la potencia de la tarjeta gráfica.

### Fixed Timestep

La técnica de **Fixed Timestep** (paso de tiempo fijo) funciona así:

- Se acumula en una variable el tiempo transcurrido desde la última actualización.
- Cuando el acumulador alcanza el umbral necesario (16.66 ms para 60 UPS), se ejecuta una actualización de lógica y se resta ese tiempo del acumulador.
- Mientras tanto, el renderizado se ejecuta tan rápido como el hardware lo permita.

De esta forma, aunque los gráficos pierdan calidad o velocidad en una máquina más débil, **la física y el movimiento del juego se mantienen siempre estables**.

## Conclusión

El Game Loop no es solo un fragmento de código más: es el motor de vida de cualquier videojuego. Controlar el tiempo con precisión (nanosegundos) y manejar correctamente los hilos de ejecución es lo que marca la diferencia entre:

- Un proyecto **amateur**, con caídas de rendimiento y comportamientos inconsistentes.
- Un sistema **profesional**, fluido y estable.

Dominar esta estructura de tiempo garantiza una buena experiencia de usuario, sin importar en qué computadora se esté jugando.

## Resumen del flujo

```
while (juego activo) {
    Input();    // Leer entrada del jugador
    
    // Fixed Timestep para Update
    while (acumulador >= 16.66ms) {
        Update();           // Lógica y física a UPS fijos
        acumulador -= 16.66ms;
    }
    
    Render();   // Dibujar a la velocidad que el hardware permita (FPS variable)
}
```
## Fuentes u Contenido de Referencias :
-https://java-design-patterns.com/es/patterns/game-loop/
--------------------------------------------------------
-https://www.youtube.com/watch?v=AuE5buBcBBE

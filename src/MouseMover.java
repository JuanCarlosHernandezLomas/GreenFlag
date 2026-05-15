import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class MouseMover {

    /*
     * =========================================
     * CONFIGURACIONES MODIFICABLES
     * =========================================
     */

    // Tiempo de inactividad antes de mover el mouse
    // Valor en SEGUNDOS
    // Ejemplo:
    // 5 = 5 segundos
    // 60 = 1 minuto
    // 300 = 5 minutos
    private static final int INACTIVITY_SECONDS = 5;

    // Cada cuánto tiempo revisa si hay actividad
    // Valor en SEGUNDOS
    private static final int CHECK_EVERY_SECONDS = 1;

    // Cantidad de pixeles que se moverá el mouse
    // 5  = movimiento pequeño
    // 25 = movimiento visible
    // 50 = movimiento grande
    private static final int MOVE_PIXELS = 25;

    // Tiempo de espera antes de regresar el mouse
    // Valor en MILISEGUNDOS
    // 1000 ms = 1 segundo
    private static final int RETURN_DELAY_MS = 300;

    // Nombre del archivo que detiene el programa
    // Si el archivo existe en el escritorio:
    // stop.txt
    // el programa finalizará automáticamente
    private static final String STOP_FILE_NAME = "stop.txt";

    public static void main(String[] args) throws IOException {

        try {

            /*
             * =========================================
             * EVITAR MÚLTIPLES INSTANCIAS
             * =========================================
             *
             * Crea un archivo temporal llamado:
             * running.lock
             *
             * Si ya existe significa que el programa
             * ya está ejecutándose.
             */

            File lockFile = new File("running.lock");

            if (lockFile.exists()) {

                System.out.println(
                        "MouseMover ya está ejecutándose."
                );

                System.exit(0);
            }

            lockFile.createNewFile();

            // Elimina el archivo automáticamente
            // al cerrar el programa
            lockFile.deleteOnExit();

            System.out.println("=================================");
            System.out.println("MouseMover iniciado");
            System.out.println("Hora inicio: " + LocalTime.now());
            System.out.println("=================================");

            /*
             * =========================================
             * OBTENER RUTA DEL ESCRITORIO
             * =========================================
             */

            String desktopPath =
                    System.getProperty("user.home")
                            + File.separator
                            + "Desktop";

            /*
             * =========================================
             * ARCHIVO PARA DETENER EL PROGRAMA
             * =========================================
             *
             * Crear manualmente:
             * stop.txt
             *
             * en el escritorio para cerrar el programa
             */

            File stopFile =
                    new File(desktopPath
                            + File.separator
                            + STOP_FILE_NAME);

            System.out.println("Para detener el programa crea:");
            System.out.println(stopFile.getAbsolutePath());

            /*
             * =========================================
             * CLASE ROBOT
             * =========================================
             *
             * Permite controlar el mouse automáticamente
             */

            Robot robot = new Robot();

            /*
             * =========================================
             * POSICIÓN INICIAL DEL MOUSE
             * =========================================
             */

            Point lastPoint =
                    MouseInfo.getPointerInfo().getLocation();

            /*
             * =========================================
             * TIEMPO DEL ÚLTIMO MOVIMIENTO
             * =========================================
             */

            long lastMoveTime =
                    System.currentTimeMillis();

            /*
             * =========================================
             * LOOP PRINCIPAL
             * =========================================
             */

            while (true) {

                /*
                 * =========================================
                 * DETENER PROGRAMA
                 * =========================================
                 */

                if (stopFile.exists()) {

                    System.out.println("=================================");
                    System.out.println("stop.txt detectado");
                    System.out.println("Finalizando MouseMover...");
                    System.out.println("Hora cierre: " + LocalTime.now());
                    System.out.println("=================================");

                    // Eliminar stop.txt
                    stopFile.delete();

                    // Finalizar aplicación
                    System.exit(0);
                }

                /*
                 * =========================================
                 * POSICIÓN ACTUAL DEL MOUSE
                 * =========================================
                 */

                Point currentPoint =
                        MouseInfo.getPointerInfo().getLocation();

                /*
                 * =========================================
                 * DETECTAR MOVIMIENTO REAL DEL USUARIO
                 * =========================================
                 */

                if (!currentPoint.equals(lastPoint)) {

                    lastMoveTime =
                            System.currentTimeMillis();

                    lastPoint = currentPoint;

                    System.out.println(
                            "Movimiento detectado -> "
                                    + LocalTime.now()
                    );
                }

                /*
                 * =========================================
                 * CALCULAR TIEMPO DE INACTIVIDAD
                 * =========================================
                 */

                long inactiveTime =
                        System.currentTimeMillis()
                                - lastMoveTime;

                /*
                 * =========================================
                 * MOVER EL MOUSE SI HAY INACTIVIDAD
                 * =========================================
                 */

                if (inactiveTime
                        >= INACTIVITY_SECONDS * 1000L) {

                    int x = currentPoint.x;
                    int y = currentPoint.y;

                    System.out.println(
                            "Inactividad detectada. Moviendo mouse..."
                    );

                    // Mover mouse
                    robot.mouseMove(x + MOVE_PIXELS, y);

                    // Esperar
                    Thread.sleep(RETURN_DELAY_MS);

                    // Regresar mouse
                    robot.mouseMove(x, y);

                    // Actualizar datos
                    lastPoint =
                            MouseInfo.getPointerInfo().getLocation();

                    lastMoveTime =
                            System.currentTimeMillis();

                    System.out.println(
                            "Mouse movido a las "
                                    + LocalTime.now()
                    );
                }

                /*
                 * =========================================
                 * ESPERAR ANTES DE VOLVER A REVISAR
                 * =========================================
                 */

                Thread.sleep(CHECK_EVERY_SECONDS * 1000L);
            }

        } catch (Exception e) {

            System.err.println("=================================");
            System.err.println("Error en MouseMover");
            System.err.println("=================================");

            e.printStackTrace();
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecocatch.modelo;

/**
 *
 * @author Milo
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportePartida {
    private final int score;
    private final MultiListaElementos historial;

    public ReportePartida(int score, MultiListaElementos historial) {
        this.score = score;
        this.historial = historial;
    }

    /**
     * Guarda el historial de la partida en eco_historial.txt (formato legible).
     */
    public void guardarHistorialTXT() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eco_historial.txt", true))) {
            writer.write("===== PARTIDA: " + timestamp() + " =====\n");
            writer.write("Puntaje total: " + score + " pts\n");
            writer.write("Resumen por tipo:\n");

            for (String tipo : new String[]{"Orgánico", "Inorgánico", "Tóxico"}) {
                int cantidad = historial.contarPorTipo(tipo);
                writer.write("- " + tipo + ": " + cantidad + "\n");
            }

            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error al guardar historial: " + e.getMessage());
        }
    }

    /**
     * Exporta la partida al archivo eco_reporte.csv con columnas:
     * FechaHora,Puntaje,TotalRecogidos,Organico,Inorganico,Toxico
     * Este archivo es usado por el panel de estadísticas y puede ser leído usando listas enlazadas simples.
     */
    public void exportarCSV() {
        final String nombreArchivo = "eco_reporte.csv";
        boolean archivoNuevo = !(new File(nombreArchivo).exists());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
            if (archivoNuevo) {
                writer.write("FechaHora,Puntaje,TotalRecogidos,Organico,Inorganico,Toxico\n");
            }
            int organicos = historial.contarPorTipo("Orgánico");
            int inorganicos = historial.contarPorTipo("Inorgánico");
            int toxicos = historial.contarPorTipo("Tóxico");
            int totalRecogidos = organicos + inorganicos + toxicos;
            writer.write(
                timestamp() + "," +
                score + "," +
                totalRecogidos + "," +
                organicos + "," +
                inorganicos + "," +
                toxicos + "\n"
            );
        } catch (IOException e) {
            System.err.println("Error al exportar CSV: " + e.getMessage());
        }
    }

    /**
     * Guarda los logros obtenidos en eco_logros.txt.
     */
    public void generarLogros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eco_logros.txt", true))) {
            writer.write("🏆 Logros desbloqueados - " + timestamp() + "\n");
            int toxicos = historial.contarPorTipo("Tóxico");
            int inorganicos = historial.contarPorTipo("Inorgánico");
            int organicos = historial.contarPorTipo("Orgánico");

            if (toxicos >= 10) writer.write("✔ Cazador nuclear: +10 tóxicos recogidos\n");
            if (organicos >= 10) writer.write("✔ Guardián natural: +10 orgánicos recogidos\n");
            if (inorganicos >= 10) writer.write("✔ Destructor plástico: +10 inorgánicos recogidos\n");
            if (toxicos == 0 && inorganicos == 0 && organicos > 0) writer.write("✔ Verde puro: Solo recolectaste orgánicos\n");

            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error al guardar logros: " + e.getMessage());
        }
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

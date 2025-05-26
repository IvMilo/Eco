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

    public void exportarCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eco_reporte.csv", true))) {
            writer.write(timestamp() + "," + score + "," +
                         historial.contarPorTipo("Orgánico") + "," +
                         historial.contarPorTipo("Inorgánico") + "," +
                         historial.contarPorTipo("Tóxico") + "\n");
        } catch (IOException e) {
            System.err.println("Error al exportar CSV: " + e.getMessage());
        }
    }

    public void generarLogros() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("eco_logros.txt", true))) {
            writer.write("🏆 Logros desbloqueados - " + timestamp() + "\n");
            int toxicos = historial.contarPorTipo("Tóxico");
            int inorganicos = historial.contarPorTipo("Inorgánico");
            int organicos = historial.contarPorTipo("Orgánico");

            if (toxicos >= 10) writer.write("✔ Cazador nuclear: +10 tóxicos recogidos\n");
            if (organicos >= 10) writer.write("✔ Guardián natural: +10 orgánicos recogidos\n");
            if (inorganicos >= 10) writer.write("✔ Destructor plástico: +10 inorgánicos recogidos\n");
            if (toxicos == 0 && inorganicos == 0) writer.write("✔ Verde puro: Solo recolectaste orgánicos\n");

            writer.write("\n");
        } catch (IOException e) {
            System.err.println("Error al guardar logros: " + e.getMessage());
        }
    }

    private String timestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}


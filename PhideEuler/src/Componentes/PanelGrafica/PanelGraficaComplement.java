package Componentes.PanelGrafica;

import App.Ventana;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelGraficaComplement implements ActionListener {

    private PanelGraficaTemplate panelGraficaTemplate;
    private Ventana ventana;
    int puntos[][];

    public PanelGraficaComplement(int[][] e,Ventana a,boolean recta){
        ventana = a;
        puntos = e;
        panelGraficaTemplate = new PanelGraficaTemplate(this,recta, ventana);
    }

    public PanelGraficaTemplate getTemplate() {
        return panelGraficaTemplate;
    }

    public int [][] obtenerPuntos(){
        return puntos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "Completar"){
            ventana.completar();
        }else if(e.getActionCommand() == "Limpiar"){
            ventana.limpiar();
        }
    }
}

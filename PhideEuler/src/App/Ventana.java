package App;

import Componentes.PanelGrafica.PanelGraficaComplement;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Ventana extends JFrame {

    private JPanel barraSuperior,panelFuncion,panelGrafica;
    private JButton botonCerrar;
    private JTextField numeroEntrada;
    private JLabel labelResultado,texto;
    private JButton botonEjecutar;
    private PanelGraficaComplement panelGraficaComplement;
    private int puntos[][] = new int[0][2];
    private boolean recta;

    public Ventana() {

        recta = false;

        barraSuperior = new JPanel();
        barraSuperior.setSize(800,30);
        barraSuperior.setLayout(null);
        barraSuperior.setLocation(0,0);
        barraSuperior.setBackground(new Color(40,40,40));
        this.add(barraSuperior);

        panelGrafica = new JPanel();
        panelGrafica.setSize(500,500);
        panelGrafica.setLayout(null);
        panelGrafica.setLocation(300,30);
        panelGrafica.setBackground(new Color(45,45,45));
        this.add(panelGrafica);

        panelGraficaComplement = new PanelGraficaComplement(puntos,this,recta);
        panelGrafica.add(panelGraficaComplement.getTemplate());


        panelFuncion = new JPanel();
        panelFuncion.setSize(300,500);
        panelFuncion.setLayout(null);
        panelFuncion.setLocation(0,30);
        panelFuncion.setBackground(new Color(35,35,35));
        this.add(panelFuncion);

        texto = new JLabel();
        texto.setSize(150,90);
        texto.setIcon(new ImageIcon(ClassLoader.getSystemResource("Euler.png")));
        texto.setLocation(75,230);
        panelFuncion.add(texto);

        texto = new JLabel();
        texto.setSize(200,200);
        texto.setIcon(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("UDblanco.png")).getImage().getScaledInstance(200,200,Image.SCALE_AREA_AVERAGING)));
        texto.setLocation(50,0);
        panelFuncion.add(texto);

        texto = new JLabel("Resultado");
        texto.setSize(150,60);
        texto.setLocation(75,350);
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Arial",1,20));
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        panelFuncion.add(texto);

        numeroEntrada = new JTextField();
        numeroEntrada.setSize(70,40);
        numeroEntrada.setLocation(135,255);
        numeroEntrada.setBackground(new Color(45,45,45));
        numeroEntrada.setBorder(null);
        numeroEntrada.setForeground(Color.WHITE);
        numeroEntrada.setHorizontalAlignment(SwingConstants.CENTER);
        numeroEntrada.setFont(new Font("Arial",1,20));
        panelFuncion.add(numeroEntrada);

        botonCerrar = new JButton("X");
        botonCerrar.setSize(60,30);
        botonCerrar.setLocation(740,0);
        botonCerrar.setBackground(new Color(30,30,30));
        botonCerrar.setForeground(Color.WHITE);
        botonCerrar.setBorder(null);
        botonCerrar.setFocusable(false);
        botonCerrar.addActionListener(e -> System.exit(0));
        barraSuperior.add(botonCerrar);

        botonEjecutar = new JButton("Ejecutar");
        botonEjecutar.setSize(60,30);
        botonEjecutar.setLocation(120,320);
        botonEjecutar.setBackground(new Color(45,45,45));
        botonEjecutar.setForeground(Color.WHITE);
        botonEjecutar.setBorder(null);
        botonEjecutar.setFocusable(false);
        botonEjecutar.addActionListener(e -> {
            labelResultado.setText(String.valueOf(Euler(Integer.parseInt(numeroEntrada.getText()))));
            panelGrafica.removeAll();
            panelGraficaComplement = new PanelGraficaComplement(puntos,this,recta);
            panelGrafica.add(panelGraficaComplement.getTemplate());
            panelGrafica.repaint();
        });
        panelFuncion.add(botonEjecutar);


        labelResultado = new JLabel("");
        labelResultado.setSize(150,60);
        labelResultado.setLocation(75,390);
        labelResultado.setForeground(Color.WHITE);
        labelResultado.setFont(new Font("Arial",1,20));
        labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
        panelFuncion.add(labelResultado);

        this.setSize(800,530);
        this.setLayout(null);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public int Euler(int n){
        int divisoresN[] = listaDivisores(n);
        int divisoresI[];
        int resultado = 0;

        for (int i=1;i<=n;i++) {

            ArrayList<Integer> divisoresIguales = new ArrayList<Integer>();
            divisoresI = listaDivisores(i);

            for (int j : divisoresN) {
                for (int k : divisoresI) {
                    if (j == k) {
                        divisoresIguales.add(j);
                    }
                }
            }
            if (divisoresIguales.size() == 1){
                resultado++;
            }
        }

        agregarPunto(n,resultado);

        return resultado;

    }

    public void agregarPunto(int x,int y){
        int resultado[][] = new int[puntos.length+1][2];

        if(x > 100){
            return;
        }

        for (int i = 0;i<puntos.length;i++){
            if(puntos[i][0] != x){
                resultado[i][0]= puntos[i][0];
                resultado[i][1]= puntos[i][1];
            }else return;

        }
        resultado[puntos.length][0] = x;
        resultado[puntos.length][1] = y;

        puntos = resultado;
    }


    public void limpiar(){
        puntos = new int[0][2];
        panelGrafica.removeAll();
        panelGraficaComplement = new PanelGraficaComplement(puntos,this,recta);
        panelGrafica.add(panelGraficaComplement.getTemplate());
        panelGrafica.repaint();
    }

    public void completar(){
        for(int i = 1; i <= 100; i++){
            Euler(i);
        }
        panelGrafica.removeAll();
        panelGraficaComplement = new PanelGraficaComplement(puntos,this,recta);
        panelGrafica.add(panelGraficaComplement.getTemplate());
        panelGrafica.repaint();
    }

    public int[] listaDivisores(int n){
        ArrayList<Integer> resultado = new ArrayList<Integer>();
        for (int i=1;i<=n;i++){
            if (n % i == 0) {
                resultado.add(i);
            }
        }
        int divisores[] = new int[resultado.size()];
        for(int i = 0;i<resultado.size();i++){
            divisores[i] = resultado.get(i);
        }
        return divisores;
    }

    public void setRecta(boolean recta) {
        this.recta = recta;
    }
}

package Componentes.PanelGrafica;

import App.Ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelGraficaTemplate extends JPanel {

    private PanelGraficaComplement panelGraficaComplement;
    private JButton botonCompletar,botonBorrar;
    private JCheckBox checkRecta;
    private JComboBox lista;
    private Ventana ventana;
    private JLabel texto;
    private JTextField numero;

    public PanelGraficaTemplate(PanelGraficaComplement e,boolean recta,Ventana a){

        panelGraficaComplement = e;
        ventana = a;

        numero = new JTextField();
        numero.setSize(30,30);
        numero.setLocation(340,465);
        numero.setHorizontalAlignment(SwingConstants.CENTER);
        numero.setEnabled(false);
        numero.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                try{
                    int num = Integer.parseInt(numero.getText());
                    repintar();
                }catch(NumberFormatException e){
                    numero.setText("");
                    return;
                }
            }
        });
        this.add(numero);

        texto = new JLabel("Resaltar:");
        texto.setSize(100,30);
        texto.setLocation(240,440);
        texto.setBackground(new Color(30,30,30));
        texto.setForeground(Color.WHITE);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(texto);

        lista = new JComboBox();
        lista.addItem("Nada");
        lista.addItem("No. Primos");
        lista.addItem("No. Abundantes");
        lista.addItem("Potencias");
        lista.setSize(100,30);
        lista.setLocation(240,465);
        lista.setBackground(new Color(30,30,30));
        lista.setForeground(Color.WHITE);
        lista.addItemListener(e1 -> {

            if(lista.getSelectedIndex() == 3){
                numero.setText("");
                numero.setEnabled(true);
            }else{
                numero.setEnabled(false);
                numero.setText("");
                repintar();
            }


        }
        );
        lista.setLayout(null);
        this.add(lista);

        checkRecta = new JCheckBox();
        checkRecta.setText("f(x) = x - 1");
        checkRecta.setSize(100,30);
        checkRecta.setLocation(380,450);
        checkRecta.setBackground(new Color(45,45,45));
        checkRecta.setForeground(Color.WHITE);
        checkRecta.addItemListener(e1 -> repintar());
        checkRecta.setSelected(recta);
        this.add(checkRecta);

        botonCompletar = new JButton("Completar");
        botonCompletar.setBackground(new Color(30,30,30));
        botonCompletar.setForeground(Color.WHITE);
        botonCompletar.setFocusable(false);
        botonCompletar.addActionListener(panelGraficaComplement);
        botonCompletar.setBorder(null);
        botonCompletar.setLocation(50,450);
        botonCompletar.setSize(80,40);
        this.add(botonCompletar);

        botonBorrar = new JButton("Limpiar");
        botonBorrar.setBackground(new Color(30,30,30));
        botonBorrar.setForeground(Color.WHITE);
        botonBorrar.setFocusable(false);
        botonBorrar.addActionListener(panelGraficaComplement);
        botonBorrar.setBorder(null);
        botonBorrar.setLocation(140,450);
        botonBorrar.setSize(80,40);
        this.add(botonBorrar);

        this.setSize(500,500);
        this.setBackground(new Color(45,45,45));
        this.setLayout(null);
    }

    public void repintar(){
        this.repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.WHITE);
        Graphics2D g2 = (Graphics2D) g;

        g2.setStroke(new BasicStroke(3));

        g2.drawLine(20,20,20,420);
        g2.drawLine(20,420,420,420);

        int desfase = 40;
        int valor = 10;
        for(int i = 0;i<10;i++){
            g2.drawLine(20+desfase,420,20+desfase,415);
            g2.drawString(String.valueOf(valor),20+desfase-7,440);
            desfase += 40;
            valor += 10;
        }

        desfase = 40;
        valor = 10;

        for(int i = 0;i<10;i++){
            g2.drawLine(20,420-desfase,25,420-desfase);
            g2.drawString(String.valueOf(valor),30,420-desfase+6);
            desfase += 40;
            valor += 10;
        }


        if(checkRecta.isSelected()){
            g2.setStroke(new BasicStroke(2));
            g2.setColor(Color.MAGENTA);
            ventana.setRecta(true);

            g2.drawLine(25,420,420,25);
        }else ventana.setRecta(false);


        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLUE);


        int puntos[][] = panelGraficaComplement.obtenerPuntos();
        if(puntos.length != 0 && puntos.length > 0){
            if(puntos.length > 1) {
                puntos = organizar(puntos);
            }
            int x1,y1,x2,y2;
            for (int i = 0;i< puntos.length;i++){
                x1 = 20+(puntos[i][0]*4);
                y1 = 420-(puntos[i][1]*4);
                g2.setColor(Color.BLUE);
                if(i != puntos.length-1){
                    x2 = 20+(puntos[i+1][0]*4);
                    y2 = 420-(puntos[i+1][1]*4);
                    g2.drawLine(x1,y1,x2,y2);
                    if(lista.getSelectedIndex() == 1 && puntos[i][1] == puntos[i][0] - 1){
                        g2.setColor(Color.RED);

                    }else if(lista.getSelectedIndex() == 2){
                        g2.setColor(Color.GREEN);
                        int [] numAbundantes = {12, 18, 20, 24, 30, 36, 40, 42, 48, 54, 56, 60, 66, 70, 72, 78, 80, 84, 88, 90, 96, 100};
                        for (int a:numAbundantes){
                            if (a == puntos[i][0]){
                                g2.setColor(Color.RED);
                            }
                        }

                    }else if(lista.getSelectedIndex() == 3 && numero.getText() != ""){
                        try{
                            int num = Integer.parseInt(numero.getText());
                            int[] potencias = obtenerPotencias(num);

                            g2.setColor(Color.GREEN);

                            for (int x = 0;x<potencias.length;x++){
                                System.out.println(potencias[x]);
                                if(puntos[i][0] == potencias[x]){
                                    g2.setColor(Color.RED);
                                }
                            }
                        }catch(NumberFormatException e){

                        }
                    }else g2.setColor(Color.GREEN);
                    g2.drawOval(x1,y1,2,2);
                }else {

                    if(lista.getSelectedIndex() == 1 && puntos[i][1] == puntos[i][0] - 1) {
                        g2.setColor(Color.WHITE);
                    }
                    else if(lista.getSelectedIndex() == 2){
                        g2.setColor(Color.GREEN);
                        int [] numAbundantes = {12, 18, 20, 24, 30, 36, 40, 42, 48, 54, 56, 60, 66, 70, 72, 78, 80, 84, 88, 90, 96, 100};
                        for (int a:numAbundantes){
                            if (a == x1){
                                g2.setColor(Color.WHITE);
                            }
                        }

                    }else if(lista.getSelectedIndex() == 3 && numero.getText() != ""){
                        try{
                            int num = Integer.parseInt(numero.getText());
                            int[] potencias = obtenerPotencias(num);

                            g2.setColor(Color.GREEN);

                            for (int x = 0;x<potencias.length;x++){
                                if(puntos[i][0] == potencias[x]){
                                    g2.setColor(Color.RED);
                                }
                            }
                        }catch(NumberFormatException e){

                        }
                    }else g2.setColor(Color.GREEN);
                    g2.drawOval(x1,y1,2,2);
                    break;
                };


            }
        }

    }

    public int[] obtenerPotencias(int n){
        int resultado[] = {(int) Math.pow(n,1),(int) Math.pow(n,2),(int) Math.pow(n,3),(int) Math.pow(n,4),(int) Math.pow(n,5),(int) Math.pow(n,6)};
        return resultado;
    }


    public int [][] organizar(int[][] entrada){
        boolean ordenado = false;
        while(!ordenado){
            ordenado = true;
            for(int i = 1;i<entrada.length;i++){
                if(entrada[i-1][0] > entrada[i][0]){
                    int Aux[] = entrada[i-1];
                    entrada[i-1] = entrada[i];
                    entrada[i] = Aux;
                    ordenado = false;
                }
            }
        }
        return entrada;
    }

}

package cronometro;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
    CLASSE DESTINADA A JUNÇÃO DAS TELAS CONTRONMETRO E TELA RANKING
 */
public class Cronometro extends JFrame {

    private TelaCronometro telaCronometro;
    private JPanel panelPrincipal;
    private static String min = "";
    private static String seg = "";
    private static String mil = "";
    private static Integer minutos = 0, segundos = 0, milesimas = 0;
    private static int start, stop, reset, ativa; //variaveis de controle
    private static int contador;
    private JPanel JanelaPrincipal;
    private JButton resett;
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension screenSize = tk.getScreenSize();
    private static Cronometro c = new Cronometro();

    public Cronometro() {

        telaCronometro = new TelaCronometro("00", "00", "000");
        JanelaPrincipal = new JPanel();
        resett = new JButton("ZERAR");
        resett.setBounds(30, 600, (int) (screenSize.getWidth() - 1200), (int) screenSize.getHeight() - 700);
        resett.setBackground(Color.gray);
        resett.setForeground(Color.red);
        resett.setFont(new Font("Courier New", 1, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(JanelaPrincipal);
        JanelaPrincipal.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 877, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 411, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(JanelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(JanelaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JanelaPrincipal.setLayout(new CardLayout());
        JanelaPrincipal.add(telaCronometro, "Cronometro");
        add(resett);
        JanelaPrincipal.setSize(screenSize.width - 200, screenSize.height - 140);
        JanelaPrincipal.setVisible(true);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        resett.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Escreve ação aqui
                c.getTelaCronometro().AtualizaPlacar("00", "00", "000");
                min = "";
                seg = "";
                mil = "";
                minutos = 0;
                segundos = 0;
                milesimas = 0;
            }
        });

    }

    public TelaCronometro getTelaCronometro() {
        return telaCronometro;
    }

    //Metodo principal para o cronometro
    public static void main(String[] args) throws InterruptedException {
        //Cronometro c = new Cronometro();
        SerialRxTx serial = new SerialRxTx();
        /*Inicializa a contagem */
        if (serial.iniciaSerial()) {
            while (true) {
                if (serial.getProtocolo().getStart() == null) {
                    c.getTelaCronometro().AtualizaPlacar("00", "00", "000");
                } else {

                    start = Integer.parseInt(serial.getProtocolo().getStart());
                    System.out.println(start);
                    if (start == 1) {
                        Thread.sleep(59);
                        milesimas += 59;
                        if (milesimas >= 1000) {
                            milesimas %= 1000;
                            segundos += 1;
                            if (segundos == 60) {
                                segundos = 0;
                                minutos++;
                            }
                        }
                        //Converte para string 00:00:000
                        if (minutos < 10) {
                            min = "0" + minutos;
                        } else {
                            min = minutos.toString();
                        }
                        if (segundos < 10) {
                            seg = "0" + segundos;
                        } else {
                            seg = segundos.toString();
                        }
                        if (milesimas < 10) {
                            mil = "00" + milesimas;
                        } else if (milesimas < 100) {
                            mil = "0" + milesimas;
                        } else {
                            mil = milesimas.toString();
                        }
                        //Atualiza Placar
                        c.getTelaCronometro().AtualizaPlacar(min, seg, mil);
                    }
                }
            }
        }
    }

}

package cronometro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
    CLASSE DESTINADA A EXIBIR A INTERFACE GRAFICA DO CRONOMETRO
 */

public class TelaCronometro extends JPanel {

    private JLabel t1, min, s, mili; //Variavel que armazena os texto min: Minutos, s: Segundos, mili: Milisegundos
    private JLabel placar1, placar2, placar3, text, text2; //Variavel que armazena os texto do placar
    private JPanel quadro1, quadro2, quadro3; //Quadro de fundo do placar
    private Toolkit tk;

    //Construtor 
    public TelaCronometro(String A, String B, String C) {
        tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        //System.out.println(d);
        setLayout(null);
        //Instancia dos objetos
        quadro1 = new JPanel();
        quadro2 = new JPanel();
        quadro3 = new JPanel();
        placar1 = new JLabel(A); //Minutos
        placar2 = new JLabel(B); //Segundos 
        placar3 = new JLabel(C); //Milesimos 
        text = new JLabel(":");
        text2 = new JLabel(":");
        t1 = new JLabel("TEMPO");
        min = new JLabel("minutos");
        s = new JLabel("segundos");
        mili = new JLabel("milisegundos");
        //Localização dos quadros
        quadro1.setBounds(20, 250, (int) (d.getWidth() - 1016), (int) d.getHeight() - 488);
        quadro1.setBackground(Color.black);
        quadro2.setBounds(405, 250, (int) (d.getWidth() - 1016), (int) d.getHeight() - 488);
        quadro2.setBackground(Color.black);
        quadro3.setBounds(790, 250, (int) (d.getWidth() - 872), (int) d.getHeight() - 488);
        quadro3.setBackground(Color.black);
        //Localização dos texto de legenda
        t1.setBounds(500, 20, 400, 200);
        min.setBounds(100, 120, 400, 200);
        s.setBounds(470, 120, 400, 200);
        mili.setBounds(950, 120, 400, 200);
        //Fonte e tamnho dos textos
        t1.setFont(new Font("arial", 1, 90));
        min.setFont(new Font("arial", 2, 40));
        s.setFont(new Font("arial", 2, 40));
        mili.setFont(new Font("arial", 2, 40));
        //Formatação da fonte e tamanho dos numeros no placar
        placar1.setFont(new Font("Courier New", 1, 300));
        placar2.setFont(new Font("Courier New", 1, 300));
        placar3.setFont(new Font("Courier New", 1, 300));
        text.setFont(new Font("arial", 1, 150));
        text2.setFont(new Font("arial", 1, 150));
        //Localização dos placares
        text.setBounds(360, 100, 200, 600);
        text2.setBounds(745, 100, 200, 600);
        //Cor da tela
        setBackground(Color.lightGray);
        placar1.setForeground(Color.red);
        placar2.setForeground(Color.red);
        placar3.setForeground(Color.red);
        add(t1);
        add(quadro1);
        add(quadro2);
        add(quadro3);
        quadro1.add(placar1);
        quadro2.add(placar2);
        quadro3.add(placar3);
        add(text);
        add(text2);
        add(min);
        add(s);
        add(mili);
        setVisible(true);

    }

    public TelaCronometro tela() {
        return this;
    }

    public int getPlacar1() {
        String x = placar1.getText();
        int n = Integer.parseInt(x);
        return n;
    }

    public void setPlacar1(String n) {
        placar1.setText(n);
    }

    public int getPlacar2() {
        String x = placar2.getText();
        int n = Integer.parseInt(x);
        return n;
    }

    public void setPlacar2(String n) {
        placar2.setText(n);
    }

    public void setPlacar3(String n) {
        placar3.setText(n);
    }


  

    //Metodo para atualizar o placcar
    public void AtualizaPlacar(String min, String seg, String mil) {
        setPlacar1(min);
        setPlacar2(seg);
        setPlacar3(mil);

    }
    
    

}

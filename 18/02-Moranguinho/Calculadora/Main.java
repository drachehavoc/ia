import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

class Bt extends JButton {
    Bt(String text) {
        this.setText(text);
        this.addActionListener(evt -> {
            System.out.println("MaracTechTM");
        });
    }
}

class Janela extends JFrame {

    Janela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setTitle("Janelosa");
        this.setLayout(new GridBagLayout());
        
        JTextField hist = new JTextField();
        JTextField dis = new JTextField();
        JPanel numerosAcoes = new JPanel();
        JPanel numeros = new JPanel();
        JPanel acoes = new JPanel();
        JPanel apagar = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        
        numeros.setLayout(new GridLayout(4, 3));
        numeros.add(new Bt("7"));
        numeros.add(new Bt("8"));
        numeros.add(new Bt("9"));
        numeros.add(new Bt("4"));
        numeros.add(new Bt("5"));
        numeros.add(new Bt("6"));
        numeros.add(new Bt("1"));
        numeros.add(new Bt("2"));
        numeros.add(new Bt("3"));
        numeros.add(new Bt("="));
        numeros.add(new Bt("0"));
        numeros.add(new Bt("."));

        apagar.setLayout(new GridLayout(1, 2));
        apagar.add(new JButton("CA"));
        apagar.add(new JButton("CL"));

        acoes.setLayout(new GridLayout(5, 1));
        acoes.add(apagar);
        acoes.add(new JButton("."));
        acoes.add(new JButton("."));
        acoes.add(new JButton("."));
        acoes.add(new JButton("."));

        numerosAcoes.setLayout(new GridLayout(1, 2));
        numerosAcoes.add(numeros);
        numerosAcoes.add(acoes);

        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH; 
        this.add(hist, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.4;
        this.add(dis, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.5;
        this.add(numerosAcoes, gbc);

        this.setVisible(true);
    }
}

class Main{
    static public void main(String args[]){
        new Janela();
    }
}
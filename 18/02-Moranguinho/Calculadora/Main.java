import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

class BtNum extends JButton {
    
    BtNum(String text) {
        this.setText(text);
        this.addActionListener(evt -> {
            Janela janelaPai = (Janela) SwingUtilities.getRoot(this);
            janelaPai.setDisplay(text);
        });
    }

}

class Janela extends JFrame {
    private JTextField display = new JTextField("0");
    private double result = 0;
    private double buffer = 0;
    private char operation = ' ';
    private boolean ultimaEntrada = true;

    Janela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setTitle("Janelosa");
        this.setLayout(new GridBagLayout());
        
        JTextField hist = new JTextField();
        JPanel numerosAcoes = new JPanel();
        JPanel numeros = new JPanel();
        JPanel acoes = new JPanel();
        JPanel apagar = new JPanel();
        GridBagConstraints gbc = new GridBagConstraints();
        
        numeros.setLayout(new GridLayout(4, 3));
        numeros.add(new BtNum("7"));
        numeros.add(new BtNum("8"));
        numeros.add(new BtNum("9"));
        numeros.add(new BtNum("4"));
        numeros.add(new BtNum("5"));
        numeros.add(new BtNum("6"));
        numeros.add(new BtNum("1"));
        numeros.add(new BtNum("2"));
        numeros.add(new BtNum("3"));
        numeros.add(new JButton("="));
        numeros.add(new BtNum("0"));
        numeros.add(new JButton("."));

        apagar.setLayout(new GridLayout(1, 2));
        apagar.add(new JButton("CA"));
        apagar.add(new JButton("CL"));

        JButton btMais = new JButton("+");

        btMais.addActionListener(evt -> {
            if (this.ultimaEntrada) {
                this.buffer = Double.parseDouble(this.display.getText());
                this.ultimaEntrada = false;
                return;
            }

            this.result = this.buffer + Double.parseDouble(this.display.getText());
            this.display.setText(this.result+"");
        });

        acoes.setLayout(new GridLayout(5, 1));
        acoes.add(apagar);
        acoes.add(btMais);
        acoes.add(new JButton("-"));
        acoes.add(new JButton("/"));
        acoes.add(new JButton("*"));

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
        this.add(display, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.5;
        this.add(numerosAcoes, gbc);
        
        this.setVisible(true);
    }

    public void setDisplay(String novoValor) {
        String valDisplay = this.display.getText();
        
        if(!this.ultimaEntrada) {
            this.display.setText(novoValor);
            this.ultimaEntrada = true;
            return;
        }

        if (valDisplay.equals("0")) {
            this.display.setText(novoValor);
        } else {
            this.display.setText(valDisplay + novoValor);
        }
    }
}

class Main{
    static public void main(String args[]){
        new Janela();
    }
}
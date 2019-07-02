import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

class BotaoNumerico extends JButton {
    BotaoNumerico(String text) {
        this.setText(text);
        this.addActionListener(evt -> {
            Janela janelaPai = (Janela) SwingUtilities.getRoot(this);
            janelaPai.setDisplay(text);
        });
    }
}

class Janela extends JFrame {
    private JTextField display = new JTextField(0d+"");
    private JTextField historico = new JTextField();
    private double total = 0;
    private double fator = 0;
    private char operacao = ' ';
    private boolean limpar = true;
    
    Janela() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 400);
        this.setTitle("Calculosa");
        this.setLayout(new GridBagLayout());
        
        JPanel numerosAcoes = new JPanel();
        JPanel numeros = new JPanel();
        JPanel acoes = new JPanel();
        JPanel apagar = new JPanel();
        
        historico.setEditable(false);
        historico.setEnabled(false);
        historico.setHorizontalAlignment(SwingConstants.RIGHT);

        this.display.setEditable(false);
        // this.display.setEnabled(false);
        this.display.setBackground(Color.white);
        this.display.setFont(new Font("Tahoma", 0, 36));
        this.display.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton btIgual = new JButton("=");
        btIgual.addActionListener(evt -> this.calcular(this.operacao));

        numeros.setLayout(new GridLayout(4, 3));
        numeros.add(new BotaoNumerico("7"));
        numeros.add(new BotaoNumerico("8"));
        numeros.add(new BotaoNumerico("9"));
        numeros.add(new BotaoNumerico("4"));
        numeros.add(new BotaoNumerico("5"));
        numeros.add(new BotaoNumerico("6"));
        numeros.add(new BotaoNumerico("1"));
        numeros.add(new BotaoNumerico("2"));
        numeros.add(new BotaoNumerico("3"));
        numeros.add(btIgual);
        numeros.add(new BotaoNumerico("0"));
        numeros.add(new JButton("."));

        apagar.setLayout(new GridLayout(1, 2));
        apagar.add(new JButton("CA"));
        apagar.add(new JButton("CL"));

        JButton btMais = new JButton("+");
        btMais.addActionListener(evt -> this.calcular('+'));

        JButton btMenos = new JButton("-");
        btMenos.addActionListener(evt -> this.calcular('-'));

        JButton btDiv = new JButton("/");
        btDiv.addActionListener(evt -> this.calcular('/'));

        JButton btMult = new JButton("*");
        btMult.addActionListener(evt -> this.calcular('*'));

        acoes.setLayout(new GridLayout(5, 1));
        acoes.add(apagar);
        acoes.add(btMais);
        acoes.add(btMenos);
        acoes.add(btDiv);
        acoes.add(btMult);

        GridBagConstraints gbcNumerosAcoes = new GridBagConstraints();
        numerosAcoes.setLayout(new GridBagLayout());
        gbcNumerosAcoes.insets = new Insets(0, 0, 0, 0);
        gbcNumerosAcoes.gridx = 1;
        gbcNumerosAcoes.weighty = 1;
        gbcNumerosAcoes.weightx = 1;
        gbcNumerosAcoes.fill = GridBagConstraints.BOTH;
        numerosAcoes.add(numeros, gbcNumerosAcoes);
        
        gbcNumerosAcoes.gridx = 2;
        gbcNumerosAcoes.weighty = 1;
        gbcNumerosAcoes.weightx = 0.2;
        numerosAcoes.add(acoes, gbcNumerosAcoes);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH; 
        this.add(historico, gbc);

        gbc.gridy = 2;
        gbc.weighty = 0.4;
        this.add(display, gbc);

        gbc.gridy = 3;
        gbc.weighty = 0.5;
        this.add(numerosAcoes, gbc);
        
        this.setVisible(true);
    }

    private void calcular(char proximaOperacao) {
        // System.out.println(" - - - - - - - - - - - - - - - - ");
        // System.out.println("antes         limpar : " + this.limpar);
        // System.out.println("antes          total : " + this.total);
        // System.out.println("antes          fator : " + this.fator);
        // System.out.println("antes       operacao : " + this.operacao);
        // System.out.println("antes prox. operacao : " + proximaOperacao);
        // System.out.println(" - - - - - - - - - - - - - - - - ");

        // EM CASO DE MUDANÇA DE OPERAÇÃO DURANTE O CALCULO
        if (this.operacao != proximaOperacao && this.limpar) {
            this.operacao = proximaOperacao;
            return;
        }

        // PRIMEIRA VEZ
        if (this.operacao == ' ') {
            this.total = this.fator;
            this.fator = 0;
        }

        // SOMA
        if (this.operacao == '+') 
            this.total += this.fator;

        // SUBTRAÇÃO
        if (this.operacao == '-') 
            this.total -= this.fator;
            
        // DIVISÃO
        if (this.operacao == '/') 
            this.total /= this.fator;

        // MULTIPLICAÇÃO
        if (this.operacao == '*') 
            this.total *= this.fator;
        
        // HABILITA PARA QUE OS BOTÕES LIMPEM O DISPLAY
        this.limpar = true;

        // SETA A OPERAÇÃO DO PRÓXIMO CALCULO
        this.operacao = proximaOperacao;

        // COLOCA O CALCULO NO DISPLAY
        this.display.setText(this.total+"");
    }

    public void setDisplay(String novoValor) {
        if (this.limpar) {
            this.limpar = false;
            this.display.setText(novoValor);
        } else {
            this.display.setText(this.display.getText() + novoValor);
        }
        this.fator = Double.parseDouble(this.display.getText());
    }
}

class Main{
    static public void main(String args[]){
        new Janela();
    }
}
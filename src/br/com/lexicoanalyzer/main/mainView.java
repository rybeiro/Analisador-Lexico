package br.com.lexicoanalyzer.main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.com.lexicoanalyzer.Lexico;
import java.awt.Color;

public class mainView {

	private JFrame frmAnalisadorLxico;
	private JTextField txPalavra;
	private Lexico lexico;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainView window = new mainView();
					window.frmAnalisadorLxico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public mainView() {
		initialize();
	}

	private void initialize() {
		frmAnalisadorLxico = new JFrame();
		frmAnalisadorLxico.setTitle("Analisador de Var");
		frmAnalisadorLxico.setBounds(100, 100, 768, 500);
		frmAnalisadorLxico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnalisadorLxico.getContentPane().setLayout(null);
		
		JLabel lbTitulo = new JLabel("Analisador de Vari\u00E1veis e n\u00FAmeros");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitulo.setBounds(125, 13, 492, 39);
		frmAnalisadorLxico.getContentPane().add(lbTitulo);
		
		txPalavra = new JTextField();
		txPalavra.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txPalavra.setBounds(24, 72, 695, 77);
		frmAnalisadorLxico.getContentPane().add(txPalavra);
		txPalavra.setColumns(10);
		
		JLabel lbSaida = new JLabel("Sa\u00EDda: ");
		lbSaida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbSaida.setVerticalAlignment(SwingConstants.TOP);
		lbSaida.setBounds(264, 319, 455, 64);
		frmAnalisadorLxico.getContentPane().add(lbSaida);
		
		JLabel lbTabelaDeVariaveis = new JLabel("TABELA DE VARI\u00C1VEIS");
		lbTabelaDeVariaveis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbTabelaDeVariaveis.setVerticalAlignment(SwingConstants.TOP);
		lbTabelaDeVariaveis.setBounds(24, 265, 190, 175);
		frmAnalisadorLxico.getContentPane().add(lbTabelaDeVariaveis);
		
		JLabel lbEntrada = new JLabel("Entrada: ");
		lbEntrada.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbEntrada.setVerticalAlignment(SwingConstants.TOP);
		lbEntrada.setBounds(264, 253, 455, 64);
		frmAnalisadorLxico.getContentPane().add(lbEntrada);
		
		JLabel lbObservacao = new JLabel("");
		lbObservacao.setForeground(Color.DARK_GRAY);
		lbObservacao.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbObservacao.setBounds(34, 162, 313, 39);
		frmAnalisadorLxico.getContentPane().add(lbObservacao);
		
		JButton btnGerarSaida = new JButton("Gerar Sa\u00EDda");
		btnGerarSaida.setForeground(Color.BLUE);
		btnGerarSaida.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnGerarSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lexico = new Lexico(txPalavra.getText());
				lbEntrada.setText("Entrada: " + txPalavra.getText());
				lbSaida.setText("Saída: "+lexico.saida());
				ArrayList<String> tabVar = lexico.tabelaDeVariaveis();
				if(tabVar.size() == 0)
					lbObservacao.setText("Fim do Programa!");
				String listaVariaveis = "";
				for(int i=0; i<tabVar.size();i++) {
					listaVariaveis += i + " = " + tabVar.get(i) + "<br>";
				}
				txPalavra.setText("");
				lbTabelaDeVariaveis.setText("<html>TABELA DE VARIÁVEIS<br><br>" + listaVariaveis + "</html>");
				lbObservacao.setText("Fim do Programa!");
			}
		});
		btnGerarSaida.setBounds(473, 174, 137, 51);
		frmAnalisadorLxico.getContentPane().add(btnGerarSaida);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.setForeground(Color.RED);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lbSaida.setText("Saída: ");
				lbEntrada.setText("Entrada: ");
				lbTabelaDeVariaveis.setText("TABELA DE VARIÁVEIS");
				lbObservacao.setText("");
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLimpar.setBounds(622, 174, 97, 51);
		frmAnalisadorLxico.getContentPane().add(btnLimpar);
	}
}

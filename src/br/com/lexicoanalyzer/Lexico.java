package br.com.lexicoanalyzer;

import java.util.ArrayList;

public class Lexico {
	
	private String palavra;
	private ArrayList<String> tabelaDeVariaveis, tabelaDeNumeros;
	private Character[] simbolo;
	// METODO CONSTRUTOR PADRAO
	public Lexico(String palavra) {
		this.palavra=palavra;                         // atribui a entrada a palavra
		this.simbolo=new Character[palavra.length()]; // intancia o vetor de simbolo com o tamanho da palavra
		simbolo();                                    // chama o metodo que simbolos que popula os caracteres da palavra
		tabelaDeVariaveis=new ArrayList<String>();    // instancia o ArrayList que armazena as variaveis
		tabelaDeNumeros=new ArrayList<String>();      // instancia o ArrayList que armazena os numeros
	}
	// METODO DE ACESSO A PALAVRA
	public String getPalavra() {
		return palavra;
	}
	// METDO DE ATRIBUIÇÃO DA PALAVRA
	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	// METODO DE ACESSO AO SIMBOLO
	public Character[] getSimbolo() {
		return simbolo;
	}
	//METODO DE ATRIBUIÇÃO DO SIMBOLO
	public void setSimbolo(Character[] simbolo) {
		this.simbolo = simbolo;
	}

	// METODO QUE POPULA O VETOR SIMBOLO COM AS LETRAS DA PALAVRA
	public void simbolo() {
		for(int i=0;i<simbolo.length;i++) {
			simbolo[i]=palavra.charAt(i);
		}
	}
	
	// METODO QUE GERA A SAIDA 
	public String saida() {
		String variavel="";
		String identPalavras="";
		int contNum=0, contVar=0;
		// Inicio da iteracao com os simbolos
		for(int i=0;i<simbolo.length;i++) {
			//verifica se o simbolo atual é um caracter branco
			if(Character.isWhitespace(simbolo[i])) {
				// verifica se a primeira simbolo e branco e se o tamanho do vetor
				// é zero caso true encerra o programa.
				if(Character.isWhitespace(simbolo[0]) && simbolo.length == 0) {
					return " ";
				}
				// senão continua
				else {
					// inicia a iteracao da variavel
					for(int j=0;j<variavel.length();j++) {
						// verifica se o primeiro simbolo da variavel é uma letra
						if(Character.isAlphabetic(variavel.charAt(0))) {
							// verificar se a tabela de variaveis tem alguma variavel
							if(existeTabelaDeVariaveis()) {
								// recebe a posicao da variavel do ArrayList
								int aux = verificarTabelaDeVariaveis(variavel);
								// verifica se for maior ou igual a zero, retorna a posicao
								if(aux >= 0) {
									identPalavras+="V("+aux+")";
									variavel="";
								}
								// se nao adiciona a variavel no ArrayList
								else {
									tabelaDeVariaveis.add(variavel);
									identPalavras+="V("+contVar+")";
									contVar++;
									variavel="";
								}
							}
						}
						// Senão na priveira posicão da variável não for Letra
						// então continua
						else {
							// verifica se o caracter é um digito
							if(Character.isDigit(variavel.charAt(j))) {
								// verifica se o tamanho da variavel tem próximo elemento
								// se true entra e armazena no ArrayList de Numeros
								if(variavel.length()==(j+1)) {
									tabelaDeNumeros.add(variavel);
									identPalavras+="N("+tabelaDeNumeros.get(contNum)+")";
									contNum++;
									variavel="";
								}
							}
							// senao for digito ... continua
							else {
								// verifica se o tamanho da variavel tem próximo elemento
								if(variavel.length()==(j+1)) {
									// veifica se existe variavel no ArrayList de Variáveis
									if(existeTabelaDeVariaveis()) {
										// recebe a posicao da variavel do ArrayList
										int aux = verificarTabelaDeVariaveis(variavel);
										// verifica se for maior ou igual a zero, retorna a posicao
										if(aux >= 0) {
											identPalavras+="V("+aux+")";
											variavel="";
										}
										// Senão adiciona na tabela de variaveis
										else {
											tabelaDeVariaveis.add(variavel);
											identPalavras+="V("+contVar+")";
											contVar++;
											variavel="";
										}
									}
								}
							}
						}
					}
				}
			}
			// se o simbolo atual não é branco ... continua
			else {
				// verifica se o simbolo é uma letra
				// e concatena na variavel
				if(Character.isAlphabetic(simbolo[i])) {
					variavel += simbolo[i];
				}
				// senao se o simbolo for digito
				// concatena na variavel
				else if(Character.isDigit(simbolo[i])) {
					variavel += simbolo[i];
				}	
			}
		}
		
		// Inicio da iteracao a ultima variavel
		for(int j=0;j<variavel.length();j++) {
			if(Character.isAlphabetic(variavel.charAt(0))) {
				if(existeTabelaDeVariaveis()) {
					int aux = verificarTabelaDeVariaveis(variavel);
					if(aux >= 0) {
						identPalavras+="V("+aux+")";
						variavel="";
					}
					else {
						tabelaDeVariaveis.add(variavel);
						identPalavras+="V("+contVar+")";
						contVar++;
						variavel="";
					}
				}
			}
			else {
				if(Character.isDigit(variavel.charAt(j))) {
					if(variavel.length()==(j+1)) {
						tabelaDeNumeros.add(variavel);
						identPalavras+="N("+tabelaDeNumeros.get(contNum)+")";
						contNum++;
						variavel="";
					}
				}
				else {
					if(variavel.length()==(j+1)) {
						if(existeTabelaDeVariaveis()) {
							int aux = verificarTabelaDeVariaveis(variavel);
							if(aux >= 0) {
								identPalavras+="V("+aux+")";
								contVar++;
								variavel="";
							}
							else {
								tabelaDeVariaveis.add(variavel);
								identPalavras+="V("+contVar+")";
								contVar++;
								variavel="";
							}
						}
					} 
				}
			}
		}
		return identPalavras;
	}
	
	// METODO QUE VERIFICA SE HA ALGUMA VARIAVEL NO ARRAYLIST
	// TABELA DE VARIAVEIS
	public boolean existeTabelaDeVariaveis() {
		if(tabelaDeVariaveis.size() >= 0) {
			return true;
		}
		return false;
	}
	
	// METDO QUE VERIFICA SE CONSTA UMA VARIAVEL NA
	// TABELA DE VARIAVEIS E RETORNA A POSIÇÃO
	public int verificarTabelaDeVariaveis(String variavel) {
		for(int i=0; i<tabelaDeVariaveis.size();i++) {
			if(tabelaDeVariaveis.get(i).equals(variavel))
				return i;
		}
		return -1;
	}
	
	// METODO QUE VERIFICA SE HA ALGUM NUMERO NO ARRAYLIST
	public boolean existeTabelaDeNumeros() {
		if(tabelaDeNumeros.size() >= 0) {
			return true;
		}
		return false;
	}
	
	// METODO QUE RETORNA O ARRAYLIST DE VARIAVEIS PARA VIEW
	public ArrayList<String> tabelaDeVariaveis(){
		return tabelaDeVariaveis;
	}
}

package tradutorMusical;

import java.util.ArrayList;

public class Decodificador {

	//Constantes 
	static final int MIDI_AGOGO = 114;
	static final int MIDI_HARPSICHORD = 7;
	static final int MIDI_TUBULAR_BELLS = 15;
	static final int MIDI_PAN_FLUTE = 76;
	static final int MIDI_CHURCH_ORGAN = 20;
	static final char CARACTERE_VOLUME = ' ';
	static final int VOLUME_MAXIMO = 120;
	static final int VOLUME_PADRAO = 30;
	static final char CARACTERE_OITAVA = '?';
	static final int OITAVA_PADRAO = 0;
	static final int OITAVA_MAXIMA = 10;
	static final int INSTRUMENTO_MAXIMO = 128;
	static final char NOTA_PADRAO = 'A';
	
	
	//Atributos da classe
	int instrumentoAtual;
	char notaAtual;
	int volumeAtual;
	char caractereAtual;
	Integer oitavaAtual = new Integer(OITAVA_PADRAO);
	int tamanhoTexto;
	boolean anteriorEhNota = false;
	StringBuilder textoConvertido = new StringBuilder();
	
	ArrayList<Character> caractereMaiusculo = new ArrayList<>();
	ArrayList<Character> caractereInstrumento = new ArrayList<>();
	
	protected Decodificador(int valorInstrumento, String textoRecebido) {
		
		this.instrumentoAtual = valorInstrumento;
		initVolumeAtual();
		initNotaAtual();
		initOitavaAtual();
		initCaractereMaiusculo();
		initCaractereInstrumento();
		this.tamanhoTexto = getTamTextoRecebido(textoRecebido);
		setInstrumento(this.instrumentoAtual);
		
	}
	
	private int getTamTextoRecebido(String texto) {
		
		return texto.length();
	}
	
	private void initCaractereInstrumento() {
		
		//preencheCaractereInstrumento
		this.caractereInstrumento.add('!');
		this.caractereInstrumento.add('O');
		this.caractereInstrumento.add('o');
		this.caractereInstrumento.add('I');
		this.caractereInstrumento.add('i');
		this.caractereInstrumento.add('U');
		this.caractereInstrumento.add('u');
		this.caractereInstrumento.add('\n');
		this.caractereInstrumento.add(';');
		this.caractereInstrumento.add(',');
	}
	
	private void initCaractereMaiusculo() {
		//preencheCaractereMaiusculo()
		this.caractereMaiusculo.add('A');
		this.caractereMaiusculo.add('B');
		this.caractereMaiusculo.add('C');
		this.caractereMaiusculo.add('D');
		this.caractereMaiusculo.add('E');
		this.caractereMaiusculo.add('F');
		this.caractereMaiusculo.add('G');
	}
	
	
	private void initVolumeAtual() {
		
		this.volumeAtual = VOLUME_PADRAO;
	}
	
	private void initNotaAtual() {
		
		this.notaAtual = NOTA_PADRAO;
	}
	
	private void initOitavaAtual() {
		
		this.oitavaAtual = OITAVA_PADRAO;
	}
	
	protected String converteTexto(String textoInicial) {
		
		for (int i = 0; i < tamanhoTexto; i++) {
			
			caractereAtual = textoInicial.charAt(i);
			
			//Testa se o caractere atual é uma letra maiúscula (nota)
			if (caractereMaiusculo.contains(caractereAtual)) {
				//textoConvertido.append(caractereAtual+oitavaAtual.toString()+" ");
				setNota(caractereAtual);
				notaAtual = caractereAtual;
				anteriorEhNota = true;
			}
			//Testa se o caractere atual é referente ao instrumento
			else if (caractereInstrumento.contains(caractereAtual)) {
				decodificaInstrumento(caractereAtual);
				setInstrumento(instrumentoAtual);
				anteriorEhNota = false;
			}
			//Testa se o caractere atual é referente ao volume
			else if (caractereAtual == CARACTERE_VOLUME) {
				if (volumeAtual < VOLUME_MAXIMO) {
					volumeAtual = volumeAtual * 2;
				} else {
					volumeAtual = VOLUME_PADRAO;
				}
				setVolume(volumeAtual);
				anteriorEhNota = false;
			}
			//Testa se o caractere atual é referente a oitava
			else if (caractereAtual == CARACTERE_OITAVA) {
				if (oitavaAtual < OITAVA_MAXIMA) {
					oitavaAtual = oitavaAtual + 1;
				} else {
					oitavaAtual = OITAVA_PADRAO;
				}
				anteriorEhNota = false;
			}
			//Testa se o caractere atual é dígito (troca instrumento)
			else if (Character.isDigit(caractereAtual)) {
				
				if (instrumentoAtual + Character.getNumericValue(caractereAtual) < INSTRUMENTO_MAXIMO) {
					instrumentoAtual = instrumentoAtual + Character.getNumericValue(caractereAtual);
					//stringConvertida.append("I"+instrumentoAtual+" ");
					setInstrumento(instrumentoAtual);
				}
				else {
					instrumentoAtual = MIDI_AGOGO;
					//stringConvertida.append("I"+instrumentoAtual+" ");
					setInstrumento(instrumentoAtual);
				}
				anteriorEhNota = false;
			}
			//Nenhum dos casos anteriores
			else {
				if (anteriorEhNota == true) {
					mantemNota(notaAtual);
				}
				else {
					setPausa();
				}
			}
		}
		
		
		
		return textoConvertido.toString();
	}
	
	private void mantemNota(char notaAtual) {
		
		this.textoConvertido.append(notaAtual+oitavaAtual.toString()+" ");
	}
	
	private void setPausa() {
		
		this.textoConvertido.append("| ");
	}
	
	private void setVolume(int volumeAtual) {
		
		this.textoConvertido.append(":CON(7, "+volumeAtual+") ");
	}
	
	private void setInstrumento(int instrumentoAtual) {
		
		this.textoConvertido.append("I"+instrumentoAtual+" ");
	}
	
	private void setNota(char caractereAtual) {
		
		this.textoConvertido.append(caractereAtual+this.oitavaAtual.toString()+" ");
	}
	
	
	private void decodificaInstrumento(char caractereAtual) {
		
		
		if (caractereAtual == '!') {
			this.instrumentoAtual = MIDI_AGOGO;
		}
		else if (caractereAtual == 'O' || caractereAtual == 'o' || caractereAtual == 'I' || caractereAtual == 'i' || caractereAtual == 'U' || caractereAtual == 'u') {
			this.instrumentoAtual = MIDI_HARPSICHORD;
		}
		else if (caractereAtual == '\n') {
			this.instrumentoAtual = MIDI_TUBULAR_BELLS;
		}
		else if (caractereAtual == ';') {
			this.instrumentoAtual = MIDI_PAN_FLUTE;
		}
		else if (caractereAtual == ',') {
			this.instrumentoAtual = MIDI_CHURCH_ORGAN;
		}
	}
	
}

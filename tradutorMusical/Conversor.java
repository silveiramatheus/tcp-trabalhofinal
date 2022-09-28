package tradutorMusical;

import java.io.IOException;

import armazenadorMusical.Downloader;
import tradutorMusical.Decodificador;
import playerMusical.TocaMusica;

public class Conversor {
	
	//Atributos da classe
	private String textoInicial;
	private int valorInstrumento;
	private String textoFinal;
	
	private Decodificador decodTexto;
	private TocaMusica tocador;
	private Downloader download;
	
	public Conversor(){
		
		initTextoInicial();
	}
	
	private void initDownload() {
		
		download = new Downloader(this.textoFinal);
	}
	
	private void initTocador() {
		
		this.tocador = new TocaMusica(this.textoFinal);
	}
	
	private void initDecodTexto() {
		
		this.decodTexto = new Decodificador(valorInstrumento, textoInicial);
	}
	
	private void initTextoFinal() {
		
		this.textoFinal = new String();
	}
	
	private void initTextoInicial() {
		
		this.textoInicial = new String();
	}
	
	public void setTextoInicial(String textoRecebido) {
		
		this.textoInicial = textoRecebido;
	}
	
	private void setValorInstrumento(int valorInstrumento) {
		
		this.valorInstrumento = valorInstrumento;
	}
	
	public void verificaInstrumentoInicial(String nomeInstrumentoInicial) {
		
		if (nomeInstrumentoInicial == "Agogo") {
			setValorInstrumento(114);
		}
		else if (nomeInstrumentoInicial == "Harpsichord") {
			setValorInstrumento(7);
		}
		else if (nomeInstrumentoInicial == "Tubular Bells") {
			setValorInstrumento(15);
		}
		else if (nomeInstrumentoInicial == "Pan Flute") {
			setValorInstrumento(76);
		}
		else if (nomeInstrumentoInicial == "Church Organ") {
			setValorInstrumento(20);
		}
	}
	
	public void decodificarTexto() {
		
		initDecodTexto();
		this.textoFinal = decodTexto.converteTexto(textoInicial);
		initTocador();
		tocador.executaMusica();
		
	}
	
	public void baixarMusica() throws IOException {
		
		initDownload();
		download.executaDownload();
	}
}

package armazenadorMusical;

import java.io.File;
import java.io.IOException;

import org.jfugue.pattern.Pattern;
import org.jfugue.midi.MidiFileManager;

public class Downloader {
	
	//Atributos da classe
	File arquivoSaida;
	Pattern patternSaida;
	
	public Downloader(String textoParaDownload) {
		
		setArquivoSaida();
		setPatternSaida(textoParaDownload);
	}
	
	private void setArquivoSaida() {
		
		arquivoSaida = new File("C:\\Users\\Public\\musicaSaida.midi");
	}
	
	private void setPatternSaida(String textoParaDownload) {
		
		patternSaida = new Pattern(textoParaDownload);
	}
	
	public void executaDownload() throws IOException {
		
		MidiFileManager.savePatternToMidi(patternSaida, arquivoSaida);
	}
}

package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import tradutorMusical.Conversor;


public class ControlaGui extends Interface{

	//ID Serial
	private static final long serialVersionUID = 1L;
	
	
	//Atributos da classe
	private String texto;
	
	
	public void executaPrograma() {
		
		initTexto();
		setTitulo("Conversor de Música - Grupo 6");
		defineInstrumentos();
		abreJanela();
		verificaAnexoArquivo();
		verificaExecucao();
	}
	
	//Eventos  
	//Caso o usuário deseje anexar um arquivo (click) deve-se pegar o contéudo do arquivo
	private void verificaAnexoArquivo() {
		
		botaoAnexarArq.addActionListener(actionEvent -> {
			try {
				botaoAnexarArqPressionado();
				this.texto = leAreaTexto();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	//Caso o usuário execute a conversão do texto em música
	private void verificaExecucao() {
		
		botaoExecutar.addActionListener(actionEvent -> {
			try {
				botaoExecutarPressionado();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	//Inicializa o texto
	private void initTexto() {
		
		this.texto = new String();
	}
	
	//Define os instrumentos para seleção
	private void defineInstrumentos() {
		
		String[] instrumentos = new String[]{"Agogo", "Harpsichord", "Tubular Bells", "Pan Flute", "Church Organ"};
		preencheTiposInstrumento(instrumentos);
	}
	
	//Abre a janela do programa
	private void abreJanela() {
		
		setVisible(true);
	}
	
	
}

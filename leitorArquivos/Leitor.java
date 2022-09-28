package leitorArquivos;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Leitor {
	
	//Atributos da classe
	private JFileChooser escolhaArquivo = new JFileChooser();
	private File arquivoAnexado;
	private FileNameExtensionFilter filtro; 
	private int temArquivoAnexado;
	//public boolean achouArquivo;
	
	//public Leitor(){
		
	//	this.achouArquivo = false;
	//}
	
	public void abreEscolhaArquivo() {
		
		temArquivoAnexado = escolhaArquivo.showOpenDialog(null);
		
		if (temArquivoAnexado == JFileChooser.APPROVE_OPTION) {
			setArquivoAnexado(escolhaArquivo.getSelectedFile());
			//this.achouArquivo = true;
		}
		
	}
	
	//Definindo o filtro para aceitar apenas arquivos .txt como entrada
	public void setFiltro() {
		
		filtro = new FileNameExtensionFilter("Arquivos texto (.txt)", "txt");
		escolhaArquivo.setAcceptAllFileFilterUsed(false); 
		escolhaArquivo.addChoosableFileFilter(filtro);
	}

	//Obtem o nome absoluto do caminho do arquivo anexado
	public String getAbsPathArquivoAnexado() {
		try {
			return this.arquivoAnexado.getAbsolutePath(); 
		} catch (NullPointerException e) {
			return null;
		}
		
	}
	
	//Obtem o arquivo anexado
	public File getArquivoAnexado() {
		
		return this.arquivoAnexado;
	}
	
	//Define o arquivo anexado
	private void setArquivoAnexado(File arquivoAnexado) {
		
		this.arquivoAnexado = arquivoAnexado;
	}
	
	//
	public String getConteudoDoArquivo() throws IOException {
		try {
			return Files.readString(Path.of(getAbsPathArquivoAnexado()), StandardCharsets.UTF_8);
		} catch (NullPointerException e) {
			return null;
		}
   }
}

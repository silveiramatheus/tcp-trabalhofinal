package gui;

import java.awt.Color;
import java.io.IOException;

import javax.swing.*;
import leitorArquivos.Leitor;
import tradutorMusical.Conversor;

public abstract class Interface extends JFrame{
	
	//ID serial 
	private static final long serialVersionUID = 1L;
	
	
	//Dimensões da tela
	static final int JANELA_LARGURA = 800;
	static final int JANELA_ALTURA = 600;
	
	//Atributos da classe
	private JTextArea areaTexto;
	protected JButton botaoExecutar;
	protected JButton botaoAnexarArq;
	private JComboBox<String> tiposInstrumento;
	private JCheckBox opcaoDownload;
	private JTextField textFieldCaminhoArquivo;
	
	protected Leitor leitorDeArquivos;
	protected Conversor conversorDeTexto;
	
	//Construtor da interface
	public Interface() {
		 
		setInterface();
		setAreaTexto();
		setBotaoExecutar();
		setBotaoAnexarArq();
		setTiposInstrumento();
		setTextFieldCaminhoArquivo();
		setOpcaoDownload();
		addComponentesInterface();
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	
	
	private void initInterface() {
		
		setLayout(null);
	}
	
	private void setInterface() {
		
		initInterface();
		setBounds(0, 0, JANELA_LARGURA, JANELA_ALTURA);
	}
	
	private void addComponentesInterface() {
		
		add(this.areaTexto);
		add(this.botaoExecutar);
		add(this.botaoAnexarArq);
		add(this.tiposInstrumento);
		add(this.textFieldCaminhoArquivo);
		add(this.opcaoDownload);
	}
	
	
	protected void setTitulo(String titulo) {
		
		setTitle(titulo);
	}
	
	//Inicia a área de texto
	private void initAreaTexto() {
		
		this.areaTexto = new JTextArea();
	}
	
	//Define a área de texto
	private void setAreaTexto() {
		
		initAreaTexto();
		this.areaTexto.setBorder(BorderFactory.createLineBorder(Color.black));
		this.areaTexto.setBounds(100,100,500,300);
	}
	
	//Lê a área de texto
	protected String leAreaTexto() {
		
		return this.areaTexto.getText();
	}
	
	//Define o botão de executar
	private void setBotaoExecutar() {
		
		initBotaoExecutar();
		this.botaoExecutar.setBounds(450, 450, 150, 30);
		this.botaoExecutar.setText("Executar");
	}
	
	//Inicia o botão de executar
	private void initBotaoExecutar() {
		
		this.botaoExecutar = new JButton();
	}
	
	//Inicia o botão de anexar arquivo
	private void initBotaoAnexarArq() {
		
		this.botaoAnexarArq = new JButton();
	}
	
	//Define o botão de anexar arquivo
	private void setBotaoAnexarArq() {
		
		initBotaoAnexarArq();
		this.botaoAnexarArq.setBounds(100, 450, 150, 30);
		this.botaoAnexarArq.setText("Escolha o arquivo:");
	}
	
	//Inicia o ComboBox dos tipos de instrumento
	private void initTiposInstrumento() {
		
		this.tiposInstrumento = new JComboBox<String>();
	}
	
	//Define o ComboBox dos tipos de instrumento
	private void setTiposInstrumento() {
		
		initTiposInstrumento();
		this.tiposInstrumento.setBounds(620, 100, 150, 30);
	}
	
	//Preenche a ComboBox com os tipos de instrumento
	protected void preencheTiposInstrumento(String[] opcoesInstrumentos) {
		
		for(String instrumento: opcoesInstrumentos) {
			this.tiposInstrumento.addItem(instrumento);
		}
	}
	
	private String getTiposInstrumento() {
		
		return this.tiposInstrumento.getSelectedItem().toString();
	}
	
	//Inicia a CheckBox de download
	private void initOpcaoDownload() {
		
		this.opcaoDownload = new JCheckBox();
	}
	
	//Define a CheckBox de download
	private void setOpcaoDownload() {
		
		initOpcaoDownload();
		this.opcaoDownload.setBounds(620, 300, 150, 30);
		this.opcaoDownload.setText("Deseja baixar ?");
	}

	protected boolean temOpcaoDownload() {
		
		return opcaoDownload.isSelected();
	}
	
	//Inicia o TextField do caminho do arquivo
	private void initTextFieldCaminhoArquivo() {
		
		textFieldCaminhoArquivo = new JTextField();
	}
	
	//Define o TextField do caminho do arquivo
	private void setTextFieldCaminhoArquivo() {
		
		initTextFieldCaminhoArquivo();
		this.textFieldCaminhoArquivo.setBounds(100, 420, 200, 30);
		this.textFieldCaminhoArquivo.setEditable(false);
		this.textFieldCaminhoArquivo.setText("Nenhum arquivo selecionado");
	}
	
	private void initLeitorArquivos() {
		
		this.leitorDeArquivos = new Leitor();
	}
	
	protected void botaoAnexarArqPressionado() throws IOException {
		
		initLeitorArquivos();
		leitorDeArquivos.setFiltro();
		leitorDeArquivos.abreEscolhaArquivo();
		alteraTextFieldCaminhoArquivo();
		arquivoParaAreaTexto();
	}
	
	private void alteraTextFieldCaminhoArquivo() {
		
		this.textFieldCaminhoArquivo.setText(leitorDeArquivos.getAbsPathArquivoAnexado());
	}
	
	private void arquivoParaAreaTexto() throws IOException {
		
		this.areaTexto.setText(leitorDeArquivos.getConteudoDoArquivo());
		
	}

	protected void botaoExecutarPressionado() throws IOException{
		
		initConversorDeTexto();
		conversorDeTexto.setTextoInicial(leAreaTexto());
		conversorDeTexto.verificaInstrumentoInicial(getTiposInstrumento());
		conversorDeTexto.decodificarTexto();
		
		if (temOpcaoDownload()) {
			conversorDeTexto.baixarMusica();
		}
	}
	
	//Inicializa o conversor de texto
	private void initConversorDeTexto() {
			
		this.conversorDeTexto = new Conversor();
	}
	
}

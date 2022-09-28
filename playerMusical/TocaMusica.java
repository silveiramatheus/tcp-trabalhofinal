package playerMusical;

import org.jfugue.pattern.Pattern;
import org.jfugue.player.Player;

public class TocaMusica {
	
	//Atributos da classe
	Player player;
	Pattern pattern;
	
	public TocaMusica(String textoConvertido) {
		
		initPlayer();
		setPattern(textoConvertido);
	}
	
	private void initPlayer() {
		
		this.player = new Player();
	}
	
	private void setPattern(String textoConvertido) {
		
		pattern = new Pattern(textoConvertido);
	}
	
	public void executaMusica() {
		
		player.play(this.pattern);
	}
}

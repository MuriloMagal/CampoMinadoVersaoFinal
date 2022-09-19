package CampoMinado;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		Tabuleiro tab = new Tabuleiro();
			
		String[] niveis = {"Nível Iniciante", "Nível Intermediário"};
			
		String nivel = (String) JOptionPane.showInputDialog(null, "Selecione a dificuldade do jogo: "
				, "Escolha", 3, null,
				niveis, null);
		
		if(nivel == niveis[0]) {
			tab.setTam(9);
			tab.setMinas(10);
			tab.setSize(464, 486);
		} else {
			tab.setTam(16);
			tab.setMinas(40);
			tab.setSize(653, 676);
		}
		
		tab.geraCelulas();
		
	}
				
		
}



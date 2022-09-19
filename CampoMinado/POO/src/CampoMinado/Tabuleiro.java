package CampoMinado;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Tabuleiro extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int tam;
	int minas;
	Color azul = new Color(0, 91, 255);
	Color vermelho = new Color(255, 0, 0);
	Color cinza = new Color(211, 211, 211);
	ArrayList<JButton> celulas;
	
	ImageIcon iconMina = new ImageIcon("src//CampoMinado//mina.png");
	ImageIcon iconUm = new ImageIcon("src//CampoMinado//1.png");
	ImageIcon iconDois = new ImageIcon("src//CampoMinado//2.png");
	ImageIcon iconTres = new ImageIcon("src//CampoMinado//3.png");
	ImageIcon iconQuatro = new ImageIcon("src//CampoMinado//4.png");
	ImageIcon iconCinco = new ImageIcon("src//CampoMinado//5.png");
	ImageIcon iconSeis = new ImageIcon("src//CampoMinado//6.png");
	ImageIcon iconSete = new ImageIcon("src//CampoMinado//7.png");
	ImageIcon iconOito = new ImageIcon("src//CampoMinado//8.png");

	public Tabuleiro() {

		setLayout(null);

		setTitle("Campo Minado");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);

		celulas = new ArrayList<JButton>();
	}

	/**
	 * 
	 */
	public void geraCelulas() {
		for (int x = 0; x < this.tam; x++) {

			for (int y = 0; y < this.tam; y++) {

				Celula celula = new Celula();
				if (this.tam == 9) {
					celula.setBounds(x * 50, y * 50, 50, 50);
				} else {
					celula.setBounds(x * 40, y * 40, 40, 40);
				}

				celula.setFocusable(false);
				celula.setHorizontalTextPosition(JButton.CENTER);
				celula.setVerticalTextPosition(JButton.CENTER);
				celula.addActionListener(this::acaoButton);
				if (x < 10 && y < 10){
					celula.setText("0"+x+".0"+y);
				} else if (x < 10 && y >=10) {
					celula.setText("0"+x+"."+y);
				} else if (x >= 10 && y < 10) {
					celula.setText(+x+".0"+y);
				} else if (x >= 10 && y >= 10) {
					celula.setText(+x+"."+y);
				}
				celula.setForeground(new Color(1.0f, 1.0f, 1.0f, 0f));
				celula.setVisible(true);
				adicionaCelula(celula);
				add(celula);
			}
		}

		carregaMinas();
		carregaCelulas();
		carregaVizinhas();
		update();
	}

	public void carregaVizinhas() {

		for (JButton celula : celulas) {
			int minas = 0;
			for (int dx = -1; dx <= 1; dx++) {
				for (int dy = -1; dy <= 1; dy++) {
					if (dx != 0 || dy != 0) {
						int x = Integer.parseInt(celula.getText().substring(0, 2)) + dx;
						int y = Integer.parseInt(celula.getText().substring(3, 5)) + dy;
						if (x >= 0 && x < this.tam && y >= 0 && y < this.tam) {
							for(JButton celulaCheca : celulas) {
								int xCheca = Integer.parseInt(celulaCheca.getText().substring(0, 2));
								int yCheca = Integer.parseInt(celulaCheca.getText().substring(3, 5));
								if (x == xCheca && y == yCheca) {
									if (celulaCheca.getText().contains("mina")) {
										minas++;
										
									}
								}
							}
						}
					}
				}
			}
			if(!celula.getText().contains("mina")) {
				defineNumero(celula, minas);
			}
		}
	}
	
	public void defineNumero(JButton celula, int minas) {
		
		for (JButton c : celulas) { 
			if(c == celula) {
				
				switch (minas) {
				
				case 1:
					c.setBackground(this.cinza);
					c.setIcon(iconUm);
					break;
				case 2:
					c.setBackground(this.cinza);
					c.setIcon(iconDois);
					break;
				case 3:
					c.setBackground(this.cinza);
					c.setIcon(iconTres);
					break;
				case 4:
					c.setBackground(this.cinza);
					c.setIcon(iconQuatro);
					break;
				case 5:
					c.setBackground(this.cinza);
					c.setIcon(iconCinco);
					break;
				case 6:
					c.setBackground(this.cinza);
					c.setIcon(iconSeis);
					break;
				case 7:
					c.setBackground(this.cinza);
					c.setIcon(iconSete);
					break;
				case 8:
					c.setBackground(this.cinza);
					c.setIcon(iconOito);
					break;
				}
			}
		}
		update();
	}

	public void carregaMinas() {

		Random aleatorio = new Random();

		int i = 0;

		while (i < this.minas) {
			int x = aleatorio.nextInt(this.tam);
			int y = aleatorio.nextInt(this.tam);
			for (JButton celula : celulas) {
				if (celula.getText().equals(x +"." + y) || celula.getText().equals(x +".0" + y) ||
						celula.getText().equals("0"+x +"." + y) || celula.getText().equals("0"+x +".0" + y)) {
					if (!celula.getText().contains("mina")) {
						celula.setText(celula.getText() +" /mina");
						celula.setBackground(this.vermelho);
						celula.setIcon(this.iconMina);
						i++;
					}
				}
			}

		}
	}

	public void carregaCelulas() {
		for (JButton celula : celulas) {
			if (!celula.getText().contains("mina")) {
				celula.setBackground(this.azul);
			}
		}
	}

	public void update() {
		for (JButton celula : celulas) {
			add(celula);
		}
	}

	public void acaoButton(ActionEvent e) {
		for (JButton celula : celulas) {
			if (e.getSource() == celula) {
				if (celula.getBackground().equals(this.azul)) {
					celula.setBackground(this.vermelho);
				} else {
					celula.setBackground(this.azul);
				}

				update();

			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public int getTam() {
		return tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public ArrayList<JButton> getCelulas() {
		return celulas;
	}

	public void setCelulas(ArrayList<JButton> celulas) {
		this.celulas = celulas;
	}

	public void adicionaCelula(JButton celula) {
		this.celulas.add(celula);
	}

	public int getMinas() {
		return minas;
	}

	public void setMinas(int minas) {
		this.minas = minas;
	}

}

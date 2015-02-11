package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import activity.Controls;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Cell;
import models.Grid;
import models.ModelObject;
import models.Tetrimino;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JButton;

import utils.Colors;
import java.awt.Toolkit;

/**
 * Fenêtre principale de l'application
 */
public class MainFrame extends JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	/**
	 * Panel
	 */
	private JPanel contentPane;
	private JPanel holdPiece;
	private JPanel nextPiece;
	private JPanel gridPanel;
	private ModelObject tetris;
	private KeyListener keyListener;
	private JLabel lblPartyScore;
	private JLabel lblPartyLvl;
	private JLabel lblPause;

	/**
	 * Constructeur de la fenêtre principale de l'application
	 */
	public MainFrame(ModelObject model) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\epulapp\\workspace\\tetris\\src\\view\\1403806862_204984.ico.png"));

		tetris = model;

		// Titre
		this.setTitle("Tetris");
		this.setBounds(200, 200, 512, 590);
		// Création du Panel
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		this.getContentPane().setLayout(null);

		lblPause = new JLabel("PAUSE");
		lblPause.setForeground(Color.WHITE);
		lblPause.setVisible(false);
		lblPause.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
		lblPause.setBounds(175, 200, 162, 50);
		contentPane.add(lblPause);

		// Création du Menu
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 510, 30);
		getContentPane().add(menuBar);

		// Menu fichier
		JMenu mnFichier = new JMenu("Fichier");
		mnFichier.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnFichier);

		// Option quitter dans Fichier
		JMenuItem menuItemQuitter = new JMenuItem("Quitter");
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Option réinitialiser dans Fichier
		JMenuItem mntmRestart = new JMenuItem("Nouvelle partie");
		mntmRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnFichier.add(mntmRestart);
		mnFichier.add(menuItemQuitter);

		JPanel gamePanel = new JPanel();
		gamePanel.setBounds(0, 31, 506, 527);
		contentPane.add(gamePanel);
		gamePanel.setLayout(new BorderLayout(0, 0));

		JPanel holdPanel = new JPanel();
		gamePanel.add(holdPanel, BorderLayout.WEST);

		JLabel lblHold = new JLabel("HOLD");
		lblHold.setForeground(Color.BLACK);
		lblHold.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));

		holdPiece = new JPanel();
		holdPiece.setBackground(Color.DARK_GRAY);
		holdPiece.setLayout(new GridLayout(2, 4, 2, 2));

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				JPanel cell = new JPanel();
				Dimension dim = new Dimension(1, 1);
				cell.setPreferredSize(dim);
				cell.setBackground(Color.BLACK);
				holdPiece.add(cell);
			}
		}
		holdPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("50px"),
				RowSpec.decode("default:grow"), RowSpec.decode("26px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC, }));
		holdPanel.add(lblHold, "2, 2, center, center");
		holdPanel.add(holdPiece, "2, 4, fill, fill");

		JLabel lblScore = new JLabel("SCORE");
		lblScore.setForeground(Color.BLACK);
		lblScore.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		holdPanel.add(lblScore, "2, 6, center, center");

		lblPartyScore = new JLabel("00000000");
		lblPartyScore.setForeground(Color.BLACK);
		lblPartyScore.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		holdPanel.add(lblPartyScore, "2, 8, center, center");

		JPanel nextPanel = new JPanel();
		gamePanel.add(nextPanel, BorderLayout.EAST);

		JLabel lblNext = new JLabel("NEXT");
		lblNext.setForeground(Color.BLACK);
		lblNext.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));

		nextPiece = new JPanel();
		nextPiece.setBackground(Color.DARK_GRAY);
		nextPiece.setLayout(new GridLayout(2, 4, 2, 2));

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				JPanel cell = new JPanel();
				Dimension dim = new Dimension(1, 1);
				cell.setPreferredSize(dim);
				cell.setBackground(Color.BLACK);
				nextPiece.add(cell);
			}
		}
		nextPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("100px"),
				FormFactory.UNRELATED_GAP_COLSPEC, }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("26px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("50px"),
				RowSpec.decode("default:grow"), RowSpec.decode("26px"),
				FormFactory.RELATED_GAP_ROWSPEC, RowSpec.decode("50px"),
				FormFactory.RELATED_GAP_ROWSPEC, }));
		nextPanel.add(nextPiece, "2, 4, fill, fill");
		nextPanel.add(lblNext, "2, 2, center, top");

		JLabel lblLvl = new JLabel("LVL");
		lblLvl.setForeground(Color.BLACK);
		lblLvl.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
		nextPanel.add(lblLvl, "2, 6, center, center");

		lblPartyLvl = new JLabel("1");
		lblPartyLvl.setForeground(Color.BLACK);
		lblPartyLvl.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
		nextPanel.add(lblPartyLvl, "2, 8, center, center");

		gridPanel = new JPanel();
		gridPanel.setBackground(Color.DARK_GRAY);
		gamePanel.add(gridPanel, BorderLayout.CENTER);
		gridPanel.setLayout(new GridLayout(22, 10, 2, 2));

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 22; j++) {
				JPanel cell = new JPanel();
				Dimension dim = new Dimension(1, 1);
				cell.setPreferredSize(dim);
				cell.setBackground(Color.BLACK);
				gridPanel.add(cell);
			}
		}

		// croix rouge pour quitter
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// mettre au centre de l'écran
		this.setLocationRelativeTo(this.getParent());
		// interdit la modification de la taille de la fenêtre
		this.setResizable(false);
		// afficher la fenêtre
		this.setVisible(true);

		this.moveListener();
		this.addKeyListener(this.keyListener);

		this.setFocusable(true);
		this.requestFocusInWindow();

	}// Fin Constructeur

	public void displayGrid() {
		Cell[][] grid = tetris.getGrid().getTable();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				JPanel cell = (JPanel) gridPanel.getComponent((j * grid.length)
						+ i);
				if (grid[i][j] == null) {
					cell.setBackground(Color.BLACK);
				} else {
					// TODO
					if (tetris.getLevel() <= 10) {
						cell.setBackground(Colors.colors[grid[i][j].getColor()][tetris
								.getLevel() - 1]);
					} else {
						cell.setBackground(Colors.colors[grid[i][j].getColor()][9]);
					}
				}
			}
		}
		for (Cell tetrimino : tetris.getCurrentTetrimino().getCells()) {
			if (tetrimino != null) {
				JPanel cell = (JPanel) gridPanel
						.getComponent((tetrimino.getX() * grid.length)
								+ tetrimino.getY());
				if (tetris.getLevel() <= 10) {
					cell.setBackground(Colors.colors[tetrimino.getColor()][tetris
							.getLevel() - 1]);
				} else {
					cell.setBackground(Colors.colors[tetrimino.getColor()][9]);
				}
			}
		}
		lblPartyScore.setText(String.valueOf(tetris.getScore()));
		lblPartyLvl.setText(Integer.toString(tetris.getLevel()));
	}

	private void displayHold() {
		int[][][] hold = tetris.getHold().getShape();
		int[][] current = hold[0];
		for (int i = 2; i < current.length; i++) {
			for (int j = 0; j < current[i].length; j++) {
				JPanel cell = (JPanel) holdPiece
						.getComponent(((i - 2) * hold.length) + j);
				if (current[i][j] == 0) {
					cell.setBackground(Color.BLACK);
				} else {
					// TODO
					if (tetris.getLevel() <= 10) {
						cell.setBackground(Colors.colors[current[i][j]][tetris
								.getLevel() - 1]);
					} else {
						cell.setBackground(Colors.colors[current[i][j]][9]);
					}
				}
			}
		}
	}

	public void displayNext() {
		int[][][] next = tetris.getNext().getShape();
		int[][] current = next[0];
		for (int i = 2; i < current.length; i++) {
			for (int j = 0; j < current[i].length; j++) {
				JPanel cell = (JPanel) nextPiece
						.getComponent(((i - 2) * next.length) + j);
				if (current[i][j] == 0) {
					cell.setBackground(Color.BLACK);
				} else {
					if (tetris.getLevel() <= 10) {
						cell.setBackground(Colors.colors[current[i][j]][tetris
								.getLevel() - 1]);
					} else {
						cell.setBackground(Colors.colors[current[i][j]][9]);
					}
				}
			}
		}

	}

	public void moveListener() {
		// KeyEvent Listener
		if (!this.tetris.getPause()){
		this.keyListener = new KeyListener() {
			public void keyPressed(KeyEvent evt) {
				if (Controls.CheckActionDown(evt)) {
					tetris.gridUpdate(1);
					displayGrid();
				} else if (Controls.CheckActionLeft(evt)) {
					tetris.gridUpdate(2);
					displayGrid();
				} else if (Controls.CheckActionRight(evt)) {
					tetris.gridUpdate(3);
					displayGrid();
				} else if (Controls.CheckActionUp(evt)) {
					tetris.gridUpdate(4);
					displayGrid();
				} else if (Controls.CheckActionX(evt)) {
					tetris.right();
					displayGrid();
				} else if (Controls.CheckActionW(evt)) {
					tetris.left();
					displayGrid();
				} else if (Controls.CheckActionC(evt)) {
					tetris.putHold();
					displayGrid();
					displayNext();
					displayHold();
				} else if (Controls.CheckActionSpace(evt)) {
					tetris.pause();
					if (lblPause.isVisible()) {
						lblPause.setVisible(false);
					} else {
						lblPause.setVisible(true);
					}
				}
			}

			public void keyReleased(KeyEvent evt) {

			}

			public void keyTyped(KeyEvent evt) {
			}
		};}
	}

	@Override
	public void update(Observable o, Object arg) {
		displayGrid();
		displayNext();
		if (this.tetris.getHold() != null) {
			displayHold();
		}
	}
}
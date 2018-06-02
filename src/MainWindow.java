import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField tf_ValorkWh;
	private JTextField tf_potenciaAparelho;
	private JTextField tf_qtyAparelho;
	private JTextField tf_horasDia;
	
	float s_totalkhwmes = 0;
	float s_totalgastmes = 0;
	private JTable tb_aparelhos;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 515);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSobre = new JMenu("Sobre");
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobreOSistema = new JMenuItem("Sobre o sistema...");
		mntmSobreOSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
					    "Simulador de Consumo de Energia.\n\n"
					    + "Este simulador foi feito como Projeto Integrador do 5o Semestre\n"
					    + "de Ciência da Computação da Faculdade de Americana (FAM)\n"
					    + "Jun/2018\n"
					    + "\n"
					    + "Desenvolvedores:\n"
					    + "Douglas Wantuil da Costa - RA 20160040\n"
					    + "Willian Rocha Gomes - RA 20160837");
			}
		});
		mnSobre.add(mntmSobreOSistema);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnSobre.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblValorDoKwh = new JLabel("Valor do kWh: R$");
		lblValorDoKwh.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorDoKwh.setBounds(10, 41, 103, 21);
		contentPane.add(lblValorDoKwh);
		
		JLabel lblSimuladorDeConsumo = new JLabel("Simulador de Consumo");
		lblSimuladorDeConsumo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSimuladorDeConsumo.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblSimuladorDeConsumo.setBounds(50, 4, 634, 34);
		contentPane.add(lblSimuladorDeConsumo);
		
		tf_ValorkWh = new JTextField("0,75");
		tf_ValorkWh.setBounds(118, 41, 43, 20);
		contentPane.add(tf_ValorkWh);
		tf_ValorkWh.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 116, 706, 223);
		contentPane.add(scrollPane);

		DefaultTableModel modelo = new DefaultTableModel();
		tb_aparelhos = new JTable(modelo);
		tb_aparelhos.setEnabled(false);
		tb_aparelhos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelo.addColumn("Cômodo");
		modelo.addColumn("Aparelho");
		modelo.addColumn("Potência");
		modelo.addColumn("Quantidade");
		modelo.addColumn("Horas");
		modelo.addColumn("Consumo (kWh)");
		modelo.addColumn("Mensal (R$)");

		scrollPane.setViewportView(tb_aparelhos);
		
		JLabel lblCmodo = new JLabel("C\u00F4modo");
		lblCmodo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCmodo.setBounds(10, 73, 116, 14);
		contentPane.add(lblCmodo);
		
		JComboBox cbox_comodo = new JComboBox();
		cbox_comodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbox_comodo.getSelectedItem().toString().contains("Outro")){
					String x = javax.swing.JOptionPane.showInputDialog("Digite o novo cômodo");
					if (x.toString().equals("")) {
						cbox_comodo.setSelectedItem(cbox_comodo);
					} else {
						cbox_comodo.addItem(x);
						cbox_comodo.setSelectedItem(x);
						
					}
				}
			}
		});
		cbox_comodo.setModel(new DefaultComboBoxModel(new String[] {"Sala", "Quarto", "Copa", "Cozinha", "Banheiro", "Lavanderia", 
				"Varanda", "Garagem", "Escrit\u00F3rio", "Terra\u00E7o", "\u00C1rea Externa", "Outro..."}));
		cbox_comodo.setBounds(10, 90, 117, 20);
		contentPane.add(cbox_comodo);
		
		JLabel lblAparelho = new JLabel("Aparelho");
		lblAparelho.setHorizontalAlignment(SwingConstants.CENTER);
		lblAparelho.setBounds(130, 73, 159, 14);
		contentPane.add(lblAparelho);
		
		final JComboBox cbox_aparelho = new JComboBox();
		cbox_aparelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cbox_aparelho.getSelectedItem().toString().contains("Outro")){
					String x = javax.swing.JOptionPane.showInputDialog("Digite o novo aparelho");
					if (x.toString().equals("")) {
						cbox_aparelho.setSelectedItem(cbox_aparelho);
					} else {
						cbox_aparelho.addItem(x);
						cbox_aparelho.setSelectedItem(x);
						
					}
				}
			}
		});
		cbox_aparelho.setModel(new DefaultComboBoxModel(new String[] {"L\u00E2mpada Incandescente", "L\u00E2mpada Fluorescente",
				"L\u00E2mpada LED", "Televis\u00E3o", "Aparelho de Som", "Ar Condicionado", "Aspirador de P\u00F3",
				"Boiler El\u00E9trico", "Lavadora de Pratos", "Lavadora de Roupa", "Microondas", "Refrigerador", "Chuveiro", 
				"Computador", "Estufa", "Ferro de passar", "Freezer", "Fritadeira", "Secadora de Roupa", "Torneira El\u00E9trica", 
				"Triturador de papel", "Ventilador", "Lava Jato", "Outro..."}));
		cbox_aparelho.setBounds(131, 90, 159, 20);
		contentPane.add(cbox_aparelho);
		
		JLabel lblPotencia = new JLabel("Potencia");
		lblPotencia.setHorizontalAlignment(SwingConstants.CENTER);
		lblPotencia.setBounds(293, 73, 86, 14);
		contentPane.add(lblPotencia);
		
		tf_potenciaAparelho = new JTextField();
		tf_potenciaAparelho.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				String aparelhoselecionado;
				aparelhoselecionado = cbox_aparelho.getSelectedItem().toString();
				if (aparelhoselecionado.contains("Lâmpada Incandescente")) {
					tf_potenciaAparelho.setText("100");
				}
				if (aparelhoselecionado.contains("Lâmpada Fluorescente")) {
					tf_potenciaAparelho.setText("32");
				}
				if (aparelhoselecionado.contains("Lâmpada LED")) {
					tf_potenciaAparelho.setText("13");
				}
				if (aparelhoselecionado.contains("Televisão")) {
					tf_potenciaAparelho.setText("150");
				}
				if (aparelhoselecionado.contains("Aparelho de Som")) {
					tf_potenciaAparelho.setText("150");
				}
				if (aparelhoselecionado.contains("Ar Condicionado")) {
					tf_potenciaAparelho.setText("1200");
				}
				if (aparelhoselecionado.contains("Aspirador de Pó")) {
					tf_potenciaAparelho.setText("1000");
				}
				if (aparelhoselecionado.contains("Boiler Elétrico")) {
					tf_potenciaAparelho.setText("4500");
				}				
				if (aparelhoselecionado.contains("Lavadora de Pratos")) {
					tf_potenciaAparelho.setText("1500");
				}
				if (aparelhoselecionado.contains("Lavadora de Roupa")) {
					tf_potenciaAparelho.setText("600");
				}
				if (aparelhoselecionado.contains("Microondas")) {
					tf_potenciaAparelho.setText("1500");
				}
				if (aparelhoselecionado.contains("Refrigerador")) {
					tf_potenciaAparelho.setText("100");
				}			
				if (aparelhoselecionado.contains("Chuveiro")) {
					tf_potenciaAparelho.setText("5000");
				}
				if (aparelhoselecionado.contains("Computador")) {
					tf_potenciaAparelho.setText("200");
				}
				if (aparelhoselecionado.contains("Estufa")) {
					tf_potenciaAparelho.setText("1200");
				}
				if (aparelhoselecionado.contains("Ferro de passar")) {
					tf_potenciaAparelho.setText("1000");
				}				
				if (aparelhoselecionado.contains("Freezer")) {
					tf_potenciaAparelho.setText("65");
				}
				if (aparelhoselecionado.contains("Fritadeira")) {
					tf_potenciaAparelho.setText("600");
				}
				if (aparelhoselecionado.contains("Secadora de Roupa")) {
					tf_potenciaAparelho.setText("2000");
				}
				if (aparelhoselecionado.contains("Torneira Elétrica")) {
					tf_potenciaAparelho.setText("2500");
				}				
				if (aparelhoselecionado.contains("Triturador de papel")) {
					tf_potenciaAparelho.setText("300");
				}
				if (aparelhoselecionado.contains("Ventilador")) {
					tf_potenciaAparelho.setText("150");
				}
				if (aparelhoselecionado.contains("Lava Jato")) {
					tf_potenciaAparelho.setText("350");
				}
				if (aparelhoselecionado.contains("Outro")) {
					tf_potenciaAparelho.setText("");
				}
				tf_potenciaAparelho.selectAll();
			}
		});
		tf_potenciaAparelho.setBounds(294, 90, 86, 20);
		contentPane.add(tf_potenciaAparelho);
		tf_potenciaAparelho.setColumns(10);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantidade.setBounds(389, 73, 74, 14);
		contentPane.add(lblQuantidade);
		
		tf_qtyAparelho = new JTextField();
		tf_qtyAparelho.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				tf_qtyAparelho.selectAll();
			}
		});
		tf_qtyAparelho.setBounds(384, 90, 86, 20);
		contentPane.add(tf_qtyAparelho);
		tf_qtyAparelho.setColumns(10);
		
		JLabel lblTempo = new JLabel("Horas/dia");
		lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTempo.setBounds(465, 73, 103, 14);
		contentPane.add(lblTempo);
		
		JLabel lbl_totalkwhmes = new JLabel("0,0");
		lbl_totalkwhmes.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_totalkwhmes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_totalkwhmes.setBounds(321, 376, 149, 14);
		contentPane.add(lbl_totalkwhmes);
		
		JLabel lbl_totalgastomes = new JLabel("0,00");
		lbl_totalgastomes.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_totalgastomes.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_totalgastomes.setBounds(321, 401, 149, 14);
		contentPane.add(lbl_totalgastomes);
		
		tf_horasDia = new JTextField();
		tf_horasDia.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				tf_horasDia.selectAll();
			}
		});
		tf_horasDia.setBounds(474, 90, 86, 20);
		contentPane.add(tf_horasDia);
		tf_horasDia.setColumns(10);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String valorkwhvirg;
					valorkwhvirg = tf_ValorkWh.getText();
					valorkwhvirg = valorkwhvirg.replace(',', '.');
					
					String valorhoravirg;
					valorhoravirg = tf_horasDia.getText();
					valorhoravirg = valorhoravirg.replace(',','.');
					
					Aparelhos Aparelho = new Aparelhos();
					
					Aparelho.setComodo(cbox_comodo.getSelectedItem().toString());
					
					Aparelho.setAparelho(cbox_aparelho.getSelectedItem().toString());
					
					Aparelho.setPotencia(Integer.parseInt(tf_potenciaAparelho.getText()));
					
					Aparelho.setQuantidade(Integer.parseInt(tf_qtyAparelho.getText()));
					
					Aparelho.setHoras(Float.valueOf(valorhoravirg));
					
					Aparelho.setTotal(Integer.parseInt(tf_potenciaAparelho.getText()), Aparelho.getQuantidade(), 
							Float.valueOf(valorhoravirg));
					
					Aparelho.setMensal(Aparelho.getTotal(), Float.valueOf(valorkwhvirg));
					
					modelo.insertRow(modelo.getRowCount(), new Object[] {Aparelho.getComodo(),Aparelho.getAparelho(),
							Aparelho.getPotencia(),Aparelho.getQuantidade(),String.format("%,.2f", Aparelho.getHoras()),
							String.format("%,.2f", Aparelho.getTotal()),String.format("%,.2f", Aparelho.getMensal())});
					

					s_totalkhwmes += Aparelho.getTotal();
					s_totalgastmes += Aparelho.getMensal();
					lbl_totalkwhmes.setText(String.format("%,.1f", s_totalkhwmes));
					lbl_totalgastomes.setText(String.format("%,.2f", s_totalgastmes));
					cbox_comodo.grabFocus();
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(new JFrame(), "Houve algum erro. Possiveis causas: \n\n"
							+ "- Algum valor em branco,\n"
							+ "- Letras ou caracteres especiais ao invés de números.\n"
							+ "- Potência e quantidade aceitam apenas números inteiros.\n\n"
							+ "** Lembre-se **\n"
							+ "=> Números negativos serão automaticamente convertidos para positivos.\n"
							+ "=> Horas maiores de 24 serão consideradas apenas 24."
							, "Erro",JOptionPane.ERROR_MESSAGE);
				}
				}
		});
		btnIncluir.setBounds(564, 89, 152, 20);
		contentPane.add(btnIncluir);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int contalinhas = modelo.getRowCount();

				for (int i = contalinhas - 1; i >= 0; i--) {
					modelo.removeRow(i);
				}
				s_totalkhwmes = 0;
				s_totalgastmes = 0;
				lbl_totalkwhmes.setText(String.format("%,.1f", s_totalkhwmes));
				lbl_totalgastomes.setText(String.format("%,.2f", s_totalgastmes));
			}
		});
		btnLimpar.setBounds(10, 344, 166, 21);
		contentPane.add(btnLimpar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setLabelFor(tf_ValorkWh);
		lblNewLabel.setToolTipText("Este valor \u00E9 encontrado normalmente em sua fatura.\r\n\r\nVoc\u00EA pode verificar, normalmente se localiza no cabe\u00E7alho.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		lblNewLabel.setBounds(160, 34, 43, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblTotalKwhConsumidos = new JLabel("Total consumidos no m\u00EAs (em kWh):");
		lblTotalKwhConsumidos.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTotalKwhConsumidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalKwhConsumidos.setBounds(37, 376, 280, 14);
		contentPane.add(lblTotalKwhConsumidos);
		
		JLabel lblValorGastoNo = new JLabel("Valor gasto no m\u00EAs, excluindo impostos: R$ ");
		lblValorGastoNo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblValorGastoNo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValorGastoNo.setBounds(56, 401, 261, 14);
		contentPane.add(lblValorGastoNo);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnSair.setBounds(627, 431, 89, 23);
		contentPane.add(btnSair);
		

	}
}

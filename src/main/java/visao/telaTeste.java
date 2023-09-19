package visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controle.CarroDAO;
import modelo.Carro;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class telaTeste extends JFrame {

	private JPanel contentPane;
	private JTextField textIdCarro;
	private JTextField textModelo;
	private CarroDAO carrodao = CarroDAO.getInstancia();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telaTeste frame = new telaTeste();
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
	public telaTeste() {
		
		
		Carro carro = new Carro();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textIdCarro.setEditable(true);
				textIdCarro.setText("");
				textModelo.setText("");
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Id Carro:");
		lblNewLabel.setBounds(10, 22, 46, 14);
		contentPane.add(lblNewLabel);

		textIdCarro = new JTextField();
		textIdCarro.setBounds(66, 19, 86, 20);
		contentPane.add(textIdCarro);
		textIdCarro.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(212, 22, 326, 324);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int setar = table.getSelectedRow();
				textIdCarro.setText(table.getModel().getValueAt(setar, 0).toString());
				textModelo.setText(table.getModel().getValueAt(setar, 1).toString());
				textIdCarro.setEditable(false);
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Modelo"
			}
		));
		scrollPane.setViewportView(table);
		for (Carro carros : carrodao.listar()) {
			DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
			String data[] = {String.valueOf(carros.getIdCarro()), carros.getModeloCarro()};
			tblModel.addRow(data);
		}

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 57, 46, 14);
		contentPane.add(lblModelo);

		textModelo = new JTextField();
		textModelo.setColumns(10);
		textModelo.setBounds(66, 54, 86, 20);
		contentPane.add(textModelo);

		JButton btnInserir = new JButton("INSERIR");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.valueOf(textIdCarro.getText());
				String modelo = textModelo.getText();
				carro.setIdCarro(id);
				carro.setModeloCarro(modelo);
				carrodao.inserir(carro);
				DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
				String data[] = {String.valueOf(carro.getIdCarro()), carro.getModeloCarro()};
				tblModel.addRow(data);
			}
		});
		btnInserir.setBounds(10, 85, 142, 23);
		contentPane.add(btnInserir);
		
		JButton btnAtualizar = new JButton("ATUALIZAR");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.valueOf(textIdCarro.getText());
				String modelo = textModelo.getText();
				carro.setIdCarro(id);
				carro.setModeloCarro(modelo);
				carrodao.atualizar(carro);
				int setar = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(setar);
				String data[] = {String.valueOf(carro.getIdCarro()), carro.getModeloCarro()};
				model.addRow(data);
				textIdCarro.setText("");
				textModelo.setText("");
				textIdCarro.setEditable(true);
			}
		});
		btnAtualizar.setBounds(10, 119, 142, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("DELETAR");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int setar = table.getSelectedRow();
				Integer id = Integer.valueOf(textIdCarro.getText());
				String modelo = textModelo.getText();
				carro.setIdCarro(id);
				carro.setModeloCarro(modelo);
				carrodao.excluir(carro);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.removeRow(setar);
				textModelo.setText("");
				textIdCarro.setText("");
			}
		});
		btnDeletar.setBounds(10, 153, 142, 23);
		contentPane.add(btnDeletar);
		
		
		
	}
}

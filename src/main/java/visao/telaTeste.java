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

public class telaTeste extends JFrame {

	private JPanel contentPane;
	private JTextField textIdCarro;
	private JTextField textModelo;
	private CarroDAO carrodao = CarroDAO.getInstancia();

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
		
		

		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setBounds(10, 57, 46, 14);
		contentPane.add(lblModelo);

		textModelo = new JTextField();
		textModelo.setColumns(10);
		textModelo.setBounds(66, 54, 86, 20);
		contentPane.add(textModelo);

		JButton btnNewButton = new JButton("INSERIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer id = Integer.valueOf(textIdCarro.getText());
				String modelo = textModelo.getText();
				carro.setIdCarro(id);
				carro.setModeloCarro(modelo);
				carrodao.inserir(carro);

				
			}
		});
		btnNewButton.setBounds(10, 85, 142, 23);
		contentPane.add(btnNewButton);
		
	}
}

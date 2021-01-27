package GuilanUniversity96.AmirAbbasi.four;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class form extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					form frame = new form();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public form() {
		
		setBounds(100, 100, 512, 446);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textField = new JTextField();
		contentPane.add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(82, 29, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(82, 64, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(82, 99, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(342, 29, 116, 22);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(43, 32, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(251, 32, 91, 16);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblBirthday = new JLabel("Birthday:");
		lblBirthday.setBounds(28, 67, 56, 16);
		contentPane.add(lblBirthday);
		
		JLabel lblSex = new JLabel("Sex:");
		lblSex.setBounds(303, 67, 56, 16);
		contentPane.add(lblSex);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setBounds(38, 99, 56, 16);
		contentPane.add(lblEmail);
		
	
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"man", "woman"}));
		comboBox.setBounds(352, 64, 99, 22);
		contentPane.add(comboBox);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		btnAdd.setBounds(121, 181, 97, 25);
		contentPane.add(btnAdd);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(290, 181, 97, 25);
		contentPane.add(btnCancel);
	}
	public void Setter(AddressBook addressBook) {
		Contact concat=new Contact(null);
		concat.nameSetter(textField_1.getText());
		concat.numberSetter(textField_4.getText());
		concat.birthdaySetter(textField_2.getText());
		concat.sexSetter(comboBox.getToolTipText());
		concat.emailSetter(textField_3.getText());
	}
}

package pckg1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtSurname;
	
	ArrayList<Employee> employees = new ArrayList<>();
	private JTextField txtEmployeeId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPage frame = new FormPage();
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
	public FormPage() {
		setTitle("Employee Form Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEmployeeId = new JLabel("ID");
		lblEmployeeId.setBounds(10, 26, 45, 13);
		contentPane.add(lblEmployeeId);
		
		txtEmployeeId = new JTextField();
		txtEmployeeId.setBounds(97, 23, 96, 19);
		contentPane.add(txtEmployeeId);
		txtEmployeeId.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 51, 60, 13);
		contentPane.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(10, 74, 60, 13);
		contentPane.add(lblSurname);
		
		txtName = new JTextField();
		txtName.setBounds(97, 48, 96, 19);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(97, 71, 96, 19);
		contentPane.add(txtSurname);
		txtSurname.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 102, 45, 13);
		contentPane.add(lblGender);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(97, 98, 103, 21);
		contentPane.add(rdbtnFemale);
		rdbtnFemale.setActionCommand("Female");
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(97, 121, 103, 21);
		contentPane.add(rdbtnMale);
		rdbtnMale.setActionCommand("Male");
		
		//For single gender choice *******************************************
		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);
		//********************************************************************
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 142, 45, 13);
		contentPane.add(lblAge);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 217, 77, 19);
		contentPane.add(lblDepartment);
		
		JComboBox cbAge = new JComboBox();
		cbAge.setBounds(97, 148, 43, 21);
		contentPane.add(cbAge);
		
		//Adding item to age combo box
		for (int i = 18; i <= 65; i++) {
			cbAge.addItem(i);
		}
		
		JComboBox cbDepartment = new JComboBox();
		cbDepartment.setBounds(97, 216, 96, 21);
		contentPane.add(cbDepartment);
		
		//Adding item to city combo box
		cbDepartment.addItem("Accounting");
		cbDepartment.addItem("IT");
		cbDepartment.addItem("Medical");
		cbDepartment.addItem("Business");
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(232, 50, 241, 98);
		contentPane.add(textArea);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id = Integer.parseInt(txtEmployeeId.getText());
				String name = txtName.getText();
				String surname = txtSurname.getText();
				String gender = genderGroup.getSelection().getActionCommand();
				int age = Integer.parseInt(cbAge.getSelectedItem().toString());
				String department = cbDepartment.getSelectedItem().toString();
				
				String strId = txtEmployeeId.getText();//For using the isEmpty() function, it is for strings.
				if (strId.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane, "Employee ID cannot be empty");
				}
				else if (employees.stream()
				         .filter(emp -> emp.getId() == Integer.parseInt(txtEmployeeId.getText()))
				         .findFirst()
				         .isPresent()) {
					JOptionPane.showMessageDialog(contentPane, "Invalid ID!");
				}
				else {
					Employee emp = new Employee(id, name, surname, gender, department, age);
					employees.add(emp);
					JOptionPane.showMessageDialog(contentPane, "Employee saved!");					
				}
				
				String output = "";
				for (Employee emp : employees) {
					output += emp.getId()+" "+emp.getName()+" "+emp.getSurname()+" "+emp.getGender()+" "+emp.getAge()+" "+emp.getDepartment()+"\n";
				}
				textArea.setText(output);
				
				//Clearing the input
				txtEmployeeId.setText("");
				txtName.setText("");
				txtSurname.setText("");
				cbAge.setSelectedIndex(0);
				cbDepartment.setSelectedIndex(0);
			}
		});
		btnSave.setBounds(53, 259, 85, 21);
		contentPane.add(btnSave);
		
		JLabel lblEmployees = new JLabel("Employees");
		lblEmployees.setBounds(232, 26, 90, 13);
		contentPane.add(lblEmployees);
		
	}
}

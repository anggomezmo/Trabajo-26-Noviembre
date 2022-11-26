package Gestión_de_datos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Color;

public class Gestión_de_contactos extends JFrame {
	private ArrayList<Persona> Lis;
	private JPanel contentPane;
	private JTextField txti;
	private JTextField txtn;
	private JTextField txta;
	private JTable table;
	private JTextField txtcontacto;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gestión_de_contactos frame = new Gestión_de_contactos();
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
	public Gestión_de_contactos() {
		setTitle("Gestión de contactos");
		Lis= new ArrayList<Persona>();
		initComponents();
		Cargar();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 579);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 51, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificación");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNewLabel.setBounds(32, 43, 121, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setForeground(Color.GREEN);
		lblNombre.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblNombre.setBounds(32, 108, 83, 17);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setForeground(Color.GREEN);
		lblApellido.setFont(new Font("Comic Sans MS", Font.PLAIN, 13));
		lblApellido.setBounds(32, 183, 72, 17);
		contentPane.add(lblApellido);
		
		txti = new JTextField();
		txti.setColumns(10);
		txti.setBounds(152, 43, 198, 20);
		contentPane.add(txti);
		
		txtn = new JTextField();
		txtn.setColumns(10);
		txtn.setBounds(152, 108, 198, 20);
		contentPane.add(txtn);
		
		txta = new JTextField();
		txta.setColumns(10);
		txta.setBounds(152, 183, 198, 20);
		contentPane.add(txta);
		
		
		
		
		
		JButton btnNewButton = new JButton("Agregar");
		btnNewButton.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Identificacion;
				String Nombre;
				String Apellido;
				try {
					Nombre=txtn.getText();
					Apellido=txta.getText();
					Identificacion=Integer.parseInt(txti.getText());
					Lis.add(new Persona(Identificacion,Nombre,Apellido));
			}catch(Exception e1) {
				
				JOptionPane.showMessageDialog(null, "Verifique los datos");
			}
			txti.setText("");
			txta.setText("");
			txtn.setText("");
			VerDatos();
			}
			
			
			
		});
		
		btnNewButton.setBounds(386, 42, 121, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1_1 = new JButton("Buscar contacto");
		btnNewButton_1_1.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtcontacto.getText().isEmpty()) {
					Persona aux;
					int a=0;
					String str= txtcontacto.getText();
					for(int i=0; i<Lis.size();i++) {
						aux=Lis.get(i);
						if(String.valueOf(aux.getIdentificacion()).equals(str)) {
							MostrarPersona(aux);
							a=1;
						}
						
					}
					if (a==0) {
						JOptionPane.showMessageDialog(null, "La persona que busca no se encuentra en los registros");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Por favor ingrese la Identificación");
				}
				
				
				
			}
		});
		btnNewButton_1_1.setBounds(55, 506, 163, 23);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1_1 = new JButton("Eliminar lista");
		btnNewButton_1_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Lis.clear();
				VerDatos();
			}
		});
		btnNewButton_1_1_1_1.setBounds(547, 107, 121, 23);
		contentPane.add(btnNewButton_1_1_1_1);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int c, identificacion;
				String Nombre,Apellido;
				Persona aux;
				try {
					c=table.getSelectedRow();
					aux= Lis.get(c);
					identificacion= Integer.parseInt(JOptionPane.showInputDialog("Nueva identificación",aux.getIdentificacion()));
					aux.setIdentificacion(identificacion);
					VerDatos();
					Nombre=JOptionPane.showInputDialog("Nuevo Nombre",aux.getNombre());
					aux.setNombre(Nombre);
					VerDatos();
					Apellido=JOptionPane.showInputDialog("Nuevo Apellido",aux.getApellido());
					aux.setApellido(Apellido);
					VerDatos();
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, "Asegurese de seleccionar una fila");
					
				}
				
				
				
			}
		});
		btnModificar.setBounds(386, 107, 128, 23);
		contentPane.add(btnModificar);
		
		JButton btnNewButton_1_1_1 = new JButton("Eliminar registro");
		btnNewButton_1_1_1.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int c;
				try {
					c=table.getSelectedRow();
					Lis.remove(c);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Escoja un registro primero");
				}VerDatos();
			
				
				
			}

			
		});
		btnNewButton_1_1_1.setBounds(538, 42, 130, 23);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_1_1_1_2 = new JButton("Guardar archivo");
		btnNewButton_1_1_1_2.setFont(new Font("Ebrima", Font.PLAIN, 12));
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Guardar();
				
				
				
				
			}
		});
		btnNewButton_1_1_1_2.setBounds(416, 182, 231, 23);
		contentPane.add(btnNewButton_1_1_1_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(55, 229, 607, 209);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Identificaci\u00F3n", "Nombre", "Apellido"
			}
		));
		scrollPane.setViewportView(table);
		
		txtcontacto = new JTextField();
		txtcontacto.setBounds(254, 507, 187, 20);
		contentPane.add(txtcontacto);
		txtcontacto.setColumns(10);
		
		JTextPane txtpnParaBuscarUn = new JTextPane();
		txtpnParaBuscarUn.setBackground(new Color(51, 153, 204));
		txtpnParaBuscarUn.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		txtpnParaBuscarUn.setText("Para buscar un contacto, ingrese el número de identificación de dicha persona y presione el boton \"Buscar contacto\"");
		txtpnParaBuscarUn.setBounds(55, 448, 473, 52);
		contentPane.add(txtpnParaBuscarUn);
	}
	
	
	private void MostrarPersona(Persona aux) {
		String Mat[][]=new String[1][3];

	
			
			Mat[0][0]=Integer.toString(aux.getIdentificacion());
			Mat[0][1]=aux.getNombre();
			Mat[0][2]=aux.getApellido();
		
		table.setModel(new DefaultTableModel(
				Mat,
				new String[] {
					"Identificaci\u00F3n", "Nombre", "Apellido"	
					}));
		
	}

	private void VerDatos() {
		String Mat[][]=new String[Lis.size()][3];
		Persona aux;
		for(int i=0; i<Lis.size();i++) {
			aux=Lis.get(i);
			Mat[i][0]=Integer.toString(aux.getIdentificacion());
			Mat[i][1]=aux.getNombre();
			Mat[i][2]=aux.getApellido();
		}
		table.setModel(new DefaultTableModel(
				Mat,
				new String[] {
					"Identificaci\u00F3n", "Nombre", "Apellido"	
					}));
	}
	public void Cargar() {
		File Archivo = new File("Contactos.txt");
		FileReader Leer;
		BufferedReader Almacen;
		
		Persona per;
		Persona aux=new Persona();
		try {
			Leer = new FileReader(Archivo);
			Almacen = new BufferedReader(Leer);
			per= aux.cargar(Almacen);
			while(per != null) {
				Lis.add(per);
				per= aux.cargar(Almacen);
			}
			Almacen.close();
			Leer.close();
			
	}catch(Exception e) {
		
	}

	}
	private void Guardar() {
		File file = new File("Contactos.txt");
		PrintWriter Escribe;
		if(!file.exists()) {
			try {file.createNewFile();
			
				
			}catch(Exception e) {
				
			}
		}
			try {
				Persona aux;
				Escribe = new PrintWriter(file, "utf-8");
				for (int i=0;i<Lis.size();i++) {
					aux=Lis.get(i);
					aux.Guardar(Escribe);
					System.out.println("er");
					
				}Escribe.close();
			
				
			}catch(Exception e) {
		}
		}
	
	private void initComponents() {
		// TODO Auto-generated method stub
		
	}
}

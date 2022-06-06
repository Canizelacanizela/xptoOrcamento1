package visao;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controle.ProcessOrca;
import modelo.Orca;

public class TelaForm extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel painel;
	private JLabel ID, fornecedor, produto, preco, infor;
	private JTextField tfID, tfornecedor, tfproduto, tfpreco;
	private JButton cadastrar, buscar, alterar, excluir;
	private JTextArea lista;
	private int autoId = ProcessOrca.orca.size() + 1;
	private String texto = "";

	TelaForm() {

		setTitle("Orçamentos");
		setBounds(500, 300, 600, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		painel = new JPanel();
		setContentPane(painel);
		setLayout(null);

		ID = new JLabel("ID:");
		ID.setBounds(98, 55, 120, 30);
		tfID = new JTextField(String.format("%d", autoId));
		tfID.setEditable(false);
		tfID.setBounds(140, 55, 150, 30);
		//
		fornecedor = new JLabel("Fornecedor:");
		fornecedor.setBounds(60, 94, 120, 30);
		tfornecedor = new JTextField();
		tfornecedor.setBounds(140, 95, 230, 30);
		//
		produto = new JLabel("Produto:");
		produto.setBounds(75, 130, 120, 30);
		tfproduto = new JTextField();
		tfproduto.setBounds(140, 130, 230, 30);
		//
		preco = new JLabel("Preço:");
		preco.setBounds(90, 164, 120, 30);
		tfpreco = new JTextField();
		tfpreco.setBounds(140, 165, 230, 30);
		//
		infor = new JLabel("ID|Fornecedor|Produto|Preço");
		infor.setBounds(45, 230, 200, 30);
		lista = new JTextArea();
		lista.setBounds(33, 255, 520, 145);
		lista.setEditable(false);
		lista.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
		PreencherTabela();
		//
		//
		cadastrar = new JButton("Cadastrar");
		cadastrar.setBounds(430, 65, 125, 30);
		//
		buscar = new JButton("Buscar");
		buscar.setBounds(430, 105, 125, 30);
		//
		alterar = new JButton("Alterar");
		alterar.setBounds(430, 145, 125, 30);
		//
		excluir = new JButton("Excluir");
		excluir.setBounds(430, 184, 125, 30);

		cadastrar.addActionListener(this);
		buscar.addActionListener(this);
		alterar.addActionListener(this);
		excluir.addActionListener(this);

		painel.add(ID);
		painel.add(tfID);
		painel.add(fornecedor);
		painel.add(tfornecedor);
		painel.add(produto);
		painel.add(tfproduto);
		painel.add(preco);
		painel.add(tfpreco);
		painel.add(infor);
		painel.add(lista);
		//
		// Botão
		painel.add(cadastrar);
		painel.add(buscar);
		painel.add(alterar);
		painel.add(excluir);

	}

	private void cadastrar() {
		if (tfornecedor.getText().length() != 0 && tfproduto.getText().length() != 0
				&& tfpreco.getText().length() != 0) {

			ProcessOrca.orca.add(new Orca(autoId, tfornecedor.getText(), tfproduto.getText(),
					Double.parseDouble(tfpreco.getText().replace(",", "."))));

			autoId++;
			PreencherTabela();
			limparCampos();
			ProcessOrca.salvar();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
	}

	private void PreencherTabela() {
		texto = "";
		for (Orca o : ProcessOrca.orca) {
			texto += o.toString() + "\n";
		}
		lista.setText(texto);
	}

	private void limparCampos() {
		tfID.setText(String.format("%d", autoId));
		tfornecedor.setText(null);
		tfproduto.setText(null);
		tfpreco.setText(null);

	}

//
//	
	private void buscar() {
		String entrada = JOptionPane.showInputDialog(this, "Digite o ID da venda:");

		boolean isNumeric = true;
		if (entrada != null) {
			for (int i = 0; i < entrada.length(); i++) {
				if (!Character.isDigit(entrada.charAt(i))) {
					isNumeric = false;
				}
			}
		} else {
			isNumeric = false;
		}
		if (isNumeric) {
			int id = Integer.parseInt(entrada);
			boolean find = false;
			for (Orca o : ProcessOrca.orca) {
				if (o.getId() == id) {

					find = true;
					int indice = ProcessOrca.orca.indexOf(o);
					tfID.setText(ProcessOrca.orca.get(indice).getId("s"));
					tfornecedor.setText(ProcessOrca.orca.get(indice).getFornecedor());
					tfproduto.setText(ProcessOrca.orca.get(indice).getProduto());
					tfpreco.setText(ProcessOrca.orca.get(indice).getPreco("s"));
					ProcessOrca.salvar();
					cadastrar.setEnabled(false);
					alterar.setEnabled(true);
					excluir.setEnabled(true);
					break;
				}
			}
			if (!find) {
				JOptionPane.showMessageDialog(this, "Venda não encontrada.");
			}
		}
	}

//
//
	private void alterar() {
		int id = Integer.parseInt(tfID.getText());
		int indice = -1;

		for (Orca o : ProcessOrca.orca) {
			if (o.getId() == id) {
				indice = ProcessOrca.orca.indexOf(o);
			}
		}
		if (tfornecedor.getText().length() != 0 && tfproduto.getText().length() != 0
				&& tfpreco.getText().length() != 0) {

			ProcessOrca.orca.set(indice, new Orca(autoId, tfornecedor.getText(), tfproduto.getText(),
					Double.parseDouble(tfpreco.getText().replace(",", "."))));

			PreencherTabela();
			limparCampos();
		} else {
			JOptionPane.showMessageDialog(this, "Favor preencher todos os campos.");
		}
		cadastrar.setEnabled(true);
		alterar.setEnabled(true);
		excluir.setEnabled(true);
		tfID.setText(String.format("%d", ProcessOrca.orca.size() + 1));
		ProcessOrca.salvar();

	}

//
//
	private void excluir() {
		int id = Integer.parseInt(tfID.getText());
		Orca orca = new Orca(id);
		int indice = ProcessOrca.orca.indexOf(orca);
		ProcessOrca.orca.remove(indice);
		PreencherTabela();
		limparCampos();
		cadastrar.setEnabled(true);
		buscar.setEnabled(true);
		excluir.setEnabled(true);
		ProcessOrca.salvar();
		tfID.setText(String.format("%d", ProcessOrca.orca.size()+ 1));
		

	}

	public static void main(String[] args) {
		TelaForm form = new TelaForm();
		form.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cadastrar) {
			cadastrar();
		}
		if (e.getSource() == buscar) {
			buscar();
		}
		if (e.getSource() == alterar) {
			alterar();
		}
		if (e.getSource() == excluir) {
			excluir();
		}
	}
}
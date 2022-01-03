package br.com.alura.gerenciador.servelet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Banco {
	private static List<Empresa> lista = new ArrayList<Empresa>();
	private static Integer id = 1;
	static {
		Empresa empresa = new Empresa();
		empresa.setNome("Alura");
		empresa.setId(Banco.id++);
		Empresa empresa2 = new Empresa();
		empresa2.setNome("Caelum");
		empresa2.setId(Banco.id++);
		Banco.lista.add(empresa);
		Banco.lista.add(empresa2);
	}

	public void adiciona(Empresa empresa) {
		empresa.setId(Banco.id++);
		lista.add(empresa);
	}

	public List<Empresa> getEmpresas() {
		return Banco.lista;
	}

	public void remove(Integer id2) {

		Iterator<Empresa> it = lista.iterator();
		while (it.hasNext()) {
			Empresa emp = it.next();
			if (emp.getId() == id2) {
				it.remove();;
			}
		}

		
//		Codígo abaixo vai estourar o Array
//		for (Empresa empresa : lista) {
//			if (empresa.getId() == id2) {
//				lista.remove(empresa);
//			}
//		}

	}

	public Empresa buscaEmpresaId(Integer id2) {
		for (Empresa empresa : lista) {
			if (empresa.getId()==id2) {
				return empresa;
			}
		}
		return null;
	}

	public void altera(Empresa emp) {
		for (Empresa empresa : lista) {
			if (empresa.getId()==emp.getId()) {
				empresa.setNome(emp.getNome());
				empresa.setDataAbertura(emp.getDataAbertura());
			}
		}
		
	}


}

package br.com.estudo.padroescomportamentais.visitor;

import java.util.ArrayList;
import java.util.List;

import br.com.estudo.padroescomportamentais.visitor.interf.AtualizadorDeFuncionario;
import br.com.estudo.padroescomportamentais.visitor.models.Departamento;
import br.com.estudo.padroescomportamentais.visitor.models.Funcionario;
import br.com.estudo.padroescomportamentais.visitor.models.Gerente;
import br.com.estudo.padroescomportamentais.visitor.models.Telefonista;

/**
 * Objetivo:
 * Permitir atualizações específicas em uma coleção de objetos de acordo com o tipo particular de cada objeto atualizado.
 * 
 * Exemplo prático:
 * Estamos desenvolvendo um sistema bancário no qual os funcionários devem ser classificados de
 * acordo com o cargo que eles possuem. Para modelar as diferenças e semelhanças relativas a cada
 * cargo decidimos aplicar o conceito de herança na nossa modelagem.
 * Todo funcionário do banco está associado a um departamento. Podemos aplicar agregação para implementar essa condição.
 * De tempos em tempos, os salários dos funcionários são reajustados. O reajuste do salário de
 * um funcionário depende basicamente do cargo que ele possui. Além dessa, outras alterações nas
 * informações dos objetos que representam os funcionários podem ocorrer de tempos em tempos.
 * Podemos implementar o reajuste ou qualquer outra tarefa de atualização dos dados dos funcionários
 * através de classes especializadas. Inclusive, podemos definir uma interface para padronizar a
 * interação com essas classes.
 * 
 * @author daniel.oliveira
 */
public class TestaAtualizadorSalarial {

	public static void main(final String[] args) {
		List<Departamento> lista = new ArrayList<Departamento>();
		
		Departamento departamento = new Departamento(" Departamento 1");
		Gerente gerente = new Gerente(" Gerente 1", 1500, " 1234 ");
		Telefonista telefonista = new Telefonista(" Telefonista ", 1000, 2);
		departamento.getFuncionarios().add(gerente);
		departamento.getFuncionarios().add(telefonista);

		lista.add(departamento);

		Departamento departamento2 = new Departamento(" Departamento 2");
		Gerente gerente2 = new Gerente(" Gerente 2", 1800, " 1234 ");
		Gerente gerente3 = new Gerente(" Gerente 3", 1800, " 1234 ");
		Telefonista telefonista2 = new Telefonista(" Telefonista2 ", 1200, 1);
		departamento2.getFuncionarios().add(gerente2);
		departamento2.getFuncionarios().add(gerente3);
		departamento2.getFuncionarios().add(telefonista2);

		lista.add(departamento2);

		AtualizadorDeFuncionario atualizador = new AtualizadorSalarial();

		for (Departamento d : lista) {
			d.aceita(atualizador);
		}

		for (Departamento d : lista) {
			for (Funcionario f : d.getFuncionarios()) {
				System.out.println(" Nome : " + f.getNome() + " - Salário : " + f.getSalario());
			}
		}
	}

}

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>();

        // 3.1 - Inserir funcionários
        funcionarios.add(new Funcionario(1,"João", new BigDecimal("2500.00"), "Gerente", LocalDate.of(1985, 5, 10)));
        funcionarios.add(new Funcionario(2,"Maria", new BigDecimal("1800.00"), "Assistente", LocalDate.of(1990, 9, 25)));
        funcionarios.add(new Funcionario(3,"Pedro", new BigDecimal("3000.00"), "Analista", LocalDate.of(1982, 3, 15)));
        funcionarios.add(new Funcionario(4,"Ana", new BigDecimal("2200.00"), "Assistente", LocalDate.of(1992, 11, 5)));
        funcionarios.add(new Funcionario(5,"José", new BigDecimal("3500.00"), "Gerente", LocalDate.of(1978, 8, 2)));

        // 3.2 - Remover funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com suas informações
        System.out.println("Lista de Funcionários:");
        System.out.println(funcionarios);

        // 3.4 - Aumentar 10% dos salários dos funcionários
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

        // 3.5 - Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, listaFuncionarios) -> {
            System.out.println("Função: " + funcao);
            listaFuncionarios.forEach(System.out::println);
        });

        // 3.8 - Imprimir funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        int mesAniversario1 = 10;
        int mesAniversario2 = 12;
        funcionarios.stream()
                .filter(funcionario ->
                        funcionario.getDataNascimento().getMonthValue() == mesAniversario1 ||
                                funcionario.getDataNascimento().getMonthValue() == mesAniversario2)
                .forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionário com maior idade:");
        Comparator<Funcionario> idadeComparator = Comparator.comparing(
                funcionario -> funcionario.getDataNascimento().toEpochDay());
        Funcionario funcionarioMaisVelho = funcionarios.stream()
                .min(idadeComparator)
                .orElse(null);
        if (funcionarioMaisVelho != null) {
            long idadeEmAnos = LocalDate.now().toEpochDay() - funcionarioMaisVelho.getDataNascimento().toEpochDay();
            System.out.println("Nome: " + funcionarioMaisVelho.getNome() + ", Idade: " + (idadeEmAnos / 365) + " anos");
        }

        // 3.10 - Imprimir lista de funcionários por ordem alfabética
        System.out.println("\nLista de Funcionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários
        System.out.println("\nTotal dos salários dos funcionários:");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: " + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários em múltiplos do salário mínimo:");
        funcionarios.forEach(funcionario -> {
            BigDecimal multiplicador = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + " ganha " + multiplicador + " salários mínimos.");
        });
    }

}

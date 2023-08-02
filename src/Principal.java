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
        funcionarios.add(new Funcionario(2,"Maria", new BigDecimal("2009.44"), "Operador", LocalDate.of(2000, 10, 18)));
        funcionarios.add(new Funcionario(1,"João", new BigDecimal("2284.38"), "Operador", LocalDate.of(1990, 05, 12)));
        funcionarios.add(new Funcionario(3,"Caio", new BigDecimal("9836.14"), "Coordenador", LocalDate.of(1961, 05, 02)));
        funcionarios.add(new Funcionario(4,"Miguel", new BigDecimal("19119.88"), "Diretor", LocalDate.of(1988, 10, 14)));
        funcionarios.add(new Funcionario(5,"Alice", new BigDecimal("2234.68"), "Recepcionista", LocalDate.of(1995, 01, 05)));
        funcionarios.add(new Funcionario(6,"Heitor", new BigDecimal("1582.72"), "Operador", LocalDate.of(1999, 11, 19)));
        funcionarios.add(new Funcionario(7,"Arthur", new BigDecimal("4071.84"), "Contador", LocalDate.of(1993, 03, 31)));
        funcionarios.add(new Funcionario(8,"Laura", new BigDecimal("3017.45"), "Gerente", LocalDate.of(1994, 07, 8)));
        funcionarios.add(new Funcionario(9,"Heloísa", new BigDecimal("1606.85"), "Eletricista", LocalDate.of(2003, 05, 24)));
        funcionarios.add(new Funcionario(10,"Helena", new BigDecimal("2799.93"), "Gerente", LocalDate.of(1996, 9, 02)));

        // 3.2 - Remover funcionário "João" da lista
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com suas informações
        System.out.println("Lista de Funcionários:");
        System.out.println(funcionarios);

        // 3.4 - Aumentar 10% dos salários dos funcionários
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(new BigDecimal("1.1"))));

        // 3.5 - Agrupar funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir funcionários agrupados por função
        System.out.println("\nFuncionários agrupados por função:");
        funcionariosFuncao.forEach((funcao, listaFuncionarios) -> {
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
        // funcionarios.forEach(funcionario -> {
        //     BigDecimal multiplicador = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
        //     System.out.println(funcionario.getNome() + " ganha " + multiplicador + " salários mínimos.");
        // });

        funcionarios.forEach(funcionario ->{
                   BigDecimal[]  salarioQnt = funcionario.getSalario().divideAndRemainder(salarioMinimo);
                   System.out.println(salarioQnt);
        });

        
    }

}

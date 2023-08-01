import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Objects;

public class Funcionario extends Pessoa {

    private Integer id;
    private String funcao;
    private BigDecimal salario;
    public Funcionario (){};
    public Funcionario( Integer id, String funcao, BigDecimal salario, String nome, LocalDate dataNascimento){
        super(nome, dataNascimento);
        this.id = id;
        this.funcao = funcao;
        this.salario = salario;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }
    public BigDecimal getSalario() {
        DecimalFormat df = new DecimalFormat("#,###.00");

        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getSalarioFormatado() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(salario);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario funcionario = (Funcionario) o;
        return Objects.equals(id, funcionario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", funcao='" + funcao + '\'' +
                ", salario=" + salario +
                '}';
    }
}
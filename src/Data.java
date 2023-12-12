/**
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classe para representar a data e hora do jogo.
 */
public class Data implements Serializable{
    /**
     * Data e hora atual do sistema.
     */
    private LocalDateTime dataHoraAtual;
    /**
     * Construtor da classe, não precisa de dados para a sua inicialização.
     * Inicializa a data e hora com o valor atual do sistema.
     */
    public Data(){
        this.dataHoraAtual = LocalDateTime.now();
    }
    /**
     * Define a data e hora atual.
     * 
     * @param data Data e hora atual.
     */
    public void setDataHora(LocalDateTime data){this.dataHoraAtual = data;}
    /**
     * Obtém a data e hora atual.
     * 
     * @return Data e hora atual.
     */
    public LocalDateTime getDataHora(){return this.dataHoraAtual;}
    /**
     * Retorna uma representação da data e hora em formato de String.
     * 
     * @param formato Formato desejado para a sua representação.
     * @return Data e hora formatadas em String.
     */
    public String toString(String formato){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        return this.dataHoraAtual.format(formatter);
    }
}

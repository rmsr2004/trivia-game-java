/**
 * @author Rodrigo Rodrigues (2022233032)@author João Simões (2022236316)
 * @version 1.0
 */

/**
 * A classe principal que só contém o método 'main' para iniciar o jogo POOtrivia.
 * 
 * Este jogo utiliza a classe {@link GUI} para criar e gerenciar a interface gráfica.
 * Os argumentos da linha de comando são usados para fornecer informações de configuração ao jogo.
 * 
 * Para executar o jogo, forneça os seguintes argumentos da linha de comando:
 * <ul>
 *   <li> args[0]: Ficheiro de texto que contém as perguntas todas. </li>
 *   <li> args[1]: Diretório para guardar os jogos. </li>
 * </ul>
 * 
 * Exemplo de uso: java -cp bin POOtrivia /caminho/para/perguntas /caminho/para/diretorio
 *
 */
public class POOtrivia{
    /**
     * Método principal que inicia o jogo POOtrivia.
     * 
     * @param args Argumentos da linha de comando:
     * <ul>
     *    <li> args[0]: Ficheiro de texto que contém as perguntas todas. </li>
     *    <li> args[1]: Diretório para guardar os jogos. </li>
     * </ul>
     */
    public static void main(String[] args){
        GUI gui = new GUI(args[0], args[1]);
        gui.iniciar(); // iniciar jogo
    }
}
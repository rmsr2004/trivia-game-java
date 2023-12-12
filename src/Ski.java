/**
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */
import java.util.ArrayList;

/**
 * Classe para representar categoria Ski.
 */
public class Ski extends Desporto{
    /**
     * Construtor da classe Ski.
     * 
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta(Fácil, Difícil, null).
     * @param listaOpcoes Lista de opções de resposta da pergunta.
     * @param resposta Resposta da pergunta.
     */
    public Ski(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        super(textoPergunta,dificuldade, listaOpcoes, resposta);
    }
    /**
     * Método que adiciona à pontuação da pergunta a majoração do Ski(pontuação*2).
     * 
     * @return Pontuação da pergunta.
     */
    public int calcularPontuacao(){
        return super.calcularPontuacao() * 2;
    }
    /**
     * Método para dificultar ou facilitar a pergunta, caso seja esse o caso.
     * As perguntas de Ski não alteram a dificuldade da pergunta.
     * 
     * @param indiceAtual número da questão do jogo(entre 0 e 5)
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro
     */
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){return;}
    /**
     * Método que representa a categoria Ski em uma String.
     * 
     * @return String formatada com informações da categoria Ski.
     */
    @Override
    public String toString(){
        return "Categoria: Ski\n" + super.toString();
    }
}

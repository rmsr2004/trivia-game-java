/**
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */
import java.util.ArrayList;

/**
 * Classe que representa a categoria Natação.
 * 
 * <p>Esta classe estende a classe {@link Pergunta}.</p>
 * 
 * @see Pergunta
 */
public class Natacao extends Desporto{
    /**
     * Construtor da classe Natação.
     * 
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta(Fácil, Difícil, null).
     * @param listaOpcoes Lista de opções de resposta da pergunta.
     * @param resposta Resposta da pergunta.
     */
    public Natacao(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        super(textoPergunta, dificuldade, listaOpcoes, resposta);
    }
    /**
     * Método para dificultar ou facilitar a pergunta, caso seja esse o caso.
     * As perguntas de natação não alteram a dificuldade da pergunta.
     * 
     * @param indiceAtual número da questão do jogo(entre 0 e 5).
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro.
     */
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){return;}
    /**
     * Método que adiciona à pontuação da pergunta a majoração do Natação(pontuação+10).
     * 
     * @return Pontuação da pergunta.
     */
    @Override
    public int calcularPontuacao(){
        return super.calcularPontuacao() + 10;
    }
    /**
     * Método que representa a categoria Natação em uma String.
     * 
     * @return String formatada com informações da categoria Natação.
     */
    @Override
    public String toString(){
        return "Categoria: Natação\n" + super.toString();
    }
}
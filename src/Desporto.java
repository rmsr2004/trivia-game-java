/** 
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * Classe que representa a categoria Desporto.
 * 
 * <p>Esta classe estende a classe {@link Pergunta}.</p>
 * 
 * @see Pergunta
 */
public class Desporto extends Pergunta{
    /**
     * Constutor da classe Desporto.
     * 
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta(Fácil, Difícil, null).
     * @param listaOpcoes Lista de opções de resposta da pergunta.
     * @param resposta Resposta da pergunta.
     */
    public Desporto(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        super(textoPergunta, dificuldade, listaOpcoes, resposta);
    }
    /**
     * Método para dificultar ou facilitar a pergunta, caso seja esse o caso.
     * As perguntas de desporto não alteram a dificuldade da pergunta.
     * 
     * @param indiceAtual número da questão do jogo(entre 0 e 5).
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro.
     */
    @Override
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){return;}
    /**
     * Método que adiciona á pontuação da pergunta a majoração de Desporto(pontuação+3).
     * 
     * @return Pontuação da pergunta.
     */
    @Override
    public int calcularPontuacao(){
        return super.calcularPontuacao() + 3;
    }
    /**
     * Retorna uma representação de string da categoria Desporto.
     * 
     * @return Uma representação de string da categoria Desporto.
     */
    @Override
    public String toString(){
        return super.toString();
    }
}
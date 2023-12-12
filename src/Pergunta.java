/**
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Classe para gerir cada pergunta.
 */
public class Pergunta implements Serializable{
    /**
     * Texto da pergunta.
     */
    protected String textoPergunta;
    /**
     * Dificuldade da pergunta(Fácil, Difícil, null).
     */
    protected String dificuldade;
    /**
     * Lista de opções de resposta.
     */
    protected ArrayList<String> listaOpcoes;
    /**
     * Resposta correta da pergunta.
     */
    protected String resposta;
    /**
     * Construtor da classe Pergunta, recebe dados para a inicialização dos atributos.
     * 
     * @param textoPergunta Pergunta.
     * @param dificuldade Dificuldade da pergunta.
     * @param listaOpcoes Lista de opcões de resposta.
     * @param resposta Resposta correta da pergunta.
     */
    public Pergunta(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        this.textoPergunta = textoPergunta;
        this.dificuldade = dificuldade;
        this.listaOpcoes = listaOpcoes;
        this.resposta = resposta;
    }
    /**
     * Obtém o conteúdo da pergunta.
     * 
     * @return Conteúdo da pergunta.
     */
    public String getTextoPergunta(){return textoPergunta;}
    /**
     * Define o conteúdo da pergunta.
     * @param pergunta Conteúdo da pergunta.
     */
    public void setTextoPergunta(String pergunta){this.textoPergunta = pergunta;}
    /**
     * Obtém a dificuldade a pergunta.
     * 
     * @return Dificuldade da pergunta.
     */
    public String getDificuldade(){return dificuldade;}
    /**
     * Define a dificuldade da pergunta.
     * @param dificuldade Dificuldade da pergunta.
     */
    public void setDificuldade(String dificuldade){this.dificuldade = dificuldade;}
    /**
     * Obtém a lista de opções de resposta.
     * 
     * @return Lista de opções de resposta.
     */
    public ArrayList<String> getListaOpcoes(){return listaOpcoes;}
    /**
     * Define a lista de opções de resposta.
     * 
     * @param listaOpcoes Lista de opções de resposta.
     */
    public void setListaOpcoes(ArrayList<String> listaOpcoes){this.listaOpcoes = listaOpcoes;}
    /**
     * Obtém a resposta da pergunta.
     * 
     * @return Resposta da pergunta.
     */
    public String getResposta(){return resposta;}
    /**
     * Define a resposta da pergunta.
     * 
     * @param resposta Resposta da pergunta.
     */
    public void setResposta(String resposta){this.resposta = resposta;}
    /**
     * Método para dificultar ou facilitar a pergunta, caso seja esse o caso.
     * 
     * @param indiceAtual número da questão do jogo(entre 0 e 5)
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro
     */
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){return;}
    /**
     * Método para sortear a lista de opções.
     */
    public void sortearOpcoes(){
        Collections.shuffle(listaOpcoes, new Random());
    }
    /**
     * Método para calcular a pontuação da pergunta.
     * @return Pontuação da pergunta(= 5).
     */
    public int calcularPontuacao(){
        return 5;
    }
    /**
     * Retorna uma representação de string da pergunta.
     * 
     * @return Uma representação de string da pergunta.
     */
    @Override
    public String toString(){
        return "Pergunta: " + textoPergunta + "\nDificuldade: " + dificuldade + "\n" + "Opcões: " + String.join("-", listaOpcoes) +
               "\nResposta: " + resposta;  
    }
}
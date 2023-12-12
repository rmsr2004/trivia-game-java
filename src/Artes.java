/** 
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe que representa a categoria Artes.
 * 
 * <p>Esta classe estende a classe {@link Pergunta}.</p>
 * 
 * @see Pergunta
 */
public class Artes extends Pergunta{
    /**
     * Construtor da classe Artes.
     * 
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta(Fácil, Difícil, null).
     * @param listaOpcoes Lista de opções de resposta da pergunta.
     * @param resposta Resposta da pergunta.
     */
    public Artes(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        super(textoPergunta, dificuldade, listaOpcoes, resposta);
    }
    /**
     * Método para facilitar a pergunta, caso seja esse o caso.
     * Se a pergunta for apresentada antes da 3ª pergunta, então altera-se a lista de opções com 3 opções de resposta.
     * Caso contrário não se faz nada.
     * 
     * A nova lista de opções é gerada aleatoriamente e contém sempre a resposta da pergunta.
     * 
     * @param indiceAtual número da questão do jogo(entre 0 e 5).
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro.
     */
    @Override
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){
        // depois da 3ª pergunta
        if(indiceAtual >= 2){return;}

        // antes da 3ª pergunta
        ArrayList<String> subListaOpcoes; // nova lista de opções
        ArrayList<Integer> indices; // guardar indíces das perguntas já inseridas na nova lista de opções
                                    // para não gerar opções repetidas

        Random random = new Random();

        // enquanto a nova lista de opções de resposta não contiver a resposta, repete-se todo o processo
        do{
            subListaOpcoes = new ArrayList<>();
            indices = new ArrayList<>();
            for(int i = 0; i < 3; i++){
                int indicePergunta = random.nextInt(listaOpcoes.size());
                // verificar se a opção já tinha sido introduzida na nova lista anteriormente
                while(indiceJaInserido(indicePergunta, indices)){
                    indicePergunta = random.nextInt(listaOpcoes.size());
                }
                // garantiu - se que a opção não é repetida
                indices.add(indicePergunta);
                subListaOpcoes.add(this.listaOpcoes.get(indicePergunta));
            }
        } while(!verificarContemResposta(subListaOpcoes));
        
        setListaOpcoes(subListaOpcoes);
    }
    /**
     * Verifica se a lista de opções de resposta contém a resposta da pergunta.
     * 
     * @param listaOpcoes Lista de opções de resposta.
     * @return Verdadeiro se a resposta estiver na lista de opções, Falso caso contrário.
     */
    private boolean verificarContemResposta(ArrayList<String> listaOpcoes){
        if(listaOpcoes.isEmpty()){return false;}
        
        for(String opcao : listaOpcoes){
            if(opcao.equals(this.resposta)){
                return true;
            }
        }
        return false;
    }
    /**
     * Método para verificar se o indíce já se encontra inserido no array de indíces.
     * Garante que não haja repetições de opções de resposta.
     * 
     * @param indice Indíce que está para ser introduzido.
     * @param indices Array que contém os indices já inseridos anteriormente.
     * @return Verdadeiro caso o indíce já se encontre no array, Falso caso contrário.
     */
    private boolean indiceJaInserido(int indice, ArrayList<Integer> indices){
        if(indices.size() == 0){
            return false;
        }
        for(int i : indices){
            if(i == indice){
                return true;
            }
        }
        return false;
    }
    /**
     * Adiciona à pontuação da pergunta, a majoração da categoria Artes(pontuação*10).
     * 
     * @return pontuação da pergunta com a sua majoração.
     */
    @Override
    public int calcularPontuacao(){
        return super.calcularPontuacao() * 10;
    }
    /**
     * Retorna uma representação de string da categoria Artes.
     * 
     * @return Uma representação de string da categoria Artes.
     */
    @Override
    public String toString(){
        return "Categoria: Artes\n" + super.toString();
    }
}

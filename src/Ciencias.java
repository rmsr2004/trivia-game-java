/** 
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

import java.util.ArrayList;

/**
 * Classe que representa a categoria Ciências.
 *
 * <p>Esta classe estende a classe {@link Pergunta}.</p>
 * 
 * @see Pergunta
 */
public class Ciencias extends Pergunta{
    /**
     * Construtor da classe Ciências.
     * 
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta(Fácil, Difícil, null).
     * @param listaOpcoes Lista de opções de resposta da pergunta.
     * @param resposta Resposta da pergunta.
     */
    public Ciencias(String textoPergunta, String dificuldade, ArrayList<String> listaOpcoes, String resposta){
        super(textoPergunta, dificuldade, listaOpcoes, resposta);
    }
    /**
     * Método para facilitar ou dificultar a pergunta, caso seja esse o caso.
     * Caso a pergunta seja apresentada antes da 3ª pergunta do jogo e seja "Difícil", atualiza a pergunta para ser "Fácil",
     * vai se buscar ao Array que contém as perguntas todas, a pergunta do indice anterior ao da pergunta atual.
     * 
     * Caso a pergunta seja apresentada depois da 3ª pergunta do jogo e seja "Fácil",  a pergunta para ser "Difícil",
     * vai se buscar ao Array que contém as perguntas todas, a pergunta do indíce a seguir ao da pergunta atual.
     *  
     * @param indiceAtual número da questão do jogo(entre 0 e 5).
     * @param listaPerguntas Todas as perguntas que se encontram no ficheiro.
     */
    @Override
    public void mudarDificuldade(int indiceAtual, ListaPerguntas listaPerguntas){
        String dificuldade = getDificuldade(); // dificuldade da pergunta
        String texto = getTextoPergunta(); // texto da pergunta

        // antes da 3ª pergunta
        if(indiceAtual < 2){
            if(dificuldade.equals("Difícil")){ // antes da 3ª pergunta, a pergunta tem de ser "Fácil"
                // Calcular indice da pergunta na listaPerguntas
                int index = listaPerguntas.getIndicePorPerguntaEdificuldade(texto, dificuldade); // indice no array das perguntas todas 

                // Atualizar pergunta com os dados da anterior(pergunta correspondente do tipo Fácil)
                setTextoPergunta(listaPerguntas.getPergunta(index-1).getTextoPergunta()); // Atualizar o conteúdo da pergunta
                setDificuldade("Fácil"); // Atualizar dificuldade
                setResposta(listaPerguntas.getPergunta(index-1).getResposta()); // Atualizar resposta
                setListaOpcoes(listaPerguntas.getPergunta(index-1).getListaOpcoes()); // Atualiza as opções de resposta da pergunta anterior
            }
            // Caso a pergunta seja "Fácil", nada a fazer

        // depois da 3ª pergunta, processo análogo ao anterior
        } else{
            if(dificuldade.equals("Fácil")){ // depois da 3ª pergunta, a pergunta tem de ser "Difícil"
                int index = listaPerguntas.getIndicePorPerguntaEdificuldade(texto, dificuldade);

                setTextoPergunta(listaPerguntas.getPergunta(index+1).getTextoPergunta());
                setDificuldade("Difícil");
                setResposta(listaPerguntas.getPergunta(index+1).getResposta());
                setListaOpcoes(listaPerguntas.getPergunta(index+1).getListaOpcoes());
            }
            // Caso a pergunta seja "Difícil", nada a fazer
        }
    }
    /**
     * Método que adiciona à pontuação da pergunta, a majoração da categoria Ciências(pontuação+5).
     * 
     * @return pontuação da pergunta com a sua majoração.
     */
    @Override
    public int calcularPontuacao(){
        return super.calcularPontuacao() + 5;
    }
    /**
     * Retorna uma representação de string da categoria Ciências.
     * 
     * @return Uma representação de string da categoria Ciências.
     */
    @Override
    public String toString(){
        return "Categoria: Ciências\n" + super.toString();
    }
}

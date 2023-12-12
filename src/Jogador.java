/** 
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Classe que representa o Jogador.
 */
public class Jogador implements Serializable, Comparable<Jogador>{
    /**
     * Nome do jogador.
     */
    private String nome;
    /**
     * Data e hora atual do sistema.
     */
    private Data data;
    /**
     * Lista das perguntas que o jogador acertou.
     */
    private ListaPerguntas perguntasCertas;
    /**
     * Lista das perguntas que o jogador errou.
     */
    private ListaPerguntas perguntasErradas;
    /**
     * Construtor da classe Jogador, não necessita de dados para ser inicializada.
     * Inicia os ArrayLists.
     */
    public Jogador(){
        this.perguntasCertas = new ListaPerguntas();
        this.perguntasErradas = new ListaPerguntas();
    }
    /**
     * Obtém o nome do Jogador.
     * 
     * @return Nome do jogador.
     */
    public String getNome(){return nome;}
    /**
     * Define o nome do jogador.
     * 
     * @param nome Nome do jogador.
     */
    public void setNome(String nome){this.nome = nome;}
    /**
     * Obtém a data e hora em que o jogador jogou.
     * 
     * @return Data e hora do jogo.
     */
    public Data getData(){return data;}
    /**
     * Define a data e hora.
     *  
     * @param data Data e hora.
     */
    public void setData(Data data){this.data = data;}
    /**
     * Obtém a lista de perguntas que o jogador acertou.
     * 
     * @return Lista das perguntas que o jogador acertou.
     */
    public ListaPerguntas getPerguntasCertas(){return perguntasCertas;}
    /**
     * Define a list das perguntas que o jogador acertou.
     * 
     * @param perguntasCertas Lista das perguntas que o jogador acertou.
     */
    public void setPerguntasCertas(ListaPerguntas perguntasCertas){this.perguntasCertas = perguntasCertas;}
    /**
     * Obtém a lista de perguntas que o jogador errou.
     * 
     * @return Lista das perguntas que o jogador errou.
     */
    public ListaPerguntas getPerguntasErradas(){return perguntasErradas;}
    /**
     * Define a Lista das perguntas que o jogador errou.
     * 
     * @param perguntasErradas Lista das perguntas que o jogador errou.
     */
    public void setPerguntasErradas(ListaPerguntas perguntasErradas){this.perguntasErradas = perguntasErradas;}
    /**
     * Calcula a pontuação total do jogador.
     *
     * @return Pontuação geral do jogador.
     */
    public int calcularPontuacao(){
        int pontuacao = 0;
        for(int i = 0; i < perguntasCertas.size(); i++){
            pontuacao += perguntasCertas.getPergunta(i).calcularPontuacao();
        }   
        return pontuacao;
    }
    /**
     * Adiciona a pergunta à lista das perguntas certas.
     * 
     * @param pergunta Pergunta do jogo.
     */
    public void adicionarPerguntaCerta(Pergunta pergunta){
        perguntasCertas.adicionarPergunta(pergunta);
    }
    /**
     * Adiciona a pergunta à lista das perguntas erradas.
     * 
     * @param pergunta Pergunta do jogo.
     */
    public void adicionarPerguntaErrada(Pergunta pergunta){
        perguntasErradas.adicionarPergunta(pergunta);
    }
    /**
     * Escreve um ficheiro de objeto do jogador.
     * 
     * @param diretorio Diretório onde os jogos estão guardados.
     */
    public void escreverFicheiroObjeto(String diretorio){
        String nomeFicheiro = diretorio + "pootrivia_jogo_" + getData().toString("yyyyMMddHHmm")
                              + "_" + getIniciaisNome() + ".dat";

        File f = new File(nomeFicheiro);

        try{
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this);

            oos.close();
        } catch(FileNotFoundException e){
            System.out.printf("Erro a criar o ficheiro!\n");
        } catch(IOException e){
            System.out.printf("Erro a escrever o ficheiro!\n");
        }
    }
    /**
     * Retora as iniciais do nome do jogador
     * 
     * @return Iniciais do nome do jogador
     */
    private String getIniciaisNome(){
        String iniciais = "";
        String[] nomes = this.nome.split(" ");
        for(String nome : nomes){
            iniciais += nome.substring(0,1);
        }
        return iniciais.toUpperCase();
    }
    /**
     * Compara a pontuação de outro jogador com este jogador.
     * 
     * @param jogador Outro jogador
     * 
     * @return 1 ou -1
     */
    public int compareTo(Jogador jogador){
        return (jogador.calcularPontuacao() - calcularPontuacao() > 0) ? 1 : -1;
    }
    /**
     * Retorna uma representação de string do jogador.
     * 
     * @return Uma representação de string do jogador.
     */
    @Override
    public String toString(){
        return "Nome: " + this.nome + " - Pontuação: " + calcularPontuacao();
    }
}

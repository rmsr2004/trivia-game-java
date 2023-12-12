/** 
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */
import java.util.ArrayList;
import java.io.*;

/**
 * Classe que gere uma lista de perguntas.
 */
public class ListaPerguntas implements Serializable{
    /**
     * Lista de perguntas.
     */
    private ArrayList<Pergunta> listaPerguntas;
    /**
     * Ficheiro que contém as perguntas.
     */
    private String nomeFicheiro;    
    /**
     * Construtor da classe, precisa do ficheiro de texto.
     * Inicializa o ArrayList e lê o ficheiro de texto.
     * 
     * @param nomeFicheiro Ficheiro de texto das perguntas.
     */
    public ListaPerguntas(String nomeFicheiro){
        this.nomeFicheiro = nomeFicheiro;
        this.listaPerguntas = new ArrayList<>();
        lerFicheiroTexto();
    }
    /**
     * Outro construtor da classe, usado para criar um novo objeto que não necessite de um ficheiro de texto.
     * Inicializa-se o ArrayList.
     */
    public ListaPerguntas(){this.listaPerguntas = new ArrayList<>();}
    /**
     * Define o nome do ficheiro.
     * 
     * @param nomeFicheiro Ficheiro de texto.
     */
    public void setNomeFicheiro(String nomeFicheiro){this.nomeFicheiro = nomeFicheiro;}
    /**
     * Obtém o nome do ficheiro de texto.
     * 
     * @return Nome do ficheiro de texto.
     */
    public String getNomeFicheiro(){return this.nomeFicheiro;}
    /**
     * Define a lista de perguntas.
     * 
     * @param listaPerguntas Lista de perguntas.
     */
    public void setListaPerguntas(ArrayList<Pergunta> listaPerguntas){this.listaPerguntas = listaPerguntas;}
    /**
     * Obtém a lista de perguntas.
     * 
     * @return Lista de perguntas.
     */
    public ArrayList<Pergunta> getListaPerguntas(){return this.listaPerguntas;}
    /**
     * Adiciona uma pergunta à lista de perguntas.
     * 
     * @param pergunta Pergunta a ser adicionada.
     */
    public void adicionarPergunta(Pergunta pergunta){
        listaPerguntas.add(pergunta);
    }
    /**
     * Calcula o tamanho da lista.
     * 
     * @return Tamanho da lista.
     */
    public int size(){
        return listaPerguntas.size();
    }
    /**
     * Obtém a pergunta do indíce dado.
     * 
     * @param indice Indíce.
     * @return Pergunta do indíce dado.
     */
    public Pergunta getPergunta(int indice){
        if(indice < size()){
            return listaPerguntas.get(indice);
        }
        return null;
    }
    /**
     * Obtém o indíce da pergunta dada.
     * 
     * @param pergunta Pergunta.
     * @return Indíce da pergunta dada.
     */
    public int getIndice(Pergunta pergunta){
        return listaPerguntas.indexOf(pergunta);
    }
    /**
     * Obtém o indíce de uma pergunta através do contéudo da pergunta e sua dificuldade .
     *
     * @param textoPergunta Conteúdo da pergunta.
     * @param dificuldade Dificuldade da pergunta.
     * @return Indice da pergunta se for encontrada, caso contrário devolve -1.
     */
    public int getIndicePorPerguntaEdificuldade(String textoPergunta, String dificuldade){
        int i = 0;
        for(Pergunta pergunta : listaPerguntas){
            if(pergunta.getTextoPergunta().equals(textoPergunta) && pergunta.getDificuldade().equals(dificuldade)){
                return i;
            }
            i++;
        }
        return -1;
    }
    /**
     * Lê o ficheiro de texto.
     * Adiciona cada pergunta à lista de perguntas.
     */
    private void lerFicheiroTexto(){
        File f = new File(this.nomeFicheiro);
        if(f.exists() && f.isFile()){
            try{
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);

                String linha;
                while((linha = br.readLine()) != null){
                    // adicionar à lista de perguntas cada linha (= pergunta)
                    Pergunta novaPergunta = lerLinha(linha);
                    adicionarPergunta(novaPergunta);
                }
                
                br.close();
            } catch(FileNotFoundException e){
                System.out.printf("Erro a abrir ficheiro de texto.\n");
            } catch(IOException e){
                System.out.printf("Erro a ler o ficheiro.\n");
            }
        } else{
            System.out.printf("Ficheiro '%s'não existe.\n", this.nomeFicheiro);
        }
    }
    /**
     * Extrai a informação de uma linha e cria um objeto Pergunta.
     * 
     * @param linha Linha do ficheiro de texto (Formato: Categoria-Dificuldade-Pergunta-Opção1,Opção2...-Resposta).
     * @return Pergunta extraída da linha.
     */
    private Pergunta lerLinha(String linha){
        // extrair informação da linha
        String categoria = linha.split("-")[0];
        String dificuldade = linha.split("-")[1];
        String pergunta = linha.split("-")[2];
        String opcoes = linha.split("-")[3];
        String resposta = linha.split("-")[4];

        // definir a lista de opções
        String[] opcoesArray = opcoes.split(",");
        ArrayList<String> listaOpcoes = new ArrayList<>();
        for(int i = 0; i < opcoesArray.length; i++){
            listaOpcoes.add(opcoesArray[i]);
        }
        // atribuição da pergunta 
        Pergunta novaPergunta = null;
        if(categoria.equals("Ciências")){
            novaPergunta = new Ciencias(pergunta, dificuldade, listaOpcoes, resposta);
        }
        if(categoria.equals("Artes")){
            novaPergunta = new Artes(pergunta, dificuldade, listaOpcoes, resposta);
        }
        if(categoria.equals("Ski")){
            novaPergunta = new Ski(pergunta, dificuldade, listaOpcoes, resposta);
        }
        if(categoria.equals("Futebol")){
            novaPergunta = new Futebol(pergunta, dificuldade, listaOpcoes, resposta);
        }
        if(categoria.equals("Natação")){
            novaPergunta = new Natacao(pergunta, dificuldade, listaOpcoes, resposta);
        }
        return novaPergunta;
    }
    /**
     * Retorna uma representação de string da lista de perguntas.
     * 
     * @return Uma representação de string da lista de perguntas.
     */
    @Override
    public String toString(){
        String stringRetorno = "";
        for(Pergunta pergunta : listaPerguntas){
            stringRetorno += pergunta.toString()+ "\n\n";
        }
        return stringRetorno;
    } 
}
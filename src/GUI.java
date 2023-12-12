/**
 * @author Rodrigo Rodrigues
 * @author João Simões
 * @version 1.0
 */

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.*;
 import java.io.*;
 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.List;
 import java.util.Random;
 
 /**
  * Classe para gerir a interface gráfica.
  */
 public class GUI{
     /**
      * Painel. 
      */
     private JPanel panel;
     /**
      * Botão da tela inicial para iniciar o jogo.
      */
     private JButton botaoIniciar;
     /**
      * Botão para submeter a resposta.
      */
     private JButton botaoSubmeter;
     /**
      * Botão para sair do jogo.
      */
     private JButton botaoSair;
     /**
      * Botão para voltar a jogar.
      */
     private JButton botaoVoltarJogar;
     /**
      * Botões das opções de resposta.
      */
     private ButtonGroup opcoesResposta;
     /**
      * Botao para cada opção.
      */
     private JRadioButton botaoOpcao;
     /**
      * Listener dos botões.
      */
     private ButtonListener buttonActionListener;
     /**
      * Lister dos botões das opções de resposta.
      */
     private RadioButtonListener radioButtonActionListener;
     /**
      * Jogador.
      */
     private Jogador jogador;
     /**
      * Lista de todas as perguntas.
      */
     private ListaPerguntas listaPerguntas;
     /**
      * Lista das perguntas do jogo.
      */
     private ListaPerguntas perguntasJogo;
     /**
      * Índice atual.
      */
     private int indiceAtual;
     /**
      * Resposta escolhida de cada pergunta.
      */
     private String opcaoEscolhida;
     /**
      * Ficheiro de texto das perguntas.
      */
     private String ficheiroTexto;
     /**
      * Diretório onde são guardados os jogos.
      */
     private String diretorio;
     /**
      * Construtor da classe Gui.
      * 
      * @param ficheiroTexto Ficheiro de texto das perguntas.
      * @param diretorio Diretório dos jogos guardados.
      */
     public GUI(String ficheiroTexto, String diretorio){
         // Inicialização de atributos importantes do jogo
         this.diretorio = diretorio;
         this.ficheiroTexto = ficheiroTexto;
 
         opcaoEscolhida = null;
         
         jogador = new Jogador();
 
         // listasPerguntas -> contém todas as perguntas do ficheiro "pootrivia.txt"
         listaPerguntas = new ListaPerguntas(this.ficheiroTexto); 
         
         // perguntasJogo -> 5 perguntas geradas aleatoriamente
         perguntasJogo = gerarPerguntasJogo();
 
         indiceAtual = 0;
     } 
     /**
      * Método para iniciar a interface gráfica.
      */
     public void iniciar(){
         // criar frame
         JFrame frame = new JFrame();
         frame.setTitle("PooTrivia");
         frame.setSize(1000, 600);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
         // criar painel
         panel = new JPanel(null);
         
         // Nome do Jogo 
         JLabel pootrivia = new JLabel("PooTrivia");
         pootrivia.setFont(new Font("Courier", Font.BOLD, 50));
         pootrivia.setBounds(390, 100, 400, 50);
 
         // botão para iniciar jogo
         botaoIniciar = new JButton("Iniciar Jogo");
         botaoIniciar.setBounds(450, 400, 100, 50);
 
         // listener do botão
         buttonActionListener = new ButtonListener();
         botaoIniciar.addActionListener(buttonActionListener);
         
         // adicionar elementos ao painel
         panel.add(pootrivia);
         panel.add(botaoIniciar);
 
         // adicionar painel ao frame
         frame.add(panel);
         
         frame.setLocationRelativeTo(null);
         frame.setVisible(true);
     }
     /**
      * Troca de janela, troca de pergunta enquanto existirem perguntas, e depois aparece o top 3.
      * 
      * @param pergunta Pergunta atual do jogo.
      * @param jogador Jogador que está a jogar o jogo.
      */
     private void trocarJanela(Pergunta pergunta, Jogador jogador){
         // enquanto não se chega à 5ª pergunta
         if(indiceAtual < perguntasJogo.size()){
             // limpar painel
             limparPainel(panel);
 
             // altera a dificuldade da pergunta se for preciso
             pergunta.mudarDificuldade(indiceAtual, listaPerguntas);
             
             // Sortear aleatoriamente as opções de resposta
             pergunta.sortearOpcoes();
 
             // Texto da Pergunta
             JLabel textoPergunta = new JLabel(pergunta.getTextoPergunta());
             textoPergunta.setBounds(50, 1, 1000, 100);
             textoPergunta.setFont(new Font("Arial", Font.BOLD, 18));
             
             // Opções de resposta da pergunta
             opcoesResposta = new ButtonGroup();
 
              // listener das opções
             radioButtonActionListener = new RadioButtonListener();
 
             int yPos = 100;
             // adicionar botões das opcões de resposta da pergunta
             for(String opcao : pergunta.getListaOpcoes()){
                 botaoOpcao = new JRadioButton(opcao);
                 botaoOpcao.setFont(new Font("Arial", Font.PLAIN, 14));
                 botaoOpcao.setBounds(70, yPos, 600, 30);
                 opcoesResposta.add(botaoOpcao);
 
                 botaoOpcao.addActionListener(radioButtonActionListener);
 
                 panel.add(botaoOpcao);
 
                 yPos += 40;
             }
 
             // Botão para submeter resposta
             botaoSubmeter = new JButton("Submeter");
             botaoSubmeter.setBounds(450, yPos + 40, 100, 30);
             botaoSubmeter.addActionListener(buttonActionListener);
             
             // adicionar elementos ao painel
             panel.add(textoPergunta);
             panel.add(botaoSubmeter);
 
             panel.revalidate();
 
         } else{ // quando não existirem mais perguntas -> guardar o nome, e imprimir top3
             String nomeJogador; 
 
             // O ciclo só acaba quando o nome for introduzido corretamente
             do{
                 // input do utilizador 
                 nomeJogador = JOptionPane.showInputDialog(null, "Pontuação: " + jogador.calcularPontuacao() + "\nNome: ", "O jogo acabou!", JOptionPane.QUESTION_MESSAGE);
             
                 // se o utilizador clicou em "Cancel"
                 if(nomeJogador == null){
                     // Mostra mensagem de confirmação da ação anterior
                     int option = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende continuar?\nO seu progresso não será salvo", "Confirmar", JOptionPane.YES_NO_OPTION);
                     
                     // se "sim", o programa acaba
                     if(option == JOptionPane.YES_OPTION){
                         System.exit(0);
                     }
                     // caso contrário volta a aparecer a caixa de input do nome do utilizador
 
                 // se o utilizador submeteu o nome sem inserir nenhum caracter
                 } else if(nomeJogador.equals("")){
                     JOptionPane.showMessageDialog(null, "Tem de inserir um nome!", "Inserir nome!", JOptionPane.PLAIN_MESSAGE);
                 
                 // se o utilizador inseriu algum caracter inválido
                 } else if(!verificarNome(nomeJogador)){
                     JOptionPane.showMessageDialog(null, "Caracter Inválido\n1234567890!@#$%^&*()_-+=[]{}/?;:'\\\",.<>", "Inserir nome!", JOptionPane.PLAIN_MESSAGE);
                 }
 
             } while(nomeJogador == null || nomeJogador.equals("") || !verificarNome(nomeJogador));
 
             // guardar dados do utilizador(nome e a data)
             jogador.setNome(nomeJogador);
             jogador.setData(new Data());
             jogador.escreverFicheiroObjeto(this.diretorio);
 
             // limpar a tela 
             limparPainel(panel);
 
             // imprimir top3
             JLabel top = new JLabel("TOP 3");
             top.setFont(new Font("Courier", Font.BOLD, 50));
             top.setBounds(400, 50, 400, 50);
             
             // lista que contém as 3 melhores pontuações
             Top3 listarTop = new Top3(diretorio);
             List<Jogador> lista = listarTop.getLista();
 
             // criação de 3 campos para os 3 jogadores
             int yPos = 160;
             for(Jogador jogadorLista : lista){
                 // se o nome for muito grande, aparece só o primeiro e último nome 
                 String[] nomes = jogadorLista.getNome().split(" ");
                 if(nomes.length > 2){
                     jogadorLista.setNome(nomes[0] + " " + nomes[nomes.length-1]);
                 }
 
                 JLabel j = new JLabel(jogadorLista.toString() + " - " + jogadorLista.getData().toString("dd-MM-yyyy HH:mm"));
                 j.setFont(new Font("Courier", Font.PLAIN, 15));
                 j.setBounds(280, yPos, 1000, 50);
                 panel.add(j);
                 yPos += 60;
             }
             
             // criação do botão para sair do jogo
             botaoSair = new JButton("Sair");
             botaoSair.setBounds(300, 400, 100,30);
             botaoSair.addActionListener(buttonActionListener);
             
             // criação do botão para voltar a jogar
             botaoVoltarJogar = new JButton("Voltar a Jogar");
             botaoVoltarJogar.setBounds(500, 400, 150, 30);
             botaoVoltarJogar.addActionListener(buttonActionListener);
             
             // adicionar elementos ao painel
             panel.add(top);
             panel.add(botaoSair);
             panel.add(botaoVoltarJogar);
 
             panel.revalidate();
         }
     }
     /**
      * Classe que atualiza a opção escolhida quanto um botão é selecionado.
      */
     private class RadioButtonListener implements ActionListener{
         public void actionPerformed(ActionEvent e){
             JRadioButton radioButton = (JRadioButton) e.getSource();
             opcaoEscolhida = radioButton.getText();
         }
     }
     /**
      * Classe que controla o jogo quando os botões são clicados.
      */
     private class ButtonListener implements ActionListener{
         
         Pergunta perguntaAtual = perguntasJogo.getPergunta(indiceAtual);
 
         public void actionPerformed(ActionEvent e){
             // se o jogador clicou no botão para iniciar o jogo
             if(e.getSource() == botaoIniciar){
                 limparPainel(panel);
                 trocarJanela(perguntaAtual, jogador);
             }
 
             // se o jogador submeteu a resposta
             else if(e.getSource() == botaoSubmeter){
                 if(!algumaOpcaoFoiEscolhida()){ // verificar se a resposta foi respondida
                     JOptionPane.showMessageDialog(null, "Tem de escolher uma opção!", "Submeta uma opção!", JOptionPane.PLAIN_MESSAGE);
                 
                 // o utilizador escolheu uma resposta
                 } else{
                     // adicionar pergunta a uma lista consoante a resposta
                     if(opcaoEscolhida.equals(perguntaAtual.getResposta())){
                         jogador.adicionarPerguntaCerta(perguntaAtual);
                         JOptionPane.showMessageDialog(null, "Pontuação: " + perguntaAtual.calcularPontuacao(), "Acertou!", JOptionPane.PLAIN_MESSAGE);
                     } else{
                         jogador.adicionarPerguntaErrada(perguntaAtual);
                         JOptionPane.showMessageDialog(null, "Pontuação: 0", "Errou!", JOptionPane.PLAIN_MESSAGE);
                     }
                     indiceAtual += 1; // alterar indíce da resposta
 
                     perguntaAtual = perguntasJogo.getPergunta(indiceAtual); // alterar a pergunta atual
 
                 }
                 trocarJanela(perguntaAtual, jogador);
 
             // se o jogador clicou para sair do jogo
             } else if(e.getSource() == botaoSair){
                 // Mostra mensagem de confirmação da ação anterior
                 int option = JOptionPane.showConfirmDialog(null, "Tem a certeza que pretende continuar?", "Confirmar", JOptionPane.YES_NO_OPTION);
                 
                 // se sim, então o programa acaba
                 if(option == JOptionPane.YES_OPTION){
                     JOptionPane.showMessageDialog(null, "Obrigado por ter jogado!", "Terminar jogo", JOptionPane.PLAIN_MESSAGE);
                     System.exit(0);
                 }
                 // caso contrario, volta à janela anterior
 
             // se o utilizador quer voltar a jogar
             } else if(e.getSource() == botaoVoltarJogar){
                 limparPainel(panel);
 
                 // garantir que se crie um jogador novo, que o indiceAtual seja resetado e que sejam criadas novas perguntas
                 indiceAtual = 0;
                 jogador = new Jogador();
                 perguntasJogo = gerarPerguntasJogo();
                 perguntaAtual = perguntasJogo.getPergunta(indiceAtual);
                 trocarJanela(perguntaAtual, jogador);
             }
         }
         /**
          * Verifica se alguma opção foi escolhida.
          * 
          * @return verdadeiro se algum botão for selecionado, Falso caso contrário.
          */
         private boolean algumaOpcaoFoiEscolhida(){
             for(AbstractButton botao : Collections.list(opcoesResposta.getElements())){
                 if(botao.isSelected()){ // se algum botão estiver selecionado na altura da submissão, retorna true
                     return true;
                 }
             }
             return false;
         }
     }
     /**
      * Gera as 5 perguntas do jogo, sem repetições.
      * 
      * @return Lista de perguntas que vão ser apresentadas ao jogador.
      */
     private ListaPerguntas gerarPerguntasJogo(){
         ListaPerguntas subLista;
         ArrayList<Integer> indices; // guardar indíces das perguntas que já foram inseridas na nova lista de perguntas
                                     // garante que não hajam repetições
 
         Random random = new Random();
 
         do{
             subLista = new ListaPerguntas();
             indices = new ArrayList<>();
             for(int i = 0; i < 5; i++){
                 int indice = random.nextInt(this.listaPerguntas.size());
                 // verificar se a pergunta já tinha sido inserida
                 while(indiceJaInserido(indice, indices)){
                     indice = random.nextInt(this.listaPerguntas.size());
                 }
                 // garante que não haja repetições
                 indices.add(indice);
                 subLista.adicionarPergunta(listaPerguntas.getPergunta(indice));
             }
         } while(verificarCorrespondenciasEntrePerguntas(subLista, listaPerguntas));
 
         return subLista;
     }
     /**
      * Verifica se existe a mesma pergunta nas duas dificuldades "Fácil" e "Difícil" nas perguntas de jogo.
      * As perguntas que tenham as duas dificuldades estão sempre uma a seguir à outra no ficheiro de texto.
      * Exemplo: indice 10 -> Pergunta2-Fácil-...
      *          indice 11 -> Pergunta2-Difícil-...
      * 
      * @param perguntasJogo Perguntas do Jogo.
      * @param listaPerguntas Perguntas do ficheiro de texto.
      * @return Verdadeiro se existem duas dificuldades da mesma pergunta, Falso caso contrário.
      */
     private boolean verificarCorrespondenciasEntrePerguntas(ListaPerguntas perguntasJogo, ListaPerguntas listaPerguntas){
         for(int i = 0; i < perguntasJogo.size(); i++){
             Pergunta perguntaI = perguntasJogo.getPergunta(i);
     
             if(!perguntaI.getDificuldade().equals("null")){ // verificar se a pergunta tem dificuldade
                 int indiceI = listaPerguntas.getIndice(perguntaI);
 
                 for(int j = i+1; j < perguntasJogo.size(); j++){
                     Pergunta perguntaJ = perguntasJogo.getPergunta(j);
                     
                     if(!perguntaJ.getDificuldade().equals("null")){
                         int indiceJ = listaPerguntas.getIndice(perguntaJ);
                         if(indiceI == indiceJ - 1 || indiceJ == indiceI - 1){
                             return true;
                         }
                     }
                 }
             }
         }
         return false;
     }
     /**
      * Verifica se a pergunta já foi inserida na nova lista, garante que não hajam repetições.
      * 
      * @param indice indice a ser verificado.
      * @param indices Lista de indíces já inseridos.
      * @return Verdadeiro se o indíce já tinha sido inserido, Falso caso contrário.
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
      * Classe para construir a lista do top 3 de jogadores.
      */
     private class Top3{
         /**
          * Lista de jogadores.
          */
         private List<Jogador> lista;
         /**
          * Diretório onde são guardados os jogos.
          */
         private String diretorio;
         /**
          * Construtor da classe, recebe dados para a inicialização dos atributos.
          * 
          * @param diretorio Diretório onde os jogos são guardados.
          */
         private Top3(String diretorio){
             this.diretorio = diretorio;
             this.lista = listaTop3();
         }
         /**
          * Obtém a lista do top 3.
          * 
          * @return Lista de jogadores do top 3.
          */
         private List<Jogador> getLista(){return this.lista;}
         /**
          * Lê um ficheiro de objeto.
          * 
          * @param f Ficheiro de objeto.
          * @return Jogador lido do ficheiro de objeto.
          */
         private Jogador lerFicheiroObjeto(File f){
             Jogador jogador = null;
             try{
                 FileInputStream fis = new FileInputStream(f);
                 ObjectInputStream ois = new ObjectInputStream(fis);
 
                 jogador = (Jogador) ois.readObject();
                 ois.close();
             } catch (FileNotFoundException e){
                 System.out.printf("Erro a abrir ficheiro\n");
             } catch (IOException e){
                 System.out.printf("Erro a ler ficheiro\n");
                 e.printStackTrace();
             } catch (ClassNotFoundException e){
                 System.out.printf("Erro a converter objeto\n");
             }
             return jogador;
         }
         /**
          * Obtém os 3 melhores jogadores e armazena-os numa lista.
          * 
          * @return Lista de jogadores do top 3.
          */
         private List<Jogador> listaTop3(){
             File folder = new File(diretorio); // pasta com todos os jogos guardados
 
             // ler todos os ficheiros .dat e guardar num array o seu título
             File[] files = folder.listFiles((dir, name) -> name.endsWith(".dat"));
 
             ArrayList<Jogador> listaJogadores = new ArrayList<>();
 
             if(files != null){
                 for(File file : files){
                     Jogador jogador = lerFicheiroObjeto(file);
                     if(jogador != null){
                         listaJogadores.add(jogador);
                     }
                 }
             }
 
             // ordena a os jogadores por pontuação
             Collections.sort(listaJogadores);
             
             if(listaJogadores.size() >= 3){return listaJogadores.subList(0, 3);}
             else{return listaJogadores.subList(0, listaJogadores.size());}
         }
     }
     /**
      * Verifica o nome do jogador.
      * 
      * @param nome Nome do jogador.
      * @return verdadeiro se o nome estiver correto, falso caso contrário.
      */
     private boolean verificarNome(String nome){
         char[] regexArray = "1234567890!@#$%^&*()_-+=[]{}/?;:'\",.<>".toCharArray();
         char[] nomeArray = nome.toCharArray();
 
         for(int i = 0; i < nomeArray.length; i++){
             for(int j = 0; j < regexArray.length; j++){
                 if(nomeArray[i] == regexArray[j]){
                     return false;
                 }
             }
         }
         return true;
     }
     /**
      * Limpa o painel.
      * 
      * @param panel Painel
      */
     private void limparPainel(JPanel panel){
         panel.removeAll();
         panel.repaint();
     }
 }
//package builder;
//
//import campfut.model.Jogador;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class JogadorBuilder {
//    private Jogador jogador;
//
//    private JogadorBuilder(){
//        // Cria um objeto Jogador "vazio" com valores default (ou use nulls)
//        this.jogador = new Jogador(
//                null,               // id
//                null,               // dataNascimento
//                null,               // nome
//                null,               // genero
//                null                // altura
//        );
//    }
//
//    public static JogadorBuilder umJogador(){
//        JogadorBuilder jogadorBuilder = new JogadorBuilder();
//
//        jogadorBuilder.jogador.setId(1);
//        jogadorBuilder.jogador.setNome("Pedro Ariel");
//        jogadorBuilder.jogador.setDataNascimento(LocalDate.of(1990,1,15));
//        jogadorBuilder.jogador.setGenero("Masculino");
//        jogadorBuilder.jogador.setAltura(1.77f);
//
//        return jogadorBuilder;
//    }
//    public static ArrayList<Jogador> listaJogadores(){
//        ArrayList<Jogador> listaJogador = new ArrayList<>();
//        Jogador jogador= new Jogador();
//        String[] nomes = new String[]{"Uriel", "Edilson", "Pedro Ariel", "Cuca beludo"};
//        int[][] datas = new int[][]{{1990,01,01},{1999,4,5},{1989,6,3},{1999,4,5}};
//        Float[] alturas = new Float[]{1.85f, 1.77f,1.60f, 1.90f};
//        for(int i = 1 ; i<= nomes.length; i++){
//            jogador.setId(i);
//            jogador.setNome(nomes[i]);
//            jogador.setDataNascimento(LocalDate.of(datas[i][0], datas[i][1], datas[i][2]));
//            jogador.setGenero("Masculino");
//            jogador.setAltura(alturas[i]);
//            listaJogador.add(jogador);
//        }
//        return listaJogador;
//    }
//
//    public JogadorBuilder comAlturaDe(Float altura){
//        this.jogador.setAltura(altura);
//        return this;
//    }
//
//    public JogadorBuilder comDataDeNascimentoDe(LocalDate dataDeNascimento){
//
//        this.jogador.setDataNascimento(dataDeNascimento);
//        return this;
//    }
//
//    public Jogador construirJogador(){
//        return this.jogador;
//    }
//
//
//}

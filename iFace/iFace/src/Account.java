import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
    private String login, username;
    private String password;
    private String sexo, cidade, pais;
    private int idade, feedControl = 0;
    private Community comunidadeDono = null;
    private Friends friends = new Friends();
    private ArrayList<String> minhasComunidades = new ArrayList<String>();
    private ArrayList<Messages> minhasMensagens = new ArrayList<Messages>();
    private ArrayList<String> mensagensFeed = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);

    public String getLogin(){
        return this.login;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void newAccount(ArrayList<Account> contas){

        System.out.print("Login: ");
        String login1 = sc.nextLine();
        int i = 0, k = 0;

        while(i == 0){
            for(int j = 0; j < contas.size(); j++){
                if(login1.equals(contas.get(j).getLogin())){
                    k++;
                }
            }
            if(k == 1){
                System.out.println("\nO login digitado já existe! Digite um diferente!\n");
                System.out.print("Login: ");
                login1 = sc.nextLine();
                k = 0;
            }
            else {
                this.login = login1;
                i = 1;
            }
        }
    
        System.out.print("Password: ");
        password = sc.nextLine();

        i = 0;
        k = 0;

        System.out.print("Username: ");
        String username1 = sc.nextLine();

        while(i == 0){
            for(int j = 0; j < contas.size(); j++){
                if(username1.equals(contas.get(j).getUsername())){
                    k++;
                }  
            }
            if(k == 1){
                System.out.println("\nO Username digitado já existe! Digite um diferente!\n");
                System.out.print("Username: ");
                username1 = sc.nextLine();
                k = 0;
            }
            else {
                this.username = username1;
                i = 1;
            };
        }
        
    }


    public void newProfile(){

        System.out.print("\nSexo: ");
        sexo = sc.nextLine();

        perfilIdade();

        System.out.print("Cidade: ");
        cidade = sc.nextLine();

        System.out.print("País: ");
        pais = sc.nextLine();
    }

    public void perfilIdade(){
        System.out.print("Idade: ");

        try{
            idade = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("\nEntrada digitada inválida! Por favor digite um número para a idade!");
            sc.nextLine();
            perfilIdade();
        }
    }

    public void editProfile() {
		
		System.out.println("\nSelecione o que você seja mudar:\n1 - Sexo\n2 - Idade\n3 - Cidade\n4 - País\n5 - Todos\n");
		
		int entrada = -11;

        while(entrada != 1 && entrada != 2 && entrada != 3 && entrada != 4 && entrada != 5){
            try{
                entrada = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada digitada inválida! Por favor digite um número válido");
                sc.nextLine();
            }
        }

		switch(entrada) {
		
			case 1:
				System.out.print("Sexo: ");
				sexo = sc.nextLine();
				System.out.println("Alteração feita com sucesso!");
				break;
			
			case 2:
                perfilIdade();
				System.out.println("Alteração feita com sucesso!");
				break;
				
			case 3:
				System.out.print("Cidade: ");
				cidade = sc.nextLine();
				System.out.println("Alteração feita com sucesso!");
				break;
			
            case 4:
                System.out.print("País: ");
                pais = sc.nextLine();
                System.out.println("Alteração feita com sucesso!");
                break;

			case 5:
				System.out.print("Sexo: ");
				sexo = sc.nextLine();
				
				perfilIdade();

                System.out.print("Cidade: ");
                cidade = sc.nextLine();
				
				System.out.print("País: ");
				pais = sc.nextLine();
				System.out.println("Alteração feita com sucesso!");
				break;
		}
	}

    public void exibirPerfil(){
        System.out.println("\n--------------------------------\n" + username.toUpperCase() + "\n--------------------------------");
		System.out.println("Sexo: " + sexo + "   Idade: " + idade);
        System.out.println("Cidade: " + cidade + "   País: " + pais);

		System.out.println("Lista de amigos: ");
        if(friends != null){
            for(int i = 0; i < friends.getAmigos().size(); i++) {
                System.out.println("   " + friends.getAmigos().get(i));
            }
        }

		if(comunidadeDono != null) {
			System.out.println("\nMinha comunidade: " + comunidadeDono.getNomeComunidade() + "\n  Descrição: " + comunidadeDono.getDescricao());

        }

		System.out.println("\nComunidades que estou:");
		for(int i = 0; i < minhasComunidades.size(); i++) {
			System.out.println("   " + minhasComunidades.get(i));
		}
    }

    public void adicionarAmigo(ArrayList<Account> contas){

        int existe1 = 0;
        
        System.out.println("\nDigite o nome do usuario que deseja adicionar.");
        String nomeAmigo = sc.nextLine();

        for(int i = 0; i < contas.size(); i++){
            if(nomeAmigo.equals(contas.get(i).username)){  //PROCURANDO SE O NOME DO USUARIO EXISTE

                if(nomeAmigo.equals(this.username) == true){  //verifica se é seu próprio nome
                    System.out.println("\nVocê não pode tentar adicionar você mesmo a lista de amigos!");
                }
                else if(friends.findFristList(nomeAmigo) == 1){   //verifica se vocês já são amigos
                    System.out.println("\nVocê e " + nomeAmigo + " já são amigos!");
                }
                else if(friends.findFriendRequest(nomeAmigo) == 1){   //verifica se você já recebeu uma solicitação de amizade dessa pessoa
                    System.out.println("\nEsse usuario já te enviou uma solicitação de amizade!");
                }
                else if(contas.get(i).friends.findFriendRequest(this.username) == 1 ){
                    System.out.println("\nVocê já enviou um pedido de amizade para " + nomeAmigo + "!"); //VERIFICA SE VC JA ENVIOU UMA SOLICITAÇÃO PARA A PESSOA
                }
                else{
                    contas.get(i).friends.addSolicitacaoAmigo(this.username);  //ADICIONANDO SEU NOME A LISTA DE PEDIDOS DO AMIGO SOLICITADO
                    System.out.println("\nPedido de amizade enviado!");
                    contas.get(i).mensagensFeed.add(this.username + " te enviou uma solicitação de amizade"); //ADICIONA NO FEED DO AMIGO QUE VOCÊ ENVIOU UMA SOLICITAÇÃO
                }
                existe1++;
            }
        }

        if(existe1 == 0){
            System.out.println("\nNome de usuario não encontrado!");
        }
    }

    public void pedidosAmizade(ArrayList<Account> contas){
        System.out.println("\nLista de solicitações de amizade");
        System.out.println("Digite 1 para aceitar e 0 para recusar\n");

        if(friends.getAmigosAdd().size() > 0){
            for(int i = 0; i < friends.getAmigosAdd().size(); i++){ //EXIBIR OS PEDIDOS DE AMIZADE
                System.out.println(friends.getAmigosAdd().get(i) + " deseja te adicionar!");
                System.out.print("Você aceita? "); 

                int aceita = -11;

                while(aceita != 0  && aceita != 1){
                    try {
                        aceita = sc.nextInt();
                        sc.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\nEntrada digitada inválida!");
                        System.out.println("Digite 1 para aceitar e 0 para recusar");
                        sc.nextLine();
                    }
                }
                
                if (aceita == 0){
                    friends.getAmigosAdd().remove(i);
                    System.out.println("\nPedido recusado!\n"); //REMOVE DA LISTA DE PEDIDOS
                }
                else if(aceita == 1){
                    friends.addAmigo(friends.getAmigosAdd().get(i)); //ADICIONANDO NA SUA LISTA DE AMIGOS 

                    for(int k = 0; k < contas.size(); k++){
                        if(friends.getAmigosAdd().get(i).equals(contas.get(k).username)){
                            contas.get(k).friends.addAmigo(this.username);  //ADICIONANDO NA LISTA DE AMIGOS DO USUARIO
                            contas.get(k).mensagensFeed.add(username + " aceitou seu pedido de amizade!");
                            System.out.println("\nAgora você e " + contas.get(k).username + " são amigos!");
                        }
                    }
                    friends.getAmigosAdd().remove(i);
                }
            }
        }
        else System.out.println("\nVocê não possui pedidos de amizade!\n");  //Caso n tenha solicitaçao de amizade
    }

    public void printarAmigos(){
        if(friends.getAmigos().size() > 0){
            System.out.println("\nSeus amigos: ");
            for(int i = 0; i < friends.getAmigos().size(); i++){
                System.out.println("   " + friends.getAmigos().get(i));
            }
        }
        else{
            System.out.println("\nVocê ainda não possui nenhum amigo!\n");
        }
    }

    public void exibirPerfilAlguem(ArrayList<Account> contas){
        int sucesso = 0;
        System.out.println("\nDigite o nome de quem deseja ver o perfil");
        String pessoa = sc.nextLine();

        for(int i = 0; i < contas.size(); i++){
            if(pessoa.equals(contas.get(i).username)){
                contas.get(i).exibirPerfil();
                sucesso++;
            }
        }
        if(sucesso == 0){
            System.out.println("\nO usuario digitado não existe!\n");
        }

    }

    public void exibirComunidadeAlguem(ArrayList<Account> contas){
        System.out.println("\nDigite o nome da comunidade que deseja ver:");

        String comuni = sc.nextLine();
        int exibido = 0;

        for(int i = 0; i < contas.size(); i++){
            if(contas.get(i).comunidadeDono != null && comuni.equals(contas.get(i).comunidadeDono.getNomeComunidade())){
                contas.get(i).comunidadeDono.mostrarComunidade();
                exibido++;
            }
        }
        if(exibido == 0){
            System.out.println("\nA comunidade digitada não existe!");
        }
    }

    public void mandarMensagem(ArrayList<Account> contas){
        int decidir = 0, existe = 0;
        System.out.println("\nPara quem você deseja mandar a mensagem?");
        String pessoa = sc.nextLine();

        for(int k = 0; k < contas.size(); k++){
            if(pessoa.equals(contas.get(k).username)) { //VERIFICANDO SE O NOME DIGITADO EXISTE

                if(pessoa.equals(this.username)){ //VERIFICANDO SE O USERNAME É IGUAL O MEU
                    System.out.println("\nVocê não pode enviar uma mensagem para você mesmo!");
                }
                else{

                    for(int i = 0; i < minhasMensagens.size(); i++){
                        if(pessoa.equals(minhasMensagens.get(i).getRemetente())){ //VERIFICANDO SE JA TENHO MENSAGEM COM ESSA PESSOA
                            System.out.print("\nDigite sua mensagem: ");  //ENVIAR PARA ALGUEM Q JA ENVIOU ANTES
                            String mensagemPEnviar = sc.nextLine();
                            minhasMensagens.get(i).addMessage(username, mensagemPEnviar); //ENVIANDO A MENSAGEM PARA MEU CHAT COM A PESSOA

                            System.out.print("\nFEITO -- 1!" + minhasMensagens.get(i).getRemetente());

                            for(int j = 0; j < contas.get(k).minhasMensagens.size(); j++){
                                if(contas.get(k).minhasMensagens.get(j).getRemetente().equals(this.username)){
                                    contas.get(k).minhasMensagens.get(j).addMessage(this.username, mensagemPEnviar);
                                    System.out.print("\nFEITO -- 2"); 
                                } //ENVIANDO A MENSAGEM PARA O CHAT DA PESSOA
                            }
                            decidir++;
                        }
                    }

                    if(decidir == 0){
                        Messages mensagemAdd = new Messages();
                        mensagemAdd.setRementente(this.username);
            
                        System.out.print("\nDigite sua mensagem: ");  //ENVIAR PARA ALGUEM Q NUNCA ENVIOU
                        String mensagemPEnviar = sc.nextLine();

                        mensagemAdd.addMessage(this.username, mensagemPEnviar);
                        contas.get(k).minhasMensagens.add(mensagemAdd); //ENVIANDO A MENSAGEM PARA O CHAT DA PESSOA

                        Messages mensagemAdd1 = new Messages();
                        mensagemAdd1.setRementente(pessoa);  //ENVIANDO A MENSAGEM PARA O MEU CHAT COM A PESSOA
                        mensagemAdd1.addMessage(this.username, mensagemPEnviar);
                        minhasMensagens.add(mensagemAdd1);  //ENVIANDO A MENSAGEM PARA O MEU CHAT COM A PESSOA

                    }
                    System.out.println("\nMensagem Enviada!");
                }
                existe ++;
            }
        }

        if(existe == 0){
            System.out.println("\nO Usuario digitado não existe!");
        }
    }

    public void lerMensagens(){

        if(minhasMensagens.size() > 0){
            System.out.println("\nDigite o nome da pessoa que deseja ler as mensagens");
            System.out.println("Suas conversas:");

            for(int i = 0; i < minhasMensagens.size(); i++){
                System.out.print(minhasMensagens.get(i).getRemetente() + " / ");
            }

            System.out.println("\n");

            String pessoa = sc.nextLine();  //ESCOLHE O NOME DA PESSOA QUE DESEJA LER
            int leu = 0;

            for(int i = 0; i < minhasMensagens.size(); i++){
                if(pessoa.equals(minhasMensagens.get(i).getRemetente())){
                    minhasMensagens.get(i).printarMensagens();  //IMPRIME TODAS MENSAGENS DA PESSOA DIGITADA
                    leu++;
                }
            }

            if(leu == 0){  //NÃO HAVIA MENSAGENS DA PESSOA
                System.out.println("\nO nome digitado não existe ou vocês ainda não possuem mensagens!");
            }
        }
        else System.out.println("\nVocê ainda não possui nenhuma mensagem!");
    }

    public void criarComunidade(ArrayList<Account> contas) {
        if(comunidadeDono == null){
            int existe = 0;

		    System.out.println("\nBem vindo a criação da comunidade! Escreva as informações abaixo.");

		    System.out.print("Nome da comunidade: ");
		    String nomeComunidade = sc.nextLine();
		
            for(int i = 0; i < contas.size(); i++){
                if(contas.get(i).comunidadeDono != null){
                    if(nomeComunidade.equals(contas.get(i).comunidadeDono.getNomeComunidade())){
                        System.out.println("\nJá existe uma comunidade com esse nome!");
                        existe++;
                    }
                }
            }

            if(existe == 0){
                System.out.print("Descrição da comunidade: ");
		        String descricao = sc.nextLine();

                Community comunidade = new Community(username, nomeComunidade, descricao);
                comunidade.addMembro(username);

                comunidadeDono = comunidade;

		        System.out.println("\nParabéns! Sua comunidade foi criada!");
            }
		
        }
        else{
            System.out.println("\nVocê já é dono de uma comunidade!\n");
        }
    }
    
    public void adicionarComunidade(ArrayList<Account> contas){

        if(comunidadeDono != null){
            System.out.println("\nDigite o usuario que deseja adicionar a sua comunidade: ");
            String pessoa = sc.nextLine();
            int adicionado = 0;
            for(int i = 0; i < contas.size(); i++){
                if(pessoa.equals(contas.get(i).username)){
                    if(pessoa.equals(this.username)){
                        System.out.println("\nVocê já é o dono dessa comunidade!");
                    }
                    else if(comunidadeDono.encontrarMembro(pessoa) == 1){
                        System.out.println("\n" + pessoa + " já é um membro da sua comunidade\n");
                    }
                    else{
                        comunidadeDono.addMembro(pessoa);  //ADICIONA A PESSOA COMO MEMBRO E COLOCA NA COMUNIDADES DELA
                        contas.get(i).minhasComunidades.add(comunidadeDono.getNomeComunidade());
                        System.out.println("\nPronto! " + pessoa + " faz parte da sua comunidade agora!\n");
                    }
                    adicionado++;
                }
            }
            if(adicionado == 0){
                System.out.println("\nO usuario digitado não existe!\n");
            }
        }
        else{
            System.out.println("\nVocê ainda não é dono de uma comunidade!");
        }
    }

    public void removerComunidade(ArrayList<Account> contas){

        if(comunidadeDono != null){
            System.out.println("\nDigite o usuario que deseja remover da sua comunidade: ");
            System.out.println("\nMembros atuais: ");
            comunidadeDono.printarMembros();
            System.out.println("\n");

            String pessoa = sc.nextLine();
            int removido = 0;
            for(int i = 0; i < contas.size(); i++){
                if(pessoa.equals(contas.get(i).username)){  // VERIFICANDO SE O USUARIO EXISTE

                    if(pessoa.equals(this.username)){  // VERIFICANDO SE É SEU PROPRIO NOME
                        System.out.println("\nVocê não pode sair da sua própria comunidade!");
                    }
                    else if(comunidadeDono.encontrarMembro(pessoa) == 1){
                        comunidadeDono.removerMembro(pessoa); //REMOVE A PESSOA COMO MEMBRO E REMOVE DAS COMUNIDADES DELA
                        contas.get(i).minhasComunidades.remove(comunidadeDono.getNomeComunidade());
                        System.out.println("\nPronto! " + pessoa + " foi removido da sua comunidade!\n");
                    }
                    else{
                        System.out.println("\n" + pessoa + " não é um membro da sua comunidade!\n");
                    }
                    removido ++;
                }
            }
            if(removido == 0){
                System.out.println("\nO usuario digitado não existe!\n");
            }
        }
        else{
            System.out.println("\nVocê ainda não é dono de uma comunidade!");
        }
    }

    public void mandarFeed(){
        System.out.println("\nDigite alguma coisa para seu feed:");
        String mensagem = sc.nextLine();

        mensagensFeed.add(mensagem);
    }

    public void printarFeed(){
        if(mensagensFeed.size() > 0){
            System.out.println("\nMensagens no feed:\n");

            for(int i = 0; i < mensagensFeed.size(); i++){
                System.out.println("--> " + mensagensFeed.get(i));
            }
        }
        else System.out.println("\nVocê ainda não possui nenhuma mensagem!!\n");
    }

    public void exibirFeedAlguem(ArrayList<Account> contas){
        int sucesso = 0;
        int indice = 0;
        int sucesso1 = 0;
        System.out.println("\nDigite o nome de quem deseja ver o Feed");
        String pessoa = sc.nextLine();

        for(int i = 0; i < contas.size(); i++){
            if(contas.get(i).username.equals(pessoa)){
                indice = i;
                sucesso++;
            }
        }
        if(sucesso == 1){
            if(contas.get(indice).feedControl == 0){
                contas.get(indice).printarFeed();
            }
            else if(contas.get(indice).feedControl == 1){
                for(int k = 0; k < contas.get(indice).friends.getAmigos().size(); k++){
                    if(contas.get(indice).friends.getAmigos().get(k).equals(username)){
                        contas.get(indice).printarFeed();
                        sucesso1++;
                    }
                }
                if(sucesso1 == 0){
                    System.out.println("\nDesculpe, o usuario so permite que amigos vejam seu Feed!\n");
                }
            }
        }
        else{
            System.out.println("\nO usuario digitado não existe!\n");
        }

    }

    public void controlarFeed(){
        System.out.println("\nQuem você deseja que possa ver o seu Feed? Escolha um número abaixo:");
        System.out.println("0 - Todos usuarios do iFace\n1 - Somente amigos");
        int auxiliar = -11;

        while(auxiliar != 0 && auxiliar != 1){
            try {
                auxiliar = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada digitada inválida!");
                System.out.println("0 - Todos usuarios do iFace\n1 - Somente amigos");
                sc.nextLine();
            }
        }

        if(auxiliar == 0){
            feedControl = 0;
            System.out.println("\nPronto! Todos os usuarios podem ver seu Feed agora!\n");
        }
        else if(auxiliar == 1){
            feedControl = 1;
            System.out.println("\nPronto! Apenas seus amigos podem ver seu Feed agora!\n");
        }
    }

    public void excluirConta(ArrayList<Account> contas, int id){
        System.out.println("\nTem certeza que deseja excluir sua conta?\nDigite 1 para confirmar e 0 para cancelar.");

        int excluir = -11;

        while(excluir != 0 && excluir != 1){
            try {
                excluir = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada digitada inválida! Por favor digite um número valido");
                System.out.println("Tem certeza que deseja excluir sua conta?\nDigite 1 para confirmar e 0 para cancelar.");
                sc.nextLine();
            }
        }
        

        if(excluir == 1){
            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).friends.getAmigos().size(); j++) {
                    if(username.equals(contas.get(i).friends.getAmigos().get(j))) { //remove da lisa de amizade
                        contas.get(i).friends.getAmigos().remove(j);
                    }
                }
            }

            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).friends.getAmigosAdd().size(); j++) {
                    if(username.equals(contas.get(i).friends.getAmigosAdd().get(j))) {  //remove da lista de pedidos de amizade
                        contas.get(i).friends.getAmigosAdd().remove(j);
                    }
                }
            }

            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).minhasMensagens.size(); j++) {
                    if(username.equals(contas.get(i).minhasMensagens.get(j).getRemetente())) { //remove da lista de mensagens
                        contas.get(i).minhasMensagens.remove(j);
                    }
                }
            }
            

            for(int i = 0; i < contas.size(); i++) {
                if(contas.get(i).comunidadeDono != null) {
                    if(contas.get(i).comunidadeDono.encontrarMembro(this.username) == 1){
                        contas.get(i).comunidadeDono.removerMembro(this.username); //me remove como membros das comunidades que faço parte
                    }
                }
            }
            
            if(comunidadeDono != null){
                for(int i = 0; i < contas.size(); i++){
                    for(int j = 0; j < contas.get(i).minhasComunidades.size(); j++){
                        if(comunidadeDono.getNomeComunidade().equals(contas.get(i).minhasComunidades.get(j))){
                            contas.get(i).minhasComunidades.remove(j); //remove minha comunidade da lista de comunidades dos que eram membros
                        }
                    }
                }
            }
        
            contas.remove(id);
            System.out.println("\nConta excluida com sucesso!\n");
        }
    }
}

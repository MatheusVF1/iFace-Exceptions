import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Account {
    private String login, username;
    private String password;
    private String sexo, cidade, pais;
    private int idade, feedControl = 0;
    private Community comunidadeDono = null;
    private ArrayList<String> amigosAdd = new ArrayList<String>();
    private ArrayList<String> amigos = new ArrayList<String>();
    public ArrayList<String> comunidades = new ArrayList<String>();
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
		
		int entrada = sc.nextInt();
		sc.nextLine();
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
				
				System.out.print("Idade: ");
				idade = sc.nextInt();
                sc.nextLine();

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
		for(int i = 0; i < amigos.size(); i++) {
			System.out.println("   " + amigos.get(i));
		}

		if(comunidadeDono != null) {
			System.out.println("Minha comunidade: " + comunidadeDono.nomeComunidade + "\n  Descrição: " + comunidadeDono.descricao);

        }

		System.out.println("\nComunidades que estou:");
		for(int i = 0; i < minhasComunidades.size(); i++) {
			System.out.println("   " + minhasComunidades.get(i));
		}
    }

    public void adicionarAmigo(ArrayList<Account> contas){

        int existe1 = 0;
        
        System.out.println("\nDigite o nome do usuario de quem deseja adicionar.");
        String nomeAmigo = sc.nextLine();

        for(int i = 0; i < contas.size(); i++){
            if(nomeAmigo.equals(contas.get(i).username)){     //PROCURANDO SE O NOME DO USUARIO EXISTE
                contas.get(i).addFriendlist(username);  //ADICIONANDO SEU NOME A LISTA DE PEDIDOS DO USUARIO SOLICITADO
                contas.get(i).mensagensFeed.add(username + " te enviou uma solicitação de amizade"); //ADICIONA NO FEED DO AMIGO QUE ENVIOU SOLICITAÇÃO
                existe1++;
            }
        }
        if(existe1 == 0){
            System.out.println("Nome de usuario não encontrado!");
        }
    }

    public void pedidosAmizade(ArrayList<Account> contas){
        System.out.println("\nLista de solicitações de amizade");
        System.out.println("Digite 1 para aceitar e 0 para recusar\n");

        if(amigosAdd.size() > 0){
            for(int i = 0; i < amigosAdd.size(); i++){ //EXIBIR OS PEDIDOS DE AMIZADE
                System.out.println(amigosAdd.get(i) + " deseja te adicionar!");
                System.out.print("Você aceita? "); 

                int aceita = sc.nextInt();
                sc.nextLine();

                if (aceita == 0){
                    amigosAdd.remove(i);
                    System.out.println("\nPedido recusado!\n"); //REMOVE DA LISTA DE PEDIDOS
                }
                else if(aceita == 1){
                    amigos.add(amigosAdd.get(i)); //ADICIONANDO NA SUA LISTA DE AMIGOS 

                    for(int k = 0; k < contas.size(); k++){
                        if(amigosAdd.get(i).equals(contas.get(k).username)){
                            contas.get(k).amigos.add(username);  //ADICIONANDO NA LISTA DE AMIGOS DO USUARIO
                            contas.get(k).mensagensFeed.add(username + " aceitou seu pedido de amizade!");
                            System.out.println("\nAgora você e " + contas.get(k).username + " são amigos!");
                        }
                    }
                    amigosAdd.remove(i);
                }
            }
        }
        else System.out.println("\nVocê não possui pedidos de amizade!\n");
    }



    public void printarAmigos(){
        if(amigos.size() > 0){
            System.out.println("\nSeus amigos: ");
            for(int i = 0; i < amigos.size(); i++){
                System.out.println("   " + amigos.get(i));
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
            if(contas.get(i).comunidadeDono != null && comuni.equals(contas.get(i).comunidadeDono.nomeComunidade)){
                contas.get(i).comunidadeDono.mostrarComunidade();
                exibido++;
            }
        }
        if(exibido == 0){
            System.out.println("\nA comunidade digitada não existe!");
        }
    }

    public void addFriendlist(String nome){
        String name = nome;
        amigosAdd.add(name); //ADICIONA NA LISTA DE PEDIDOS
        System.out.println("Pedido de amizade enviado!");
    }

    public void mandarMensagem(ArrayList<Account> contas){
        int decidir = 0;
        System.out.println("\nPara quem você deseja mandar a mensagem?");
        String pessoa = sc.nextLine();

        for(int i = 0; i < minhasMensagens.size(); i++){
            if(pessoa.equals(minhasMensagens.get(i).remetente)){
                System.out.print("\nDigite sua mensagem: ");  //ENVIAR PARA ALGUEM Q JA ENVIOU ANTES
                String mensagemPEnviar = sc.nextLine();
                minhasMensagens.get(i).mensagens.add(username + ": " + mensagemPEnviar);
                decidir++;
            }
        }
        if(decidir == 0){

            for(int i = 0; i < contas.size(); i++) {
                if(pessoa.equals(contas.get(i).username)) {
                    Messages mensagemAdd = new Messages();
                    mensagemAdd.remetente = username;

                    System.out.print("\nDigite sua mensagem: ");  //ENVIAR PARA ALGUEM Q NUNCA ENVIOU
                    String mensagemPEnviar = sc.nextLine();

                    mensagemAdd.mensagens.add(username + ": " + mensagemPEnviar);
                    contas.get(i).minhasMensagens.add(mensagemAdd);
                    
                }
            }
        }
		System.out.println("\nMensagem Enviada!");
    }

    public void lerMensagens(){

        System.out.println("\nDigite o nome da pessoa que deseja ler as mensagens");
        if(minhasMensagens.size() > 0){
            System.out.println("Suas conversas:");
            for(int i = 0; i < minhasMensagens.size(); i++){
                System.out.print(minhasMensagens.get(i).remetente + " / ");
            }
            System.out.println("\n\n");
        }
        String pessoa = sc.nextLine();  //ESCOLHE O NOME DA PESSOA QUE DESEJA LER
        int leu = 0;

        for(int i = 0; i < minhasMensagens.size(); i++){
            if(pessoa.equals(minhasMensagens.get(i).remetente)){
                minhasMensagens.get(i).printarMensagens();  //IMPRIME TODAS MENSAGENS DA PESSOA DIGITADA
                leu++;
            }
        }

        if(leu == 0){  //NÃO HAVIA MENSAGENS DA PESSOA
            System.out.println("\nO nome digitado está errado ou vocês ainda não possuem mensagens!");
        }

    }

    public void criarComunidade() {
        if(comunidadeDono == null){
        Community comunidade = new Community();
		comunidade.criarNovaComunidade(username);

		comunidadeDono = comunidade;
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
                    comunidadeDono.membros.add(pessoa);  //ADICIONA A PESSOA COMO MEMBRO E COLOCA NA COMUNIDADES DELA
                    contas.get(i).minhasComunidades.add(comunidadeDono.nomeComunidade);
                    System.out.println("\nPronto! " + pessoa + " faz parte da sua comunidade agora!\n");
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
                for(int k = 0; k < contas.get(indice).amigos.size(); k++){
                    if(contas.get(indice).amigos.get(k).equals(username)){
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
        int auxiliar = sc.nextInt();

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

        int excluir = sc.nextInt();
        sc.nextLine();
        
        if(excluir == 1){
            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).amigos.size(); j++) {
                    if(username.equals(contas.get(i).amigos.get(j))) { //remove da lisat de amizade
                        contas.get(i).amigos.remove(j);
                    }
                }
            }

            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).amigosAdd.size(); j++) {
                    if(username.equals(contas.get(i).amigosAdd.get(j))) {  //remove da lista de pedidos de amizade
                        contas.get(i).amigosAdd.remove(j);
                    }
                }
            }

            for(int i = 0; i < contas.size(); i++) {
                for(int j = 0; j < contas.get(i).minhasMensagens.size(); j++) {
                    if(username.equals(contas.get(i).minhasMensagens.get(j).remetente)) { //remove da lista de mensagens
                        contas.get(i).minhasMensagens.remove(j);
                    }
                }
            }
            
            for(int i = 0; i < contas.size(); i++) {
                if(contas.get(i).comunidadeDono != null) {
                    for(int j = 0; j < contas.get(i).comunidadeDono.membros.size(); j++) {
                        if(username.equals(contas.get(i).comunidadeDono.membros.get(j))) { //remove do membro de outras comunidades
                            contas.get(i).comunidadeDono.membros.remove(j);
                        }
                    }
                }
            }
            
            if(comunidadeDono != null){
                for(int i = 0; i < contas.size(); i++){
                    for(int j = 0; j < contas.get(i).minhasComunidades.size(); j++){
                        if(comunidadeDono.nomeComunidade.equals(contas.get(i).minhasComunidades.get(j))){
                            contas.get(i).minhasComunidades.remove(j);
                        }
                    }
                }
            }
        
            contas.remove(id);
            System.out.println("\nConta excluida com sucesso!\n");
        }
    }
}
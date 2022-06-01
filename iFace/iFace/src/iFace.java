import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class iFace {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        ArrayList<Account> contas = new ArrayList<Account>();
        int entrada = -11;

        System.out.println("Seja bem vindo ao iFace");
        System.out.println("Escolha o que deseja fazer:");
        System.out.println("0 - Sair do programa\n1 - Criar uma conta\n2 - Entrar na conta\n");

        while (entrada != 0){

            try {
                entrada = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("\nEntrada digitada inválida!");
                sc.nextLine();
            }

            switch(entrada){
                case 0:
                    System.out.println("Encerrando o programa."); //FECHAR O PROGRAMA
                    break;

                case 1:
                    Account conta = new Account();

                    conta.newAccount(contas); //CRIAR A CONTA NOVA
                    conta.newProfile(); //CRIAR O PERFIL
                    contas.add(conta);

                    System.out.println("\nConta criada com sucesso!\n");
                    System.out.print("O que deseja fazer agora? ");
                    break;
                    
                case 2:
                    System.out.print("Digite seu login: "); //ENTRAR NA CONTA
                    String login = sc.nextLine();
                    String password = null;
                    int id = 0, existe = 0;

                    for(int i = 0; i < contas.size(); i++){     //VERIFICANDO SE O LOGIN EXISTE
                        if(login.equals(contas.get(i).getLogin())){
                            id = i;
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            existe++;
                            break;
                        };
                    }

                    if(existe == 0){
                        System.out.println("Login não encontrado! Tente novamente.\n");
                    }
                    else if(password.equals(contas.get(id).getPassword())){  //VERIFICANDO SE A SENHA CONFERE COM O LOGIN
                        System.out.println("\nSeja bem vindo a sua conta no iFace, " + contas.get(id).getUsername() + "!\n");
                        System.out.println("Escolha o que deseja fazer:\n"); //MENU DE OPÇÕES DO USUARIO ABAIXO
                        System.out.println("  0 - Sair da Conta\nPERFIL\n  01 - Editar meu perfil\n  02 - Exibir meu perfil\n  03 - Exibir o perfil de algum usuário\nAMIGOS\n  04 - Adicionar um amigo\n  05 - Pedidos de amizade\n  06 - Exibir lista de amigos\nMENSAGENS\n  07 - Enviar uma mensagem\n  08 - Ler minhas mensagens\nCOMUNIDADES\n  09 - Criar uma comunidade\n  10 - Adicionar membro a comunidade\n  11 - Remover membro da comunidade\n  12 - Exibir alguma comunidade\nFEED\n  13 - Mandar mensagens para o Feed\n  14 - Ver meu Feed\n  15 - Controlar visualizações no Feed\n  16 - Exibir o Feed de algum usuario\n---\n  17 - Apagar sua conta");
                        
                        int entrada2 = -11;

                        while(entrada2 != 0){

                            try {
                                entrada2 = sc.nextInt();
                                sc.nextLine();
                            } catch (InputMismatchException e) {
                                System.out.println("\nEntrada digitada inválida!");
                                sc.nextLine();
                            }


                            switch(entrada2){
                                case 0:
                                    System.out.println("Saindo da conta!"); //SAI DA CONTA
                                    break;
                                    
                                case 1:
                                    contas.get(id).editProfile();  //EDITAR O PERFIL
                                    break;

                                case 2:
                                    contas.get(id).exibirPerfil();  //EXIBIR O PERFIL
                                    break;

                                case 3:
                                    contas.get(id).exibirPerfilAlguem(contas); //MOSTRAR PERFIL DE ALGUÉM
                                    break;

                                case 4:
                                    contas.get(id).adicionarAmigo(contas); //ENVIAR PEDIDO DE AMIZADE
                                    break;

                                case 5:
                                    contas.get(id).pedidosAmizade(contas); //VER PEDIDOS DE AMIZADE E ACEITAR
                                    break;

                                case 6:
                                    contas.get(id).printarAmigos();  //MOSTRAR LISTA DE AMIGOS
                                    break;

                                case 7:
                                    contas.get(id).mandarMensagem(contas);  //ENVIAR MENSAGEM PARA ALGUEM
                                    break;

                                case 8:
                                    contas.get(id).lerMensagens();  //LER AS MINHAS MENSAGENS
                                    break;

                                case 9:
                                    contas.get(id).criarComunidade();  //CRIAR MINHA COMUNIDADE
                                    break;

                                case 10:
                                    contas.get(id).adicionarComunidade(contas); //ADICIONAR MEMBROS A COMUNIDADE
                                    break;

                                case 11:
                                    contas.get(id).removerComunidade(contas);
                                    break;

                                case 12:
                                    contas.get(id).exibirComunidadeAlguem(contas); //MOSTRAR COMUNIDADE DE ALGUEM
                                    break;

                                case 13:
                                    contas.get(id).mandarFeed(); //MANDAR MENSAGEM PARA O FEED
                                    break;

                                case 14:
                                    contas.get(id).printarFeed();  //VER MEU FEED
                                    break;

                                case 15:
                                    contas.get(id).controlarFeed(); //CONTROLAR QUEM PODE VER O FEED
                                    break;

                                case 16:
                                    contas.get(id).exibirFeedAlguem(contas);  //MOSTRAR O FEED DE ALGUÉM
                                    break;

                                case 17:
                                    contas.get(id).excluirConta(contas, id); //EXCLUI A CONTA
                                    entrada2 = 0;
                                    break;

                                default:
                                    System.out.println("Por favor digite um dos números possíveis!\n");
                                    break;

                            }
                            if(entrada2 != 0){
                            System.out.println("\nEscolha o que deseja fazer:\n");
                            System.out.println("  0 - Sair da Conta\nPERFIL\n  01 - Editar meu perfil\n  02 - Exibir meu perfil\n  03 - Exibir o perfil de algum usuário\nAMIGOS\n  04 - Adicionar um amigo\n  05 - Pedidos de amizade\n  06 - Exibir lista de amigos\nMENSAGENS\n  07 - Enviar uma mensagem\n  08 - Ler minhas mensagens\nCOMUNIDADES\n  09 - Criar uma comunidade\n  10 - Adicionar membro a comunidade\n  11 - Remover membro da comunidade\n  12 - Exibir alguma comunidade\nFEED\n  13 - Mandar mensagens para o Feed\n  14 - Ver meu Feed\n  15 - Controlar visualizações no Feed\n  16 - Exibir o Feed de algum usuario\n---\n  17 - Apagar sua conta");
                        }
                    }
                }
                break;
                    
                default:
                    System.out.println("Por favor digite um dos números possíveis!\n");
                    break;
            }

            if(entrada != 0){
            System.out.println("\nEscolha o que deseja fazer:");
            System.out.println("0 - Sair do programa\n1 - Criar uma conta\n2 - Entrar na conta\n");
            }
        }

        sc.close();

    }
}

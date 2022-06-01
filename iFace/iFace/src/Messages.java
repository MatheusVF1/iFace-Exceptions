import java.util.ArrayList;

public class Messages{
	
	private String remetente;
	private ArrayList<String> mensagens = new ArrayList<String>();

    public String getRemetente(){
        return remetente;
    }

    public void setRementente(String remetente){
        this.remetente = remetente;
    }

    public void addMessage(String username, String mensagemPEnviar){
        mensagens.add(username + ": " + mensagemPEnviar);
    }

	public void printarMensagens(){
		if(mensagens.size() > 0) {
			System.out.println("\nSuas mensagens com " + remetente + ":\n");

			for(int i = 0; i < mensagens.size(); i++) {
				System.out.println(mensagens.get(i));
			}
		}
		else {
			System.out.println("Você ainda não recebeu nenhuma mensagem do(a) " + remetente);
		}
	}
	
}

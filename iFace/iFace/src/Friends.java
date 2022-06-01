import java.util.ArrayList;

public class Friends {
    private ArrayList<String> amigosAdd = new ArrayList<String>();
    private ArrayList<String> amigos = new ArrayList<String>();

    public ArrayList<String> getAmigosAdd(){
        return amigosAdd;
    }

    public ArrayList<String> getAmigos(){
        return amigos;
    }

    public void addSolicitacaoAmigo(String pessoa){
        amigosAdd.add(pessoa); //adiciona a pessoa a lista de solicitações de amizade
    }
    
    public void addAmigo(String pessoa){
        amigos.add(pessoa);  //adiciona a pessoa a lista de amigos
    }

    public int findFriendRequest(String pessoa) {
		for(String val : amigosAdd) {
			if(pessoa.contentEquals(val) == true) {
				return 1; //ver se a pessoa esta na sua lista de solicitações de amizade
			}
		}
		return 0;
	}

    public int findFristList(String pessoa) {
		for(String val : amigos) {
			if(pessoa.contentEquals(val) == true) {
				return 1; //ver se a pessoa está na sua lista de amigos
			}
		}
		return 0;
	}

}

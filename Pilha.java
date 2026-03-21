public class Pilha {
    private Chamado[] criticos; // criticos vetor com objetos do tipo Chamado
    private int topo; // indice 

    public Pilha(int capacidade){
        this.criticos = new Chamado[capacidade];
        this.topo = -1;
    }

    public boolean isEmpty(){
        return topo == -1;
    }

    public boolean isFull(){
        return topo == criticos.length -1;
    }

    //aleração caio no tratamento primeiro if
    public void push(Chamado critico){
        if (critico == null) {
        System.out.println("Chamado inválido");
        return;
        }
        if (isFull()){
            System.out.println("Críticos está cheio");
        } else {
            criticos[++topo] = critico; 
        }
    }

    //alteração caio para realmente remover da Pilha o primeiro do topo e retornar qual foi removido
    public Chamado pop(){
        if(isEmpty()){
            System.out.println("Críticos está vazio");
            return null;
        } else {
            Chamado removido = criticos[topo];
            criticos[topo] = null;
            topo--;
            return removido;
        }
    }

    public Chamado top(){
        if (isEmpty()) {
            System.out.println("Críticos está vazio");
            return null;
        } else {
            return criticos[topo];
        }
    }
    //alteração caio verificar o tamanho da lista
    public int size(){
        return topo +1;
    }


}

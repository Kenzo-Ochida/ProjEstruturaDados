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

    public void push(Chamado critico){
        if (isFull()){
            System.out.println("Críticos está cheio");
        } else {
            criticos[++topo] = critico; 
        }
    }

    public Chamado pop(){
        if(isEmpty()){
            System.out.println("Críticos está vazio");
            return null;
        } else {
            return criticos[topo --];
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
}

public class Fila{
    private Chamado[] comuns;
    private int inicio;
    private int fim;
    private int quantidade;

    public Fila(int capacidade){
        this.comuns = new Chamado[capacidade];
        this.inicio = 0;
        this.fim = 0;
        this.quantidade = 0;
    }

    public boolean isEmpty(){
        return quantidade == 0;
    }
    public boolean isFull(){
        return quantidade == comuns.lenght;
    }
    public void enqueue(Chamado comum){
        if(comum == null){
            System.out.println("Chamado invalido");
            return;
        }
        if(isFull()){
            System.out.println("Fila está cheia");
        } else{
            comuns[fim] = comum;
            fim = (fim + 1) % comuns.lenght;
            quantidade++;
        }
    }

    public Chamado dequeue(){
        if (isEmpty()){
            System.out.println("Fila está vazia");
            return null;
        } else {
            Chamado removido = comuns[inicio];
            comuns[inicio] = null;
            inicio = (inicio + 1) % comuns.lenght;
            quantidade --;
            return removido;
        }
    }

    public Chamado front(){
        if(isEmpty()){
            System.out.println("Fila está vazia");
            return null;
        } else{
            return comuns[inicio];
        }
    }

    public int size(){
        return quantidade;
    }
}
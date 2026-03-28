import java.util.ArrayList;

public class Main{
    public static void main(String[] args) {

        static ArrayList<Chamado> ocorrencia = new ArrayList<>(50); // tudo entra por aqui
        static ArrayList<Chamado> historico = new ArrayList<>(); // mostra todos os chamados
        static ArrayList<Chamado> ativos = new ArrayList<>(50); // so os ativos

        Pilha pilha = new Pilha(30);
        Fila fila = new Fila(30);

        ocorrencia.add(new Chamado(1, "Centro", "Vazamento de água em via pública", 5, "ABERTO"));
        ocorrencia.add(new Chamado(2, "Jardim América", "Poste de iluminação apagado", 3, "EM_ATENDIMENTO"));
        ocorrencia.add(new Chamado(3, "Vila Nova", "Buraco grande na rua principal", 4, "ABERTO"));
        ocorrencia.add(new Chamado(4, "Santa Clara", "Coleta de lixo não realizada", 2, "FINALIZADO"));
        ocorrencia.add(new Chamado(5, "São José", "Semáforo com defeito", 5, "EM_ATENDIMENTO"));
        ocorrencia.add(new Chamado(6, "Boa Vista", "Árvore caída bloqueando calçada", 4, "ABERTO"));
        ocorrencia.add(new Chamado(7, "Industrial", "Esgoto entupido", 5, "ABERTO"));
        ocorrencia.add(new Chamado(8, "Morada do Sol", "Terreno baldio com mato alto", 2, "FINALIZADO"));
        ocorrencia.add(new Chamado(9, "Alvorada", "Falta de sinalização em cruzamento", 3, "EM_ATENDIMENTO"));
        ocorrencia.add(new Chamado(10, "Parque das Flores", "Animal morto em via pública", 4, "ABERTO"));

        public static void filtro(){
            for (Chamado oc : ocorrencia){
                if (oc.getNivelDeUrgencia() >= 4){
                    pilha.push(oc);
                    historico.add(oc);
                    System.out.println("Foi adicionado na fila prioritaria");
                } else{
                    fila.enqueue(oc);
                    historico.add(oc);
                    System.out.println("Foi adicionado na fila");
                }
            }
        }

        public void atender(){
            for (Chamado oc : ocorrencia){
                if(oc.getNivelDeUrgencia() >= 4){
                    pilha.pop(oc);
                    oc.setStatus("EM_ATENDIMENTO");

                } else{
                    fila.dequeue(oc);
                    oc.setStatus("EM_ATENDIMENTO");
                }

            }
        }

        for(Chamado c : historico) {
            c.getStatus();
        }

        public void finalizar(){
            for (Chamado oc : ocorrencia){
                if(oc.getNivelDeUrgencia() >= 4){
                    ativo.remove(oc);
                } else{
                    ativo.remove(oc);
                }

            }
        }




    }
}

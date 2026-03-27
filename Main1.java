import java.util.ArrayList;

public class Main1 {

    static ArrayList<Chamado> ocorrencia = new ArrayList<>(50);
    static ArrayList<Chamado> historico = new ArrayList<>();
    static ArrayList<Chamado> ativo = new ArrayList<>(50);

    static Pilha pilha = new Pilha(30);
    static Fila fila = new Fila(30);

    public static void main(String[] args) {

        ocorrencia.add(new Chamado(1, "Centro", "Vazamento de água em via pública", 5, "ABERTO"));
        ocorrencia.add(new Chamado(2, "Jardim América", "Poste de iluminação apagado", 3, "ABERTO"));
        ocorrencia.add(new Chamado(3, "Vila Nova", "Buraco grande na rua principal", 4, "ABERTO"));
        ocorrencia.add(new Chamado(4, "Santa Clara", "Coleta de lixo não realizada", 2, "ABERTO"));
        ocorrencia.add(new Chamado(5, "São José", "Semáforo com defeito", 5, "ABERTO"));
        ocorrencia.add(new Chamado(6, "Boa Vista", "Árvore caída bloqueando calçada", 4, "ABERTO"));
        ocorrencia.add(new Chamado(7, "Industrial", "Esgoto entupido", 5, "ABERTO"));
        ocorrencia.add(new Chamado(8, "Morada do Sol", "Terreno baldio com mato alto", 2, "ABERTO"));
        ocorrencia.add(new Chamado(9, "Alvorada", "Falta de sinalização em cruzamento", 3, "ABERTO"));
        ocorrencia.add(new Chamado(10, "Parque das Flores", "Animal morto em via pública", 4, "ABERTO"));

        filtro();

        atender();
        atender();
        atender();

        finalizar(7);
        finalizar(10);

        for (Chamado c : historico) {
            System.out.println("ID: " + c.getId() + " | Status: " + c.getStatus());
        }
    }

    public static void filtro() {
        for (Chamado oc : ocorrencia) {
            if (oc.getNivelDeUrgencia() >= 4) {
                pilha.push(oc);
                System.out.println("Foi adicionado na pilha prioritária");
            } else {
                fila.enqueue(oc);
                System.out.println("Foi adicionado na fila");
            }

            historico.add(oc);
        }
    }

    public static void atender() {
        Chamado atendido;

        if (!pilha.isEmpty()) {
            atendido = pilha.pop();
        } else if (!fila.isEmpty()) {
            atendido = fila.dequeue();
        } else {
            System.out.println("Não há chamados para atender");
            return;
        }

        atendido.setStatus("EM_ATENDIMENTO");
        ativo.add(atendido);
        atualizarStatusHistorico(atendido.getId(), "EM_ATENDIMENTO");

        System.out.println("Chamado " + atendido.getId() + " em atendimento");
    }

    public static void finalizar(int id) {
        for (int i = 0; i < ativo.size(); i++) {
            if (ativo.get(i).getId() == id) {
                Chamado finalizado = ativo.remove(i);
                finalizado.setStatus("FINALIZADO");
                atualizarStatusHistorico(id, "FINALIZADO");

                System.out.println("Chamado " + id + " finalizado");
                return;
            }
        }

        System.out.println("Chamado não encontrado na lista de ativos");
    }

    public static void atualizarStatusHistorico(int id, String novoStatus) {
        for (Chamado c : historico) {
            if (c.getId() == id) {
                c.setStatus(novoStatus);
                return;
            }
        }
    }
}
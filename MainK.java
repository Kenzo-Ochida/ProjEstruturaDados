import java.util.ArrayList;

public class MainK {
    static ArrayList<Chamado> ocorrencia = new ArrayList<>(50);
    static ArrayList<Chamado> historico = new ArrayList<>();
    static ArrayList<Chamado> ativos = new ArrayList<>(50);

    static Pilha pilha = new Pilha(30);
    static Fila fila = new Fila(30);

    public static void main(String[] args) {
        adicionarChamado(new Chamado(10, "PRIMEIRO", "Vazamento", 5, "ABERTO"));
        adicionarChamado(new Chamado(2, "Centro", "Poste apagado", 3, "ABERTO"));
        adicionarChamado(new Chamado(3, "Vila Nova", "Buraco", 4, "ABERTO"));
        adicionarChamado(new Chamado(4, "Centro", "Lixo", 2, "ABERTO"));
        adicionarChamado(new Chamado(5, "São José", "Semáforo", 5, "ABERTO"));
        adicionarChamado(new Chamado(6, "Vila Nova", "Árvore caída", 4, "ABERTO"));
        adicionarChamado(new Chamado(7, "Industrial", "Esgoto", 5, "ABERTO"));
        adicionarChamado(new Chamado(8, "Centro", "Mato alto", 2, "ABERTO"));
        adicionarChamado(new Chamado(9, "Alvorada", "Sinalização", 3, "ABERTO"));
        adicionarChamado(new Chamado(10, "ULTIMO", "Animal morto", 4, "ABERTO"));

        System.out.println("=== FILTRO ===");
        filtro();

        System.out.println("\n=== HISTÓRICO ===");
        mostrarHistorico();

        System.out.println("\n=== ATENDIMENTO ===");
        atender();

        System.out.println("\n=== HISTÓRICO ===");
        mostrarHistorico();

        System.out.println("\n=== FINALIZAÇÃO ===");
        finalizar();

        System.out.println("\n=== HISTÓRICO ===");
        mostrarHistorico();

        System.out.println("\n=== RELATÓRIOS ===");
        totalPorBairro();
        mediaUrgencia();
        chamadosPendentes();
        rankingBairros();
    }

    public static void adicionarChamado(Chamado c) {
        if (c == null) return;

        if (idExiste(c.getId())) {
            System.out.println("O chamado '"+ c.getDescricao() +  "' não foi adicionado" + " | ID duplicado: " + c.getId());
            return;
        }

        if (c.getNivelDeUrgencia() < 1 || c.getNivelDeUrgencia() > 5) {
            System.out.println("Urgência inválida");
            return;
        }

        ocorrencia.add(c);
    }

    public static boolean idExiste(int id) {
        for (Chamado c : ocorrencia) {
            if (c.getId() == id) return true;
        }
        return false;
    }

    public static void filtro() {
        for (Chamado c : ocorrencia) {
            if (c.getNivelDeUrgencia() >= 4) {
                pilha.push(c);
                System.out.println("Pilha: " + c.getId());
            } else {
                fila.enqueue(c);
                System.out.println("Fila: " + c.getId());
            }
            historico.add(c);
        }
    }

    public static void atender() {
        while (!pilha.isEmpty() || !fila.isEmpty()) {
            Chamado c;

            if (!pilha.isEmpty()) {
                c = pilha.pop();
                System.out.println("O chamado urgente: " + c.getId() + " está sendo atendido");
            } else {
                c = fila.dequeue();
                System.out.println("O chamado comum: " + c.getId() + " está sendo atendido");
            }

            if (c != null) {
                c.setStatus("EM_ATENDIMENTO");
                ativos.add(c);
            }
        }
    }

    public static void finalizar() {
        for (Chamado c : ativos) {
            c.setStatus("FINALIZADO");
            atualizarHistorico(c.getId(), "FINALIZADO");
        }
        ativos.clear();
    }

    public static void atualizarHistorico(int id, String status) {
        for (Chamado c : historico) {
            if (c.getId() == id) {
                c.setStatus(status);
                break;
            }
        }
    }

    public static void mostrarHistorico() {
        for (Chamado c : historico) {
            System.out.println(c.getId() + " | " + c.getBairro() + " | " + c.getStatus());
        }
    }

    public static void totalPorBairro() {
        System.out.println("\nTotal por bairro:");

        ArrayList<String> bairros = new ArrayList<>();
        ArrayList<Integer> contagem = new ArrayList<>();

        for (Chamado c : historico) {
            String b = c.getBairro();

            int index = bairros.indexOf(b);
            if (index == -1) {
                bairros.add(b);
                contagem.add(1);
            } else {
                contagem.set(index, contagem.get(index) + 1);
            }
        }

        for (int i = 0; i < bairros.size(); i++) {
            System.out.println(bairros.get(i) + ": " + contagem.get(i));
        }
    }

    public static void mediaUrgencia() {
        double soma = 0;

        for (Chamado c : historico) {
            soma += c.getNivelDeUrgencia();
        }

        double media = soma / historico.size();
        System.out.printf("Média de urgência: %.2f\n", media);
    }

    public static void chamadosPendentes() {
        System.out.println("\nPendentes:");

        for (Chamado c : historico) {
            if (!c.getStatus().equals("FINALIZADO")) {
                System.out.println(c.getId());
            }
        }
    }

    public static void rankingBairros() {
        ArrayList<String> bairros = new ArrayList<>();
        ArrayList<Integer> contagem = new ArrayList<>();

        for (Chamado c : historico) {
            String b = c.getBairro();

            int index = bairros.indexOf(b);
            if (index == -1) {
                bairros.add(b);
                contagem.add(1);
            } else {
                contagem.set(index, contagem.get(index) + 1);
            }
        }

        for (int i = 0; i < contagem.size(); i++) {
            int max = i;

            for (int j = i + 1; j < contagem.size(); j++) {
                if (contagem.get(j) > contagem.get(max)) {
                    max = j;
                }
            }

            int tempC = contagem.get(i);
            contagem.set(i, contagem.get(max));
            contagem.set(max, tempC);

            String tempB = bairros.get(i);
            bairros.set(i, bairros.get(max));
            bairros.set(max, tempB);
        }

        System.out.println("\nRanking de bairros:");
        for (int i = 0; i < bairros.size(); i++) {
            System.out.println((i + 1) + "º " + bairros.get(i) + " - " + contagem.get(i));
        }
    }
}

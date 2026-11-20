# AEDS 2 — Algoritmos e Estruturas de Dados 2

![C](https://img.shields.io/badge/linguagem-C-blue)
![Java](https://img.shields.io/badge/linguagem-Java-orange)

Repositório com os **trabalhos práticos** e os **exercícios de treino** da disciplina de Algoritmos e Estruturas de Dados 2 (Ciência da Computação — PUC Minas).

Os códigos estão escritos em **C** e **Java** e cobrem desde recursão e manipulação de strings até ordenação, estruturas lineares e árvores balanceadas.

---

## 📚 Conteúdos abordados

| Tema | Onde aparece |
|---|---|
| Recursão e strings | TP1, `treinamento/` |
| Pesquisa sequencial e binária | TP2, TP3, `treinamento/` |
| Ordenação: Seleção, Inserção, Bolha | TP2, TP4, `treinamento/` |
| Ordenação: Counting, Radix, Bucket | TP2, `treinamento/` |
| Ordenação: Mergesort, Heapsort, Quicksort | TP3, `treinamento/verde/` |
| Ordenação parcial (top-k) | TP4, `treinamento/ordenacao_parcial/` |
| Listas (sequencial, simples, dupla e circular) | TP2, TP3, `treinamento/` |
| Pilhas e filas (inclusive fila circular) | TP2, `treinamento/` |
| Matriz encadeada e estruturas híbridas | `treinamento/matriz_lista/`, `treinamento/estruturas_hibridas/` |
| Árvore binária de busca e caminhamentos | TP4, `treinamento/arvore_lista/` |
| Árvore AVL e rotações | TP4, `treinamento/balanceamento/` |

---

## 📁 Estrutura do repositório

```
AEDS-2/
├── trabalho pratico 1/      # Recursão e manipulação de strings
├── trabalho pratico 2/      # Modelagem de Veiculo, ordenação, listas, pilha e fila
├── trabalho pratico 3/      # Merge, Heap e Quicksort (em vetor e em lista)
├── trabalho pratico 4/      # Ordenação parcial, árvore binária e AVL
└── treinamento/             # Listas de exercícios e simulados de prova
    ├── arvore_lista/        # ABB, caminhamentos, aplicações e transformações
    ├── balanceamento/       # AVL e rotações
    ├── matriz_lista/        # Matriz encadeada e diagonal em lista dupla
    ├── ordenacao_parcial/   # Seleção, inserção e quicksort parciais
    ├── estruturas_hibridas/ # Estruturas combinadas
    ├── lista_02/, lista_2.1/# Listas de revisão para as provas teóricas
    ├── treino_prova2/       # Simulados da Prova 2 (PROVA A a G)
    ├── treino_prova3/       # Material de treino para a Prova 3
    └── verde/               # Exercícios no formato do juiz online (Verde)
```

## ⏱️ Complexidade dos algoritmos de ordenação

| Algoritmo | Melhor caso | Caso médio | Pior caso | Estável? |
|---|---|---|---|---|
| Seleção | Θ(n²) | Θ(n²) | Θ(n²) | Não |
| Inserção | Θ(n) | Θ(n²) | Θ(n²) | Sim |
| Bolha | Θ(n)* | Θ(n²) | Θ(n²) | Sim |
| Mergesort | Θ(n log n) | Θ(n log n) | Θ(n log n) | Sim |
| Heapsort | Θ(n log n) | Θ(n log n) | Θ(n log n) | Não |
| Quicksort | Θ(n log n) | Θ(n log n) | Θ(n²) | Não |
| Counting Sort | Θ(n + k) | Θ(n + k) | Θ(n + k) | Sim |
| Radix Sort | Θ(d·(n + k)) | Θ(d·(n + k)) | Θ(d·(n + k)) | Sim |
| Bucket Sort | Θ(n + k) | Θ(n + k) | Θ(n²) | Sim** |

\* Com a otimização de parar quando nenhuma troca ocorre.
\** Quando cada balde é ordenado com um método estável (por exemplo, Inserção).

**Pesquisa:** sequencial Θ(n) · binária Θ(log n) · ABB Θ(log n) no caso médio e Θ(n) no pior caso · AVL Θ(log n) no pior caso.

---

## 👩‍💻 Autora

**Paula Moschioni** — [github.com/paulamoschioni](https://github.com/paulamoschioni)

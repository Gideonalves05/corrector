package com.gideon.Writing_Corrector_API.service.corrector;


import org.springframework.stereotype.Service;

@Service
public class PromptService {

    public String criarPrompt(String textoRedacao) {
        return String.format(
                "Você é um corretor de redações, faça uma correção se baseando nas cinco competências exigidas para a correção de redações, faça uma avaliação completa do texto fornecido, atribuindo uma nota de 0 a 200 para cada competência. Além das notas, forneça recomendações detalhadas de melhorias e, se necessário, inclua exemplos de frases reescritas, sugestões de citações ou argumentos para fortalecer o texto.\n" +
                        "\n" +
                        "### Competências para Avaliação:\n" +
                        "\n" +
                        "1. *Competência 1:* Avalie o domínio da norma culta da língua escrita.\n" +
                        "   - Verifique a ortografia, acentuação, pontuação e gramática em geral.\n" +
                        "   - Identifique erros de concordância verbal e nominal, problemas de regência, crase, e uso de pronomes.\n" +
                        "   - Sugestões de melhoria devem incluir explicações sobre os erros e exemplos de como reescrever trechos de forma correta.\n" +
                        "\n" +
                        "2. *Competência 2:* Verifique a compreensão do tema e a capacidade de argumentação.\n" +
                        "   - Avalie se o texto atende ao tema proposto e se desenvolve argumentos sólidos e relevantes.\n" +
                        "   - Verifique se o autor mantém uma linha de raciocínio clara e coerente com o tema.\n" +
                        "   - Recomende como melhorar a articulação dos argumentos e, se necessário, sugira fontes, citações ou exemplos práticos para fortalecer a argumentação.\n" +
                        "\n" +
                        "3. *Competência 3:* Analise a coesão e coerência do texto, verificando a organização das ideias.\n" +
                        "   - Verifique a estrutura do texto, incluindo introdução, desenvolvimento e conclusão.\n" +
                        "   - Avalie o uso de conectivos e a fluidez entre as partes do texto.\n" +
                        "   - Forneça recomendações sobre como reorganizar parágrafos, usar melhor os conectores ou melhorar a transição entre ideias.\n" +
                        "\n" +
                        "4. *Competência 4:* Avalie o conhecimento dos mecanismos linguísticos e a adequação das escolhas vocabulares.\n" +
                        "   - Verifique a riqueza lexical, repetição de palavras e uso de expressões adequadas ao gênero e ao tema.\n" +
                        "   - Sugira palavras ou expressões mais apropriadas e identifique casos de repetição excessiva ou termos inadequados.\n" +
                        "   - Indique formas de diversificar o vocabulário, usando sinônimos ou expressões mais precisas.\n" +
                        "\n" +
                        "5. *Competência 5:* Verifique a elaboração de uma proposta de intervenção para o problema abordado, respeitando os direitos humanos.\n" +
                        "   - Avalie se a proposta de intervenção é concreta, detalhada e viável.\n" +
                        "   - Verifique se a proposta está alinhada ao tema e se respeita os direitos humanos, sem incitar preconceitos, violência ou discriminação.\n" +
                        "   - Sugira maneiras de melhorar a clareza e a viabilidade da proposta, além de indicar elementos que poderiam ser acrescentados (como agentes, ações, detalhamento de meios).\n" +
                        "\n" +
                        "### Formato de Resposta Esperado:\n" +
                        "\n" +
                        "*Competência 1:*\n" +
                        "- *Nota:* [0-200]\n" +
                        "- *Erros Identificados:* [Listar os principais erros gramaticais encontrados no texto].\n" +
                        "- *Recomendações:* [Sugestões de melhoria com exemplos de reescrita].\n" +
                        "  \n" +
                        "*Competência 2:*\n" +
                        "- *Nota:* [0-200]\n" +
                        "- *Compreensão do Tema:* [Explicar se o tema foi abordado corretamente e como melhorar a argumentação].\n" +
                        "- *Sugestões:* [Citações ou argumentos que poderiam ser usados para enriquecer o texto].\n" +
                        "\n" +
                        "*Competência 3:*\n" +
                        "- *Nota:* [0-200]\n" +
                        "- *Organização das Ideias:* [Avaliação da estrutura do texto, coesão entre parágrafos e uso de conectivos].\n" +
                        "- *Recomendações:* [Dicas para melhorar a fluidez e a organização do texto].\n" +
                        "\n" +
                        "*Competência 4:*\n" +
                        "- *Nota:* [0-200]\n" +
                        "- *Escolhas Vocabulares:* [Análise da riqueza lexical e adequação do vocabulário].\n" +
                        "- *Sugestões:* [Palavras mais adequadas e formas de evitar repetições].\n" +
                        "\n" +
                        "*Competência 5:*\n" +
                        "- *Nota:* [0-200]\n" +
                        "- *Proposta de Intervenção:* [Avaliação da clareza, viabilidade e respeito aos direitos humanos].\n" +
                        "- *Recomendações:* [Sugestões para melhorar a proposta de intervenção].\n" +
                        "\n" +
                        "*Nota Final:* [Soma das notas das cinco competências].\n" +
                        "\n" +
                        "### Observações Finais:\n" +
                        "Inclua qualquer observação geral sobre a redação, como pontos positivos, aspectos que o autor se destacou, ou sugestões adicionais de estudo para melhorar o desempenho em futuras redações.",
                textoRedacao);
    }
}
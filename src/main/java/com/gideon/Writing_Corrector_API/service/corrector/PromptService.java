package com.gideon.Writing_Corrector_API.service.corrector;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
public class PromptService {

    public Prompt generatePrompt(String text) {
        String fullPrompt = text + "Você é um corretor de redações, faça uma correção se baseando nas cinco competências exigidas para a correção de redações, faça uma avaliação completa do texto fornecido, atribuindo uma nota de 0 a 200 para cada competência. Além das notas, forneça recomendações detalhadas de melhorias e, se necessário, inclua exemplos de frases reescritas, sugestões de citações ou argumentos para fortalecer o texto.\n" + //
                "\n" +
                "Competências para Avaliação:\n" +
                "Competência 1: Avalie o domínio da norma culta da língua escrita.   - Verifique a ortografia, acentuação, pontuação e gramática em geral.   - Identifique erros de concordância verbal e nominal, problemas de regência, crase, e uso de pronomes.   - Sugestões de melhoria devem incluir explicações sobre os erros e exemplos de como reescrever trechos de forma correta.\n" + //
                "\n" +
                "Competência 2: Verifique a compreensão do tema e a capacidade de argumentação.   - Avalie se o texto atende ao tema proposto e se desenvolve argumentos sólidos e relevantes.   - Verifique se o autor mantém uma linha de raciocínio clara e coerente com o tema.   - Recomende como melhorar a articulação dos argumentos e, se necessário, sugira fontes, citações ou exemplos práticos para fortalecer a argumentação.\n" + //
                "\n" +
                "Competência 3: Analise a coesão e coerência do texto, verificando a organização das ideias.   - Verifique a estrutura do texto, incluindo introdução, desenvolvimento e conclusão.   - Avalie o uso de conectivos e a fluidez entre as partes do texto.   - Forneça recomendações sobre como reorganizar parágrafos, usar melhor os conectores ou melhorar a transição entre ideias.\n" + //
                "\n" +
                "Competência 4: Avalie o conhecimento dos mecanismos linguísticos e a adequação das escolhas vocabulares.   - Verifique a riqueza lexical, repetição de palavras e uso de expressões adequadas ao gênero e ao tema.   - Sugira palavras ou expressões mais apropriadas e identifique casos de repetição excessiva ou termos inadequados.   - Indique formas de diversificar o vocabulário, usando sinônimos ou expressões mais precisas.\n" + //
                "\n" +
                "Competência 5: Verifique a elaboração de uma proposta de intervenção para o problema abordado, respeitando os direitos humanos.   - Avalie se a proposta de intervenção é concreta, detalhada e viável.   - Verifique se a proposta está alinhada ao tema e se respeita os direitos humanos, sem incitar preconceitos, violência ou discriminação.   - Sugira maneiras de melhorar a clareza e a viabilidade da proposta, além de indicar elementos que poderiam ser acrescentados (como agentes, ações, detalhamento de meios).\n" + //
                "\n" +
                "Formato de Resposta Esperado:\n" +
                "Competência 1:\n" +
                "\n" +
                "Nota: [0-200]\n" +
                "Erros Identificados: [Listar os principais erros gramaticais encontrados no texto].\n" + //
                "Recomendações: [Sugestões de melhoria com exemplos de reescrita].    Competência 2:\n" + //
                "Nota: [0-200]\n" +
                "Compreensão do Tema: [Explicar se o tema foi abordado corretamente e como melhorar a argumentação].\n" + //
                "Sugestões: [Citações ou argumentos que poderiam ser usados para enriquecer o texto].\n" + //
                "Competência 3:\n" +
                "\n" +
                "Nota: [0-200]\n" +
                "Organização das Ideias: [Avaliação da estrutura do texto, coesão entre parágrafos e uso de conectivos].\n" + //
                "Recomendações: [Dicas para melhorar a fluidez e a organização do texto].\n" + //
                "Competência 4:\n" +
                "\n" +
                "Nota: [0-200]\n" +
                "Escolhas Vocabulares: [Análise da riqueza lexical e adequação do vocabulário].\n" + //
                "Sugestões: [Palavras mais adequadas e formas de evitar repetições].\n" + //
                "Competência 5:\n" +
                "\n" +
                "Nota: [0-200]\n" +
                "Proposta de Intervenção: [Avaliação da clareza, viabilidade e respeito aos direitos humanos].\n" + //
                "Recomendações: [Sugestões para melhorar a proposta de intervenção].\n" +
                "Nota Final: [Soma das notas das cinco competências].\n" +
                "\n" + //
                "Observações Finais:\n" +
                "Inclua qualquer observação geral sobre a redação, como pontos positivos, aspectos que o autor se destacou, ou sugestões adicionais de estudo para melhorar o desempenho em futuras redações. Texto da redação enviado: Desafios para a valorização de comunidades e povos tradicionais no Brasil A diversidade cultural é uma das principais características do Brasil, evidenciada pela rica presença de comunidades e povos tradicionais, como indígenas, quilombolas e ribeirinhos. Esses grupos desempenham um papel crucial na preservação de saberes ancestrais e na manutenção de uma relação sustentável com o meio ambiente. No entanto, a valorização dessas comunidades enfrenta desafios significativos, que vão desde o preconceito até a falta de políticas públicas efetivas. A ausência de reconhecimento pleno dessas culturas ameaça sua sobrevivência e a manutenção de sua contribuição ao patrimônio nacional. Um dos principais desafios enfrentados por esses povos é o preconceito histórico enraizado na sociedade brasileira. Desde o período colonial, os povos indígenas e africanos foram submetidos a uma hierarquia social que marginalizava suas culturas e direitos. Embora a Constituição de 1988 tenha garantido direitos fundamentais, como o reconhecimento de territórios tradicionais, a discriminação cultural e racial ainda persiste, dificultando a inclusão plena dessas comunidades no tecido social e econômico do país. Assim, o preconceito continua a ser uma barreira significativa para a valorização e respeito dessas culturas. Outro obstáculo relevante é a falta de políticas públicas eficazes que protejam os direitos dessas comunidades. A demarcação de terras indígenas, por exemplo, é frequentemente interrompida por pressões políticas e econômicas, especialmente em áreas de interesse do agronegócio e da mineração. Além disso, programas de desenvolvimento raramente incluem as necessidades específicas dos povos tradicionais, resultando em uma exclusão social e econômica que perpetua a vulnerabilidade dessas comunidades. A falta de políticas públicas impede, portanto, que esses povos possam viver e preservar seus modos de vida com dignidade. Por fim, a ameaça ambiental representa um terceiro grande desafio para essas comunidades. O avanço do desmatamento e das mudanças climáticas afeta diretamente os territórios tradicionais, pondo em risco a sobrevivência de várias comunidades que dependem de recursos naturais para sua subsistência. A destruição de florestas, rios e outros biomas não só compromete o meio ambiente, mas também ameaça a cultura e o modo de vida dessas populações, que possuem uma relação de simbiose com a natureza. Portanto, é fundamental que o Brasil enfrente esses desafios para valorizar suas comunidades e povos tradicionais. É preciso que o preconceito seja combatido através de uma educação que promova o respeito à diversidade cultural, além de ser necessário o fortalecimento de políticas públicas que garantam os direitos territoriais e sociais dessas populações. Somente assim o país poderá proteger e preservar sua rica herança cultural, que é essencial não apenas para a identidade nacional, mas também para a sustentabilidade ambiental."
                ;
        ;
        return new Prompt(new UserMessage(fullPrompt));
    }
}

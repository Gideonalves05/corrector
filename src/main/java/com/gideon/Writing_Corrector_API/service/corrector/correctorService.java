package com.gideon.Writing_Corrector_API.service.corrector;

import com.gideon.Writing_Corrector_API.model.corrector.correctorModel;
import com.gideon.Writing_Corrector_API.model.repository.correctorRepository;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class correctorService {

    @Autowired
    private correctorRepository correctionRepository;

    private final PdfReaderService pdfReaderService;
    private final PromptService promptService;
    private final VertexAiGeminiChatModel chatModel;

    public correctorService(PdfReaderService pdfReaderService, PromptService promptService, VertexAiGeminiChatModel chatModel) {
        this.pdfReaderService = pdfReaderService;
        this.promptService = promptService;
        this.chatModel = chatModel;
    }

    public List<correctorModel> showAllCorrectionItems(int pageNumber, int pageSize) {
        return correctionRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    public Mono<List<Generation>> corrigirRedacao(String caminhoArquivo) throws IOException {
        String textoRedacao;

        // Extrair o texto do PDF
        try {
            textoRedacao = pdfReaderService.extrairTextoDoPDF(caminhoArquivo);
        } catch (RuntimeException e) {
            throw new IOException("Erro ao processar o PDF. Verifique se o arquivo está correto.", e);
        }

        // Criar o prompt com o texto da redação
        String promptText = promptService.criarPrompt(textoRedacao);
        Prompt prompt = new Prompt(new UserMessage(promptText));


        // Enviar o prompt para a IA e processar a resposta
        return chatModel
                .stream(prompt)
                .next()
                .map(this::processarRespostaIA);
             //   .defaultIfEmpty((List<Generation>) this.corrigirRedacao(textoRedacao));
    }



    private List<Generation> processarRespostaIA(ChatResponse response) {
        // Log para verificar o conteúdo completo da resposta durante a depuração
        System.out.println("ChatResponse: " + response);



        // Verifica se há resultados e tenta extrair o texto da primeira geração
        if (response.getResults() != null && !response.getResults().isEmpty()) {
            AssistantMessage assistantMessage = response.getResults().get(0).getOutput();

         //   if (assistantMessage != null) {
        //        String responseText = assistantMessage.getContent();
       //         return responseText != null ? responseText : "Não foi possível gerar um feedback.";
      //      }
        }

        // Caso não haja conteúdo ou ocorram falhas, retorna uma mensagem padrão
        return response.getResults();
    }




}

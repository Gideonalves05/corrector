package com.gideon.Writing_Corrector_API.controller.corrector;

import com.gideon.Writing_Corrector_API.service.corrector.PdfReaderService;
import com.gideon.Writing_Corrector_API.service.corrector.correctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("/api/corrector")
public class correctorController {

    private final correctorService correctorService;
    private final PdfReaderService pdfReaderService;

    @Autowired
    public correctorController(correctorService correctorService, PdfReaderService pdfReaderService) {
        this.correctorService = correctorService;
        this.pdfReaderService = pdfReaderService;
    }

    @PostMapping(value = "/submit-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> submitPdf(@RequestParam("file") MultipartFile file) {
        return Mono.fromCallable(() -> {
                    // Salvar arquivo temporário
                    File tempFile = File.createTempFile("uploaded-", ".pdf");
                    file.transferTo(tempFile);

                    // Extrair texto do PDF
                    String extractedText = pdfReaderService.extrairTextoDoPDF(tempFile.getAbsolutePath());

                    System.out.println("Texto extraiodo" + extractedText);

                    // Excluir arquivo temporárioa
                    tempFile.delete();

                    return extractedText;

                })
                .flatMap(extractedText ->
                        // Obter o feedback da correção
                        correctorService.getCorrectionFeedback(extractedText)
                                .collectList() // Converte o Flux<String> para List<String>
                                .map(feedbackList -> ResponseEntity.ok(Map.of("feedback", feedbackList)))

                );


    }
}

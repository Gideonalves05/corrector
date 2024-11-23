package com.gideon.Writing_Corrector_API.controller.corrector;

import com.gideon.Writing_Corrector_API.model.user.UserModel;
import com.gideon.Writing_Corrector_API.service.corrector.PdfReaderService;
import com.gideon.Writing_Corrector_API.service.corrector.correctorService;
import com.gideon.Writing_Corrector_API.service.user.UserService;
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
import java.util.Optional;

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
    public Mono<ResponseEntity<?>> submitPdf(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("title") String title) {
        return Mono.fromCallable(() -> {

                    // Extrair texto do PDF
                    File tempFile = File.createTempFile("uploaded-", ".pdf");
                    file.transferTo(tempFile);

                    String extractedText = pdfReaderService.extrairTextoDoPDF(tempFile.getAbsolutePath());
                    tempFile.delete();

                    // Salvar redação no banco
                   UserModel user = UserService.getUserById(userId);
                   correctorService.saveEssay(title, extractedText, user.orElse(null));
                    return extractedText;

                })
                .flatMap(extractedText ->
                        correctorService.getCorrectionFeedback(extractedText)
                                .collectList()
                                .map(feedbackList -> ResponseEntity.ok(Map.of("feedback", feedbackList)))
                );
    }
}
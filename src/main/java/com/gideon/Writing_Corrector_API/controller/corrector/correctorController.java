package com.gideon.Writing_Corrector_API.controller.corrector;

import com.gideon.Writing_Corrector_API.model.user.UserModel;
import com.gideon.Writing_Corrector_API.service.corrector.PdfReaderService;
import com.gideon.Writing_Corrector_API.service.corrector.correctorService;
import com.gideon.Writing_Corrector_API.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    private final UserService UserService;


    @Autowired
    public correctorController(correctorService correctorService, PdfReaderService pdfReaderService, UserService UserService) {
        this.correctorService = correctorService;
        this.pdfReaderService = pdfReaderService;
        this.UserService = UserService;

    }

    @PostMapping(value = "/submit-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> submitPdf(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long Id,
            @RequestParam("title") String title) {
        return Mono.fromCallable(() -> {

                    File tempFile = File.createTempFile("uploaded-", ".pdf");
                    file.transferTo(tempFile);

                    String extractedText = pdfReaderService.extrairTextoDoPDF(tempFile.getAbsolutePath());
                    tempFile.delete();

                    Optional<UserModel> user = UserService.getUserById(Id);
                    return Map.of("extractedText", extractedText, "user", user.orElse(null));
                })
                .flatMap(data ->
                        correctorService.getCorrectionFeedback(
                                        (String) data.get("extractedText"),
                                        title,
                                        (UserModel) data.get("user"))
                                .collectList()
                                .map(feedbackList -> ResponseEntity.ok(Map.of("", feedbackList)))
                );
    }

}
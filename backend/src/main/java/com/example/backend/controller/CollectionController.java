package com.example.backend.controller;

import com.example.backend.dto.CollectionDTO;
import com.example.backend.model.Collection;
import com.example.backend.service.CollectionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;

@Controller
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ModelAttribute
    public void addAttributes(Model model, HttpSession session) {
        if (session.getAttribute("logged")==null
                || session.getAttribute("user")==null
                || !session.getAttribute("logged").equals(true)) {
            model.addAttribute("logged", false);
        }else
            model.addAttribute("logged", true);
    }

    @GetMapping("/{id}")
    public String getCollection(Model model, @PathVariable long id) {
        CollectionDTO collection = collectionService.findCollectionById(id);
        model.addAttribute("collection", collection);
        return "collection-example";
    }

    @GetMapping("/{id}/image")
    @Transactional
    public ResponseEntity<byte[]> downloadImage(@PathVariable long id) throws SQLException, IOException {
        Optional<Collection> optionalCollection = collectionService.findById(id);

        if (optionalCollection.isPresent() && optionalCollection.get().getBookImage() != null) {
            Collection collection = optionalCollection.get();
            InputStream imageStream = collection.getBookImage().getBinaryStream();

            // Convert InputStream to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = imageStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust content type if needed

            return ResponseEntity.ok().headers(headers).body(byteArrayOutputStream.toByteArray());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

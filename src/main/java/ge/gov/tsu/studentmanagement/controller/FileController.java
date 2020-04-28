package ge.gov.tsu.studentmanagement.controller;

import ge.gov.tsu.studentmanagement.apiutils.file.FileUtil;
import ge.gov.tsu.studentmanagement.rest.response.ResponseObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@RestController
public class FileController extends BaseController {


    public static ResponseEntity<byte[]> getFileResponse(String filePath) {
        final HttpHeaders headers = new HttpHeaders();
        try {
            byte[] file = FileUtil.getOriginalFile(filePath);
            return new ResponseEntity<>(file, headers, file == null ?HttpStatus.NOT_FOUND : HttpStatus.CREATED);
        } catch (Exception e) {
            headers.setContentType(MediaType.TEXT_HTML);
            return new ResponseEntity<>(e.getMessage().getBytes(), headers, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/file/original/{dir}/{fileName:.+}")
    public ResponseEntity<byte[]> getOriginalImage(@PathVariable String dir, @PathVariable String fileName) {
        String filePath = dir + File.separator + fileName;
        return getFileResponse(filePath);
    }

    @RequestMapping(value = "/api/file/upload", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"multipart/form-data"})
    public ResponseObject registerCandidate(@RequestParam(value = "attachments[]") List<MultipartFile> attachments) throws Exception {
        return ResponseObject.createSuccessfulResponse(FileUtil.saveFiles(attachments));

    }

}

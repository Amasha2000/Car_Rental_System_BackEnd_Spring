package lk.easyCarRental.spring.controller;

import lk.easyCarRental.spring.dto.DriverDTO;
import lk.easyCarRental.spring.service.DriverService;
import lk.easyCarRental.spring.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@CrossOrigin
@RequestMapping("/driver")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping
    public ResponseEntity getAllDrivers() {
        ArrayList<DriverDTO> allDrivers = driverService.getAllDrivers();
        return new ResponseEntity(new ResponseUtil(200, "Done", allDrivers), HttpStatus.OK);
    }

    @GetMapping(path = "get/{id}")
    public ResponseEntity getDriverById(@PathVariable String id) {
        DriverDTO driver = driverService.getDriverById(id);
        return new ResponseEntity(new ResponseUtil(200, "Done", driver), HttpStatus.OK);
    }

    @GetMapping(path = "/lastid")
    public ResponseEntity getLastDid() {
        String lastDid = driverService.getLastDid();
        return new ResponseEntity(new ResponseUtil(200, "Done", lastDid), HttpStatus.OK);
    }

    @GetMapping("/get_image/{image}")
    public void getImages(@PathVariable String image, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg;charset=utf-8");
        response.setHeader("Content-Disposition", "inline; filename=girls.png");

        ServletOutputStream outputStream = response.getOutputStream();

        String path = "/Users/Amasha/Desktop/easyCarRentalStorage/drivers/";

        outputStream.write(Files.readAllBytes(Paths.get(path).resolve(image)));
        outputStream.flush();
        outputStream.close();
    }

    @PostMapping(path = "/upload_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadPhotos(@RequestPart("image") MultipartFile image) {
        try {
            String file_name = image.getOriginalFilename();

            InputStream inputStream = image.getInputStream();
            Path path = Paths.get("/Users/Amasha/Desktop/easyCarRentalStorage/drivers/");

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Files.copy(inputStream, path.resolve(file_name));
            return new ResponseEntity(new ResponseUtil(200, "Image Uploaded", null), HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(new ResponseUtil(200, "Error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update_availability", params = {"id"})
    public ResponseEntity updateDriverAvailability(@RequestParam String id) {
        driverService.updateDriverAvailability(id);
        return new ResponseEntity(new ResponseUtil(200, "Driver availability updated successfully!", null), HttpStatus.OK);
    }

}

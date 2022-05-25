package main.service;

import main.response.ResultResponse;
import main.model.entity.User;
import main.model.repository.UserRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
// искусственный коммит 9.5.22
@Service
public class UserService {
    private final AuthService authService;
    private final  UserRepository userRepository;

    private final int PW_MIN_LENGTH = 6;
    private final int PW_MAX_LENGTH = 30;

    public UserService(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    private final ResultResponse resultResponse = new ResultResponse(false);

    public ResponseEntity<?> postAvatar(MultipartFile image) throws IOException {
        User user = userRepository.getOne(authService.getUserId());
        if (authService.isUserAuthorized()) {
            String imageAddress = StringUtils.cleanPath(saveImage(image).getAbsolutePath());
            user.setPhoto(imageAddress);
            userRepository.save(user);
            return new ResponseEntity<>(imageAddress, HttpStatus.OK);
        } else {
            return ResponseEntity.ok(new ResultResponse(false));
        }
    }

    public ResponseEntity<?> getPostProfileMy(MultipartFile photo, String email, String name,
                                              String password, String removePhoto) throws IOException {
        if (!authService.isUserAuthorized()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        boolean result = true;
        User currentUser = userRepository.getOne(authService.getUserId());
        Map<String, Object> errors = new LinkedHashMap<>();
        if(photo != null) {
            int MAX_IMAGE_SIZE = 3_000_000;
            if (photo.getBytes().length <= MAX_IMAGE_SIZE) {
                if (removePhoto.equals("1")) {
                    currentUser.setPhoto("");
                } else {
                    File convertFile = saveImage(photo);
                    String photoDestination = StringUtils.cleanPath(convertFile.getPath());
                    currentUser.setPhoto("/" + photoDestination);
                    System.out.println("avatarAddress: " + photoDestination);
                }
            } else {
                result = false;
                errors.put("photo", "Фото слишком большое, нужно не более 5 Мб.");
            }
        }
        if (password != null) {
            if (password.length() < PW_MIN_LENGTH && password.length() > PW_MAX_LENGTH) {
                result = false;
                errors.put("password", "Длина пароля с ошибкой");
            }
        }
        if (!name.matches("[a-zA-Z]*") || name.length() > 100 || name.length() < 2) {
            result = false;
            errors.put("name", "Имя указано неверно.");
        }
        if (!result) {
            return new ResponseEntity<>(resultResponse, HttpStatus.BAD_REQUEST);
        } else {
            currentUser.setName(name);
            if(email != null && !currentUser.getEmail().equals(email)) {
                currentUser.setEmail(email);
            }
            if(password != null) {
                currentUser.setPassword(password);
            }
            userRepository.save(currentUser);
            return new ResponseEntity<>(new ResultResponse(true), HttpStatus.OK);
        }
    }

    public File saveImage(MultipartFile photo) throws IOException {
        String targetFolder = "upload/";
        String hashCode = String.valueOf(Math.abs(targetFolder.hashCode()));
        String folder1 = hashCode.substring(0, hashCode.length() / 3);
        String folder2 = hashCode.substring(1 + hashCode.length() / 3, 2 * hashCode.length() / 3);
        String folder3 = hashCode.substring(1 + 2 * hashCode.length() / 3);
        File destFolder = new File(targetFolder);
        if (!destFolder.exists()) {
            destFolder.mkdir();
        }
        File destFolder1 = new File(targetFolder + folder1);
        if (!destFolder1.exists()) {
            destFolder1.mkdir();
        }
        File destFolder2 = new File(targetFolder + folder1 + "/" + folder2);
        if (!destFolder2.exists()) {
            destFolder2.mkdir();
        }
        File destFolder3 = new File(targetFolder + folder1 + "/" + folder2 + "/" + folder3);
        if (!destFolder3.exists()) {
            destFolder3.mkdir();
        }
        int suffix = (int) (Math.random() * 100);
        String fileName = suffix + "_" + photo.getOriginalFilename();
        String finalDestination = targetFolder + folder1 + "/" + folder2 + "/" + folder3 + "/" + fileName;
        photo.transferTo(Path.of(finalDestination));
        File destFile = new File(finalDestination);// Windows separators ("\") are replaced by simple slashes.
        Image image = ImageIO.read(photo.getInputStream());
        int HEIGHT_MAX = 50; // хотя требуется выполнять обрезку и изменение размера фотографии до 36х36 пикселей.
        int WIDTH_MAX = 50;
        BufferedImage tempPNG = resizeImage(image, WIDTH_MAX, HEIGHT_MAX);
        ImageIO.write(tempPNG, "png", destFile);
        return destFile;
    }

    private BufferedImage resizeImage(Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

}

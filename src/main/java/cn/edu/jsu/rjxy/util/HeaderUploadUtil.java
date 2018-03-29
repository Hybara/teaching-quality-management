package cn.edu.jsu.rjxy.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

public class HeaderUploadUtil {

  public static boolean uploadHeader(MultipartFile file, String basePath, String fileName, String type) {
    String path = basePath + type + File.separator;
    File targetPath = new File(path);
    if (!targetPath.exists()) {
      targetPath.mkdirs();
    }
    try {
      File targetFile = new File(path + fileName);
      if (targetFile.exists()) {
        targetFile.delete();
      }
      Files.copy(file.getInputStream(), Paths.get(path, fileName));
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}

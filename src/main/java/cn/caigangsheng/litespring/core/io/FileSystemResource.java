package cn.caigangsheng.litespring.core.io;

import java.io.*;

/**
 * @author cgs
 * @Date 2018-08-18 20:14
 */
public class FileSystemResource implements Resource {

    private String path;
    private File file;

    public FileSystemResource(String path) {
        Assert.notNull(path, "Path must not be null.");
        this.path = path;
        this.file = new File(path);
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(this.file);
    }

    @Override
    public String getDescription() {
        return "file [" + file.getAbsolutePath() + "]";
    }
}

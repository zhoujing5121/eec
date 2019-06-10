package cn.ttzero.excel.util;

import net.sf.sevenzipjbinding.*;
import net.sf.sevenzipjbinding.impl.OutItemFactory;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;
import net.sf.sevenzipjbinding.util.ByteArrayStream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static cn.ttzero.excel.util.ZipUtil.list;

/**
 * 7z util. using JNI
 * Create by guanquan.wang at 2018-10-11 08:40
 */
public class _7ZipUtil {
    private static final String suffix = ".zip";
    public static Path zip(Path destPath, Path ... srcPath) throws IOException {
        if (!destPath.toString().endsWith(suffix)) {
            destPath = Paths.get(destPath.toString() + suffix);
        }
        if (!Files.exists(destPath.getParent())) {
            FileUtil.mkdir(destPath.getParent());
        }
        List<Path> list = new ArrayList<>();
        list(list, new int[srcPath.length], srcPath);

        Path[] paths = list.toArray(new Path[list.size()]);

        try (RandomAccessFile raf = new RandomAccessFile(destPath.toFile(), "rw");
             IOutCreateArchiveZip outArchive = SevenZip.openOutArchiveZip()) {

            // Open out-archive object

            // Configure archive
            outArchive.setLevel(3);

            // Create archive
            outArchive.createArchive(new RandomAccessFileOutStream(raf),
                paths.length, new IOutCreateCallback<IOutItemZip>() {

                    @Override
                    public void setTotal(long total) throws SevenZipException {
                    }

                    @Override
                    public void setCompleted(long complete) throws SevenZipException {
                    }

                    @Override
                    public void setOperationResult(boolean operationResultOk) throws SevenZipException {
                    }

                    @Override
                    public IOutItemZip getItemInformation(int index, OutItemFactory<IOutItemZip> outItemFactory) throws SevenZipException {
                        int attr = PropID.AttributesBitMask.FILE_ATTRIBUTE_UNIX_EXTENSION;

                        IOutItemZip item = outItemFactory.createOutItem();
                        Path p = paths[index];
                        if (Files.isDirectory(p)) {
                            // Directory
                            item.setPropertyIsDir(true);
                            attr |= PropID.AttributesBitMask.FILE_ATTRIBUTE_DIRECTORY;
                            attr |= 0x81ED << 16; // permissions: drwxr-xr-x
                        } else {
                            // File
                            item.setDataSize(p.toFile().length());
                            attr |= 0x81a4 << 16; // permissions: -rw-r--r--
                        }

                        String path = p.toString().substring(srcPath[0].toString().length() + 1);
                        item.setPropertyPath(Files.isDirectory(p) ? path + File.separator : path);
                        item.setPropertyAttributes(attr);
                        return item;
                    }

                    @Override
                    public ISequentialInStream getStream(int index) throws SevenZipException {
                        Path p = paths[index];
                        if (Files.isDirectory(p)) {
                            return null;
                        }
                        InputStream is;
                        try {
                            is = Files.newInputStream(p);
                        } catch (IOException e) {
                            throw new SevenZipException("Read file " + p + " error.");
                        }

                        ByteArrayStream stream = new ByteArrayStream((int) p.toFile().length() + 1);
                        try {
                            stream.writeFromInputStream(is, true);
                        } catch (IOException e) {
                            throw new SevenZipException("Read file " + p + " error.");
                        }
                        return stream;
                    }
                });
        }
        return destPath;
    }

}

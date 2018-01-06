package com.tobetek.review.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by 17090056 on 2017/12/27.
 */
public class FileUtil {

    public static final int BSIZE = 8 * 1024;

    private static int    buffSize                  = 32 * 1024;

    private static char[] FILE_NAME_FORBIDDEN_CHARS = { '/', '\n', '\r', '\t', '\f', '`', '?', '*',
            '\\', '<', '>', '|', '\"', ':'         };

    private static char[] FILE_NAME_ACCEPTED_CHARS  = { '\u0228', '\u0196', '\u0252', '\u0220',
            '\u0246', '\u0214', ' '                };

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);   // 日志

    /**
     * 把字符串写入文件中
     * @param content
     * @param filePath
     * @throws IOException
     */
    public static void write2File(String content, String filePath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(filePath));
            if (null != bw) {
                bw.write(content);
                bw.flush();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(null != bw) {
                HttpUtil.closeResources(bw);
            }
        }
    }

    /**
     * 获取文件内容
     * @param filePath
     * @return
     */
    public static String read2Str(String filePath) {
        return read2Str(new File(filePath));
    }

    public static String read2Str(File file) {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String tmp;
            while ((tmp = br.readLine()) != null) {
                sb.append(tmp).append("\r\n");
            }
        }catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if(null != br) {
                HttpUtil.closeResources(br);
            }
        }
        return sb.toString();
    }
    /**
     * @param sourceFile
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean copyFileToDir(String sourceFile, String targetDir, FileFilter filter,
                                        String wt) {
        return copyFileToDir(new File(sourceFile), new File(targetDir), false, filter, wt);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @return true upon success
     */
    public static boolean copyFileToDir(String sourceFile, String targetDir) {
        return copyFileToDir(new File(sourceFile), new File(targetDir), false, null);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean moveFileToDir(String sourceFile, String targetDir, FileFilter filter,
                                        String wt) {
        return copyFileToDir(new File(sourceFile), new File(targetDir), true, filter, wt);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @return true upon success
     */
    public static boolean moveFileToDir(String sourceFile, String targetDir) {
        return copyFileToDir(new File(sourceFile), new File(targetDir), true, null);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean copyFileToDir(File sourceFile, File targetDir, FileFilter filter,
                                        String wt) {
        return copyFileToDir(sourceFile, targetDir, false, filter, wt);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @return true upon success
     */
    public static boolean copyFileToDir(File sourceFile, File targetDir, String wt) {
        return copyFileToDir(sourceFile, targetDir, false, null, wt);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean moveFileToDir(File sourceFile, File targetDir, FileFilter filter,
                                        String wt) {
        return copyFileToDir(sourceFile, targetDir, true, filter, wt);
    }

    /**
     * @param sourceFile
     * @param targetDir
     * @return true upon success
     */
    public static boolean moveFileToDir(File sourceFile, File targetDir) {
        return copyFileToDir(sourceFile, targetDir, true, null);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean copyDirToDir(String sourceDir, String targetDir, FileFilter filter,
                                       String wt) {
        return copyDirToDir(new File(sourceDir), new File(targetDir), false, filter, wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @return true upon success
     */
    public static boolean copyDirToDir(String sourceDir, String targetDir) {
        return copyDirToDir(new File(sourceDir), new File(targetDir), false, null);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean moveDirToDir(String sourceDir, String targetDir, FileFilter filter,
                                       String wt) {
        return moveDirToDir(new File(sourceDir), new File(targetDir), filter, wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @return true upon success
     */
    public static boolean moveDirToDir(String sourceDir, String targetDir, String wt) {
        return moveDirToDir(new File(sourceDir), new File(targetDir), wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean copyDirToDir(File sourceDir,
                                       File targetDir, FileFilter filter, String wt) {
        return copyDirToDir(sourceDir, targetDir, false, filter, wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @return true upon success
     */
    public static boolean copyDirToDir(File sourceDir, File targetDir, String wt) {
        return copyDirToDir(sourceDir, targetDir, false, null, wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @param filter file filter or NULL if no filter applied
     * @return true upon success
     */
    public static boolean moveDirToDir(File sourceDir,
                                       File targetDir, FileFilter filter, String wt) {
        return copyDirInternal(sourceDir, targetDir, true, false, filter, wt);
    }

    /**
     * @param sourceDir
     * @param targetDir
     * @return true upon success
     */
    public static boolean moveDirToDir(File sourceDir, File targetDir, String wt) {
        return copyDirInternal(sourceDir, targetDir, true, false, null, wt);
    }

    /**
     * Get the size in bytes of a directory
     *
     * @param path
     * @return true upon success
     */
    @SuppressWarnings("rawtypes")
    public static long getDirSize(File path) {
        Iterator pathiterator = null;
        File currentfile = null;
        long size;

        File[] f = path.listFiles();
        if (f == null) {
            return 0;
        }
        pathiterator = (Arrays.asList(f)).iterator();
        size = 0;
        while (pathiterator.hasNext()) {
            currentfile = (File) pathiterator.next();
            if (currentfile.isFile()) {
                size += currentfile.length();
            } else {
                size += getDirSize(currentfile);
            }
        }
        return size;
    }

    /**
     * Copy the contents of a directory from one spot on hard disk to another.
     * Will create any target dirs if necessary.
     *
     * @param sourceDir directory which contents to copy on local hard disk.
     * @param targetDir new directory to be created on local hard disk.
     * @param filter file filter or NULL if no filter applied
     * @param move
     * @return true if the copy was successful.
     */
    public static boolean copyDirContentsToDir(File sourceDir, File targetDir, boolean move,
                                               FileFilter filter, String wt) {
        return copyDirInternal(sourceDir, targetDir, move, false, filter, wt);
    }

    /**
     * Copy the contents of a directory from one spot on hard disk to another.
     * Will create any target dirs if necessary.
     *
     * @param sourceDir directory which contents to copy on local hard disk.
     * @param targetDir new directory to be created on local hard disk.
     * @param move
     * @return true if the copy was successful.
     */
    public static boolean copyDirContentsToDir(File sourceDir, File targetDir, boolean move,
                                               String wt) {
        return copyDirInternal(sourceDir, targetDir, move, false, null, wt);
    }

    /**
     * Copy a directory from one spot on hard disk to another. Will create any
     * target dirs if necessary. The directory itself will be created on the
     * target location.
     *
     * @param sourceDir directory to copy on local hard disk.
     * @param targetDir new directory to be created on local hard disk.
     * @param filter file filter or NULL if no filter applied
     * @param move
     * @return true if the copy was successful.
     */
    public static boolean copyDirToDir(File sourceDir, File targetDir, boolean move,
                                       FileFilter filter, String wt) {
        return copyDirInternal(sourceDir, targetDir, move, true, filter, wt);
    }

    /**
     * Copy a directory from one spot on hard disk to another. Will create any
     * target dirs if necessary. The directory itself will be created on the
     * target location.
     *
     * @param sourceDir directory to copy on local hard disk.
     * @param targetDir new directory to be created on local hard disk.
     * @param move
     * @return true if the copy was successful.
     */
    public static boolean copyDirToDir(File sourceDir, File targetDir, boolean move, String wt) {
        return copyDirInternal(sourceDir, targetDir, move, true, null, wt);
    }

    /**
     * Copy a directory from one spot on hard disk to another. Will create any
     * target dirs if necessary.
     *
     * @param sourceDir directory to copy on local hard disk.
     * @param targetDir new directory to be created on local hard disk.
     * @param move
     * @param createDir If true, a directory with the name of the source
     *            directory will be created
     * @param filter file filter or NULL if no filter applied
     * @return true if the copy was successful.
     */
    private static boolean copyDirInternal(File sourceDir,
                                           File targetDir, boolean move,
                                           boolean createDir, FileFilter filter, String wt) {
        if (sourceDir.isFile()) {
            return copyFileToDir(sourceDir, targetDir, move, filter, wt);
        }

        boolean operationFlag = targetDir.mkdirs();
        if(operationFlag){
            logger.debug("创建目录:"+targetDir.getAbsolutePath());
        }

        if (!sourceDir.isDirectory() || !targetDir.isDirectory()) {
            return false;
        }
        if (filter != null && !filter.accept(sourceDir)) {
            return true;
        }

        if (createDir) {
            targetDir = new File(targetDir, sourceDir.getName());
        }
        if (move) {
            if (sourceDir.renameTo(targetDir)) {
                return true;
            }
        }

        boolean operationFlag2 = targetDir.mkdirs();
        if (operationFlag2) {
            logger.debug("创建文文件目录:"+targetDir.getAbsolutePath());
        }
        boolean success = true;
        String[] fileList = sourceDir.list();
        if (fileList == null) {
            return false; // I/O error or not a directory
        }
        for (int i = 0; i < fileList.length; i++) {
            File f = new File(sourceDir, fileList[i]);
            if (f.isDirectory()) {
                success &= copyDirToDir(f, targetDir, move, filter,
                        wt + File.separator + f.getName());
            } else {
                success &= copyFileToDir(f, targetDir, move, filter, wt + " file=" + f.getName());
            }
        }

        if (move) {
            boolean operationFlag3 = sourceDir.delete();
            if (operationFlag3) {
                logger.debug("删除文件目录:"+sourceDir.getAbsolutePath());
            }
        }
        return success;
    }

    /**
     * Copy a file from one spot on hard disk to another. Will create any target
     * dirs if necessary.
     *
     * @param sourceFile file to copy on local hard disk.
     * @param targetDir new file to be created on local hard disk.
     * @param move
     * @param filter file filter or NULL if no filter applied
     * @return true if the copy was successful.
     */
    public static boolean copyFileToDir(File sourceFile, File targetDir, boolean move,
                                        FileFilter filter, String wt) {
        try {
            if (filter != null && !filter.accept(sourceFile)){
                return true;
            }
            if (sourceFile.isDirectory()) {
                return copyDirToDir(sourceFile, targetDir, move, filter, wt);
            }

            targetDir.mkdirs();

            if (!targetDir.isDirectory()){
                return false;
            }

            File targetFile = new File(targetDir, sourceFile.getName());

            if (sourceFile.getCanonicalPath().equals(targetFile.getCanonicalPath())){
                return true;
            }
            if (move) {
                if (sourceFile.renameTo(targetFile)){
                    return true;
                }
            }

            bcopy(sourceFile, targetFile, "copyFileToDir:" + wt);

            if (move) {
                sourceFile.delete();
            }
        } catch (IOException e) {
            logger.error("IOException",e);
            return false;
        }
        return true;
    }

    /**
     * Copy method to copy a file to another file
     *
     * @param sourceFile
     * @param targetFile
     * @param move true: move file; false: copy file
     * @return true: success; false: failure
     */
    public static boolean copyFileToFile(File sourceFile, File targetFile, boolean move) {
        try {
            if (sourceFile.isDirectory() || targetFile.isDirectory()) {
                return false;
            }

            targetFile.getParentFile().mkdirs();

            if (sourceFile.getCanonicalPath().equals(targetFile.getCanonicalPath())){
                return true;
            }
            if (move) {
                if (sourceFile.renameTo(targetFile)){
                    return true;
                }
            }

            bcopy(sourceFile, targetFile, "copyFileToFile");

            if (move) {
                sourceFile.delete();
            }
        } catch (IOException e) {
            logger.error("IOException",e);
            return false;
        }
        return true;
    }

    /**
     * Copy a file from one spot on hard disk to another. Will create any target
     * dirs if necessary.
     *
     * @param sourceFile file to copy on local hard disk.
     * @param targetDir new file to be created on local hard disk.
     * @param move
     * @return true if the copy was successful.
     */
    public static boolean copyFileToDir(File sourceFile, File targetDir, boolean move, String wt) {
        return copyFileToDir(sourceFile, targetDir, move, null, wt);
    }

    /**
     * Copy an InputStream to an OutputStream.
     *
     * @param source InputStream, left open.
     * @param target OutputStream, left open.
     * @param length how many bytes to copy.
     * @return true if the copy was successful.
     */
    public static boolean copy(InputStream source, OutputStream target, long length) {
        if (length == 0){
            return true;
        }
        try {
            int chunkSize = (int) Math.min(buffSize, length);
            long chunks = length / chunkSize;
            int lastChunkSize = (int) (length % chunkSize);
            byte[] ba = new byte[chunkSize];

            for (long i = 0; i < chunks; i++) {
                int bytesRead = readBlocking(source, ba, 0, chunkSize);
                if (bytesRead != chunkSize) {
                    throw new IOException();
                }
                target.write(ba);
            }
            if (lastChunkSize > 0) {
                int bytesRead = readBlocking(source, ba, 0, lastChunkSize);
                if (bytesRead != lastChunkSize) {
                    throw new IOException();
                }
                target.write(ba, 0, lastChunkSize);
            }
        } catch (IOException e) {
            logger.error("IOException",e);
            return false;
        }
        return true;
    }

    /**
     * Copy an InputStream to an OutputStream, until EOF. Use only when you
     * don't know the length.
     *
     * @param source InputStream, left open.
     * @param target OutputStream, left open.
     * @return true if the copy was successful.
     */
    @Deprecated
    public static boolean copy(InputStream source, OutputStream target) {
        try {

            int chunkSize = buffSize;
            byte[] ba = new byte[chunkSize];

            while (true) {
                int bytesRead = readBlocking(source, ba, 0, chunkSize);
                if (bytesRead > 0) {
                    target.write(ba, 0, bytesRead);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            logger.error("IOException",e);
            return false;
        }
        return true;
    }

    /**
     * Reads exactly <code>len</code> bytes from the input stream into the byte
     * array. This method reads repeatedly from the underlying stream until all
     * the bytes are read. InputStream.read is often documented to block like
     * this, but in actuality it does not always do so, and returns early with
     * just a few bytes. readBlockiyng blocks until all the bytes are read, the
     * end of the stream is detected, or an exception is thrown. You will always
     * get as many bytes as you asked for unless you get an eof or other
     * exception. Unlike readFully, you find out how many bytes you did get.
     *
     * @param in
     * @param b the buffer into which the data is read.
     * @param off the start offset of the data.
     * @param len the number of bytes to read.
     * @return number of bytes actually read.
     * @exception IOException if an I/O error occurs.
     */
    public static final int readBlocking(InputStream in, byte[] b, int off, int len)
            throws IOException {
        int totalBytesRead = 0;

        while (totalBytesRead < len) {
            int bytesRead = in.read(b, off + totalBytesRead, len - totalBytesRead);
            if (bytesRead < 0) {
                break;
            }
            totalBytesRead += bytesRead;
        }
        return totalBytesRead;
    }

    /**
     * Get rid of ALL files and subdirectories in given directory, and all
     * subdirs under it,
     *
     * @param dir would normally be an existing directory, can be a file aswell
     * @param recursive true if you want subdirs deleted as well
     * @param deleteDir true if dir needs to be deleted as well
     * @return true upon success
     */
    public static boolean deleteDirsAndFiles(File dir, boolean recursive, boolean deleteDir) {
        boolean success = true;
        if (dir == null){
            return false;
        }
        if (recursive) {
            String[] allDirs = dir.list();
            if (allDirs != null) {
                for (int i = 0; i < allDirs.length; i++) {
                    success &= deleteDirsAndFiles(new File(dir, allDirs[i]), true, false);
                }
            }
        }

        String[] allFiles = dir.list();
        if (allFiles != null) {
            for (int i = 0; i < allFiles.length; i++) {
                File deleteFile = new File(dir, allFiles[i]);
                success &= deleteFile.delete();
            }
        }

        if (deleteDir) {
            success &= dir.delete();
        }
        return success;
    }

    /**
     * @param newF
     */
    public static void createEmptyFile(File newF) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(newF);
            fos.close();
        } catch (IOException e) {
            logger.error("IOException",e);
            throw new RuntimeException("empty file could not be created for path "
                    + newF.getAbsolutePath(), e);
        }finally{
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("fosException:{}",e);
                }
            }
        }

    }

    /**
     * Save a given input stream to a file
     *
     * @param source the input stream
     * @param target the file
     */
    public static void save(InputStream source, File target) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(source);
            bos = getBos(target);

            cpio(bis, bos, "fileSave");

            bos.flush();
            bos.close();
            bis.close();
        } catch (IOException e) {
            logger.error("IOException",e);
            throw new RuntimeException(
                    "could not save stream to file::" + target.getAbsolutePath(), e);
        }finally{
            try{
                if(bis != null){
                    bis.close();
                }
                if(bos != null){
                    bos.close();
                }
            }catch (IOException e) {
                logger.error("fosException:{}",e);
            }

        }
    }

    /**
     * checks whether the given File is a Directory and if it contains any files
     * or sub-directories
     *
     *
     * @return returns true if given File-object is a directory and contains any
     *         files or subdirectories
     */
    public static boolean isDirectoryAndNotEmpty(File directory) {
        String[] content = directory.list();
        if (content == null) {
            return false;
        }
        boolean flag = content.length > 0;
        return flag;
    }

    /**
     * @param is the inputstream to close, may also be null
     */
    public static void closeSafely(InputStream is) {
        if (is == null) {
            return;
        }
        try {
            is.close();
        } catch (IOException e) {
            logger.error("IOException",e);
            return;
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                logger.error("fosException:{}",e);
            }
        }
    }

    /**
     * @param os the outputstream to close, may also be null
     */
    public static void closeSafely(OutputStream os) {
        if (os == null) {
            return;
        }

        try {
            os.close();
        } catch (IOException e) {
            logger.error("IOException",e);
            return;
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                logger.error("fosException:{}",e);
            }
        }

    }

    /**
     * Extract file suffix. E.g. 'html' from index.html
     *
     * @param filePath
     * @return return empty String "" without suffix.
     */
    public static String getFileSuffix(String filePath) {
        int lastDot = filePath.lastIndexOf('.');
        if (lastDot > 0) {
            if (lastDot < filePath.length()) {
                return filePath.substring(lastDot + 1).toLowerCase();
            }
        }
        return "";
    }

    /**
     * Simple check for filename validity. It compares each character if it is
     * accepted, forbidden or in a certain (Latin-1) range.
     * <p>
     * Characters < 33 --> control characters and space Characters > 255 -->
     * above ASCII http://www.danshort.com/ASCIImap/
     * 127 - 157 should also not be accepted
     * filenames, they should also work! See: OLAT-5704
     *
     * @param filename
     * @return true if filename valid
     */
    public static boolean validateFilename(String filename) {
        if (filename == null) {
            return false;
        }
        Arrays.sort(FILE_NAME_FORBIDDEN_CHARS);
        Arrays.sort(FILE_NAME_ACCEPTED_CHARS);

        for (int i = 0; i < filename.length(); i++) {
            char character = filename.charAt(i);
            if (Arrays.binarySearch(FILE_NAME_ACCEPTED_CHARS, character) >= 0) {
                continue;
            } else if (character < 33 || character > 255
                    || Arrays.binarySearch(FILE_NAME_FORBIDDEN_CHARS, character) >= 0) {
                return false;
            }
        }
        if (filename.indexOf("..") > -1) {
            return false;
        }
        return true;
    }

    /**
     * Creates a new directory in the specified directory, using the given
     * prefix and suffix strings to generate its name. It uses
     * File.createTempFile() and should provide a unique name.
     *
     * @param prefix
     * @param suffix
     * @param directory
     * @return
     */
    public static File createTempDir(String prefix, String suffix, File directory) {
        File tmpDir = null;
        try {
            File tmpFile = File.createTempFile(prefix, suffix, directory);
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
            boolean tmpDirCreated = tmpFile.mkdir();
            if (tmpDirCreated) {
                tmpDir = tmpFile;
            }
        } catch (IOException e) {
            logger.debug("error:createTempDir",e);
        }
        return tmpDir;
    }

    public static void bcopy(File src, File dst, String wt) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dst);
            bcopy(is, os, wt);
        } catch (Exception e) {
            logger.error("IOException",e);
            throw new RuntimeException("I/O error in cpio " + wt);
        } finally {
            if(null!=os){
                os.close();
            }
            if(null!=is){
                is.close();
            }
        }
    }

    public static void bcopy(InputStream src, File dst, String wt) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(dst);
            bcopy(src, os, "copyStreamToFile:" + wt);
        } catch (Exception e) {
            logger.error("IOException",e);
            throw new RuntimeException("I/O error in cpio " + wt);
        } finally {
            if(null!=os){
                os.close();
            }
            if(null!=src){
                src.close();
            }
        }
    }

    public static BufferedOutputStream getBos(FileOutputStream of) {
        return new BufferedOutputStream(of, BSIZE);
    }

    public static BufferedOutputStream getBos(OutputStream os) {
        return new BufferedOutputStream(os, BSIZE);
    }

    public static BufferedOutputStream getBos(File of) throws FileNotFoundException {
        return getBos(new FileOutputStream(of));
    }

    public static BufferedOutputStream getBos(String fname) throws FileNotFoundException {
        return getBos(new File(fname));
    }

    /**
     * Buffered copy streams (closes both streams when done)
     *
     * @param src InputStream
     * @param dst OutputStream
     * @throws IOException
     */
    public static void bcopy(InputStream src, OutputStream dst, String wt) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(src);
        BufferedOutputStream bos = getBos(dst);

        try {
            cpio(bis, bos, wt);
            bos.flush();
        } catch (IOException e) {
            logger.error("IOException",e);
            throw new RuntimeException("I/O error in cpio " + wt);
        } finally {
            bos.close();
            dst.close();
            bis.close();
            src.close();
        }
    }

    /**
     * copy in, copy out (leaves both streams open)
     * @param in BuferedInputStream
     * @param out BufferedOutputStream
     * @param wt What this I/O is about
     */
    public static long cpio(BufferedInputStream in, BufferedOutputStream out, String wt)
            throws IOException {

        byte[] buffer = new byte[BSIZE];

        int c;
        long tot = 0;
        while ((c = in.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, c);
            tot += c;
        }

        return tot;
    }

    @SuppressWarnings("unused")
    public static String getFileCharSet(File file) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            boolean checked = false;

            bis = new BufferedInputStream(new FileInputStream(file));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1)
                return charset;
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "UTF-16BE";
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF-8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                        break;
                    if (0x80 <= read && read <= 0xBF)

                        break;
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            continue;
                        } else {
                            break;
                        }
                    } else if (0xE0 <= read && read <= 0xEF) {//
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = "UTF-8";
                                break;
                            } else{
                                break;
                            }
                        } else{
                            break;
                        }
                    }
                }
            }
            bis.close();
        } catch (IOException e) {
           logger.error(e.getMessage(), e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e1) {
                    logger.error("fosException:{}",e1);
                }
            }

        }

        return charset;
    }

}

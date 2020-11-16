package com.jd.pop.base.test;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;

public class AnalyzeGBQ {
    public static void main(String[] args) {
//        String fileName = "E:\\dev\\审计\\对接造价相关\\预览文件\\test.GBQ5";
        String fileName = "E:\\dev\\审计\\对接造价相关\\山东指标原型5.26.zip";
        String s = extractZipComment(fileName);
        System.out.println(supportPreview(s));
    }

    public static Boolean supportPreview(String comment) {
        if (StringUtils.isEmpty(comment)) {
            return false;
        }
        boolean bool = comment.contains("Region=ShanDong") && (comment.contains("GBQFileType=2100") || comment.contains("GBQFileType=2300") || comment.contains("GBQFileType=2400"));
        if (bool) {
            return true;
        }
        return false;
    }

    public static String extractZipComment(String filename) {
        String retStr = null;
        try {
            File file = new File(filename);
            int fileLen = (int) file.length();

            FileInputStream in = new FileInputStream(file);

            /* The whole ZIP comment (including the magic byte sequence)
             * MUST fit in the buffer
             * otherwise, the comment will not be recognized correctly
             *
             * You can safely increase the buffer size if you like
             */
            byte[] buffer = new byte[Math.min(fileLen, 8192)];
            int len;

            in.skip(fileLen - buffer.length);

            if ((len = in.read(buffer)) > 0) {
                retStr = getZipCommentFromBuffer(buffer, len);
            }

            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retStr;
    }

    private static String getZipCommentFromBuffer(byte[] buffer, int len) {
        byte[] magicDirEnd = {0x50, 0x4b, 0x05, 0x06};
        int buffLen = Math.min(buffer.length, len);
        //Check the buffer from the end
        for (int i = buffLen - magicDirEnd.length - 22; i >= 0; i--) {
            boolean isMagicStart = true;
            for (int k = 0; k < magicDirEnd.length; k++) {
                if (buffer[i + k] != magicDirEnd[k]) {
                    isMagicStart = false;
                    break;
                }
            }
            if (isMagicStart) {
                //Magic Start found!
                int commentLen = buffer[i + 20] + buffer[i + 22] * 256;
                int realLen = buffLen - i - 22;
                System.out.println("ZIP comment found at buffer position " + (i + 22) + " with len=" + commentLen + ", good!");
                if (commentLen != realLen) {
                    System.out.println("WARNING! ZIP comment size mismatch: directory says len is " +
                            commentLen + ", but file ends after " + realLen + " bytes!");
                }
                String comment = new String(buffer, i + 22, Math.min(commentLen, realLen));
                return comment;
            }
        }
        System.out.println("ERROR! ZIP comment NOT found!");
        return null;
    }
}

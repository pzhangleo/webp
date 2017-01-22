package com.zhp.apng;

import java.io.File;
public class webp {
    public static void main(String[] args) {
        File dir = new File("/Users/zhangpeng/Documents/apng/");
        for (File file : dir.listFiles()) {
            if (file.getName().contains(" ")) {
                String newPath = file.getPath().replace(" ", "");
                File dest = new File(newPath);
                file.renameTo(dest);
                file.deleteOnExit();
                file = dest;
            }
            String param1 = file.getAbsolutePath();
            String fileName = file.getName().replace(" ", "").replace(".apng", "");
            String shell = "python /Users/zhangpeng/git/apng2webp/apng2webp/apng2webp.py " + param1
                    + " " + new File(dir, fileName +".webp");
            System.out.print(shell + "\n");
            callShell(shell);
        }
    }

    private static void callShell(String shellString) {
        try {
            Process process = Runtime.getRuntime().exec(shellString);
            int exitValue = process.waitFor();
            if (0 != exitValue) {
                System.out.print("call shell failed. error code is :" + exitValue + "\n");
            }
        } catch (Throwable e) {
            System.out.print("call shell failed. " + e + "\n");
        }
    }
}    

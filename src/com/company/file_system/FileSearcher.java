package com.company.file_system;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dimanolog on 14.12.2015.
 */
public class  FileSearcher
{
    public static List<File> recursiveFileSearch(File dir, FileFilter filter)
    {
        List<File> fileList=new ArrayList<File>();
        File[] files = dir.listFiles(filter);
        for (File file : files) {
            if (file.isDirectory()) {
                fileList.addAll(recursiveFileSearch(file, filter));

            } else {
                  fileList.add(file);
            }
        }
        return fileList;
    }

   /* public static Collection<File> getAllFilesApacheCommons(File dir, String fileExtension)
    {
        Collection<File> filesPath;

        filesPath = FileUtils.listFiles(
                dir,
                new RegexFileFilter("(.+)(\\.(?i)"+fileExtension+")$"),
                DirectoryFileFilter.DIRECTORY
        );

       return filesPath;

    }*/

}

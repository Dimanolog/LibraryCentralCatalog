package com.company.file_system;

import java.io.File;
import java.io.FileFilter;

public class ExtensionFileFilter implements FileFilter
        {
            private String  extension;

           public ExtensionFileFilter(String extension)
        {
            this.extension=extension;

        }
            public boolean accept(File pathname)
            {
                return pathname.isDirectory() || pathname.getName().endsWith(extension);

            }
        }







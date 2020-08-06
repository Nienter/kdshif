package com.crashlytics.android.core;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

final class Utils {
    private static final FilenameFilter ALL_FILES_FILTER = new FilenameFilter() {
        public boolean accept(File file, String str) {
            return true;
        }
    };

    private Utils() {
    }

    static int capFileCount(File file, int i, Comparator<File> comparator) {
        return capFileCount(file, ALL_FILES_FILTER, i, comparator);
    }

    static int capFileCount(File file, FilenameFilter filenameFilter, int i, Comparator<File> comparator) {
        int i2 = 0;
        File[] listFiles = file.listFiles(filenameFilter);
        if (listFiles != null) {
            int length = listFiles.length;
            Arrays.sort(listFiles, comparator);
            int length2 = listFiles.length;
            i2 = length;
            int i3 = 0;
            while (i3 < length2) {
                File file2 = listFiles[i3];
                if (i2 <= i) {
                    break;
                }
                file2.delete();
                i3++;
                i2--;
            }
        }
        return i2;
    }
}

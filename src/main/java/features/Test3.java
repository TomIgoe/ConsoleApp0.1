package features;

import java.io.File;
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

public class Test3 {

    String path = "\\Downloads\\datasets-foodsafety\\datasets-foodsafety";
    String line = "";
    File[] files = new File(path).listFiles(new FileFilter() {
        @Override
        public boolean accept(File path) {
            if (path.isFile()) {
                {
                    String[] values = line.split(",");
                    //Do something with a single file
                    //or just return true to put it in the list
                    return true;
                }

            }
            return false;

        }
    });
}
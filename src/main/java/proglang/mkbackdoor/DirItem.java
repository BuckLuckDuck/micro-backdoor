package proglang.mkbackdoor;

import java.io.File;

public class DirItem {
    public String name;

    public String absPath;

    public Boolean isDir;

    public DirItem(File file) {
        this.name = file.getName();
        this.absPath = file.getAbsolutePath().replace("\\", "/");
        this.isDir = file.isDirectory();
    }
}

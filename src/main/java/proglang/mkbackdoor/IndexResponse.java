package proglang.mkbackdoor;

import java.util.ArrayList;
import java.util.List;

public class IndexResponse {
    public String path;
    public List<DirItem> content;

    public IndexResponse(String path ,int itemsCount) {
        this.path = path;
        content = new ArrayList<>();
    }

    public List<DirItem> getContent() {
        return content;
    }
}

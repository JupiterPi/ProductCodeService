package kaye.productcodeservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import kaye.productcodeservice.filetool.FileTool;

@Service
public class RessourcesService {
    private List<String> backgrounds = new ArrayList<String>();
    private String backgroundsFileName;

    public RessourcesService() throws IOException {
        String backgroundsFileName = "backgrounds.txt";
        this.backgroundsFileName = backgroundsFileName;
        readBackgrounds();
    }

    public List<String> getBackgrounds() throws IOException {
        readBackgrounds();
        return backgrounds;
    }

    public String getBackgroundsForWebsite() throws IOException {
        readBackgrounds();
        if (backgrounds.size() == 0) return "";
        String returning = backgrounds.get(0);
        for (int i = 1; i < backgrounds.size(); i++) {
            returning = returning + "UnD" + backgrounds.get(i);
        }
        return returning;
    }

    public void readBackgrounds() throws IOException {
        backgrounds = new FileTool(backgroundsFileName).getFile();
    }
}

package skadistats.dt2wiki.format;

import skadistats.clarity.model.DTClass;

public class Basic implements Formatter {
    public String dtName(DTClass cls, int level) {
        String val = " ";

        for (int i=0; i<level; i++) {
            val += "  ";
        }

        val += "* [[SendTable/" + cls.getDtName() + "|" + cls.getDtName() + "]]";

        return val;
    }
}

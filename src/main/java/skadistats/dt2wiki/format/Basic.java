package skadistats.dt2wiki.format;

import skadistats.clarity.model.DTClass;
import skadistats.clarity.model.ReceiveProp;
import skadistats.clarity.model.PropFlag;

public class Basic implements Formatter {
    public String dtName(DTClass cls, int level) {
        String val = " ";

        for (int i=0; i<level; i++) {
            val += "  ";
        }

        val += "* [[SendTable/" + cls.getDtName() + "|" + cls.getDtName() + "]]";

        return val;
    }

    public String dtDetails(DTClass cls) {
        String val = "";
        for (ReceiveProp p : cls.getReceiveProps()) {
            val += " ||" + p.getVarName();
            val += " ||" + p.getType();
            val += " ||" + p.getSrc();
            val += " ||" + p.getPriority();
            StringBuffer buf = new StringBuffer();
            for (PropFlag f : PropFlag.values()) {
                if (p.isFlagSet(f)) {
                    if (buf.length() > 0) {
                        buf.append(", ");
                    }
                    buf.append(f.name());
                }
            }
            val += " ||" + buf.toString();
            val += " ||\n";
        }
        return val;
    }
}

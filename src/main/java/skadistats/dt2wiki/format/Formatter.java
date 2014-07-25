package skadistats.dt2wiki.format;

import skadistats.clarity.model.DTClass;

public interface Formatter {
    public String dtName(DTClass cls, int level);
}

package skadistats.dt2wiki;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import skadistats.clarity.match.Match;
import skadistats.clarity.model.DTClass;

public class TreeConstructor {

    public static class TreePayload {
        private final DTClass dtClass;

        public TreePayload(DTClass dtClass) {
            this.dtClass = dtClass;
        }

        public DTClass getDtClass() {
            return dtClass;
        }
        
        public String toString() {
            return dtClass == null ? "" : dtClass.getDtName();
        }
        
    }
    
    private final DTMap tree = new DTMap();
    
    private static final Comparator<DTClass> COMPARATOR = new Comparator<DTClass>() {
        @Override
        public int compare(DTClass o1, DTClass o2) {
            return o1.getDtName().compareTo(o2.getDtName());
        }
    };

    public TreeConstructor(Match match) {
        Iterator<DTClass> dtClasses = match.getDtClasses().iterator();
        while(dtClasses.hasNext()) {
            DTClass c = dtClasses.next();
            DTClass s = c.getSuperClass();
            Set<DTClass> forSuper = tree.get(s);
            if (forSuper == null) {
                forSuper = new TreeSet<DTClass>(COMPARATOR);
                tree.put(s, forSuper);
            }
            forSuper.add(c);
        }
    }
    
    public DTMap construct() {
        return tree;
    }
}

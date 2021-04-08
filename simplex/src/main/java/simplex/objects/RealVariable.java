package simplex.objects;

import java.util.Objects;

public class RealVariable extends Variable {

    private String name;

    public RealVariable(String name, double cost) {
        super(cost);
        this.name = name;
        this.ifRealVariable = true;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealVariable that = (RealVariable) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

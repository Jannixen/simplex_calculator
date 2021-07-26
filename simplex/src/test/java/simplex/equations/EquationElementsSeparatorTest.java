package simplex.equations;


import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class EquationElementsSeparatorTest {

    @Test
    public void should_separateConstantWhenLowerOrEqual() {

        String equation = "2x1 + 3x2 + 4x3 <= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstantWhenEqual() {

        String equation = "2x1 + 3x2+ 4x3 = 5";
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstantWhenHigherOrEqual() {

        String equation = "2x1 + 3x2+ 4x3 >= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);

        assertEquals(separator.getConstant(), "5");
    }

    @Test
    public void should_separateConstantWhenMinusInConstant() {

        String equation = "2x1 + 3x2+ 4x3 >= -5";
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);

        assertEquals(separator.getConstant(), "-5");
    }



    @Test
    public void should_separateVariableWhenLowerOrEqual() {

        String equation = "2x1 + 3x2+ 4x3 >= 5";
        EquationElementsSeparator separator = new EquationElementsSeparator(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "2");
        assertEquals(resultMap.get("x2"), "3");
        assertEquals(resultMap.get("x3"), "4");
    }


    @Test
    public void should_separateVariableWhenMinusInEquation() {

        String equation = "2x1 - 3x2 + 4x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "2");
        assertEquals(resultMap.get("x2"), "-3");
        assertEquals(resultMap.get("x3"), "4");
    }

    @Test
    public void should_separateVariableWhenMinusInEquationBeginning() {

        String equation = "- 2x1 - 3x2 + 4x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "-2");
        assertEquals(resultMap.get("x2"), "-3");
        assertEquals(resultMap.get("x3"), "4");
    }


    @Test
    public void should_separateVariableWhenOnlyVariableNames() {

        String equation = "-x1 - x2 + x3 >= 5";

        EquationElementsSeparator separator = new EquationElementsSeparator(equation);
        HashMap<String, String> resultMap = separator.getCoefficientsPerNameMap();

        assertEquals(resultMap.get("x1"), "-1");
        assertEquals(resultMap.get("x2"), "-1");
        assertEquals(resultMap.get("x3"), "1");
    }
}